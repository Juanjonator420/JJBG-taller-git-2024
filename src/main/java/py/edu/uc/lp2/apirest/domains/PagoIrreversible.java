package py.edu.uc.lp2.apirest.domains;

public class PagoIrreversible extends Pago{

    protected boolean esIrreversible;




    public boolean isEsIrreversible() {
        return esIrreversible;
    }

    public void setEsIrreversible(boolean esIrreversible) {
        this.esIrreversible = esIrreversible;
    }
}
