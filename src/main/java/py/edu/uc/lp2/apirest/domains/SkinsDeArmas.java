package py.edu.uc.lp2.apirest.domains;

public class SkinsDeArmas extends Skins{
    private String compatibilidadArma;


    // Constructor, getters y setters
    public SkinsDeArmas(int rareza) {
        super(rareza);
    }

    public String getCompatibilidadArma() {
        return compatibilidadArma;
    }

    public void setCompatibilidadArma(String compatibilidadArma) {
        this.compatibilidadArma = compatibilidadArma;
    }
}
