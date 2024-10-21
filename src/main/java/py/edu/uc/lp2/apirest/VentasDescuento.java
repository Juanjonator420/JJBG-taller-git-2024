package py.edu.uc.lp2.apirest;

public class VentasDescuento extends VentasDirecto{
    private float porcentajeDesucento;
    private String codigoPromocional;



    public float getPorcentajeDesucento() {
        return porcentajeDesucento;
    }

    public void setPorcentajeDesucento(float porcentajeDesucento) {
        this.porcentajeDesucento = porcentajeDesucento;
    }

    public String getCodigoPromocional() {
        return codigoPromocional;
    }

    public void setCodigoPromocional(String codigoPromocional) {
        this.codigoPromocional = codigoPromocional;
    }
}
