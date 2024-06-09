package inicio;

import dominio.EtiquetaDiscapacitado;
import dominio.EtiquetaElectrico;
import dominio.EtiquetaEmpleado;
import dominio.Parking;
import dominio.Propietario;
import dominio.TendenciaEstable;
import dominio.TendenciaNegativa;
import dominio.TendenciaPositiva;
import java.util.ArrayList;
import java.util.Random;
import simuladortransito.Estacionable;
import simuladortransito.Transitable;
import sistemas.Fachada;


public class Data {
         private static Data instancia = new Data();

        public static Data getInstancia() {
        return instancia;
        }
    
        private Fachada fachada = Fachada.getInstancia();
        
        //SE AGREGAN LAS TENDENCIAS AL SISTEMA
        public void agregarTendencias(){
                fachada.agregarTendencia(new TendenciaEstable());
                fachada.agregarTendencia(new TendenciaNegativa());
                fachada.agregarTendencia(new TendenciaPositiva());
         }
        
        //Se registran dos parkings
        public  void agregarParkings(){
            fachada.agregarParking("ParkGuardián", "Av. Italia 2500", 75, 1.0, fachada.obtenerTendenciaPorNombre("Estable"));
            fachada.agregarParking("ParkCordón", "Av. 18 de Julio 1500", 66,1.0,fachada.obtenerTendenciaPorNombre("Estable"));
        }
        
        //SE AGREGAN ETIQUETAS
        public void agregarEtiquetas(){
            fachada.agregarEtiquetas(new EtiquetaDiscapacitado());
            fachada.agregarEtiquetas(new EtiquetaElectrico());
            fachada.agregarEtiquetas(new EtiquetaEmpleado());
        }
        //SE ASIGNAN LAS ETIQUETAS A LOS PARKINGS
        public void agregarEtiquetasParkings(){
            fachada.agregarEtiquetasEnParking();
        }
        
        //Se generan 50 propietarios
        public void generarPropietarios (){
            fachada.generarPropietarios(50);
        }
        
        //Se asignan la cantidad solicitada de etiquetas a las cocheras (20%)
        public void asignarEtiquetasCocheras(){
            fachada.asignarEtiquetasCocheras();
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
        
        //Se utiliza solamente para ver los propietarios creados
         public ArrayList<Propietario> obtenerPropietarios (){
             return fachada.obtenerPropietarios();
         }
        
        //Se utiliza solamente para ver  los parkings creados
         public ArrayList<Parking> obtenerParkings(){
             return fachada.obtenerParkings();
        }
         
         public ArrayList<Transitable> obtenerTransitables(){
             return fachada.obtenerVehiculosTransitables();
         }
         
         public ArrayList<Estacionable> obtenerEstacionables(){
             return fachada.obtenerCocherasEstacionables();
         }
         
         public void cargarData(){
             this.agregarParkings();
             this.agregarTendencias();
             this.agregarEtiquetas();
             this.generarPropietarios();
             this.asignarEtiquetasCocheras();
             this.agregarEtiquetasParkings();
             this.registrarTipoVehiculo();
             this.registrarVehiculos();
             this.asignarEtiquetasVehiculos();
             this.inicializarTarifasEnParking();
         }
}
