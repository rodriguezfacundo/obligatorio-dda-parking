
package controlador;

import dominio.Estadia;
import dominio.Parking;
import java.util.ArrayList;

public interface VistaTableroControl {
    void mostrarCantidadEstadias(int cantidad);
    void mostrarTotalFacturado(float total);
    void mostrarListaParkings(ArrayList<Parking> parkings);
    void mostrarAnomaliasCheckbox(ArrayList<Estadia> estadiasAnomali);
    void mensajeError(String message);
    void monitorearAnomalias();
    void listaPrecios();
    void carteleraElectronica();
}
