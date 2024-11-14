package py.edu.uc.lp2.apirest.domains;

public class ArmasDeFuego extends Armas {
    protected int carga;
    protected float cadencia;
    protected String tipoDeArma;

    // ... constructores, getters y setters ...

    public float getCadencia() {
        return cadencia;
    }

    public void setCadencia(float cadencia) {
        this.cadencia = cadencia;
    }

    public String getTipoDeArma() {
        return tipoDeArma;
    }

    public void setTipoDeArma(String tipoDeArma) {
        this.tipoDeArma = tipoDeArma;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
}
