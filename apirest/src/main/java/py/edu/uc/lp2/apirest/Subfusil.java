package py.edu.uc.lp2.apirest;

public class Subfusil extends ArmasDeFuego {
    private int precioDinero;
    private int nivelMinimoRequerido;

    // ... constructores, getters y setters ...

    public Subfusil(int precioDinero, int nivelMinimoRequerido, String descripcion, String nombre) {
        this.precioDinero = precioDinero;
        this.nivelMinimoRequerido = nivelMinimoRequerido;
        //this.precio = 0;
        //this.nombre = "Sin nombre";
        //this.descripcion = "Sin descripcion";
        this.carga = 30;
        setDescripcion(descripcion);
        setNombre(nombre);
        setPrecio(precioDinero);
    }

    public int getPrecioDinero() {
        return precioDinero;
    }

    public void setPrecioDinero(int precioDinero) {
        this.precioDinero = precioDinero;
    }

    public int getNivelMinimoRequerido() {
        return nivelMinimoRequerido;
    }

    public void setNivelMinimoRequerido(int nivelMinimoRequerido) {
        this.nivelMinimoRequerido = nivelMinimoRequerido;
    }
}
