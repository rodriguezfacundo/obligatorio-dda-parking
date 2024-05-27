package dominio;

public class TendenciaEstable extends Tendencia {

    public TendenciaEstable() {
        super("Estable");
    }
    @Override
    public void evaluarTendencia(Parking parking) {
        if (Math.abs(parking.diferenciaEntreIngresoYEgresos()) <= 0.1 * parking.getCapacidad()) {
            parking.setTendencia(this);
        }
    }
    @Override
    public double calcularFactorDemanda(int cantidadUT, Parking parking) {
            return Math.max(0.25, parking.getFactorDemanda() - 0.01 * cantidadUT);
    }
}
