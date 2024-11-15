package py.edu.uc.lp2.apirest.domains;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Vendible {

    private String categoria;
    protected String nombre;
    protected double precio;
    protected String descripcion;
    @Id
    private Long id;

    //constructores...

    public Vendible() {
    }

    public Vendible(String categoria, String nombre, double precio, String descripcion, Long id) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id = id;
    }

    // getters y setters ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}