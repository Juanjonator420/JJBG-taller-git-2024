package py.edu.uc.lp2.apirest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import py.edu.uc.lp2.apirest.domains.Person;
import py.edu.uc.lp2.apirest.service.impl.PersonaService;

//interface para almacenar y recuperar los datos sobre las personas
public interface PersonaRepository extends PagingAndSortingRepository<Person, Long> {
    static void save(Person persona) {
    }

    Person findByNumeroCedula(int numeroCedula);
}
