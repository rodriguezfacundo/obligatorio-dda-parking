package sistemas;
import dominio.Cochera;
import dominio.Estadia;
import dominio.Etiqueta;
import dominio.Parking;
import dominio.Propietario;
import dominio.Tendencia;
import dominio.TipoVehiculo;
import dominio.Vehiculo;
import java.util.ArrayList;
import observador.Observable;
import simuladortransito.Estacionable;
import simuladortransito.Transitable;

public class Fachada extends Observable{
    
    private SistemaParking sParking = SistemaParking.getInstancia();
    
    private static Fachada instancia = new Fachada();

    public static Fachada getInstancia() {
        return instancia;
    }
    
    private Fachada() {
    }
    
    public void agregarTendencia(Tendencia ten){
        sParking.agregarTendencia(ten);
    }
    public void agregarParking(String nombre, String direccion, int cantidadCocheras, Double factorDemanda, Tendencia tendencia){
        sParking.agregarParking(new Parking(nombre, direccion, cantidadCocheras, factorDemanda, tendencia));
    }
    
    public void agregarEtiquetas(Etiqueta etiqueta){
        sParking.agregarEtiqueta(etiqueta);
    }
    
    public ArrayList<Etiqueta> obtenerEtiquetas(){
        return sParking.obtenerEtiquetas();
    }
    
    public void asignarEtiquetasCocheras(){
        sParking.asignarEtiquetas(this.obtenerCocheras());
    }
    
    public ArrayList<Estacionable> obtenerCocherasEstacionables(){
        return sParking.obtenerCocherasEstacionables();
    }
    
    public ArrayList<Cochera> obtenerCocheras(){
        return sParking.obtenerCocheras();
    }
    
    public void generarPropietarios(int cantidadAGenerar){
        sParking.generarPropietarios(cantidadAGenerar);
    }
    
    public ArrayList<Propietario> obtenerPropietarios(){
        return sParking.obtenerPropietarios();
    }
    
    public void registrarTipoVehiculo(String nombre){
        sParking.registrarTipoVehiculo(new TipoVehiculo(nombre));
    }
    
    public void registrarVehiculos(){
        sParking.registrarVehiculos(this.sParking.obtenerCocheras().size() * 2);
    }
    
    public ArrayList<Transitable> obtenerVehiculosTransitables(){
        return sParking.obtenerVehiciulosTransitables();
    }
    
     public ArrayList<Vehiculo> obtenerVehiculos(){
        return sParking.obtenerVehiciulos();
    }
    
    public void asignarEtiquetasVehiculos(){
        sParking.asignarEtiquetas(this.obtenerVehiculos());
    }
    
    public void inicializarTarifasEnParking(){
        sParking.inicializarTarifasEnParking();
    }
    
    public ArrayList<Parking> obtenerParkings(){
        return sParking.obtenerParkings();
    }
    
    public Vehiculo obtenerVehiculoPorPatente(String patente){
        return sParking.obtenerVehiculoPorPatente(patente);
    }
    
    public Cochera obtenerCocheraPorCodigo(String codCochera){
        return sParking.obtenerCocheraPorCodigo(codCochera);
    }
    
   public ArrayList<Estadia> obtenerEstadias(){
        return sParking.obtenerEstadias();
    }
    
   public Tendencia obtenerTendenciaPorNombre(String nombre){
       return sParking.obtenerTendenciaPorNombre(nombre);
   }
   
   public int obtenerCantidadEstadias(){
       return sParking.obtenerCantidadEstadias();
   }    
   public double obtenerTotalFacturado(){
       return sParking.obtenerTotalFacturado();
   }

    public void agregarEstadia(Estadia estadiaNueva) {
        sParking.agregarNuevaEstadia(estadiaNueva);
    }

    public void agregarEtiquetasEnParking() {
        sParking.agregarEtiquetasParkings();
    }
}
