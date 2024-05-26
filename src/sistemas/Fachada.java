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
    
    public enum Eventos{entraVehiculo, saleVehiculo};
    
    private SistemaParking sParking = new SistemaParking();
    private SistemaEtiqueta sEtiqueta = new SistemaEtiqueta();
    private SistemaCochera sCochera = SistemaCochera.getInstancia();
    private SistemaPropietario sPropietario = SistemaPropietario.getInstancia();
    private SistemaVehiculo sVehiculo = SistemaVehiculo.getInstancia();
    private SistemaEstadia sEstadia = new SistemaEstadia();

    
    private static Fachada instancia = new Fachada();

    public static Fachada getInstancia() {
        return instancia;
    }
    
    private Fachada() {
    }
    
    public void agregarParking(String nombre, String direccion, int cantidadCocheras, Double factorDemanda){
        sParking.agregarParking(new Parking(nombre, direccion, cantidadCocheras, factorDemanda));
    }
    
    public void agregarEtiquetas(Etiqueta etiqueta){
        sEtiqueta.agregarEtiqueta(etiqueta);
    }
    
    public ArrayList<Etiqueta> obtenerEtiquetas(){
        return sEtiqueta.obtenerEtiquetas();
    }
    
    public void asignarEtiquetasCocheras(){
        sEtiqueta.asignarEtiquetas(this.obtenerCocheras());
    }
    
    public ArrayList<Estacionable> obtenerCocherasEstacionables(){
        return sCochera.obtenerCocherasEstacionables();
    }
    
    public ArrayList<Cochera> obtenerCocheras(){
        return sCochera.obtenerCocheras();
    }
    
    public void generarPropietarios(int cantidadAGenerar){
        sPropietario.generarPropietarios(cantidadAGenerar);
    }
    
    public ArrayList<Propietario> obtenerPropietarios(){
        return sPropietario.obtenerPropietarios();
    }
    
    public void registrarTipoVehiculo(String nombre){
        sVehiculo.registrarTipoVehiculo(new TipoVehiculo(nombre));
    }
    
    public void registrarVehiculos(){
        sVehiculo.registrarVehiculos(this.sCochera.obtenerCocheras().size() * 2);
    }
    
    public ArrayList<Transitable> obtenerVehiculosTransitables(){
        return sVehiculo.obtenerVehiciulosTransitables();
    }
    
     public ArrayList<Vehiculo> obtenerVehiculos(){
        return sVehiculo.obtenerVehiciulos();
    }
    
    public void asignarEtiquetasVehiculos(){
        sEtiqueta.asignarEtiquetas(this.obtenerVehiculos());
    }
    
    public void inicializarTarifasEnParking(){
        sParking.inicializarTarifasEnParking();
    }
    
    public ArrayList<Parking> obtenerParkings(){
        return sParking.obtenerParkings();
    }
    
    public Vehiculo obtenerVehiculoPorPatente(String patente){
        return sVehiculo.obtenerVehiculoPorPatente(patente);
    }
    
    public Cochera obtenerCocheraPorCodigo(String codCochera){
        return sCochera.obtenerCocheraPorCodigo(codCochera);
    }
    
    public void iniciarEstadia(String patenteVehiculo, String codigoCochera){
       sEstadia.ingreso(obtenerVehiculoPorPatente(patenteVehiculo), obtenerCocheraPorCodigo(codigoCochera));
    }
    
    public void finalizarEstadia(String patenteVehiculo, String codigoCochera) {
        sEstadia.egreso(obtenerVehiculoPorPatente(patenteVehiculo), obtenerCocheraPorCodigo(codigoCochera));
    }
    
    public void agregarTendencia(Tendencia ten){
        sParking.agregarTendencia(ten);
    }
    
   public ArrayList<Estadia> obtenerEstadias(){
        return sEstadia.obtenerEstadias();
    }
    
}
