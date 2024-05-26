package sistemas;

import dominio.Parking;
import dominio.Tarifa;
import dominio.Tendencia;
import java.util.ArrayList;


public class SistemaParking {
    
    private ArrayList<Parking> parkings = new ArrayList<>();
    private ArrayList<Tendencia> tendencias = new ArrayList<>();
    
    private static SistemaParking instancia = new SistemaParking();

    public static SistemaParking getInstancia() {
        return instancia;
    }
    
    public  void agregarParking(Parking parking){
        parkings.add(parking);
    }
    
    public ArrayList<Parking> obtenerParkings(){
        return parkings;
    }    
    
    public  void inicializarTarifasEnParking(){
        ArrayList<Tarifa> tarifas = new ArrayList<>();
         SistemaVehiculo sVehiculo = SistemaVehiculo.getInstancia();
        tarifas.add(new Tarifa(0.05, sVehiculo.obtenerTipoDeVehiculoPorNombre("Motocicleta")));
        tarifas.add(new Tarifa(0.1,  sVehiculo.obtenerTipoDeVehiculoPorNombre("Standard")));
        tarifas.add(new Tarifa(0.1, sVehiculo.obtenerTipoDeVehiculoPorNombre ("Carga")));
        tarifas.add(new Tarifa(0.1,  sVehiculo.obtenerTipoDeVehiculoPorNombre("Pasajeros")));

        for(Parking park:this.parkings){
            park.setTarifas(tarifas);
        }
    }

    public void agregarTendencia(Tendencia ten) {
        this.tendencias.add(ten);
    }
    
    public ArrayList<Tendencia> getTendencias(){
        return this.tendencias;
    }

    Tendencia obtenerTendenciaPorNombre(String nombre) {
        for(Tendencia tendencia:this.tendencias){
            if(tendencia.getNombre().equals(nombre)){
                return tendencia;
            }
        }
        return null;
    }
    
    public int obtenerCantidadEstadias(){
       int cantidad = 0;
       for(Parking p:parkings){
           cantidad = cantidad + p.obtenerCantidadEstadias();
       }
       return cantidad;
   }
    
    public double obtenerTotalFacturado(){
        double total = 0;
      for(Parking p:parkings){
           total = total + p.obtenerTotalFacturado();
       }
       return total;
   }
    
}
