package py.edu.uc.lp2.apirest.domains;

public class VentasDirecto extends Ventas{
    protected float metodoPago;
    protected String vendedorID;

    //constructores, getters y setters...

    public float getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(float metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getVendedorID() {
        return vendedorID;
    }

    public void setVendedorID(String vendedorID) {
        this.vendedorID = vendedorID;
    }
}
