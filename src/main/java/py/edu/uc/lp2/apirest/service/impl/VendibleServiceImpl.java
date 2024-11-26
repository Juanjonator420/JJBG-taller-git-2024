package py.edu.uc.lp2.apirest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.edu.uc.lp2.apirest.domains.Armas;
import py.edu.uc.lp2.apirest.domains.Vendible;
import py.edu.uc.lp2.apirest.repository.VendibleRepository;
import py.edu.uc.lp2.apirest.service.VendibleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VendibleServiceImpl implements VendibleService {

    private final VendibleRepository vendibleRepository;

    private static final double TARIFA_BASE_ENVIO = 50.0; //costo de envio basico para servicio de envio de armas
    private static final double TARIFA_MANTENIMIENTO_DANO = 10.0; //costo de mantenimiento de armas

    @Autowired
    public VendibleServiceImpl(VendibleRepository vendibleRepository) {
        this.vendibleRepository = vendibleRepository;
    }

    @Override
    public List<Vendible> getAllVendibles() {
        return vendibleRepository.findAll();
    }

    @Override
    public Optional<Vendible> getVendibleById(Long id) {
        return vendibleRepository.findById(Math.toIntExact(id));
    }

    @Override
    public Vendible saveVendible(Vendible vendible) {
        return vendibleRepository.save(vendible);
    }

    @Override
    public void deleteVendible(Long id) {
        vendibleRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Vendible> saveAll(List<Vendible> vendibles) {
        return vendibleRepository.saveAll(vendibles);
    }

    @Override
    public double calculateTotalPrice(List<Integer> ids) {
        return vendibleRepository.findAllById(ids)
                .stream()
                .mapToDouble(Vendible::getPrecio)
                .sum();
    }

    @Override //calcula como un carrito la compra de varios items por id's
    public List<Map<String, Object>> calculateTotalPriceWithQuantities(List<Map<String, Object>> vendibleRequests) {
        List<Map<String, Object>> vendibleDetails = new ArrayList<>();
        vendibleRequests.forEach(request -> {
            Integer id = (Integer) request.get("id");
            Integer cantidad = (Integer) request.get("cantidad");

            vendibleRepository.findById(id).ifPresent(vendible -> {
                double total = vendible.getPrecio() * cantidad;
                Map<String, Object> vendibleDetail = Map.of(
                        "cantidad", cantidad,
                        "nombre", vendible.getNombre(),
                        "total", total
                );
                vendibleDetails.add(vendibleDetail);
            });
        });
        return vendibleDetails;
    }

    @Override
    public double calculateGrandTotal(List<Map<String, Object>> vendibleDetails) {
        return vendibleDetails.stream()
                .mapToDouble(detail -> (double) detail.get("total"))
                .sum();
    }

    @Override
    public void procesarCompra(List<Vendible> vendibles) {
        vendibles.forEach(vendible -> {
            System.out.println("Procesando compra de " + vendible.getNombre());
        });
    }

    @Override
    public boolean validarListaVendibles(List<Vendible> vendibles) {
        return vendibles.stream().allMatch(v -> v.getPrecio() > 0);
    }

    // Servicio de Envío de armas
    @Override
    public double calcularCostoEnvio(Vendible vendible) {
        double costoEnvio = TARIFA_BASE_ENVIO + (vendible.getPrecio() * 0.1);
        System.out.println("Costo de envío para " + vendible.getNombre() + ": " + costoEnvio);
        return costoEnvio;
    }

    // Servicio de Mantenimiento de armas
    @Override
    public double calcularCostoMantenimiento(Vendible vendible) {
        if (vendible instanceof Armas) {
            Armas arma = (Armas) vendible;
            double costoMantenimiento = TARIFA_MANTENIMIENTO_DANO * arma.getDaño();
            System.out.println("Costo de mantenimiento para " + arma.getNombre() + ": " + costoMantenimiento);
            return costoMantenimiento;
        }
        System.out.println("Mantenimiento no disponible para " + vendible.getNombre());
        return 0.0;
    }
}
