package py.edu.uc.lp2.apirest.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.uc.lp2.apirest.domains.Person;
import py.edu.uc.lp2.apirest.domains.Persona;
import py.edu.uc.lp2.apirest.service.impl.PersonaService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    private final PersonaService personaService;

    private static List<Persona> personas = new ArrayList<>();

    static {
        personas.add(new Persona(1L, "Juan", "Pérez", "123456789", LocalDate.of(2000, 1, 15)));
        personas.add(new Persona(2L, "María", "Rodríguez", "987654321", LocalDate.of(2010, 5, 20)));
    }

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<String> createPersona(@RequestBody Persona persona) {
        log.info("Creando nueva persona: {}", persona);
        persona.setId(personas.size() + 1L);

        if (!personaService.esMayorDeEdad(persona)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La persona debe ser mayor de edad.");
        }

        personas.add(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada exitosamente.");
    }

    @RequestMapping(value = "/personas/bulk", method = RequestMethod.POST)
    public void addBulk(@RequestBody List<Person> personas) {personaService.saveList(personas);}

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        log.info("Actualizando persona con ID: {}", id);

        if (!personaService.esMayorDeEdad(persona)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La persona debe ser mayor de edad.");
        }

        int index = -1;
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            persona.setId(id);
            personas.set(index, persona);
            log.info("Persona actualizada: {}", persona);
            return ResponseEntity.ok("Persona actualizada exitosamente.");
        } else {
            log.warn("Persona con ID {} no encontrada para actualizar", id);
            return ResponseEntity.notFound().build();
        }
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