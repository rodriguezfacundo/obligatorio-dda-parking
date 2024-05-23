package dominio;

import interfaces.IEtiquetable;
import java.util.ArrayList;
import simuladortransito.Estacionable;
import sistemas.SistemaCochera;



public class Cochera implements IEtiquetable, Estacionable  {
    private static int contadorID = 1;
    private String codigo;
    private Parking parking;
    private Vehiculo vehiculo;
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
        SistemaCochera.getInstancia().agregarCochera(this);
    }
    
     @Override
        public void agregarEtiqueta(Etiqueta etiqueta) {
             this.etiquetas.add(etiqueta);
        }
    
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Cochera{");
            sb.append("codigo='").append(codigo).append('\'');
            sb.append(", parking=").append(parking.getNombre()); 
            sb.append(", vehiculo=").append(vehiculo);
            sb.append(", esta ocupada?=").append(estaOcupada); 
            sb.append(", etiquetas=[");
            for (int i = 0; i < etiquetas.size(); i++) {
                sb.append(etiquetas.get(i).getNombre());
                if (i < etiquetas.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]}");
            return sb.toString();
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

        public Estadia getEstadiaSinFinalizar() {
            for(Estadia est:this.estadias){
                if(!est.getEsFinalizada()){
                    return est;
                }
            }
            return null;
        }

       public ArrayList<Etiqueta> getEtiquetas() {
        return this.etiquetas;
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
}
