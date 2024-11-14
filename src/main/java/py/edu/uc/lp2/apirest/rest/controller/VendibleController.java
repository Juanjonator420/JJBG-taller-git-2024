package py.edu.uc.lp2.apirest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@RestController
@RequestMapping ("/vendibles")
public class VendibleController {

    protected final VendibleService vendibleService;

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

        // Explicitly return 200 OK status code
        return new ResponseEntity<>(vendibles, HttpStatus.OK);
    }

    @Autowired
    public VendibleController(VendibleService vendibleService) {
        this.vendibleService = vendibleService;
    }

    @PostMapping("/comprar")
    public ResponseEntity<String> comprar(@RequestBody List<Vendible> vendibles) {
        // Validar la lista de vendibles
        if (!vendibleService.validarListaVendibles(vendibles)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La lista de productos es nula, está vacía, o el monto total no cumple con el mínimo de "
                            + VendibleServiceImpl.MONTO_MINIMO_COMPRA);
        }

        // Procesar la compra si la validación fue exitosa
        vendibleService.procesarCompra(vendibles);
        return ResponseEntity.ok("Compra realizada exitosamente. Vendibles: " + vendibles);
    }
}