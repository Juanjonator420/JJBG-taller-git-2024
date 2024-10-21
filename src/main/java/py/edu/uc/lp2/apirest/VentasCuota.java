package py.edu.uc.lp2.apirest;

public class VentasCuota extends VentasDirecto{

    private int cantidadCutoas;
    private float interes;



    public int getCantidadCutoas() {
        return cantidadCutoas;
    }

    public void setCantidadCutoas(int cantidadCutoas) {
        this.cantidadCutoas = cantidadCutoas;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }
}
