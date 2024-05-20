package inicio;

import dominio.Cochera;
import dominio.Parking;
import dominio.Propietario;
import dominio.Vehiculo;
import java.util.ArrayList;
import simuladortransito.Estacionable;
import simuladortransito.Transitable;
import sistemas.Fachada;


public class Data {
         private static Data instancia = new Data();

        public static Data getInstancia() {
        return instancia;
        }
    
        private Fachada fachada = Fachada.getInstancia();
        
        //Se registran dos parkings
        public  void agregarParkings(){
            fachada.agregarParking("ParkGuardián", "Av. Italia 2500", 75);
            fachada.agregarParking("ParkCordón", "Av. 18 de Julio 1500", 66);
        }
       
        
        public void agregarEtiquetas(){
            fachada.agregarEtiqueta("Discapacitado");
            fachada.agregarEtiqueta("Eléctrico");
            fachada.agregarEtiqueta("Empleado");
        }

        
        //Se generan 50 propietarios
        public void generarPropietarios (){
            fachada.generarPropietarios(50);
        }
        
        //Se asignan la cantidad solicitada de etiquetas a las cocheras (20%)
        public void asignarEtiquetasCocheras(){
            fachada.asignarEtiquetasCocheras();
        }
        
        //Se utiliza solamente para ver las cocheras creadas y que estan disponibles para estacionar
        public ArrayList<Estacionable> obtenerCocheras(){
            ArrayList<Estacionable> estacionablesToReturn = new ArrayList<>();
            for(Cochera cochera:fachada.obtenerCocheras() ){
                               estacionablesToReturn.add(cochera);

            }
            return estacionablesToReturn;
        }

        //Se registran los tipos de vehiculos solicitados.
        public void registrarTipoVehiculo (){
            fachada.registrarTipoVehiculo("Motocicleta");
            fachada.registrarTipoVehiculo("Standard");
            fachada.registrarTipoVehiculo("Carga");
            fachada.registrarTipoVehiculo("Pasajeros");
        }
       
        
        //Se registran los vehiculos (el doble de vehículos que total de cocheras considerando todos los parkings)
        public void registrarVehiculos(){
                    fachada.registrarVehiculos();
        }
        //Se asignan la cantidad solicitada de etiquetas a los vehiculos (20%)
        public void asignarEtiquetasVehiculos(){
                    fachada.asignarEtiquetasVehiculos();
        }
        
        public void inicializarTarifasEnParking(){
                    fachada.inicializarTarifasEnParking();
        }
        
        //Solamente para ver los vehiculos registrados
        public ArrayList<Transitable> obtenerVehiculos(){
           ArrayList<Transitable> transitablesToReturn = new ArrayList<>();
            for(Vehiculo vehiculo:fachada.obtenerVehiculos() ){
                               transitablesToReturn.add(vehiculo);

            }
            return transitablesToReturn;
        }

        //Se utiliza solamente para ver los propietarios creados
         public ArrayList<Propietario> obtenerPropietarios (){
             return fachada.obtenerPropietarios();
         }
        
        //Se utiliza solamente para ver  los parkings creados
         public ArrayList<Parking> obtenerParkings(){
             return fachada.obtenerParkings();
        }
         
         public void cargarData(){
             this.agregarParkings();
             this.agregarEtiquetas();
             this.generarPropietarios();
             this.asignarEtiquetasCocheras();
             this.registrarTipoVehiculo();
             this.registrarVehiculos();
             this.asignarEtiquetasVehiculos();
             this.inicializarTarifasEnParking();
         }
        



}
