package py.edu.uc.lp2.apirest.rest.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.uc.lp2.apirest.domains.*;
import py.edu.uc.lp2.apirest.service.ArmasService;

import java.util.*;

@RestController
@RequestMapping("/armas")
public class ArmasController {

    private final ArmasService armasService;

    @Autowired
    public ArmasController(ArmasService armasService) {
        this.armasService = armasService;
    }

    //carga armas en la base de datos a traves del bulk
    @PostMapping("/bulk-cargar")
    public ResponseEntity<String> cargarArmas(@RequestBody List<Map<String, Object>> armasRaw) {
        List<Armas> armas = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            for (Map<String, Object> raw : armasRaw) {
                String tipo = (String) raw.get("tipo");
                Armas arma;

                switch (tipo) {
                    case "ArmasDeFuego":
                        arma = objectMapper.convertValue(raw, ArmasDeFuego.class);
                        break;
                    case "Melee":
                        arma = objectMapper.convertValue(raw, Melee.class);
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Tipo desconocido: " + tipo);
                }

                armas.add(arma);
            }

            armasService.guardarArmas(armas);
            return ResponseEntity.ok("Armas cargadas correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar el JSON: " + e.getMessage());
        }
    }
}
