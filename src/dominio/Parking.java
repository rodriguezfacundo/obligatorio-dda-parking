package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import observador.Observable;
import sistemas.SistemaParking;

public class Parking extends Observable {

    private static int contadorID = 1;
    private int id;
    private String nombre;
    private String direccion;
    private ArrayList<Cochera> cocheras = new ArrayList<>();
    private ArrayList<Tarifa> tarifas = new ArrayList<>();
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private Tendencia tendencia;
    private double factorDemanda;
    private Estadia ultimaEstadiaConAnomalia;

    public Parking(String nombre, String direccion, int cantidadCocheras, Double factorDemanda, Tendencia tendencia) {
        this.id = this.contadorID;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contadorID++;
        this.factorDemanda = factorDemanda; //El factor de demanda es un valor de entre 0.25 y 10 que se establece en cada parking, el factor de demanda inicial es 1
        this.cocheras = agregarCocheras(cantidadCocheras);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Cochera> getCocheras() {
        return cocheras;
    }

    public void setCocheras(ArrayList<Cochera> cocheras) {
        this.cocheras = cocheras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private boolean esCantidadCocherasAceptable(int cantidadCocheras) {
        return cantidadCocheras >= 50 && cantidadCocheras <= 100;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
    public ArrayList<Tarifa> getTarifas() {
        return this.tarifas;
    }
    public void actualizarValorTarifa(int pos, Double nuevoValor) throws ParkingException{
           this.tarifas.get(pos).actualizarPrecio(nuevoValor);
           this.avisar(Observable.Eventos.PARKING_CAMBIO);
    }
    private ArrayList<Cochera> agregarCocheras(int cantidadCocheras) {
        ArrayList<Cochera> cocherasNuevas = new ArrayList<>();
        if (esCantidadCocherasAceptable(cantidadCocheras)) {
            for (int i = 0; i <= cantidadCocheras; i++) {
                cocherasNuevas.add(new Cochera(this));
            }
        }
        return cocherasNuevas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PARKING{");
        sb.append("id='").append(this.id).append('\'');
        sb.append(", nombre=").append(this.nombre);
        sb.append(", direccion=").append(this.direccion);
        sb.append(", cocheras=[");
        for (int i = 0; i < this.cocheras.size(); i++) {
            sb.append(this.cocheras.get(i).getCodigo());
            if (i < this.cocheras.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        sb.append(", tarifas=[");
        for (int i = 0; i < this.tarifas.size(); i++) {
            sb.append(this.tarifas.get(i).getPrecioPorUT());
            if (i < this.tarifas.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
    @Override
      public boolean equals(Object o) {
          String parkingId = String.valueOf(this.getId());
          Parking p = (Parking)o;
          String parkingCompararId = String.valueOf(p.getId());
          return parkingId.equals(parkingCompararId);
      }
    public Double getFactorDemanda() {
        return this.factorDemanda;
    }

    public double getPrecioTipoVehiculo(TipoVehiculo tipo) {
        for (Tarifa tarifa : this.tarifas) {
            if (tarifa.getTipoVehiculo().equals(tipo)) {
                return tarifa.getPrecioPorUT();
            }
        }
        return 0d;
    }

    public int getCapacidad() {
        return this.getCocheras().size();
    }

    public int getCantidadCocherasOcupadas() {
        int cantidad = 0;
        for (Cochera cochera : this.cocheras) {
            if (cochera.estaOcupada()) {
                cantidad += 1;
            }
        }
        return cantidad;
    }

    public int diferenciaEntreIngresoYEgresosRecientes(int cantidadUT) {
        int ingresosRecientes = 0;
        int egresosRecientes = 0;
        for (Cochera cochera : this.cocheras) {
            ArrayList<Estadia> estadias = cochera.getEstadias();
            for (Estadia estadia : estadias) {
                if (estadia.esIngresoReciente(cantidadUT)) {
                    ingresosRecientes++;
                }
                if (estadia.esEgresoReciente(cantidadUT)) {
                    egresosRecientes++;
                }
            }
        }
        return ingresosRecientes - egresosRecientes;
    }

    public Tendencia getTendencia() {
        return this.tendencia;
    }

    public void setTendencia(Tendencia ten) {
        this.tendencia = ten;
        this.avisar(Observable.Eventos.CAMBIO_TENDENCIA);
    }

    public void evaluarTendencia() {
        SistemaParking.getInstancia().evaluarTendencia(this);
    }

    public double obtenerValorFactorDemanda(int cantidadUT) {
        return this.tendencia.calcularFactorDemanda(cantidadUT, this);
    }

    public long getCantidadCocherasDisponibles() {
        long cantidadNoOcupadas = cocheras.stream()
                .filter(cochera -> !cochera.estaOcupada())
                .count();
        return cantidadNoOcupadas;
    }

    public int contarCocherasDisponiblesPorEtiqueta(Etiqueta etiqueta) {
        return (int) cocheras.stream()
                .filter(cochera -> !cochera.estaOcupada() && cochera.getEtiquetas().contains(etiqueta))
                .count();
    }

    public double getPrecioTarifaPorTipoVehiculo(TipoVehiculo tipo) {
        for (Tarifa tarifa : this.tarifas) {
            if (tarifa.getTipoVehiculo().equals(tipo)) {
                return tarifa.getPrecioPorUT();
            }
        }
        return 0d;
    }

    public int obtenerCantidadEstadias() {
        int cantidadEstadias = 0;
        for (Cochera c : cocheras) {
            cantidadEstadias = cantidadEstadias + c.obtenerCantidadEstadias();
        }
        return cantidadEstadias;
    }

    public double obtenerTotalFacturado() {
        double total = 0;
        for (Cochera c : cocheras) {
            total = total + c.obtenerTotalFacturado();
        }
        return total;
    }

    public Double obtenerTotalFacturadoMultas() {
        Double total = 0.00;
        for (Cochera c : cocheras) {
            total = total + c.obtenerTotalFacturadoMultas();
        }
        return total;
    }
    public void avisarCambioEstadoEstadia() {
        this.avisar(Observable.Eventos.INGRESO_EGRESO_ESTADIA);
    }
    public void avisarAnomalia(Estadia estadiaConAnomalia){
        this.ultimaEstadiaConAnomalia = estadiaConAnomalia;
        this.avisar(Observable.Eventos.ANOMALIA_REGISTRADA );
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
    
    public ArrayList<Etiqueta> getEtiquetas(){
        return this.etiquetas;
    }
    
    public Map<String, Integer> obtenerDisponibilidadPorEtiqueta() {
        Map<String, Integer> disponibilidadPorEtiqueta = new HashMap<>();
        
        for (Cochera cochera : this.cocheras) {
            for (Etiqueta etiqueta : cochera.getEtiquetas()) {
                String nombreEtiqueta = etiqueta.getNombre();
                disponibilidadPorEtiqueta.putIfAbsent(nombreEtiqueta, 0);

                if (!cochera.estaOcupada()) {
                    disponibilidadPorEtiqueta.put(nombreEtiqueta, disponibilidadPorEtiqueta.get(nombreEtiqueta) + 1);
                }
            }
        }
        return disponibilidadPorEtiqueta;
    }
   
    public Estadia getUltimaEstadiaConAnomalia() {return ultimaEstadiaConAnomalia;}
    public void setUltimaEstadiaConAnomalia(Estadia ultimaEstadiaConAnomalia) {this.ultimaEstadiaConAnomalia = ultimaEstadiaConAnomalia;}
}
