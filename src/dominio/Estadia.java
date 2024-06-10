package dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Estadia{
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
        this.entrada = new Date();
        this.temporizadorUT = new TemporizadorUT();
        this.temporizadorUT.start();
        this.precioBase = obtenerPrecioBase(vehiculo.getTipo(), cochera.getParking());
        this.esFinalizada = false;
        this.cochera.setOcupada(true);
        this.vehiculo.setEstacionado(true);
    }
    
    //METODOS AUXILIARES
    public void calcularValorFacturado() {
        //Valor de estadía = ( PB * <UT> * FD ) [+ M]
        this.valorFacturado = (this.precioBase * this.getCantidadUT() * this.valorFactorDemanda()) + this.costoMultas();
    }
    //Verifica que si la estadia tuvo multas o no.
    public boolean tieneMultas(){
        if(this.multas.size()>0){
            return true;
        }else{
            return false;
        }
    }
    //Recorre las multas y va sumando su importe.
    public Double costoMultas(){
        Double total = 0d;
        if(tieneMultas()){
            for(Multa multa:this.multas){
                total+= multa.valorMulta(this.getCantidadUT(), this.valorFacturado);
            }
        }
        return total;
    }
    //Verifica si es multable y en caso de serlo agrega esa nueva multa a la estadia.
    public void verificarSiEsMultable(){
        if(this.anomalia == null){
         for (Etiqueta etiqueta : this.cochera.getEtiquetas()) {
            if (!this.getVehiculo().tieneEtiqueta(etiqueta.getClass())) {
                this.multas.add(new Multa(etiqueta, this));
            }
        }   
        }
    }
    //Obtiene el precio base
    private double obtenerPrecioBase(TipoVehiculo tipoVehiculo, Parking parking) {
        return parking.getPrecioTipoVehiculo(tipoVehiculo);
    }
    //Obtiene el factor demanda.
    private double valorFactorDemanda(){
        return this.cochera.getParking().obtenerValorFactorDemanda(this.getCantidadUT());
    }
    //Valida si contiene un vehiculo dado.
    boolean contieneVehiculo(Vehiculo vehiculo) {
        if(this.vehiculo.equals(vehiculo)){
            return true;
        } else{
            return false;
        }
    }
    //Obtiene la diferencia de la fecha actual - la fecha de entrada, y si es menor o igual a 10 segundos devuelve true, sino false.
    public boolean esIngresoReciente() {
        long diferenciaMilisegundos = new Date().getTime() - this.entrada.getTime();
        long diferenciaSegundos = TimeUnit.MILLISECONDS.toSeconds(diferenciaMilisegundos);
        return diferenciaSegundos <= 10;
    }
    //Obtiene la diferencia de la fecha actual - la fecha de salida, y si es menor o igual a 10 segundos devuelve true, sino false.
    public boolean esEgresoReciente() {
        if (this.salida == null) return false;
        long diferenciaMilisegundos = new Date().getTime() - this.salida.getTime();
        long diferenciaSegundos = TimeUnit.MILLISECONDS.toSeconds(diferenciaMilisegundos);
        return diferenciaSegundos <= 10;
    }
    //Finaliza la estadia.
    void finalizar() {
        this.salida = new Date();
        this.temporizadorUT.stop();
        this.esFinalizada = true;
        this.cochera.setOcupada(false);
        this.vehiculo.setEstacionado(false);
        this.verificarSiEsMultable();
        this.calcularValorFacturado();
        this.vehiculo.getPropietario().restarCuentaCorriente(this.valorFacturado);
    }
    
    
    //AVISAR
    
    //Agrega nueva anomalia HOUDINI
    public void anomaliaHoudini(){
        this.esFinalizada = true;
        this.valorFacturado = 0;
        this.salida = null;
        this.anomalia = new Anomalia("HOUDINI", this);
        temporizadorUT.stop();
        avisarAnomalia();
    }
    //Agrega nueva anomalia MISTERY
    public void anomaliaMistery(){
        this.temporizadorUT.stop();
        this.esFinalizada = true;
        this.valorFacturado = 0;
        Date fecha = new Date();
        this.entrada = fecha;
        this.salida = fecha;
        this.anomalia = new Anomalia("MISTERY", this);
        this.cochera.setOcupada(false);
        this.vehiculo.setEstacionado(false);
        this.cochera.agregarEstadia(this);
        avisarAnomalia();
    }
    //Agrega nueva anomalia TRANSPORTADOR1
    public void anomaliaTransportadorUno(){
        this.valorFacturado = 0;
        this.anomalia = new Anomalia("TRANSPORTADOR1", this);
        avisarAnomalia();
    }
    //Agrega nueva anomalia TRANSPORTADOR2
    void anomaliaTransportadorDos() {
        this.anomalia = new Anomalia("TRANSPORTADOR2", this);
        this.entrada = null;
        this.salida = new Date();
        this.esFinalizada = true;
        this.vehiculo.setEstacionado(false);
        avisarAnomalia();
    }
    void avisarAnomalia(){
        this.cochera.avisarAnomalia(this);
    }
    
    //GETTERS Y SETTERS
    public double getValorFacturado() {return valorFacturado;}
    public void setValorFacturado(float valorFacturado) {this.valorFacturado = valorFacturado;} 
    public Vehiculo getVehiculo() {return vehiculo;}
    public Cochera getCochera() {return cochera;} 
    public Date getEntrada() {return entrada;}
    public Date getSalida() {return salida;}
    public boolean getEsFinalizada(){return this.esFinalizada;}    
    public void setCocheraOcupada(boolean estaOcupada){this.cochera.setOcupada(estaOcupada);}
    public Anomalia getAnomalia(){return this.anomalia;}    
    public void setAnomalia(Anomalia anomalia){this.anomalia = anomalia;} 
    public void setFechaEntrada(Date entrada){this.entrada = entrada;}
    public void setFechaSalida(Date salida) {this.salida = salida;}   
    public int getCantidadUT(){return this.temporizadorUT.getUT();}
}
