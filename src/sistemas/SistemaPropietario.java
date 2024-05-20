package sistemas;

import dominio.Propietario;
import dominio.Vehiculo;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class SistemaPropietario {
        private ArrayList<Propietario> propietarios = new ArrayList<>();
        
        private static SistemaPropietario instancia = new SistemaPropietario();

        public static SistemaPropietario getInstancia() {
            return instancia;
        }

        private SistemaPropietario() {
        }
        
        public void generarPropietarios(int cantidadAGenerar){
            while(this.propietarios.size() < cantidadAGenerar){
                String nombre = generarNombre();
                String cedula = generarCedula();
                double saldoCuentaCorriente = generarSaldo();
                Propietario propietario = new Propietario(cedula, nombre, saldoCuentaCorriente);
                propietarios.add(propietario);
            }
        }
    
        private String generarNombre() {
            String[] nombres = {"Juan", "María", "Pedro", "Luis", "Ana", "Carlos", "Laura", "Diego", "Sofía", "Andrés"};
            String[] apellidos = {"Pérez", "Gómez", "García", "Martínez", "Fernández", "López", "Díaz", "Rodríguez", "Sánchez", "Romero"};

            Random rnd = new Random();
            String nombre = nombres[rnd.nextInt(nombres.length)];
            String apellido = apellidos[rnd.nextInt(apellidos.length)];

            return nombre + " " + apellido;
        }

        private String generarCedula() {
            Random rnd = new Random();
            StringBuilder cedula = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                cedula.append(rnd.nextInt(10));
            }
            return cedula.toString();
         }
        

        private double generarSaldo() {
            Random rnd = new Random();
            DecimalFormat df = new DecimalFormat("#.00");
            return Double.parseDouble(df.format(rnd.nextDouble() * 110 - 10));
        }
        
        public ArrayList<Propietario> obtenerPropietarios(){
            return this.propietarios;
        }
        
        public void agregarVehiculoEnPropietario(Propietario propietario, Vehiculo vehiculo){
            propietario.agregarVehiculo(vehiculo);
        }

}
