package py.edu.uc.lp2.apirest.rest.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.uc.lp2.apirest.domains.*;
import py.edu.uc.lp2.apirest.service.VendibleService;
import py.edu.uc.lp2.apirest.service.impl.VendibleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
@RequestMapping("/vendibles")
public class VendibleController {

    private final VendibleService vendibleService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(VendibleController.class);

    @Autowired
    public VendibleController(VendibleServiceImpl vendibleServiceImpl) {
        this.vendibleService = vendibleServiceImpl;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    //listar los productos como un catalogo pero a traves del API client Bruno
    @GetMapping("/lista-productos")
    public ResponseEntity<List<Map<String, Object>>> mostrarVendibles() {
        List<Vendible> vendibles = vendibleService.getAllVendibles();

        if (vendibles.isEmpty()) {
            logger.info("No se encontraron productos en la base de datos.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Map<String, Object>> respuesta = vendibles.stream().map(vendible -> {
            Map<String, Object> datos = new HashMap<>();
            datos.put("id", vendible.getId());
            datos.put("nombre", vendible.getNombre());
            datos.put("precio", vendible.getPrecio());
            datos.put("descripcion", vendible.getDescripcion());
            datos.put("categoria", vendible.getCategoria());
            datos.put("tipo", vendible instanceof ArmasDeFuego ? "ArmasDeFuego" : vendible instanceof Melee ? "Melee" : "Otro");

            // Atributos específicos de ArmasDeFuego
            if (vendible instanceof ArmasDeFuego) {
                ArmasDeFuego arma = (ArmasDeFuego) vendible;
                datos.put("carga", arma.getCarga());
                datos.put("cadencia", arma.getCadencia());
                datos.put("tipoDeArma", arma.getTipoDeArma());
            }

            // Atributos específicos de Melee
            if (vendible instanceof Melee) {
                Melee melee = (Melee) vendible;
                datos.put("material", melee.getMaterial());
                datos.put("peso", melee.getPeso());
            }

            return datos;
        }).toList();

        logger.info("Lista de productos recuperada exitosamente. Cantidad: {}", respuesta.size());
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }


    @GetMapping("/inspeccionar/{id}") //para citar por id un producto y ver sus atributos
    public ResponseEntity<Vendible> obtenerVendiblePorId(@PathVariable Long id) {
        Optional<Vendible> vendible = vendibleService.getVendibleById(id);
        return vendible.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/comprar") //para comprar items en singular y te da un precio total de la compra
    public ResponseEntity<Map<String, Object>> comprar(@RequestBody List<Vendible> vendibles) {
        // Validar la lista de vendibles
        if (vendibles == null || vendibles.isEmpty()) {
            logger.warn("Intento de realizar una compra con una lista vacía o nula.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "La lista de productos es nula o está vacía."));
        }

        boolean esValida = vendibleService.validarListaVendibles(vendibles);
        if (!esValida) {
            logger.warn("La lista de productos no cumple con las condiciones mínimas requeridas.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "La lista de productos no cumple con las condiciones mínimas requeridas."));
        }

        // Procesar la compra
        vendibleService.procesarCompra(vendibles);

        // Calcular el precio total
        double precioTotal = vendibles.stream().mapToDouble(Vendible::getPrecio).sum();

        // Obtener los nombres de los productos comprados
        List<String> nombresProductos = vendibles.stream().map(Vendible::getNombre).toList();

        // Loggear la información de la compra
        logger.info("Compra realizada exitosamente. Productos: {}, Precio total: {}", nombresProductos, precioTotal);

        // Responder con el detalle de la compra
        Map<String, Object> respuesta = Map.of(
                "mensaje", "Compra realizada exitosamente.",
                "productos", nombresProductos,
                "precioTotal", precioTotal
        );
        return ResponseEntity.ok(respuesta);
    }

    //sirve para eliminar un item de la lista mediante su id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVendible(@PathVariable Long id) {
        try {
            if (vendibleService.getVendibleById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El vendible con id " + id + " no existe.");
            }
            vendibleService.deleteVendible(id);
            return ResponseEntity.ok("Vendible eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el vendible: " + e.getMessage());
        }
    }

    @PostMapping("/bulk-add") //sirve para cargar y/o actualizar varios items a la vez a la base de datos de lista-productos
    public ResponseEntity<String> bulkAddVendibles(@RequestBody List<Vendible> vendibles) {
        // Validar que el JSON no esté vacío
        if (vendibles == null || vendibles.isEmpty()) {
            logger.warn("Intento de cargar un JSON vacío o sin vendibles válidos.");
            return ResponseEntity.badRequest().body("El archivo JSON está vacío o no contiene vendibles válidos.");
        }

        try {
            // Guardar los vendibles en la base de datos
            vendibleService.saveAll(vendibles);
            logger.info("Vendibles cargados correctamente desde el JSON. Cantidad: {}", vendibles.size());
            return ResponseEntity.status(HttpStatus.CREATED).body("Vendibles cargados correctamente desde el JSON.");
        } catch (Exception e) {
            logger.error("Error al cargar los vendibles desde el JSON.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al procesar el archivo JSON.");
        }
    }

    //carrito, compra de multiples items a la vez, como un carrito, mediante id's y cantidades
    @PostMapping("/total-price")
    public ResponseEntity<Map<String, Object>> getTotalPriceWithQuantities(@RequestBody List<Map<String, Object>> vendibleRequests) {
        List<Map<String, Object>> vendibleDetails = vendibleService.calculateTotalPriceWithQuantities(vendibleRequests);
        double grandTotal = vendibleService.calculateGrandTotal(vendibleDetails);

        Map<String, Object> response = Map.of(
                "detalles", vendibleDetails,
                "totalGeneral", grandTotal
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //agregado para verificacion de herencias de servicios
    @GetMapping("/envio/{id}") //endpoint para envio de armas
    public ResponseEntity<Map<String, Object>> calcularCostoEnvio(@PathVariable Long id) {
        Optional<Vendible> vendible = vendibleService.getVendibleById(id);
        if (vendible.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Producto no encontrado."));
        }

        double costoEnvio = vendibleService.calcularCostoEnvio(vendible.get());
        return ResponseEntity.ok(Map.of("nombre", vendible.get().getNombre(), "costoEnvio", costoEnvio));
    }

    @GetMapping("/mantenimiento/{id}") //endpoint de mantenimiento de armas
    public ResponseEntity<Map<String, Object>> calcularCostoMantenimiento(@PathVariable Long id) {
        Optional<Vendible> vendible = vendibleService.getVendibleById(id);
        if (vendible.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Producto no encontrado."));
        }

        double costoMantenimiento = vendibleService.calcularCostoMantenimiento(vendible.get());
        return ResponseEntity.ok(Map.of("nombre", vendible.get().getNombre(), "costoMantenimiento", costoMantenimiento));
    }
}