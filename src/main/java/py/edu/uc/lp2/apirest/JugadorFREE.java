package py.edu.uc.lp2.apirest;

public class JugadorFREE extends Jugador{
    private boolean anunciosMostrar;
    private boolean limitadorXP;




    public boolean isLimitadorXP() {
        return limitadorXP;
    }

    public void setLimitadorXP(boolean limitadorXP) {
        this.limitadorXP = limitadorXP;
    }

    public boolean isAnunciosMostrar() {
        return anunciosMostrar;
    }

    public void setAnunciosMostrar(boolean anunciosMostrar) {
        this.anunciosMostrar = anunciosMostrar;
    }

    public JugadorFREE(Long id, String nombre, String apellido, String cedula) {
        super(id, nombre, apellido, cedula);
    }

    public JugadorFREE(String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
    }
}
