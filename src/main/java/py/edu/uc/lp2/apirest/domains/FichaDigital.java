package py.edu.uc.lp2.apirest.domains;

public class FichaDigital extends Digital{

    private String token;
    private int montoDigital;





    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMontoDigital() {
        return montoDigital;
    }

    public void setMontoDigital(int montoDigital) {
        this.montoDigital = montoDigital;
    }
}
