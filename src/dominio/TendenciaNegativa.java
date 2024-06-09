package dominio;

public class TendenciaNegativa extends Tendencia {

    public TendenciaNegativa() {
        super("Negativa");
    }
    
    @Override
    public void evaluarTendencia(Parking parking) {
        if ((parking.diferenciaEntreIngresoYEgresosRecientes(10) < 0) &&  (parking.diferenciaEntreIngresoYEgresosRecientes(10) < 0.1 * parking.getCapacidad())) {
            parking.setTendencia(this);
        }
    }
    @Override
    public double calcularFactorDemanda(int cantidadUT, Parking parking) {
        if (parking.getFactorDemanda() > 1) {
            return 1;
        } else {
            return Math.max(0.25, parking.getFactorDemanda() - 0.05 * cantidadUT);
        }
    } 
}
