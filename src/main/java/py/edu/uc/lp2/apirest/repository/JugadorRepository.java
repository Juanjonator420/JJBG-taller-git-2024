package py.edu.uc.lp2.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.edu.uc.lp2.apirest.domains.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
