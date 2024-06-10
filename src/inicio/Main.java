package inicio;

import dominio.SensorEstadia;
import java.util.ArrayList;
import simuladorIU.SimuladorIU;
import simuladortransito.ConfiguracionException;
import simuladortransito.Estacionable;
import simuladortransito.Transitable;
import Interfaz.Menu;

public class Main {
    
    public static void main(String[] args) throws ConfiguracionException {
        Data data = Data.getInstancia();
        data.cargarData();
        new Menu().setVisible(true);
        SensorEstadia sensor= new SensorEstadia();
        ArrayList<Estacionable> cocheras = data.obtenerEstacionables();
        ArrayList<Transitable> vehiculos = data.obtenerTransitables();
        new SimuladorIU(null, false,sensor,cocheras,vehiculos).setVisible(true);
    }
}