package dominio;

public class EtiquetaDiscapacitado extends Etiqueta{

    public EtiquetaDiscapacitado() {
        super("Discapacitado");
    }

    //Estacionar incorrectamente en una cochera para discapacitados: la multa es un monto fijo de $250.
    @Override
    public Double calcularMulta(int UT, double valorFacturado) {
        return 250.0;
    }
    
}
