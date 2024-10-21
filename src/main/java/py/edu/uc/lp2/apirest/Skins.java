package py.edu.uc.lp2.apirest;

public class Skins extends Cosmeticos {
    private Armas arma; // Asociación con la clase Arma (suponiendo que exista)
    protected String AlternativaColor;

    public Skins(int rareza) {
    }

    public String getAlternativaColor() {
        return AlternativaColor;
    }

    public void setAlternativaColor(String alternativaColor) {
        AlternativaColor = alternativaColor;
    }

    public Armas getArma() {
        return arma;
    }

    public void setArma(Armas arma) {
        this.arma = arma;
    }
// Constructor, getters y setters
}