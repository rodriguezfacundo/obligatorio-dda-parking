package sistemas;

import dominio.Anomalia;
import dominio.Cochera;
import dominio.Estadia;
import dominio.Vehiculo;
import java.util.ArrayList;
import java.util.Date;
import simuladortransito.Estacionable;
import simuladortransito.Sensor;
import simuladortransito.Transitable;

public class SistemaEstadia implements Sensor {
    private ArrayList<Estadia> estadias = new ArrayList<>();
        
    public ArrayList<Estadia> obtenerEstadias(){
        return this.estadias;
    }
    
    public Estadia obtenerEstadiaAbiertaPorCochera(String codigoCochera){
        for(Estadia est:this.estadias){
            if(est.getCochera().getCodigo().equals(codigoCochera) && !est.getEsFinalizada()){
                return est;
            }
        }
        return null;
    }

    @Override
    public void ingreso(Transitable transitable, Estacionable estacionable) {
        Vehiculo vehiculo = (Vehiculo)transitable;
         Cochera cochera =(Cochera)estacionable;
        Estadia estadiaNueva = new Estadia(vehiculo, cochera, 0.1, 1);
        if(cochera.estaOcupada()){
            Estadia estadiaSinAvisarFinalizacion = cochera.getEstadiaSinFinalizar();
            setEstadiaErrorHoudini(estadiaSinAvisarFinalizacion);
        } 
        setDatosEstadiaNueva(estadiaNueva, true, false);
        agregarNuevaEstadia(estadiaNueva);
        Fachada.getInstancia().avisar(Fachada.Eventos.entraVehiculo);
    }

    @Override
    public void egreso(Transitable transitable, Estacionable estacionable) {
        Vehiculo vehiculo = (Vehiculo)transitable;
         Cochera cochera =(Cochera)estacionable;
        if(cochera!=null && !cochera.estaOcupada()){
            Estadia estadiaMistery = new Estadia(vehiculo, cochera);
            setDatosEstadiaNueva(estadiaMistery, false, true);
            setEstadiaErrorMistery(estadiaMistery);
            agregarNuevaEstadia(estadiaMistery);
            Fachada.getInstancia().avisar(Fachada.Eventos.saleVehiculo);
        }
        if(cochera!=null && cochera.estaOcupada()){
            Estadia estadia = obtenerEstadiaAbiertaPorCochera(cochera.getCodigo());
            if(estadia != null && !estadia.getVehiculo().getPatente().equals(vehiculo.getPatente())){
                setEstadiaErrorTransportador1(estadia);
                //setEstadiaErrorTransportador2(estadia);
            } else{
                estadia.verificarSiEsMultable();
                estadia.setEsFinalizada(true);
                estadia.calcularValorFacturado();
            }
            Fachada.getInstancia().avisar(Fachada.Eventos.saleVehiculo);
        }
    }
    
    private void setDatosEstadiaNueva(Estadia estadiaNueva, boolean cocheraOcupada, boolean esFinalizada) {
        if(estadiaNueva != null){
            estadiaNueva.setCocheraOcupada(cocheraOcupada);
            estadiaNueva.setFechaEntrada(new Date());
            estadiaNueva.setEsFinalizada(esFinalizada);
            estadiaNueva.getCochera().agregarEstadia(estadiaNueva);
        }
    }
    
    private void setEstadiaErrorHoudini(Estadia estadiaSinAvisarFinalizacion) {
        if(estadiaSinAvisarFinalizacion != null){
            estadiaSinAvisarFinalizacion.setEsFinalizada(true);
            estadiaSinAvisarFinalizacion.setFechaSalida(null);
            Anomalia anomalia = new Anomalia("HOUDINI", estadiaSinAvisarFinalizacion);
            estadiaSinAvisarFinalizacion.setAnomalia(anomalia);
        }
    }
    
    private void setEstadiaErrorMistery(Estadia estadiaConCocheraLibre) {
        if(estadiaConCocheraLibre != null){
            Anomalia anomalia = new Anomalia("MISTERY", estadiaConCocheraLibre);
            estadiaConCocheraLibre.setAnomalia(anomalia);
        }
    }
    
    public void agregarNuevaEstadia(Estadia estadiaNueva){
        this.estadias.add(estadiaNueva);
    }

    private void setEstadiaErrorTransportador1(Estadia estadia) {
        if(estadia != null){
            Anomalia anomalia = new Anomalia("TRANSPORTADOR1", estadia);
            estadia.setAnomalia(anomalia);
        }
    }

    //Queda revisar bien porque la letra dice
    //otra anomalía asociada al vehículo que acaba de identificarse como saliente con el código
    //“TRANSPORTADOR2”
    private void setEstadiaErrorTransportador2(Estadia estadia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
