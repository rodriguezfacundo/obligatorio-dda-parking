package dominio;

public class TendenciaPositiva extends Tendencia{

    @Override
    public double calcularFactorDemanda(double factorDemandaActual, int ocupacion, int capacidad, int diferenciaIngresosEgresos, int cantidadUT) {
        if (diferenciaIngresosEgresos > 0.1 * capacidad) {
            if (ocupacion < 0.33 * ocupacion) {
                return Math.min(10, factorDemandaActual + 0.05 * cantidadUT);
            } 
            if ((ocupacion >= 0.33 * ocupacion) && (ocupacion <= 0.66 * ocupacion)) {
                return Math.min(10, factorDemandaActual + 0.1 * cantidadUT);
            } 
            if(ocupacion > 0.66 * ocupacion){
                return Math.min(10, factorDemandaActual + 0.15 * cantidadUT);
            }
        }
        return factorDemandaActual;
    }
}