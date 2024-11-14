package py.edu.uc.lp2.apirest.domains;

public class FusilAsalto extends ArmasDeFuego {
    private int precioDinero;
    private int nivelMinimoRequerido;
    private int nivelMinimoFuegoSeguido;
    protected boolean modoRafaga;
    protected String adiciones;


    // ... constructores, getters y setters ...

    public String getAdiciones() {
        return adiciones;
    }

    public void setAdiciones(String adiciones) {
        this.adiciones = adiciones;
    }

    public boolean isModoRafaga() {
        return modoRafaga;
    }

    public void setModoRafaga(boolean modoRafaga) {
        this.modoRafaga = modoRafaga;
    }
}
