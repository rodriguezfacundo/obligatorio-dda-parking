package dominio;

public class TendenciaNegativa extends Tendencia {

    public TendenciaNegativa() {
        super("Negativa");
    }
    
    //En los últimos 10 <UT>, la diferencia entre ingresos y egresos es negativa y menor al 10% de la capacidad.
    @Override
    public void evaluarTendencia(Parking parking) {
        if ((parking.diferenciaEntreIngresoYEgresosRecientes() < 0) &&  (parking.diferenciaEntreIngresoYEgresosRecientes() < 0.1 * parking.getCapacidad())) {
            parking.setTendencia(this);
        }
    }
    //Si el factor de demanda actual es > 1, se establece en 1. 
    //Si el factor de demanda es <= 1, se reduce en 0.05 por cada <UT> hasta llegar a 0.25. El factor de demanda 
    //nunca puede ser menor a 0.25
    @Override
    public double calcularFactorDemanda(int cantidadUT, Parking parking) {
        if (parking.getFactorDemanda() > 1) {
            return 1;
        } else {
            return Math.max(0.25, parking.getFactorDemanda() - 0.05 * cantidadUT);
        }
    } 
}
