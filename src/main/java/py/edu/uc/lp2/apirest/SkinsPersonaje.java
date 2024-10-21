package py.edu.uc.lp2.apirest;

public class SkinsPersonaje extends Skins {
    private int avatar;
    protected String ModeloPersonaje;
    public SkinsPersonaje(int rareza, int avatar) {
        super(rareza);
        this.avatar = avatar;
    }


    // Constructor, getters y setters
    public String getModeloPersonaje() {
        return ModeloPersonaje;
    }

    public void setModeloPersonaje(String modeloPersonaje) {
        ModeloPersonaje = modeloPersonaje;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

}
