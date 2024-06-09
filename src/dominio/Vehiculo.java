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

    //METODOS AUXILIARES
    @Override
    public void agregarEtiqueta(Etiqueta etiqueta) {
        this.etiquetas.add(etiqueta);
    }
    
    //METODO QUE VALIDA SI TIENE ETIQUETA O NO, SE UTILIZA EN LA ESTADIA PARA VALIDAR SI ES POSIBLE DE MULTA O NO.
    public boolean tieneEtiqueta(Class<? extends Etiqueta> etiquetaClass) {
        for (Etiqueta etiqueta : etiquetas) {
            if (etiquetaClass.isInstance(etiqueta)) {
                return true;
            }
        }
        return false;
    }
    
    //TO STRING
    @Override
    public String toString() {
        return patente + " - " + (this.estaEstacionado ? " ESTACIONADO" : " NO ESTACIONADO") + " - Etiquetas: " + obtenerEtiquetasString();
    }
    private StringBuilder obtenerEtiquetasString() {
        StringBuilder etiquetasStr = new StringBuilder();
        for (Etiqueta etiqueta : etiquetas) {
            etiquetasStr.append(etiqueta.getNombre()).append(" ");
        }
        return etiquetasStr;
    }
    
    //GETTERS Y SETTERS
    public void setEstacionado(boolean b) {this.estaEstacionado = b;}
    public boolean estaEstacionado(){return this.estaEstacionado;}
    public void setPropietario(Propietario propietario) {this.propietario = propietario;}
    public ArrayList<Etiqueta> getEtiquetas() {return etiquetas;}
    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {this.etiquetas = etiquetas;}
    public ArrayList<Multa> getInfracciones() {return multas;}
    public void setInfracciones(ArrayList<Multa> infracciones) {this.multas = infracciones;}
    public String getPatente() {return patente;}
    public void setPatente(String patente) {this.patente = patente;}
    public TipoVehiculo getTipo() {return tipo;}
    public void setTipo(TipoVehiculo tipo) {this.tipo = tipo;}
    public Propietario getPropietario() {return propietario;}
    
    //NO SE UTILIZAN, SOLAMENTE ESTAN POR EL SENSOR
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
