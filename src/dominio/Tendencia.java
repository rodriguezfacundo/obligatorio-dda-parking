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
    
    public abstract void evaluarTendencia(Parking parking);
    public abstract double calcularFactorDemanda(int cantidadUT, Parking parking);
}
