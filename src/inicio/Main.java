package inicio;

import iuparking.UIIngresoEgresoVehiculo;
import simuladorIU.SimuladorIU;

public class Main {
    public static void main(String[] args) {
         
        Data data = Data.getInstancia();
        data.cargarData();
        //SensorDePrueba sensor= new SensorDePrueba();
        //ArrayList<Estacionable> cocheras = data.obtenerCocheras();
        //ArrayList<Transitable> vehiculos = data.obtenerVehiculos();
       // new SimuladorIU(null, false,sensor,cocheras,vehiculos).setVisible(true);
        new UIIngresoEgresoVehiculo().setVisible(true);
        new UIIngresoEgresoVehiculo().setVisible(true);

    }
}