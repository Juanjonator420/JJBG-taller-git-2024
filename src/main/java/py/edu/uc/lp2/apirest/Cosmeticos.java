package py.edu.uc.lp2.apirest;

public class Cosmeticos extends Vendible {
    protected double precio;
    protected int rareza;

    // Constructor, getters y setters

    public Cosmeticos() {}

    public Cosmeticos(int rareza) {
        this.rareza = rareza;
    }
    public int getRareza() {
        return rareza;
    }
    public void setRareza(int rareza) {
        this.rareza = rareza;
    }
}