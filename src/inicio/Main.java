package inicio;

import dominio.SensorDePrueba;
import java.util.ArrayList;
import simuladorIU.SimuladorIU;
import simuladortransito.Estacionable;
import simuladortransito.Transitable;


public class Main {
    public static void main(String[] args) {
         
        Data data = Data.getInstancia();
        data.cargarData();
        SensorDePrueba sensor= new SensorDePrueba();
        ArrayList<Estacionable> cocheras = data.obtenerCocheras();
        ArrayList<Transitable> vehiculos = data.obtenerVehiculos();
        new SimuladorIU(null, false,sensor,cocheras,vehiculos).setVisible(true);
    }
}