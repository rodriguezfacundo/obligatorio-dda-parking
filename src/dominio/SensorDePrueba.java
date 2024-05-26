package dominio;

import simuladortransito.Estacionable;
import simuladortransito.Sensor;
import simuladortransito.Transitable;

public class SensorDePrueba implements Sensor{
    
    @Override
    public void ingreso(Transitable transitable, Estacionable estacionable) {
        Vehiculo v = (Vehiculo) transitable;
        Cochera c = (Cochera) estacionable;
        v.setEstacionado(true);
        c.setOcupada(true);
    }

    @Override
    public void egreso(Transitable transitable, Estacionable estacionable) {
        Vehiculo v = (Vehiculo) transitable;
        Cochera c = (Cochera) estacionable;
        v.setEstacionado(false);
        c.setOcupada(false);
    }
}
