package py.edu.uc.lp2.apirest.rest.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import py.edu.uc.lp2.apirest.domains.*;
import py.edu.uc.lp2.apirest.service.impl.PersonaService;
import py.edu.uc.lp2.apirest.repository.PersonaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<String> createPersona(@RequestBody Persona persona) {
        log.info("Creando nueva persona: {}", persona);

        if (persona.getEdad() < 18) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La persona debe ser mayor de edad.");
        }

        personaService.save(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body("Persona creada exitosamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        log.info("Actualizando persona con ID: {}", id);

        if (!personaService.esMayorDeEdad(persona)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La persona debe ser mayor de edad.");
        }

        Persona existingPersona = personaService.findById(id);
        if (existingPersona == null) {
            log.warn("Persona con ID {} no encontrada para actualizar", id);
            return ResponseEntity.notFound().build();
        }

        persona.setId(id);
        personaService.save(persona);
        log.info("Persona actualizada: {}", persona);
        return ResponseEntity.ok("Persona actualizada exitosamente.");
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        log.info("Se ha recibido una solicitud para obtener todas las personas");
        return ResponseEntity.ok(personaService.getAllPersonas());
    }

    //cita una persona a traves citando su id
    @GetMapping("/citar-persona/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        log.info("Buscando persona con ID: {}", id);
        Persona persona = personaService.findById(id);
        if (persona != null) {
            log.info("Persona encontrada: {}", persona);
            return ResponseEntity.ok(persona);
        } else {
            log.warn("Persona con ID {} no encontrada", id);
            return ResponseEntity.notFound().build();
        }
    }

    //endpoint para borrar una persona a traves de su id
    @DeleteMapping("/borrar-persona/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        log.info("Eliminando persona con ID: {}", id);
        Persona persona = personaService.findById(id);
        if (persona == null) {
            log.warn("Persona con ID {} no encontrada para eliminar", id);
            return ResponseEntity.notFound().build();
        }

        personaService.deleteById(id);
        log.info("Persona eliminada correctamente");
        return ResponseEntity.noContent().build();
    }

    //endpoint donde hace bulk y carga multiples datos a la vez para jerarquia de persona
    @PostMapping("/bulk-personas")
    public ResponseEntity<String> cargarPersonas(@RequestBody List<Map<String, Object>> personasRaw) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Persona> personas = new ArrayList<>();

        try {
            for (Map<String, Object> raw : personasRaw) {
                String tipo = (String) raw.get("tipo");
                Persona persona;

                switch (tipo) {
                    case "Persona":
                        persona = objectMapper.convertValue(raw, Persona.class);
                        break;
                    case "Usuario":
                        persona = objectMapper.convertValue(raw, Usuario.class);
                        break;
                    case "Jugador":
                        persona = objectMapper.convertValue(raw, Jugador.class);
                        break;
                    case "JugadorVIP":
                        persona = objectMapper.convertValue(raw, JugadorVIP.class);
                        break;
                    case "JugadorFREE":
                        persona = objectMapper.convertValue(raw, JugadorFREE.class);
                        break;
                    case "Empleado": // Maneja el tipo Empleado
                        persona = objectMapper.convertValue(raw, Empleados.class);
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Tipo desconocido: " + tipo);
                }

                personas.add(persona);
            }

            personaService.saveAll(personas); //Usar un método que guarde múltiples instancias
            return ResponseEntity.status(HttpStatus.CREATED).body("Datos cargados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar el JSON: " + e.getMessage());
        }
    }

}
