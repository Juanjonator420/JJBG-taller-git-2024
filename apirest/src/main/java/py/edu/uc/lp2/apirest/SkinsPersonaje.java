package py.edu.uc.lp2.apirest;

public class SkinsPersonaje extends Skin {
    private int avatar;

    public SkinsPersonaje(int avatar) {
        this.avatar = avatar;
    }

    public SkinsPersonaje(int rareza, int avatar) {
        super(rareza);
        this.avatar = avatar;
    }

    // Constructor, getters y setters
}
