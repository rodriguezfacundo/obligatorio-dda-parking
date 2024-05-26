package dominio;

import java.util.ArrayList;
import java.util.Date;

public class Estadia {
    private Date entrada;
    private Date salida;
    private Vehiculo vehiculo;
    private Cochera cochera;
    private double valorFacturado;
    private boolean esFinalizada;
    private Anomalia anomalia;
    private TemporizadorUT temporizadorUT;
    private double precioBase;
    private ArrayList<Multa> multas = new ArrayList<>();
    
    public Estadia(Vehiculo vehiculo, Cochera cochera){
        this.cochera = cochera;
        this.vehiculo = vehiculo;
        this.temporizadorUT = new TemporizadorUT();
        this.temporizadorUT.start();
        this.precioBase = obtenerPrecioBase(vehiculo.getTipo(), cochera.getParking());
    }
    
    public Estadia(Vehiculo vehiculo){
        this.cochera = null;
        this.vehiculo = vehiculo;
        this.temporizadorUT = new TemporizadorUT();
        this.temporizadorUT.start();
        setEsFinalizada(true);
    }
    
    public double getValorFacturado() {
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
            this.salida = new Date();
            temporizadorUT.stop();
            if(this.cochera!=null){
                this.cochera.setOcupada(false);
                this.cochera.getParking().sumarUnEgreso();
            }
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
    
    public Anomalia getAnomalia(){
        return this.anomalia;
    }
    
    public void setAnomalia(Anomalia anomalia){
        this.anomalia = anomalia;
    }
    
    public void setFechaEntrada(Date entrada){
        this.entrada = entrada;
    }

    public void setFechaSalida(Date salida) {
        this.salida = salida;
    }
    
    public int getCantidadUT(){
        return this.temporizadorUT.getUT();
    }
    
    public void calcularValorFacturado() {
        //Valor de estadía = ( PB * <UT> * FD ) [+ M]
        this.valorFacturado = (this.precioBase * this.getCantidadUT() * this.valorFactorDemanda()) + this.costoMultas();
    }
    
    public boolean tieneMultas(){
        if(this.multas.size()>0){
            return true;
        }else{
            return false;
        }
    }
    
    public Double costoMultas(){
        Double total = 0d;
        if(tieneMultas()){
            for(Multa multa:this.multas){
                total+= multa.valorMulta(this.getCantidadUT(), this.valorFacturado);
            }
        }
        return total;
    }
    
    public void verificarSiEsMultable(){
        if(this.anomalia != null){
         for (Etiqueta etiqueta : this.cochera.getEtiquetas()) {
            if (!this.getVehiculo().tieneEtiqueta(etiqueta.getClass())) {
                this.multas.add(new Multa(etiqueta, this));//Si es multable va a agregar esas nuevas multas a la estadia asociada.
            }
        }   
        }
    }
    
    private double obtenerPrecioBase(TipoVehiculo tipoVehiculo, Parking parking) {
        return parking.getPrecioTipoVehiculo(tipoVehiculo);
    }
    
    private double valorFactorDemanda(){
        return this.cochera.getParking().obtenerValorFactorDemanda(this.getCantidadUT());
    }
}
