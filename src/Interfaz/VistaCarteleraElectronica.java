package Interfaz;

import dominio.Etiqueta;
import dominio.Tarifa;
import java.util.ArrayList;
import java.util.Map;

public interface VistaCarteleraElectronica {
     void mostrarTitulo(String titulo);
     void mostrarPrecios(ArrayList<Tarifa> tarifas);
     void mostrarDisponibilidad(Long cantidad);
     void mostrarDisponibilidadCocheraPorEtiqueta(Map<String, Integer> etiquetas);
}
