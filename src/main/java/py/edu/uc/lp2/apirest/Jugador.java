package py.edu.uc.lp2.apirest;

public class Jugador extends Usuario{
    protected int nivel;
    protected String nickName;
    private String estado;





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

    public Jugador(Long id, String nombre, String apellido, String cedula) {
        super(id, nombre, apellido, cedula);
    }

    public Jugador(String nombre, String apellido, String cedula) {
        super(nombre, apellido, cedula);
    }
}
