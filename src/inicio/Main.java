package inicio;

import dominio.Cochera;
import dominio.Vehiculo;
import vistaParking.UIIngresoEgresoVehiculo;
import java.util.ArrayList;
import simuladortransito.ConfiguracionException;
import simuladortransito.Estacionable;
import simuladortransito.FlujoIngreso;
import simuladortransito.Periodo;
import simuladortransito.Sensor;
import simuladortransito.SimuladorTransito;
import simuladortransito.Transitable;
import sistemas.Fachada;
import vistaParking.Menu;

public class Main {
    private static SimuladorTransito simulador = SimuladorTransito.getInstancia();
    
    public static void main(String[] args) throws ConfiguracionException {
        Data data = Data.getInstancia();
        data.cargarData();
        //Fachada fachada = Fachada.getInstancia();
        /*ArrayList<Estacionable> cocheras = data.obtenerEstacionables();
        ArrayList<Transitable> vehiculos = data.obtenerTransitables();
        simulador.addEstacionables(cocheras);
        simulador.addTransitables(vehiculos);
        
        try{
            simulador.programar(new FlujoIngreso("Ingreso matutino", new Periodo(0,5), 20));
            simulador.iniciar(new Sensor(){
                @Override
                public void ingreso(Transitable transitable, Estacionable estacionable) {
                    Vehiculo vehiculo = (Vehiculo) transitable;
                    Cochera cochera = (Cochera) estacionable;
                }

                @Override
                public void egreso(Transitable transitable, Estacionable estacionable) {
                   //fachada.finalizarEstadia(transitable.getPatente(), estacionable.getCodigo());
                }
                
            });
        } catch (ConfiguracionException ex){
            
        }*/
        //new Menu().setVisible(true);
        new UIIngresoEgresoVehiculo().setVisible(true);
    }
}