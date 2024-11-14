package py.edu.uc.lp2.apirest.domains;

public class Melee extends Armas {
    protected String Material;
    protected int Peso;

    // ... atributos específicos de melee ...


    public int getPeso() {
        return Peso;
    }

    public void setPeso(int peso) {
        Peso = peso;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }
}
