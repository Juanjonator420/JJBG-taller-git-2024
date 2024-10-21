package py.edu.uc.lp2.apirest;

public class JugadorVIP extends Jugador{
    private float descuentoTienda;
    protected boolean paseBatalla;




    public float getDescuentoTienda() {
        return descuentoTienda;
    }

    public void setDescuentoTienda(float descuentoTienda) {
        this.descuentoTienda = descuentoTienda;
    }

    public boolean isPaseBatalla() {
        return paseBatalla;
    }

    public void setPaseBatalla(boolean paseBatalla) {
        this.paseBatalla = paseBatalla;
    }

    public JugadorVIP(String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
    }

    public JugadorVIP(Long id, String nombre, String apellido, String cedula) {
        super(id, nombre, apellido, cedula);
    }
}
