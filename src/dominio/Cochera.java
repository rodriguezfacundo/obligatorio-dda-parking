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
    private String tipo;


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
        
        public void setOcupada(boolean ocupada) {
        this.estaOcupada = ocupada;
    }

    @Override
    public boolean esDiscapacitado() {
        return tipo.equals("DISCAPACITADO");
    }

    @Override
    public boolean esElectrico() {
        return  tipo.equals("ELECTRICO");
    }

    @Override
    public boolean esEmpleado() {
        return tipo.equals("EMPLEADO");
    }

}
