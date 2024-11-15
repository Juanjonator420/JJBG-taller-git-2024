package py.edu.uc.lp2.apirest.domains;

public class Cosmeticos extends Vendible {
    protected double precio;
    protected int rareza;

    // Constructor, getters y setters
    public Cosmeticos() {
    }

    public Cosmeticos(int rareza) {
        this();
        this.rareza = rareza;
    }

    public Cosmeticos(String categoria, String nombre, double precio, String descripcion, Long id, double precio1, int rareza) {
        super(categoria, nombre, precio, descripcion, id);
        this.precio = precio1;
        this.rareza = rareza;
    }

    public Cosmeticos(String categoria, String nombre, double precio, String descripcion, Long id) {
        super(categoria, nombre, precio, descripcion, id);
    }

    public int getRareza() {
        return rareza;
    }
    public void setRareza(int rareza) {
        this.rareza = rareza;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}