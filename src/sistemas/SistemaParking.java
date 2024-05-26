package sistemas;

import dominio.Parking;
import dominio.Tarifa;
import dominio.Tendencia;
import java.util.ArrayList;


public class SistemaParking {
    
    private ArrayList<Parking> parkings = new ArrayList<>();
    
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

    void agregarTendencia(Tendencia ten) {
        for(Parking par: this.parkings){
            par.getTendencias().add(ten);
        }
    }
}
