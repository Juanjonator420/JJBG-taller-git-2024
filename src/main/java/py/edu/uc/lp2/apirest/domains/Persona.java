package py.edu.uc.lp2.apirest.domains;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Estrategia JOINED
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String cedula;

    private int edad; // Nuevo atributo para reemplazar fechaNacimiento

    // Constructor por defecto
    public Persona() {}

    // Constructor completo
    public Persona(Long id, String nombre, String apellido, String cedula, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.edad = edad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
