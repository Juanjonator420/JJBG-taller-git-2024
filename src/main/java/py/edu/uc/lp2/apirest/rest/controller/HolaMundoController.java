package py.edu.uc.lp2.apirest.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {

    @GetMapping("/hola")

    public String saludar() {
        return "Hola mundo!";
    }
}