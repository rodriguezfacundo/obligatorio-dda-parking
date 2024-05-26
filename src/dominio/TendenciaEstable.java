package dominio;

public class TendenciaEstable extends Tendencia {

    public TendenciaEstable() {
        super("Estable");
    }

    @Override
    public double calcularFactorDemanda(double factorDemandaActual, int ocupacion, int capacidad, int diferenciaIngresosEgresos, int cantidadUT, Parking parking) {
        if (Math.abs(diferenciaIngresosEgresos) <= 0.1 * capacidad) {
            parking.setTendencia(this);
            return Math.max(0.25, factorDemandaActual - 0.01 * cantidadUT);
        }
        return factorDemandaActual;
    }
}
