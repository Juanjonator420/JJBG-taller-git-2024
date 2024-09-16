package py.edu.uc.lp2.apirest;

public class Vendible {
    protected String nombre;
    protected double precio;
    protected String descripcion;

    // ... constructores, getters y setters ...


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}