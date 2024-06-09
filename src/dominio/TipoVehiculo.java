package dominio;

import java.util.ArrayList;

public class TipoVehiculo {
    private String nombre;
    private ArrayList<Tarifa> tarifasAsociadas;

    public TipoVehiculo(String nombre) {
        this.tarifasAsociadas = new ArrayList<Tarifa>();
        this.nombre = nombre;
    }

    //GETTERS Y SETTERS
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public ArrayList<Tarifa> getTarifasAsociadas() {return tarifasAsociadas;}
    public void setTarifasAsociadas(ArrayList<Tarifa> tarifasAsociadas) {this.tarifasAsociadas = tarifasAsociadas;}
    public void agregarTarifa(Tarifa nueva){this.tarifasAsociadas.add(nueva);}
    
    //Calcula el promedio de las tarifas asociadas y lo retorna.
    public double calcularPromedio(){
        double precioSuma = 0;
        int contador = 0;
        for(Tarifa t:this.tarifasAsociadas){
            precioSuma = precioSuma + t.getPrecioPorUT();
            contador++;
        }
        if(contador==0)return 0;
        return precioSuma / contador;
    }
}
