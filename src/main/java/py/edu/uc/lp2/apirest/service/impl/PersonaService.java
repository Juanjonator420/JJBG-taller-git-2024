package py.edu.uc.lp2.apirest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import py.edu.uc.lp2.apirest.domains.Person;
import py.edu.uc.lp2.apirest.domains.Persona;
import py.edu.uc.lp2.apirest.repository.PersonaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PersonaService {

    private static final Logger logger = LoggerFactory.getLogger(PersonaService.class);
    private static final int EDAD_MINIMA = 18;

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
    public void saveList(List<Person> personas){ //agregado el 14 del 11
        for (Person aGuardar : personas){
            save(aGuardar);
        }
    }
    public void save(Person persona) { PersonaRepository.save(persona); //agregado el 14 del 11
    }
}
