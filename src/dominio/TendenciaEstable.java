package dominio;

public class TendenciaEstable extends Tendencia {

    public TendenciaEstable() {
        super("Estable");
    }
    
    @Override
    public void evaluarTendencia(Parking parking) {
        if (Math.abs(parking.diferenciaEntreIngresoYEgresosRecientes()) <= 0.1 * parking.getCapacidad()) {
            parking.setTendencia(this);
        }
    }
    //El factor de demanda se le resta 0.01 por cada <UT> en que la tendencia transcurre en estado estable. 
    //El factor de demanda nunca puede ser menor a 0.25
    @Override
    public double calcularFactorDemanda(int cantidadUT, Parking parking) {
            return Math.max(0.25, parking.getFactorDemanda() - 0.01 * cantidadUT);
    }
}
