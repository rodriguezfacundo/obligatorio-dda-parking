package dominio;

public class TendenciaNegativa extends Tendencia {

    @Override
    public double calcularFactorDemanda(double factorDemandaActual, int ocupacion, int capacidad, int diferenciaIngresosEgresos, int cantidadUT) {
        if (diferenciaIngresosEgresos < 0.1 * capacidad) {
            if (factorDemandaActual > 1) {
                return 1;
            } else {
                return Math.max(0.25, factorDemandaActual - 0.05 * cantidadUT);
            }
        }
        return factorDemandaActual;
    }
    
}
