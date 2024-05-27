package dominio;

public class TendenciaPositiva extends Tendencia{

    public TendenciaPositiva() {
        super("Positiva");
    }
    
    @Override
    public void evaluarTendencia(Parking parking) {
        if (parking.diferenciaEntreIngresoYEgresos() > 0.1 * parking.getCapacidad()) {
            parking.setTendencia(this);
        }
    }
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
