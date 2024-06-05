package dominio;

import simuladortransito.Estacionable;
import simuladortransito.Sensor;
import simuladortransito.Transitable;
import sistemas.Fachada;
import sistemas.SistemaParking;

public class SensorEstadia implements Sensor{
    
    @Override
    public void ingreso(Transitable transitable, Estacionable estacionable) {
         Vehiculo vehiculo = (Vehiculo)transitable;
         Cochera cochera =(Cochera)estacionable;
         if(cochera!=null && vehiculo !=null){
             cochera.ingreso(vehiculo);
         }
    }

    @Override
    public void egreso(Transitable transitable, Estacionable estacionable) {
        Vehiculo vehiculo = (Vehiculo)transitable;
        Cochera cochera =(Cochera)estacionable;
        if(vehiculo!=null && cochera!=null){
            cochera.egreso(vehiculo);
        }
    }
}
