//CONTROLADOR TABLERO CONTROL
package controlador;

import dominio.Cochera;
import dominio.Estadia;
import dominio.Parking;
import dominio.ParkingException;
import interfaces.ITableroControl;
import java.util.ArrayList;
import vistaParking.UITableroControl;
import observador.IObservador;
import observador.Observable;
import sistemas.Fachada;

public class ControladorTableroControl implements IObservador{
    private VistaTableroControl vista;
    private Fachada fachada;
    private ArrayList<Parking> parkings;
    private Parking parkingSeleccionado;
    private ArrayList<Estadia> estadiasAnomalias;
    
    
    public ControladorTableroControl(VistaTableroControl vistaTableroControl){
        this.fachada = Fachada.getInstancia();
        this.vista = vistaTableroControl;
        this.parkings = fachada.obtenerParkings();
        this.parkingSeleccionado = null;
        this.estadiasAnomalias =  new ArrayList<Estadia>();
        agregarObservadorParkings();
        mostrarTableroControl();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
      if (((Observable.Eventos) evento).equals(Observable.Eventos.ANOMALIA_REGISTRADA)) {
          Parking parkingOrigen = (Parking)origen;
          if(parkingOrigen.equals(this.parkingSeleccionado))mostrarAnomalias(parkingOrigen);
        }  
      if(((Observable.Eventos) evento).equals(Observable.Eventos.INGRESO_EGRESO_ESTADIA)) {
           mostrarTableroControl();
        }
    }
    public Parking getParkingSeleccionado(int pos)throws ParkingException{
        if(pos<0 || pos>this.parkings.size())throw new ParkingException("No se selecciono ningun parking");
        return this.parkings.get(pos);
    }
    public void mostrarTableroControl(){
        mostrarCantidadEstadias();
        mostrarTotalFacturado();
        mostrarListaParkings();
    }
    
    public void mostrarCantidadEstadias(){
        this.vista.mostrarCantidadEstadias(fachada.obtenerCantidadEstadias());
    }
    public void mostrarTotalFacturado(){
        this.vista.mostrarTotalFacturado((float) fachada.obtenerTotalFacturado());
    }
    public void mostrarListaParkings(){
        this.parkings = fachada.obtenerParkings();
        this.vista.mostrarListaParkings(this.parkings);
    }
    public void mostrarAnomalias(Parking parkingAnomalia){
        this.estadiasAnomalias.add(parkingAnomalia.getUltimaEstadiaConAnomalia());
        this.vista.mostrarAnomaliasCheckbox(estadiasAnomalias);
    }
    public void agregarObservadorParkings(){
        for (Parking p : parkings) {
            p.agregarObservador(this);
        }
    }
    public void quitarObservadorParkings(){
        for (Parking p : parkings) {
            p.quitarObservador(this);
        }
    }
    public void monitorearAnomaliasParking(int pos,boolean monitorear)throws ParkingException{
        if(pos<0)throw new ParkingException("Ningun parking seleccionado");
        if(monitorear)this.parkingSeleccionado = this.parkings.get(pos);
        if(!monitorear)this.parkingSeleccionado = null;
        
    }
}