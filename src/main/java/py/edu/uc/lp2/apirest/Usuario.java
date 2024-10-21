package py.edu.uc.lp2.apirest;

public class Usuario extends Persona{
    protected String correo;
    protected String contraseña;





    public Usuario(Long id, String nombre, String apellido, String cedula) {
        super(id, nombre, apellido, cedula);
    }

    public Usuario(String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
    }

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
