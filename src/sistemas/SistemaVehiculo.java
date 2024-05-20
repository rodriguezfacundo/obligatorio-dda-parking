package sistemas;

import dominio.Propietario;
import dominio.TipoVehiculo;
import dominio.Vehiculo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SistemaVehiculo {
            private ArrayList<TipoVehiculo> tiposVehiculos = new ArrayList<>();
            private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
            
            private static SistemaVehiculo instancia = new SistemaVehiculo();

            public static SistemaVehiculo getInstancia() {
                return instancia;
            }

            private SistemaVehiculo() {
            }

            public void registrarTipoVehiculo(TipoVehiculo tv){
               tiposVehiculos.add(tv);
            }
        
            public void registrarVehiculos(int cantidadARegistrar){
                Random rnd = new Random();
                Set<String> patentesGeneradas = new HashSet<>();
                ArrayList<Propietario> propietarios = Fachada.getInstancia().obtenerPropietarios();
                
                for (int i = 0; i <= cantidadARegistrar; i++) {
                    String patente = generarPatenteUnica(patentesGeneradas);
                    TipoVehiculo tipo = tiposVehiculos.get(rnd.nextInt(tiposVehiculos.size()));
                    Propietario propietario = propietarios.get(rnd.nextInt(propietarios.size()));
                    Vehiculo vehiculoNuevo = new Vehiculo(patente, tipo, propietario);
                    vehiculos.add(vehiculoNuevo);//Agrego el nuevo vehiculo en la lista de vehiculos del sistema.
                    SistemaPropietario.getInstancia().agregarVehiculoEnPropietario(propietario, vehiculoNuevo);//Agrego ese vehiculo en el propietario
                }

            }

            private static String generarPatenteUnica(Set<String> patentesGeneradas) {
                String patente;
                Random rnd = new Random();
                do {
                    patente = "MAT" + (1000 + rnd.nextInt(9000));
                } while (patentesGeneradas.contains(patente));
                patentesGeneradas.add(patente);
                return patente;
            }
            
            public ArrayList<Vehiculo> obtenerVehiciulos(){
                return this.vehiculos;
            }
        
            public TipoVehiculo obtenerTipoDeVehiculoPorNombre(String nombre){
                TipoVehiculo tipoVehiculoARetornar = null;
                int i = 0;
                while(tipoVehiculoARetornar == null || this.tiposVehiculos.size() < i){
                    if(this.tiposVehiculos.get(i).getNombre().equals(nombre)){
                        tipoVehiculoARetornar = this.tiposVehiculos.get(i);
                    }
                    i++;
                }
                return tipoVehiculoARetornar;
            }
        
}
