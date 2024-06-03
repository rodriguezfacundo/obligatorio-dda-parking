/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dominio.Parking;
import dominio.Tarifa;
import java.util.ArrayList;
import observador.IObservador;
import observador.Observable;
import sistemas.Fachada;
import vistaParking.UIListaPrecios;

public class ControladorListaPrecios implements IObservador{
    private VistaListaPrecios vista;
    private Fachada fachada;
    private Parking parking;
    
    
    public ControladorListaPrecios(VistaListaPrecios vista,Parking parking) {
        this.fachada = Fachada.getInstancia();
        this.vista = vista;
        this.parking=parking;
        agregarObservador();
        mostrarPrecios();
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
       if (((Observable.Eventos) evento).equals(Observable.Eventos.PARKING_CAMBIO)) {
            mostrarPrecios();
        }  
    }
    
    public void agregarObservador(){
        this.parking.agregarObservador(this);
    }
    public void quitarObservador(){
        this.parking.quitarObservador(this);
    }
    
    public void mostrarPrecios(){
        this.vista.mostrarPrecios(this.parking.getTarifas());
    }
    public void mostrarTitulo(){
        this.vista.mostrarTitulo(parking.getNombre());
    }
    public void cambiarValor(int pos,Double nuevoValor){
        if(pos==0)pos=1;
        this.parking.actualizarValorTarifa(pos,nuevoValor);
    }
    
}
