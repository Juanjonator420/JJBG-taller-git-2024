package py.edu.uc.lp2.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.edu.uc.lp2.apirest.domains.Vendible;

@Repository
public interface VendibleRepository extends JpaRepository<Vendible, Integer> {
    // Puedes agregar métodos personalizados aquí si necesitas consultas específicas
}
