package py.edu.uc.lp2.apirest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public double calculateTotalPrice(List<Integer> ids) {
        return vendibleRepository.findAllById(ids)
                .stream()
                .mapToDouble(Vendible::getPrecio)
                .sum();
    }

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

    public double calculateGrandTotal(List<Map<String, Object>> vendibleDetails) {
        return vendibleDetails.stream()
                .mapToDouble(detail -> (double) detail.get("total"))
                .sum();
    }

    public Vendible updateVendible(Integer id, Vendible updatedVendible) {
        return vendibleRepository.findById(id)
                .map(vendible -> {
                    vendible.setNombre(updatedVendible.getNombre());
                    vendible.setDescripcion(updatedVendible.getDescripcion());
                    vendible.setPrecio(updatedVendible.getPrecio());
                    vendible.setCategoria(updatedVendible.getCategoria());
                    return vendibleRepository.save(vendible);
                })
                .orElseThrow(() -> new RuntimeException("Vendible not found with id " + id));
    }

    @Override
    public void procesarCompra(List<Vendible> vendibles) {
        // Lógica para procesar una compra, ejemplo:
        vendibles.forEach(vendible -> {
            System.out.println("Procesando compra de " + vendible.getNombre());
            // Lógica de negocio adicional para procesar la compra
        });
    }

    @Override
    public boolean validarListaVendibles(List<Vendible> vendibles) {
        // Lógica para validar la lista de vendibles, ejemplo:
        return vendibles.stream().allMatch(v -> v.getPrecio() > 0);
    }
}
