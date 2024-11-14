package py.edu.uc.lp2.apirest.domains;

public class Reembolsable extends Pago{

    protected boolean esRemmbolsable;




    public boolean isEsRemmbolsable() {
        return esRemmbolsable;
    }

    public void setEsRemmbolsable(boolean esRemmbolsable) {
        this.esRemmbolsable = esRemmbolsable;
    }
}
