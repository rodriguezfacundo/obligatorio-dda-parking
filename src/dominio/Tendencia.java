package dominio;

public abstract class Tendencia {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tendencia(String nombre) {
        this.nombre = nombre;
    }
    public abstract double calcularFactorDemanda(double factorDemandaActual, int ocupacion, int capacidad, int diferenciaIngresosEgresos, int cantidadUT, Parking parking);
}
