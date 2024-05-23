package dominio;

public class EtiquetaEmpleado extends Etiqueta {

    public EtiquetaEmpleado() {
        super("Empleado");
    }

    @Override
    public Double calcularMulta(int UT, double valorFacturado) {
        return UT / 10.0;
    }
    
}
