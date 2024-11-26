package py.edu.uc.lp2.apirest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.edu.uc.lp2.apirest.domains.Armas;
import py.edu.uc.lp2.apirest.repository.VendibleRepository;
import py.edu.uc.lp2.apirest.service.ArmasService;

import java.util.List;

@Service
public class ArmasServiceImpl implements ArmasService {

    private final VendibleRepository vendibleRepository;

    @Autowired
    public ArmasServiceImpl(VendibleRepository vendibleRepository) {
        this.vendibleRepository = vendibleRepository;
    }

    @Override
    public void guardarArmas(List<Armas> armas) {
        vendibleRepository.saveAll(armas);
    }

    @Override
    public List<Armas> obtenerTodasLasArmas() {
        return vendibleRepository.findAll()
                .stream()
                .filter(v -> v instanceof Armas)
                .map(v -> (Armas) v)
                .toList();
    }
}
