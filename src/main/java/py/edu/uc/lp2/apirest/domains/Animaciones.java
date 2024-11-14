package py.edu.uc.lp2.apirest.domains;

public class Animaciones extends Cosmeticos {
    private int precioDinero;
    public int duracion;
    protected String tipoAnimacion;

    // Constructor, getters y setters

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTipoAnimacion() {
        return tipoAnimacion;
    }

    public void setTipoAnimacion(String tipoAnimacion) {
        this.tipoAnimacion = tipoAnimacion;
    }
}