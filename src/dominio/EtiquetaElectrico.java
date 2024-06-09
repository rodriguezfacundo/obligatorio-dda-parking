package dominio;

public class EtiquetaElectrico extends Etiqueta {

    public EtiquetaElectrico() {
        super("Electrico");
    }

    //Estacionar incorrectamente en una cochera para vehículos eléctricos: la multa es equivalente al 50% del monto de
    //la estadía.
    @Override
    public Double calcularMulta(int UT, double valorFacturado) {
        return 0.5 * valorFacturado;
    }
    
}
