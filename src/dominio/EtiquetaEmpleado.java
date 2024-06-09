package dominio;

public class EtiquetaEmpleado extends Etiqueta {

    public EtiquetaEmpleado() {
        super("Empleado");
    }

    //Estacionar incorrectamente en una cochera para empleados internos: la multa es de $ 1 por cada 10 <UT> de
    //estadía.
    @Override
    public Double calcularMulta(int UT, double valorFacturado) {
        return UT / 10.0;
    }
    
}
