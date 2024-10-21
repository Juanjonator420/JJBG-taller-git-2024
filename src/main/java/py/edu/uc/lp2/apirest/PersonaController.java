package py.edu.uc.lp2.apirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    private static List<Persona> personas = new ArrayList<>();

    static {
        // Datos de prueba iniciales
        personas.add(new Persona(1L, "Juan", "Pérez", "123456789"));
        personas.add(new Persona(2L, "María", "Rodríguez", "987654321"));
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        log.info("Se ha recibido una solicitud para obtener todas las personas");
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        log.info("Buscando persona con ID: {}", id);
        return personas.stream()
                .filter(persona -> persona.getId().equals(id))
                .findFirst()
                .map(persona -> {
                    log.info("Persona encontrada: {}", persona);
                    return ResponseEntity.ok(persona);
                })
                .orElseGet(() -> {
                    log.warn("Persona con ID {} no encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        log.info("Creando nueva persona: {}", persona);
        persona.setId(personas.size() + 1L);
        personas.add(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        log.info("Actualizando persona con ID: {}", id);
        int index = personas.indexOf(persona);
        if (index >= 0) {
            personas.set(index, persona);
            log.info("Persona actualizada: {}", persona);
            return ResponseEntity.ok(persona);
        } else {
            log.warn("Persona con ID {} no encontrada para actualizar", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        log.info("Eliminando persona con ID: {}", id);
        boolean removed = personas.removeIf(persona -> persona.getId().equals(id));
        if (removed) {
            log.info("Persona eliminada correctamente");
        } else {
            log.warn("Persona con ID {} no encontrada para eliminar", id);
        }
        return ResponseEntity.noContent().build();
    }
}