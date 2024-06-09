package dominio;

public class Multa {
    private String nombre;
    private Etiqueta etiqueta;
    private Estadia estadia;

    public Multa(Etiqueta etiqueta, Estadia estadia) {
        this.nombre = "Multa por etiqueta" + etiqueta.getNombre();
        this.etiqueta = etiqueta;
        this.estadia = estadia;
    }
    //Calcula el valor de la multa a traves de su etiqueta.
    public Double valorMulta(int UT, double valorFacturado){
        return this.etiqueta.calcularMulta(UT, valorFacturado);
    }
    
     public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public String getNombre() {
        return nombre;
    }
}
