/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dominio.Parking;
import observador.IObservador;
import observador.Observable;
import sistemas.Fachada;
import vistaParking.UIListaPrecios;

public class ControladorListaPrecios implements IObservador{
    private VistaListaPrecios vista;
    private Fachada fachada;
    private Parking parking;
    
    
    public ControladorListaPrecios(VistaListaPrecios vista,Parking parking) {
        this.vista = vista;
        agregarObservador();
        mostrarPrecios();
        this.parking=parking;
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void agregarObservador(){
        fachada.agregarObservador(this);
    }
    
    public void mostrarPrecios(){
        
    }
    public void mostrarTitulo(){
        this.vista.mostrarTitulo(parking.getNombre());
    }
    
    
}
