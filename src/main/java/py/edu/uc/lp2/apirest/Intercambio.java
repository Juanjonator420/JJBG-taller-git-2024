package py.edu.uc.lp2.apirest;

public class Intercambio extends Ventas{
    private String itemsIntercambiados;
    protected String compañeroDeIntercambio;

    //constructores, setters y getters...

    public String getItemsIntercambiados() {
        return itemsIntercambiados;
    }

    public void setItemsIntercambiados(String itemsIntercambiados) {
        this.itemsIntercambiados = itemsIntercambiados;
    }

    public String getCompañeroDeIntercambio() {
        return compañeroDeIntercambio;
    }

    public void setCompañeroDeIntercambio(String compañeroDeIntercambio) {
        this.compañeroDeIntercambio = compañeroDeIntercambio;
    }
}
