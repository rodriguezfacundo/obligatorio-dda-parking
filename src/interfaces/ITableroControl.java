/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Estadia;
import dominio.Parking;
import java.util.ArrayList;

/**
 *
 * @author mende
 */
public interface ITableroControl {
    void mostrarCantidadEstadias(int cantidad);
    void mostrarTotalFacturado(float total);
    void mostrarListaParkings(ArrayList<Parking> parkings);
    void mostrarAnomaliasCheckbox(Estadia nueva);
}
