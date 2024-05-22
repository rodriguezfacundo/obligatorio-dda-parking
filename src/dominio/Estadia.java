package dominio;

import java.util.ArrayList;
import java.util.Date;

public class Estadia {
    private Date entrada;
    private Date salida;
    private Vehiculo vehiculo;
    private Cochera cochera;
    private float valorFacturado;
    private boolean esFinalizada;
    private ArrayList<Anomalia> anomalias = new ArrayList<>();

    /*public Estadia(Date fechaEntrada, int horaEntrada, Date fechaSalida, int horaSalida, float valorFacturado) {
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.valorFacturado = valorFacturado;
    }*/
    
    public Estadia(Vehiculo vehiculo, Cochera cochera){
        this.cochera = cochera;
        this.vehiculo = vehiculo;
    }
    
    public float getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(float valorFacturado) {
        this.valorFacturado = valorFacturado;
    }
    
     public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cochera getCochera() {
        return cochera;
    }
    
    public Date getEntrada() {
        return entrada;
    }

    public Date getSalida() {
        return salida;
    }
    
    public void setEsFinalizada(boolean esFinalizada){
        if(!esFinalizada){
            this.esFinalizada = false;
        }else{
            this.esFinalizada = true;
            this.cochera.setOcupada(false);
            this.salida = new Date();
        }
    }
    public boolean getEsFinalizada(){
        return this.esFinalizada;
    }
    
        public String getEsFinalizadaToString(){
        if(!this.esFinalizada){
            return "ABIERTA";
        } else{
            return "FINALIZADA";
        }
    }
    public void setCocheraOcupada(boolean estaOcupada){
        this.cochera.setOcupada(estaOcupada);
    }
    
    public ArrayList<Anomalia> getAnomalias(){
        return this.anomalias;
    }
    
    public void agregarAnomalia(Anomalia anomalia){
        this.anomalias.add(anomalia);
    }
    
    public void setFechaEntrada(Date entrada){
        this.entrada = entrada;
    }

    public void setFechaSalida(Date salida) {
        this.salida = salida;
    }

    public String getAnomaliasAsignadas() {
        String anomalias = "";
        for(Anomalia a:this.anomalias){
            anomalias += " | " + a.getCodigoError();
        }
        return anomalias;
    }
}
