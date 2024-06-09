package controlador;

import dominio.Parking;
import observador.IObservador;
import observador.Observable;

public class ControladorCarteleraElectronica implements IObservador{
    private VistaCarteleraElectronica vista;
    private Parking parking;
    
    public ControladorCarteleraElectronica(VistaCarteleraElectronica vista, Parking parking){
        this.vista = vista;
        this.parking = parking;
        agregarObservador();
        this.mostrarCarteleraElectronica();
    }

    //Aca se procesan los eventos del modelo
    @Override
    public void actualizar(Object evento, Observable origen) {
        if (((Observable.Eventos) evento).equals(Observable.Eventos.PARKING_CAMBIO)) {
             mostrarPrecios();
        } 
        if(((Observable.Eventos) evento).equals(Observable.Eventos.INGRESO_EGRESO_ESTADIA)){
            mostrarDisponibilidad();
            mostrarEtiquetas();
         }
    }
    public void agregarObservador(){
        this.parking.agregarObservador(this);
    }
    public void quitarObservador(){
        this.parking.quitarObservador(this);
    }
    private void mostrarCarteleraElectronica(){
        mostrarPrecios();
        mostrarTitulo();
        mostrarDisponibilidad();
        mostrarEtiquetas();
    }
    public void mostrarPrecios(){
        this.vista.mostrarPrecios(this.parking.getTarifas());
    }
    public void mostrarEtiquetas(){
        this.vista.mostrarDisponibilidadCocheraPorEtiqueta(this.parking.obtenerDisponibilidadPorEtiqueta());
    }
    public void mostrarTitulo(){
        this.vista.mostrarTitulo(parking.getNombre());
    }
    public void mostrarDisponibilidad(){
        this.vista.mostrarDisponibilidad(this.parking.getCantidadCocherasDisponibles());
    }
}
