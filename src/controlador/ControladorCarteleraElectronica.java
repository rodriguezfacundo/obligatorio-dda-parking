package controlador;

import dominio.Parking;
import observador.IObservador;
import observador.Observable;

public class ControladorCarteleraElectronica implements IObservador{
    private VistaParking vista;
    private Parking parking;
    
    public ControladorCarteleraElectronica(VistaParking vista, Parking parking){
        this.vista = vista;
        this.parking = parking;
        
    }

    //Aca se procesan los eventos del modelo
    @Override
    public void actualizar(Object evento, Object origen) {
        
    }
}
