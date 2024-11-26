package py.edu.uc.lp2.apirest.service;

import py.edu.uc.lp2.apirest.domains.Vendible;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//interfaz de servicios para vendibles

public interface VendibleService {
    List<Vendible> getAllVendibles();
    Optional<Vendible> getVendibleById(Long id);
    Vendible saveVendible(Vendible vendible);
    void deleteVendible(Long id);
    void procesarCompra(List<Vendible> vendibles);
    boolean validarListaVendibles(List<Vendible> vendibles);
    List<Vendible> saveAll(List<Vendible> vendibles);

    double calculateTotalPrice(List<Integer> ids);
    double calculateGrandTotal(List<Map<String, Object>> vendibleDetails);
    List<Map<String, Object>> calculateTotalPriceWithQuantities(List<Map<String, Object>> vendibleRequests);

    // Métodos para los nuevos servicios
    double calcularCostoEnvio(Vendible vendible);
    double calcularCostoMantenimiento(Vendible vendible);
}
