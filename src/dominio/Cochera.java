package dominio;

import interfaces.IEtiquetable;
import java.util.ArrayList;
import simuladortransito.Estacionable;
import sistemas.SistemaParking;



public class Cochera implements IEtiquetable, Estacionable  {

    private static int contadorID = 1;
    private String codigo;
    private Parking parking;
    private boolean estaOcupada;
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private ArrayList<Estadia> estadias = new ArrayList<>();


    public Cochera( Parking parking) {
        this.codigo = "cochera" + this.contadorID + "-parking" + parking.getId();
        this.parking = parking;
        this.estaOcupada = false;
        this.contadorID++;
        agregarCochera();
    }
    
     public String getCodigo() {
        return codigo;
     }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void agregarCochera(){
        SistemaParking.getInstancia().agregarCochera(this);
    }
    
     @Override
        public void agregarEtiqueta(Etiqueta etiqueta) {
             this.etiquetas.add(etiqueta);
        }
    
        public void setOcupada(boolean estaOcupada) {
             this.estaOcupada = estaOcupada;
        }
        
        public boolean estaOcupada(){
            return this.estaOcupada ? true : false;
        }
        
        public String getEstadoToString(){
            if (!this.estaOcupada){
                return "Libre";
            } else{
                return "Ocupada";
            }
        }
        
        public ArrayList<Estadia> getEstadias(){
            return this.estadias;
        }
        
        public void agregarEstadia(Estadia estadia){
            this.estadias.add(estadia);
        }

        public ArrayList<Etiqueta> getEtiquetas() {
            return this.etiquetas;
        }
       
        public Parking getParking(){
            return this.parking;
        }
        
        public int obtenerCantidadEstadias(){
            return this.estadias.size();
        }
        public double obtenerTotalFacturado(){
            double total = 0;
            for(Estadia e:estadias){
                total = total + e.getValorFacturado();
            }
            return total;
        }

        public Double obtenerTotalFacturadoMultas(){
            Double total = 0.00;
            for(Estadia e:estadias){
                total = total + e.costoMultas();
            }
            return total;
        }
        
        public Estadia obtenerEstadiaAbierta(){
            for(Estadia est:this.estadias){
                if(!est.getEsFinalizada()){
                    return est;
                }
            }
            return null;
         }
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
    
    
    @Override
    public String toString() {
        return codigo + " - " + (this.estaOcupada ? " OCUPADA" : " LIBRE") + " - Etiquetas: " + obtenerEtiquetasString();
    }
    
        private StringBuilder obtenerEtiquetasString(){
        StringBuilder etiquetasStr = new StringBuilder();
        for (Etiqueta etiqueta : etiquetas) {
            etiquetasStr.append(etiqueta.getNombre()).append(" ");
        }
        return etiquetasStr;
    }
}
