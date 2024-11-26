package py.edu.uc.lp2.apirest.domains;

import jakarta.persistence.*;

@Entity
public class JugadorVIP extends Jugador {
    private boolean paseBatalla;
    private double descuentoTienda;

    // Constructor por defecto
    public JugadorVIP() {
        super(); // Llama al constructor por defecto de Jugador
    }

    // Constructor completo
    public JugadorVIP(Long id, String nombre, String apellido, String cedula, int edad, String correo, String contraseña, int nivel, String nickName, String estado, boolean paseBatalla, double descuentoTienda) {
        super(id, nombre, apellido, cedula, edad, correo, contraseña, nivel, nickName, estado);
        this.paseBatalla = paseBatalla;
        this.descuentoTienda = descuentoTienda;
    }

    // Getters y Setters
    public boolean isPaseBatalla() {
        return paseBatalla;
    }

    public void setPaseBatalla(boolean paseBatalla) {
        this.paseBatalla = paseBatalla;
    }

    public double getDescuentoTienda() {
        return descuentoTienda;
    }

    public void setDescuentoTienda(double descuentoTienda) {
        this.descuentoTienda = descuentoTienda;
    }
}
