package py.edu.uc.lp2.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository; //tambien se podria utilizar PagingAndSortingRepository
import org.springframework.stereotype.Repository; //import para Repository, agregado por IA
import py.edu.uc.lp2.apirest.domains.Persona;

@Repository
//interface para almacenar y recuperar los datos sobre las personas
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findByCedula(String cedula);
}
