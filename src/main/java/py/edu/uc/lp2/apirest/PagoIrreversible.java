package py.edu.uc.lp2.apirest;

public class PagoIrreversible extends Pago{

    protected boolean esIrreversible;




    public boolean isEsIrreversible() {
        return esIrreversible;
    }

    public void setEsIrreversible(boolean esIrreversible) {
        this.esIrreversible = esIrreversible;
    }
}
