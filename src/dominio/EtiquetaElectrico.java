package dominio;

public class EtiquetaElectrico extends Etiqueta {

    public EtiquetaElectrico() {
        super("Electrico");
    }

    @Override
    public Double calcularMulta(int UT, double valorFacturado) {
        return 0.5 * valorFacturado;
    }
    
}
