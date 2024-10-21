package py.edu.uc.lp2.apirest;

public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    // Agrega aquí otros atributos que necesites (e.g., fechaNacimiento, dirección, etc.)

    // Constructor completo
    public Persona(Long id, String nombre, String apellido, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        // Asigna valores a los otros atributos
    }

    // Constructor sin ID
    public Persona(String nombre, String apellido, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        // Asigna valores a los otros atributos
    }

    // Getters y setters
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

    // Getters y setters para los otros atributos
}
