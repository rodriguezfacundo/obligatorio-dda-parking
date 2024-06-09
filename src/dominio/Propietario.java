package dominio;

import java.util.ArrayList;

public class Propietario {
    private String cedula;
    private String nombre;
    private double cuentaCorriente;
    private ArrayList<Vehiculo> vehiculos;

    public Propietario(String cedula, String nombre,double cuentaCorriente) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.cuentaCorriente = cuentaCorriente;
        this.vehiculos = new ArrayList<>();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
        this.vehiculos.add(vehiculo);
    }
    public void restarCuentaCorriente(double monto) {
        double valorMinimoCuentaCorriente = -10;
        double nuevoMonto = this.cuentaCorriente - monto;
        if(nuevoMonto < valorMinimoCuentaCorriente){
           this.cuentaCorriente = valorMinimoCuentaCorriente;
        } else{
            this.cuentaCorriente = nuevoMonto;
        }
    }

}
