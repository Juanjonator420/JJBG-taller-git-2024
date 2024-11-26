package py.edu.uc.lp2.apirest.domains;

import jakarta.persistence.*;

@Entity
public class JugadorFREE extends Jugador {
    private boolean anunciosMostrar;
    private int limitadorXP;

    // Constructor por defecto
    public JugadorFREE() {
        super(); // Llama al constructor por defecto de Jugador
    }

    // Constructor completo
    public JugadorFREE(Long id, String nombre, String apellido, String cedula, int edad, String correo, String contraseña, int nivel, String nickName, String estado, boolean anunciosMostrar, int limitadorXP) {
        super(id, nombre, apellido, cedula, edad, correo, contraseña, nivel, nickName, estado);
        this.anunciosMostrar = anunciosMostrar;
        this.limitadorXP = limitadorXP;
    }

    // Getters y Setters
    public boolean isAnunciosMostrar() {
        return anunciosMostrar;
    }

    public void setAnunciosMostrar(boolean anunciosMostrar) {
        this.anunciosMostrar = anunciosMostrar;
    }

    public int getLimitadorXP() {
        return limitadorXP;
    }

    public void setLimitadorXP(int limitadorXP) {
        this.limitadorXP = limitadorXP;
    }
}
