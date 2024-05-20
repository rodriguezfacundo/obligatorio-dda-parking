package sistemas;
import dominio.Cochera;
import dominio.Etiqueta;
import dominio.Parking;
import dominio.Propietario;
import dominio.TipoVehiculo;
import dominio.Vehiculo;
import java.util.ArrayList;

public class Fachada {
    private SistemaParking sParking = new SistemaParking();
    private SistemaEtiqueta sEtiqueta = new SistemaEtiqueta();
    private SistemaCochera sCochera = SistemaCochera.getInstancia();
    private SistemaPropietario sPropietario = SistemaPropietario.getInstancia();
    private SistemaVehiculo sVehiculo = SistemaVehiculo.getInstancia();

    
    private static Fachada instancia = new Fachada();

    public static Fachada getInstancia() {
        return instancia;
    }
    
    private Fachada() {
    }
    
    public void agregarParking(String nombre, String direccion, int cantidadCocheras){
        sParking.agregarParking(new Parking(nombre, direccion, cantidadCocheras));
    }
    
    public void agregarEtiqueta(String nombre){
        sEtiqueta.agregarEtiquetea(new Etiqueta(nombre));
    }
    
    public ArrayList<Etiqueta> obtenerEtiquetas(){
        return sEtiqueta.obtenerEtiquetas();
    }
    
    public void asignarEtiquetasCocheras(){
        sEtiqueta.asignarEtiquetas(this.obtenerCocheras());
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
    
}
