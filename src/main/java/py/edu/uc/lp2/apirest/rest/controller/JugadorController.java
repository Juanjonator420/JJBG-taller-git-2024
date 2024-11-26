package py.edu.uc.lp2.apirest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.uc.lp2.apirest.domains.Jugador;
import py.edu.uc.lp2.apirest.repository.JugadorRepository;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorRepository jugadorRepository;

    @Autowired
    public JugadorController(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @PostMapping
    public ResponseEntity<String> createJugador(@RequestBody Jugador jugador) {
        jugadorRepository.save(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body("Jugador creado exitosamente.");
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> getAllJugadores() {
        return ResponseEntity.ok(jugadorRepository.findAll());
    }
}

