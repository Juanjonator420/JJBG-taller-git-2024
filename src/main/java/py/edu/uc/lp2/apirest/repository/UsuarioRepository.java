package py.edu.uc.lp2.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.edu.uc.lp2.apirest.domains.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

