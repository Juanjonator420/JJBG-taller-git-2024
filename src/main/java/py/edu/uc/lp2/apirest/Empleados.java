package py.edu.uc.lp2.apirest;

public class Empleados extends Usuario{
    private String puesto;
    protected int horasDeTrabajo;





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

    public Empleados(Long id, String nombre, String apellido, String cedula) {
        super(id, nombre, apellido, cedula);
    }

    public Empleados(String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
    }
}
