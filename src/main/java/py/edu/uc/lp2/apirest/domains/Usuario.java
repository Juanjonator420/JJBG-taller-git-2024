package py.edu.uc.lp2.apirest.domains;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Usuario extends Persona {
    private String correo;
    private String contraseña;

    // Constructor por defecto
    public Usuario() {
        super(); // Llama al constructor por defecto de Persona
    }

    // Constructor completo
    public Usuario(Long id, String nombre, String apellido, String cedula, int edad, String correo, String contraseña) {
        super(id, nombre, apellido, cedula, edad);
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
