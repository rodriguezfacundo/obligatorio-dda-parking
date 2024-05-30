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
            if(cochera.estaOcupada()){
                Estadia estadiaAnomaliaHoudini = cochera.obtenerEstadiaAbierta();
                estadiaAnomaliaHoudini.anomaliaHoudini();
                Fachada.getInstancia().agregarEstadia(estadiaAnomaliaHoudini);
            } 
            Estadia estadiaNueva = new Estadia(vehiculo, cochera);
            SistemaParking.getInstancia().agregarNuevaEstadia(estadiaNueva);
         }
    }

    @Override
    public void egreso(Transitable transitable, Estacionable estacionable) {
        Vehiculo vehiculo = (Vehiculo)transitable;
        Cochera cochera =(Cochera)estacionable;
        if(vehiculo!=null && cochera!=null){
            if(!cochera.estaOcupada()){
                Estadia estadiaMistery = new Estadia(vehiculo, cochera);
                estadiaMistery.anomaliaMistery();
                 SistemaParking.getInstancia().agregarNuevaEstadia(estadiaMistery);
            } else{
                Estadia estadia = cochera.obtenerEstadiaAbierta();
                if(estadia != null && !estadia.contieneVehiculo(vehiculo)){
                    estadia.anomaliaTransportadorUno();
                    Estadia estadiaTransportadorDos = new Estadia(vehiculo);
                    estadiaTransportadorDos.anomaliaTransportadorDos();
                    SistemaParking.getInstancia().agregarNuevaEstadia(estadiaTransportadorDos);
                } else{
                    estadia.finalizar();
                 }
            }   
        }
    }
}
