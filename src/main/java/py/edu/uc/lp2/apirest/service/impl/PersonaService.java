package py.edu.uc.lp2.apirest.service.impl;

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

    // Obtener todas las personas desde la base de datos
    public List<Persona> getAllPersonas() {
        logger.info("Obteniendo lista de todas las personas desde la base de datos");
        return personaRepository.findAll();
    }

    // Verificar si la persona es mayor de edad
    public boolean esMayorDeEdad(Persona persona) {
        return persona.getEdad() >= 18;
    }

    // Guardar una persona en la base de datos
    public void save(Persona persona) {
        logger.info("Guardando persona con ID: {}", persona.getId());
        personaRepository.save(persona);
    }

    // Guardar una lista de personas en la base de datos
    public void saveList(List<Persona> personas) {
        logger.info("Guardando lista de personas. Cantidad: {}", personas.size());
        personaRepository.saveAll(personas);
    }

    public void saveAll(List<Persona> personas) {
        personaRepository.saveAll(personas);
    }

    // Buscar una persona por ID
    public Persona findById(Long id) {
        logger.info("Buscando persona con ID: {}", id);
        return personaRepository.findById(id).orElse(null);
    }

    // Eliminar una persona por ID
    public void deleteById(Long id) {
        logger.info("Eliminando persona con ID: {}", id);
        personaRepository.deleteById(id);
    }
}
