package py.edu.uc.lp2.apirest;

public class Skin extends Vendible {
    protected double precio;
    protected int rareza;

    public Skin() {}

    public Skin(int rareza) {
        this.rareza = rareza;
    }
    public int getRareza() {
        return rareza;
    }
    public void setRareza(int rareza) {
        this.rareza = rareza;
    }
}