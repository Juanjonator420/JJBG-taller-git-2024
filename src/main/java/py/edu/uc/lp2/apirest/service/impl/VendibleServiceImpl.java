package py.edu.uc.lp2.apirest.service.impl;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.edu.uc.lp2.apirest.domains.Vendible;
import py.edu.uc.lp2.apirest.service.VendibleService;

import java.util.List;

@Service
public class VendibleServiceImpl implements VendibleService {

    protected static final Logger logger = LoggerFactory.getLogger(VendibleServiceImpl.class);
    public static final double MONTO_MINIMO_COMPRA = 100.0; // Ajusta según tu necesidad

    @Override
    public boolean validarCompraMinima(double totalCompra) {
        boolean esValido = totalCompra >= MONTO_MINIMO_COMPRA;
        logger.info("Total de compra: {} - ¿Cumple el monto mínimo? {}", totalCompra, esValido);
        return esValido;
    }

    @Override
    public boolean validarListaVendibles(List<Vendible> vendibles) {
        if (vendibles == null || vendibles.isEmpty()) {
            logger.warn("La lista de vendibles es nula o está vacía.");
            return false;
        }

        // Calcular el total de la compra
        double totalCompra = vendibles.stream().mapToDouble(Vendible::getPrecio).sum();
        logger.info("Suma total de la lista de vendibles: {}", totalCompra);

        // Validar el monto mínimo de compra
        return validarCompraMinima(totalCompra);
    }

    @Override
    public void procesarCompra(List<Vendible> vendibles) {
        logger.info("Procesando compra de los siguientes productos: {}", vendibles);
    }
}

