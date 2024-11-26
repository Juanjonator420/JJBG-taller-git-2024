package py.edu.uc.lp2.apirest.domains;

import jakarta.persistence.*;

@Entity
public class Jugador extends Usuario {
    private int nivel;
    private String nickName;
    private String estado;

    // Constructor por defecto
    public Jugador() {
        super(); // Llama al constructor por defecto de Usuario
    }

    // Constructor completo
    public Jugador(Long id, String nombre, String apellido, String cedula, int edad, String correo, String contraseña, int nivel, String nickName, String estado) {
        super(id, nombre, apellido, cedula, edad, correo, contraseña);
        this.nivel = nivel;
        this.nickName = nickName;
        this.estado = estado;
    }

    // Getters y Setters
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
