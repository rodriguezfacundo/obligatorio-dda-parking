package controlador;

import dominio.Cochera;
import dominio.Estadia;
import dominio.Parking;
import interfaces.ITableroControl;
import java.util.ArrayList;
import vistaParking.UITableroControl;
import observador.IObservador;
import observador.Observable;
import sistemas.Fachada;

public class ControladorTableroControl implements IObservador{
    private ITableroControl vista;
    private Fachada fachada;
    private ArrayList<Parking> parkings;
    private Parking parkingSeleccionado;
    private ArrayList<Estadia> estadiasAnomalias;
    
    
    public ControladorTableroControl(UITableroControl vistaTableroControl){
        this.fachada = Fachada.getInstancia();
        this.vista = vistaTableroControl;
        this.parkings = fachada.obtenerParkings();
        this.parkingSeleccionado = null;
        this.estadiasAnomalias =  new ArrayList<Estadia>();
        agregarObservadorParkings();
        mostrarTableroControl();
    }

    @Override
    public void actualizar(Object evento, Object origen) {
      if (((Observable.Eventos) evento).equals(Observable.Eventos.ANOMALIA_REGISTRADA)) {
          Estadia estadiaOrigen = (Estadia)origen;
          if(estadiaOrigen.getCochera().getParking().equals(this.parkingSeleccionado))mostrarAnomalias(estadiaOrigen);
        }  
      if(((Observable.Eventos) evento).equals(Observable.Eventos.INGRESO_EGRESO_ESTADIA)) {
           mostrarTableroControl();
        }
    }
    public Parking getParkingSeleccionado(int pos){
        //if(pos==0)pos=1;
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
    public void mostrarAnomalias(Estadia estadiaNuevaAnomalia){
        this.estadiasAnomalias.add(estadiaNuevaAnomalia);
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
    public void guardarParkingSeleccionado(int pos){
        //if(pos==0)pos=1;
        this.parkingSeleccionado = this.parkings.get(pos);
        System.out.println("guardar parking seleccionado ejecutado : " + this.parkingSeleccionado);
    }
    public void quitarParkingSeleccionado(int pos){
        this.parkingSeleccionado = null;
    }
}