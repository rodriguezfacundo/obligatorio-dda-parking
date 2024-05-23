package dominio;

public abstract class Etiqueta {
    private String nombre;

    public Etiqueta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
   public abstract Double calcularMulta(int UT, double valorFacturado);
}
