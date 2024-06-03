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
    private ArrayList<Estadia> estadiasAnomalias;
    
    
    public ControladorTableroControl(UITableroControl vistaTableroControl){
        this.fachada = Fachada.getInstancia();
        this.vista = vistaTableroControl;
        this.parkings = fachada.obtenerParkings();
        this.estadiasAnomalias =  new ArrayList<Estadia>();
        agregarObservadorParkings();
        mostrarTableroControl();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
      if (((Observable.Eventos) evento).equals(Observable.Eventos.ANOMALIA_REGISTRADA)) {
            Estadia nuevaAnomalia = (Estadia) origen;
            this.estadiasAnomalias.add(nuevaAnomalia);
            mostrarAnomalias();
        }  
      if(((Observable.Eventos) evento).equals(Observable.Eventos.INGRESO_EGRESO_ESTADIA)) {
           mostrarTableroControl();
        }
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
    public void mostrarAnomalias(){
        this.vista.mostrarAnomaliasCheckbox(this.estadiasAnomalias);
    }
    public Parking getParkingSeleccionado(int seleccionado){
          if(seleccionado==0)seleccionado=1;
        return this.parkings.get(seleccionado);
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
    public void agregarObservadorEstadias(int pos){
        if(pos==0)pos=1;
        System.out.print(pos);
        Parking seleccionado = this.parkings.get(pos);
        for(Cochera c:seleccionado.getCocheras()){
            for(Estadia e:c.getEstadias()){
                e.agregarObservador(this);
            }
        }
    }
    public void quitarObservadorEstadias(int pos){
        Parking seleccionado = this.parkings.get(pos);
        for(Cochera c:seleccionado.getCocheras()){
            for(Estadia e:c.getEstadias()){
                e.quitarObservador(this);
            }
        }
    }
}