package dominio;

public class TendenciaPositiva extends Tendencia{

    public TendenciaPositiva() {
        super("Positiva");
    }
    
    //En los últimos 10 <UT> la diferencia entre ingresos y egresos es menor o igual al 10% de la capacidad delparking.
    @Override
    public void evaluarTendencia(Parking parking) {
        if (parking.diferenciaEntreIngresoYEgresosRecientes() > 0.1 * parking.getCapacidad()) {
            parking.setTendencia(this);
        }
    }
    //Si la ocupación del parking es menor al 33%, el factor de demanda aumenta 0.05 por cada <UT> en que la tendencia transcurre en estado
    //positivo.
    @Override
    public double calcularFactorDemanda(int cantidadUT, Parking parking) {
            double fd = parking.getFactorDemanda();
            if (parking.getCantidadCocherasOcupadas() < 0.33 * parking.getCantidadCocherasOcupadas()) {
                fd = Math.min(10, parking.getFactorDemanda() + 0.05 * cantidadUT);
            } 
            if ((parking.getCantidadCocherasOcupadas() >= 0.33 * parking.getCantidadCocherasOcupadas()) && (parking.getCantidadCocherasOcupadas() <= 0.66 * parking.getCantidadCocherasOcupadas())) {
                fd = Math.min(10, parking.getFactorDemanda() + 0.1 * cantidadUT);
            } 
            if(parking.getCantidadCocherasOcupadas() > 0.66 * parking.getCantidadCocherasOcupadas()){
                fd = Math.min(10, parking.getFactorDemanda() + 0.15 * cantidadUT);
            }
            return fd;
    }
}
