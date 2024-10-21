package py.edu.uc.lp2.apirest;

public class Transferencias extends Fisico{

    private String bancoID;
    private String cuentaNumeroBanco;





    public String getBancoID() {
        return bancoID;
    }

    public void setBancoID(String bancoID) {
        this.bancoID = bancoID;
    }

    public String getCuentaNumeroBanco() {
        return cuentaNumeroBanco;
    }

    public void setCuentaNumeroBanco(String cuentaNumeroBanco) {
        this.cuentaNumeroBanco = cuentaNumeroBanco;
    }
}
