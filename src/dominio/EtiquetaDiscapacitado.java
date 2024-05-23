package dominio;

public class EtiquetaDiscapacitado extends Etiqueta{

    public EtiquetaDiscapacitado() {
        super("Discapacitado");
    }

    @Override
    public Double calcularMulta(int UT, double valorFacturado) {
        return 250.0;
    }
    
}
