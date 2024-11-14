package py.edu.uc.lp2.apirest.service;

import py.edu.uc.lp2.apirest.domains.Vendible;

import java.util.List;

public interface VendibleService {

    boolean validarCompraMinima(double totalCompra);
    void procesarCompra(List<Vendible> totalCompra);

    boolean validarListaVendibles(List<Vendible> vendibles);
}

