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

    @Override
    public void ingreso(Transitable transitable, Estacionable estacionable) {
        Vehiculo vehiculo = (Vehiculo)transitable;
         Cochera cochera =(Cochera)estacionable;
        Estadia estadiaNueva = new Estadia(vehiculo, cochera);
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
            if(estadia != null && !coincideVehiculoConEstadia(estadia, vehiculo.getPatente())){
                setEstadiaErrorTransportador1(estadia);
                Estadia estadiaTransportador2 = new Estadia(vehiculo);
                setEstadiaErrorTransportador2(estadiaTransportador2);
            } else{
                estadia.verificarSiEsMultable();
                estadia.setEsFinalizada(true);
                estadia.calcularValorFacturado();
            }
            Fachada.getInstancia().avisar(Fachada.Eventos.saleVehiculo);
        }
    }
    
    public Estadia obtenerEstadiaAbiertaPorCochera(String codigoCochera){
        for(Estadia est:this.estadias){
            if(est.getCochera().getCodigo().equals(codigoCochera) && !est.getEsFinalizada()){
                return est;
            }
        }
        return null;
    }
    
    public void agregarNuevaEstadia(Estadia estadiaNueva){
        this.estadias.add(estadiaNueva);
    }
    
    private boolean coincideVehiculoConEstadia(Estadia estadia, String patenteVehiculo){
        if(estadia.getVehiculo().getPatente().equals(patenteVehiculo)){
            return true;
        }   else{
            return false;
        }
    }
    
    private void setDatosEstadiaNueva(Estadia estadiaNueva, boolean cocheraOcupada, boolean esFinalizada) {
        if(estadiaNueva != null){
            estadiaNueva.setCocheraOcupada(cocheraOcupada);
            estadiaNueva.setFechaEntrada(new Date());
            estadiaNueva.setEsFinalizada(esFinalizada);
            estadiaNueva.getCochera().agregarEstadia(estadiaNueva);
            estadiaNueva.getCochera().getParking().sumarUnIngreso();
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
            estadiaConCocheraLibre.getCochera().getParking().sumarUnEgreso();
        }
    }
    
     private void setEstadiaErrorTransportador1(Estadia estadia) {
        if(estadia != null){
            Anomalia anomalia = new Anomalia("TRANSPORTADOR1", estadia);
            estadia.setAnomalia(anomalia);
        }
    }

    private void setEstadiaErrorTransportador2(Estadia estadia) {
        if(estadia != null){
            Anomalia anomalia = new Anomalia("TRANSPORTADOR2", estadia);
            estadia.setAnomalia(anomalia);
            this.agregarNuevaEstadia(estadia);
        }
    }
}
