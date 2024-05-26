package dominio;

public abstract class Tendencia {
    public abstract double calcularFactorDemanda(double factorDemandaActual, int ocupacion, int capacidad, int diferenciaIngresosEgresos, int cantidadUT);
}
