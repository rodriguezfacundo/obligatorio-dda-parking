package controlador;

import dominio.Anomalia;
import dominio.Estadia;
import observador.IObservador;
import observador.Observable;
import sistemas.Fachada;

/**
 *
 * @author mende
 */
/*public class ControladorTableroControl implements IObservador{
    private VistaTableroControl vista;
    private Fachada fachada;
    
    public ControladorTableroControl(VistaTableroControl vistaTableroControl){
        this.fachada = Fachada.getInstancia();
        this.vista = vistaTableroControl;
        agregarObservador();
        mostrarTableroControl();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
      if (((Observable.Eventos) evento).equals(Observable.Eventos.ANOMALIA_REGISTRADA)) {
            Estadia nuevaAnomalia = (Estadia) origen;
            mostrarAnomalias(nuevaAnomalia);
        }  
      if(((Observable.Eventos) evento).equals(Observable.Eventos.PARKING_CAMBIO)) {
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
        this.vista.mostrarListaParkings(fachada.obtenerParkings());
    }
    public void mostrarAnomalias(Estadia nueva){
        this.vista.mostrarAnomaliasCheckbox(nueva);
    }
    public void agregarObservador(){
        fachada.agregarObservador(this);
    }
    public void quitarObservador(){
        fachada.quitarObservador(this);
    }
}*/