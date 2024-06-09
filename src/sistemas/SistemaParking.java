package sistemas;

import dominio.Cochera;
import dominio.Estadia;
import dominio.Etiqueta;
import dominio.Parking;
import dominio.Propietario;
import dominio.Tarifa;
import dominio.Tendencia;
import dominio.TipoVehiculo;
import dominio.Vehiculo;
import interfaces.IEtiquetable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import simuladortransito.Estacionable;
import simuladortransito.Transitable;


public class SistemaParking <T extends IEtiquetable> {
    
        private ArrayList<Parking> parkings = new ArrayList<>();
        private ArrayList<Tendencia> tendencias = new ArrayList<>();
        private ArrayList<Cochera> cocheras = new ArrayList<>();
        private ArrayList<Estadia> estadias = new ArrayList<>();
        private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        private ArrayList<Propietario> propietarios = new ArrayList<>();
        private ArrayList<TipoVehiculo> tiposVehiculos = new ArrayList<>();
        private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    private static SistemaParking instancia = new SistemaParking();

    //METODOS DE PARKING
    public static SistemaParking getInstancia() {
        return instancia;
    }
    public  void agregarParking(Parking parking){
        parkings.add(parking);
    }
    public ArrayList<Parking> obtenerParkings(){
        return parkings;
    }    
    public void inicializarTarifasEnParking(){
        for(Parking park:this.parkings){
            ArrayList<Tarifa> tarifas = new ArrayList<>();
            tarifas.add(new Tarifa(0.05, this.obtenerTipoDeVehiculoPorNombre("Motocicleta")));
            tarifas.add(new Tarifa(0.1,  this.obtenerTipoDeVehiculoPorNombre("Standard")));
            tarifas.add(new Tarifa(0.1, this.obtenerTipoDeVehiculoPorNombre ("Carga")));
            tarifas.add(new Tarifa(0.1,  this.obtenerTipoDeVehiculoPorNombre("Pasajeros")));
            park.setTarifas(tarifas);
        }
    }
    public double obtenerTotalFacturado(){
        double total = 0;
      for(Parking p:parkings){
           total = total + p.obtenerTotalFacturado();
       }
       return total;
   }
    
    //TENDENCIAS
    public void agregarTendencia(Tendencia ten) {
        this.tendencias.add(ten);
    }
    public ArrayList<Tendencia> getTendencias(){
        return this.tendencias;
    }
    Tendencia obtenerTendenciaPorNombre(String nombre) {
        for(Tendencia tendencia:this.tendencias){
            if(tendencia.getNombre().equals(nombre)){
                return tendencia;
            }
        }
        return null;
    }
    public void evaluarTendencia(Parking parking) {
        for(Tendencia ten: this.tendencias){
                ten.evaluarTendencia(parking);
        }
    }
    
    //ESTADIAS
    public int obtenerCantidadEstadias(){
       int cantidad = 0;
       for(Parking p:parkings){
           cantidad = cantidad + p.obtenerCantidadEstadias();
       }
       return cantidad;
   }
    
   //COCHERAS
    public void agregarCochera(Cochera cochera) {
        cocheras.add(cochera);
    }
    public ArrayList<Cochera> obtenerCocheras(){
        return this.cocheras;
    }
    ArrayList<Estacionable> obtenerCocherasEstacionables() {
        ArrayList<Estacionable> estacionables = new ArrayList<>();
        for(Cochera cochera:this.cocheras){
            if(!cochera.estaOcupada()){
                estacionables.add((Estacionable) cochera);
            }
        }
        return estacionables;
    }
    
    //ESTADIAS
    public ArrayList<Estadia> obtenerEstadias(){
        return this.estadias;
    }
    
    //ETIQUETAS
    public void agregarEtiqueta(Etiqueta etiqueta){
        etiquetas.add(etiqueta);
    }
    public ArrayList<Etiqueta> obtenerEtiquetas(){
        return this.etiquetas;
    }
      public void agregarEtiquetasParkings(){
        for(Parking parking: this.parkings){
            parking.setEtiquetas(this.etiquetas);
        }
    }
    public void asignarEtiquetas(ArrayList<T> listaEntidad) {
        int cantidad = listaEntidad.size() * 20 / 100; // el 20% de la lista que reciba por parametro debera tener etiquetas
        Collections.shuffle(listaEntidad);//Para que me desordene la lista
        for (int i = 0; i < cantidad; i++) {
            T entidad = listaEntidad.get(i);
            if (!this.etiquetas.isEmpty()) {
                int indiceEtiqueta = (int) (Math.random() * this.etiquetas.size());
                entidad.agregarEtiqueta(this.etiquetas.get(indiceEtiqueta));
            }
        }
    }
    
    //PROPIETARIOS
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
        
    //VEHICULOS Y TIPO VEHICULOS
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
                this.agregarVehiculoEnPropietario(propietario, vehiculoNuevo);//Agrego ese vehiculo en el propietario
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
    public Vehiculo obtenerVehiculoPorPatente(String patente) {
        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equals(patente)) {
                    return v;
            }
        }
        return null;
    }
    ArrayList<Transitable> obtenerVehiciulosTransitables() {
        ArrayList<Transitable> transitables = new ArrayList<>();
        for(Vehiculo vehiculo:this.vehiculos){
            if(!vehiculo.estaEstacionado()){
                transitables.add((Transitable) vehiculo);
            }
        }
        return transitables;
    }
}
