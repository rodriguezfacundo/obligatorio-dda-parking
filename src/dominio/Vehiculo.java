package dominio;

import interfaces.IEtiquetable;
import java.util.ArrayList;
import simuladortransito.Transitable;

public class Vehiculo implements IEtiquetable, Transitable{
    private String patente;
    private TipoVehiculo tipo;
    private Propietario propietario;
    private ArrayList<Etiqueta> etiquetas;
    private ArrayList<Multa> multas;
    private boolean estaEstacionado;

    public Vehiculo(String patente, TipoVehiculo tipo, Propietario propietario) {
        this.patente = patente;
        this.tipo = tipo;
        this.propietario = propietario;
        this.etiquetas = new ArrayList<>();
        this.multas = new ArrayList<>();
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
        return multas;
    }

    public void setInfracciones(ArrayList<Multa> infracciones) {
        this.multas = infracciones;
    }
 
    
    private StringBuilder obtenerEtiquetasString(){
        StringBuilder etiquetasStr = new StringBuilder();
        for (Etiqueta etiqueta : etiquetas) {
            etiquetasStr.append(etiqueta.getNombre()).append(" ");
        }
        return etiquetasStr;
    }
    
         @Override
    public String toString() {
        return this.patente + " - " + (this.estaEstacionado ? " ESTACIONADO" : "NO ESTACIONADO")  + " - Etiquetas: " +  this.obtenerEtiquetasString();
    }

    @Override
    public void agregarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.add(etiqueta);
    }
    
    public boolean tieneEtiqueta(Class<? extends Etiqueta> etiquetaClass) {
        for (Etiqueta etiqueta : etiquetas) {
            if (etiquetaClass.isInstance(etiqueta)) {
                return true;
            }
        }
        return false;
    }

    public void setEstacionado(boolean b) {
        this.estaEstacionado = b;
    }
    
    public boolean estaEstacionado(){
        return this.estaEstacionado;
    }

    @Override
    public boolean esDiscapacitado() {
        boolean esDiscapacitado = false;
        for(Etiqueta e:etiquetas){
            if(e.getNombre().equals("Discapacitado"))esDiscapacitado=true;
        }
        return esDiscapacitado;
    }

    @Override
    public boolean esElectrico() {
        boolean esElectrico = false;
        for(Etiqueta e:etiquetas){
            if(e.getNombre().equals("Electrico"))esElectrico=true;
        }
        return esElectrico;
    }

    @Override
    public boolean esEmpleado() {
        boolean esEmpleado = false;
        for(Etiqueta e:etiquetas){
            if(e.getNombre().equals("Empleado"))esEmpleado=true;
        }
        return esEmpleado;
    }
    
}
