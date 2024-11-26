package py.edu.uc.lp2.apirest.domains;

import jakarta.persistence.Entity;

@Entity
public class Empleados extends Usuario {

    private String puesto;
    private int horasDeTrabajo;

    // Constructor por defecto
    public Empleados() {
        super(); // Llama al constructor por defecto de Usuario
    }

    // Constructor completo
    public Empleados(Long id, String nombre, String apellido, String cedula, int edad, String correo, String contraseña, String puesto, int horasDeTrabajo) {
        super(id, nombre, apellido, cedula, edad, correo, contraseña);
        this.puesto = puesto;
        this.horasDeTrabajo = horasDeTrabajo;
    }

    // Getters y Setters
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getHorasDeTrabajo() {
        return horasDeTrabajo;
    }

    public void setHorasDeTrabajo(int horasDeTrabajo) {
        this.horasDeTrabajo = horasDeTrabajo;
    }
}
