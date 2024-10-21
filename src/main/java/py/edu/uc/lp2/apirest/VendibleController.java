package py.edu.uc.lp2.apirest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VendibleController {


    @GetMapping("/vendibles")
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
}


