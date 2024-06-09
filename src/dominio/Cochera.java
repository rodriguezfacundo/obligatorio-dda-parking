package dominio;

import interfaces.IEtiquetable;
import java.util.ArrayList;
import observador.Observable;
import simuladortransito.Estacionable;
import sistemas.SistemaParking;

public class Cochera implements IEtiquetable, Estacionable {

    private static int contadorID = 1;
    private String codigo;
    private Parking parking;
    private boolean estaOcupada;
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private ArrayList<Estadia> estadias = new ArrayList<>();

    public Cochera(Parking parking) {
        this.codigo = "cochera" + this.contadorID + "-parking" + parking.getId();
        this.parking = parking;
        this.estaOcupada = false;
        this.contadorID++;
        agregarCochera();
    }
    
    //METODOS AUXILIARES
    public boolean estaOcupada() {
        return this.estaOcupada ? true : false;
    }
    public Estadia obtenerEstadiaAbierta() {
        for (Estadia est : this.estadias) {
            if (!est.getEsFinalizada()) {
                return est;
            }
        }
        return null;
    }
    public void agregarEstadia(Estadia estadia) {
        this.estadias.add(estadia);
    }
    public double obtenerTotalFacturado() {
        double total = 0;
        for (Estadia e : estadias) {
            total = total + e.getValorFacturado();
        }
        return total;
    }
    public Double obtenerTotalFacturadoMultas() {
        Double total = 0.00;
        for (Estadia e : estadias) {
            total = total + e.costoMultas();
        }
        return total;
    }
    public int obtenerCantidadEstadias() {
        return this.estadias.size();
    }
    public void agregarCochera() {
        SistemaParking.getInstancia().agregarCochera(this);
    }
    @Override
    public void agregarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.add(etiqueta);
    }
    
    //AVISAR
    public void ingreso(Vehiculo vehiculo) {
         if(this.estaOcupada()){
                Estadia estadiaAnomaliaHoudini = this.obtenerEstadiaAbierta();
                estadiaAnomaliaHoudini.anomaliaHoudini();
            } 
            Estadia estadiaNueva = new Estadia(vehiculo, this);
            this.estadias.add(estadiaNueva);
            this.parking.evaluarTendencia();
            this.parking.avisar(Observable.Eventos.INGRESO_EGRESO_ESTADIA);
    }
    public void egreso(Vehiculo vehiculo) {
        this.parking.evaluarTendencia();
        if (!this.estaOcupada()) {
            Estadia estadiaMistery = new Estadia(vehiculo, this);
            estadiaMistery.anomaliaMistery();
            this.estadias.add(estadiaMistery);
        } else {
            Estadia estadia = this.obtenerEstadiaAbierta();
            if (estadia != null && !estadia.contieneVehiculo(vehiculo)) {
                estadia.anomaliaTransportadorUno();
                Estadia estadiaTransportadorDos = new Estadia(vehiculo, this);
                estadiaTransportadorDos.anomaliaTransportadorDos();
            } else {
                estadia.finalizar();
            }
        }
        this.parking.avisar(Observable.Eventos.INGRESO_EGRESO_ESTADIA);
    }
    public void avisarAnomalia(Estadia estadiaConAnomalia){
        this.parking.avisarAnomalia(estadiaConAnomalia);
    }

    //NO SE USAN, ESTAN SOLAMENTE POR EL SIMULADOR DADO.
    @Override
    public boolean esDiscapacitado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean esElectrico() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean esEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //TO STRING
    @Override
    public String toString() {
        return codigo + " - " + (this.estaOcupada ? " OCUPADA" : " LIBRE") + " - Etiquetas: " + obtenerEtiquetasString();
    }
    private StringBuilder obtenerEtiquetasString() {
        StringBuilder etiquetasStr = new StringBuilder();
        for (Etiqueta etiqueta : etiquetas) {
            etiquetasStr.append(etiqueta.getNombre()).append(" ");
        }
        return etiquetasStr;
    }
    
    //GETTERS Y SETTERS
    public void setOcupada(boolean estaOcupada) {this.estaOcupada = estaOcupada;}
    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
    public ArrayList<Estadia> getEstadias() {return this.estadias;}
    public Parking getParking() {return this.parking;}
    public ArrayList<Etiqueta> getEtiquetas() {return this.etiquetas;}
}
