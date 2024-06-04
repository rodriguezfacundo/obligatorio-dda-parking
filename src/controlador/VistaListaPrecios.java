package controlador;

import dominio.Tarifa;
import java.util.ArrayList;

public interface VistaListaPrecios {
    void mostrarTitulo(String titulo);
    void mostrarPrecios(ArrayList<Tarifa> tarifas);
    void guardar();
    void cancelar();
    void mensajeError(String msg);
}
