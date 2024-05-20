package dominio;

import interfaces.IEtiquetable;
import java.util.ArrayList;
import simuladortransito.Transitable;

public class Vehiculo implements IEtiquetable, Transitable{
    private String patente;
    private TipoVehiculo tipo;
    private Propietario propietario;
    private ArrayList<Etiqueta> etiquetas;
    private ArrayList<Multa> infracciones;
    private boolean estacionado;
   private String tipoEtiqueta;


    public Vehiculo(String patente, TipoVehiculo tipo, Propietario propietario) {
        this.patente = patente;
        this.tipo = tipo;
        this.propietario = propietario;
        this.etiquetas = new ArrayList<>();
        this.infracciones = new ArrayList<>();
        this.estacionado = false;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public ArrayList<Multa> getInfracciones() {
        return infracciones;
    }

    public void setInfracciones(ArrayList<Multa> infracciones) {
        this.infracciones = infracciones;
    }
 
    
     @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("VEHICULO{");
            sb.append("Patente='").append(this.patente).append('\'');
            sb.append(", Tipo=").append(this.tipo.getNombre()); 
            sb.append(", CI Propietario=").append(this.propietario.getNombre());
            sb.append(", Etiquetas=[");
            for (int i = 0; i < etiquetas.size(); i++) {
                sb.append(etiquetas.get(i).getNombre());
                if (i < etiquetas.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]}");
            sb.append(", Infracciones=[");
            for (int i = 0; i < this.infracciones.size(); i++) {
                sb.append(this.infracciones.get(i).getMulta());
                if (i < this.infracciones.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]}");
            return sb.toString();
        }

    @Override
    public void agregarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.add(etiqueta);
    }
    
    public void setEstacionado(boolean estacionado) {
        this.estacionado = estacionado;
    }
    @Override
    public boolean esDiscapacitado() {
        return tipoEtiqueta.equals("DISCAPACITADO");
    }

    @Override
    public boolean esElectrico() {
        return  tipoEtiqueta.equals("ELECTRICO");
    }

    @Override
    public boolean esEmpleado() {
        return tipoEtiqueta.equals("EMPLEADO");
    }
}
