package py.edu.uc.lp2.apirest.service;

import py.edu.uc.lp2.apirest.domains.Armas;

import java.util.List;

public interface ArmasService {
    void guardarArmas(List<Armas> armas);
    List<Armas> obtenerTodasLasArmas();
}

