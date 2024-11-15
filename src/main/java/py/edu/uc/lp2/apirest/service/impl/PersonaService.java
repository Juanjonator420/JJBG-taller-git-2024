package py.edu.uc.lp2.apirest.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.edu.uc.lp2.apirest.domains.Persona;
import py.edu.uc.lp2.apirest.repository.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    private static final Logger logger = LoggerFactory.getLogger(PersonaService.class);
    private static final int EDAD_MINIMA = 18;
    protected static final List<Persona> personas = new ArrayList<>();

    static {
        personas.add(new Persona(1L, "Juan", "Pérez", "123456789", LocalDate.of(2000, 1, 15)));
        personas.add(new Persona(2L, "María", "Rodríguez", "987654321", LocalDate.of(2010, 5, 20)));
    }

    public List<Persona> getAllPersonas() {
        logger.info("Obteniendo lista de todas las personas");
        return personas;
    }

    // Verifica si la persona es mayor de edad
    public boolean esMayorDeEdad(Persona persona) {
        if (persona.getFechaNacimiento() == null) {
            logger.warn("Fecha de nacimiento no especificada para la persona con ID: {}", persona.getId());
            return false;
        }

        // Calcula la edad actual
        int edad = Period.between(persona.getFechaNacimiento(), LocalDate.now()).getYears();
        boolean esMayor = edad >= EDAD_MINIMA;

        logger.info("Persona con ID {} tiene {} años. ¿Es mayor de edad? {}", persona.getId(), edad, esMayor);
        return esMayor;
    }


    // Funcion para guardar persona con persistencia Parametros: Persona persona
    // : el persona que guardaremos con persistencia Retorno: ninguno
    public void save(Persona persona) { personaRepository.save(persona); //agregado el 14 del 11
    }

    //Funcion para guarda una lista de personas con persistencia Parametros:
    // List<Persona> personas : la lista de las personas que guardaremos con persistencia Retorno: ninguno
    public void saveList(List<Persona> personas){ //agregado el 14 del 11
        for (Persona aGuardar : personas) save(aGuardar);
    }

}
