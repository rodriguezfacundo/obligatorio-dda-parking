package controlador;

import dominio.Tarifa;
import java.util.ArrayList;

public interface VistaCarteleraElectronica {
     void mostrarTitulo(String titulo);
     void mostrarPrecios(ArrayList<Tarifa> tarifas);
     void mostrarDisponibilidad(Long cantidad);
}
