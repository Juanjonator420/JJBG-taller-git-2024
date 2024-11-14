package py.edu.uc.lp2.apirest.domains;

public class Pago {
    protected String nombrePago;
    protected float montoDelPago;




    public String getNombrePago() {
        return nombrePago;
    }

    public void setNombrePago(String nombrePago) {
        this.nombrePago = nombrePago;
    }

    public float getMontoDelPago() {
        return montoDelPago;
    }

    public void setMontoDelPago(float montoDelPago) {
        this.montoDelPago = montoDelPago;
    }
}
