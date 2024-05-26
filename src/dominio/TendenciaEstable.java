package dominio;

public class TendenciaEstable extends Tendencia {

    @Override
    public double calcularFactorDemanda(double factorDemandaActual, int ocupacion, int capacidad, int diferenciaIngresosEgresos, int cantidadUT) {
        if (Math.abs(diferenciaIngresosEgresos) <= 0.1 * capacidad) {
            return Math.max(0.25, factorDemandaActual - 0.01 * cantidadUT);
        }
        return factorDemandaActual;
    }
}
