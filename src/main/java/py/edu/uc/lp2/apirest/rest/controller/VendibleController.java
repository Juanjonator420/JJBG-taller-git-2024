package py.edu.uc.lp2.apirest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.uc.lp2.apirest.domains.SkinsPersonaje;
import py.edu.uc.lp2.apirest.domains.Subfusil;
import py.edu.uc.lp2.apirest.domains.Vendible;
import py.edu.uc.lp2.apirest.service.VendibleService;
import py.edu.uc.lp2.apirest.service.impl.VendibleServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vendibles")
public class VendibleController {

    private final VendibleService vendibleService;

    //@Autowired
    //public VendibleController(VendibleService vendibleService) {
    //    this.vendibleService = vendibleService;
    //}

    @GetMapping("/lista-productos")
    public ResponseEntity<List<Vendible>> mostrarVendibles() {
        List<Vendible> vendibles = new ArrayList<>();
        SkinsPersonaje latino = new SkinsPersonaje(123, 1);
        latino.setDescripcion("Operador y jugador profesional de futbol");
        latino.setNombre("Leo Messi");
        Subfusil subfusil = new Subfusil(1700, 5, "Subfusil compacto y economico", "MP7"); // Assuming Subfusil class exists
        vendibles.add(subfusil);
        vendibles.add(latino);

        Subfusil subfusil2 = new Subfusil(2950, 10, "Subfusil con alta capacidad de carga", "P90"); // Assuming Subfusil class exists
        subfusil2.setCarga(50); //la p90 tiene 50 balas en su cargador, ojo en el constructor se puso por default 30
        vendibles.add(subfusil2);

        return new ResponseEntity<>(vendibles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendible> obtenerVendiblePorId(@PathVariable Long id) {
        Optional<Vendible> vendible = vendibleService.getVendibleById(id);
        return vendible.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/comprar")
    public ResponseEntity<String> comprar(@RequestBody List<Vendible> vendibles) {
        // Validar la lista de vendibles
        if (!vendibleService.validarListaVendibles(vendibles)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La lista de productos es nula, está vacía, o el monto total no cumple con el mínimo requerido.");
        }

        // Procesar la compra si la validación fue exitosa
        vendibleService.procesarCompra(vendibles);
        return ResponseEntity.ok("Compra realizada exitosamente. Vendibles: " + vendibles);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Vendible> agregarVendible(@RequestBody Vendible vendible) {
        Vendible nuevoVendible = vendibleService.saveVendible(vendible);
        return new ResponseEntity<>(nuevoVendible, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVendible(@PathVariable Long id) {
        vendibleService.deleteVendible(id);
        return ResponseEntity.ok("Vendible con ID " + id + " eliminado correctamente.");
    }


    @PostMapping("/bulk-add")
    public ResponseEntity<String> bulkAddVendibles(@RequestBody List<Vendible> vendibles) {
        vendibleService.saveAll(vendibles);
        return new ResponseEntity<>("Vendibles cargados correctamente", HttpStatus.CREATED);
    }

    @Autowired
    public VendibleController(VendibleServiceImpl vendibleServiceImpl) {
        this.vendibleService = vendibleServiceImpl;
    }

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
}
