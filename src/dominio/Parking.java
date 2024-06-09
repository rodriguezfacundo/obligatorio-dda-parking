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
        
    //TO STRING Y EQUALS
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
    
    //METODOS AUXILIARES
    
    //Valida que las cantidad de cocheras a registrar al parking sea como minimo 50 y como maximo 100
    private boolean esCantidadCocherasAceptable(int cantidadCocheras) {
        return cantidadCocheras >= 50 && cantidadCocheras <= 100;
    }
    //Agrega la cantidad de cocheras que se pasa por parametro
    private ArrayList<Cochera> agregarCocheras(int cantidadCocheras) {
        ArrayList<Cochera> cocherasNuevas = new ArrayList<>();
        if (esCantidadCocherasAceptable(cantidadCocheras)) {
            for (int i = 0; i <= cantidadCocheras; i++) {
                cocherasNuevas.add(new Cochera(this));
            }
        }
        return cocherasNuevas;
    }
    //Retorna el precio del tipo de vehiculo pasado por parametro.
    public double getPrecioTipoVehiculo(TipoVehiculo tipo) {
        for (Tarifa tarifa : this.tarifas) {
            if (tarifa.getTipoVehiculo().equals(tipo)) {
                return tarifa.getPrecioPorUT();
            }
        }
        return 0d;
    }
    //Retorna la cantidad de cocheras ocupadas.
    public int getCantidadCocherasOcupadas() {
        int cantidad = 0;
        for (Cochera cochera : this.cocheras) {
            if (cochera.estaOcupada()) {
                cantidad += 1;
            }
        }
        return cantidad;
    }
    //Metodo que se utiliza luego en las evaluaciones de las tendencias, retorna la diferencia entre ingresos y egresos de los ultimos
    //10 UT.
    public int diferenciaEntreIngresoYEgresosRecientes() {
        int ingresosRecientes = 0;
        int egresosRecientes = 0;
        for (Cochera cochera : this.cocheras) {
            ArrayList<Estadia> estadias = cochera.getEstadias();
            for (Estadia estadia : estadias) {
                if (estadia.esIngresoReciente()) {
                    ingresosRecientes++;
                }
                if (estadia.esEgresoReciente()) {
                    egresosRecientes++;
                }
            }
        }
        return ingresosRecientes - egresosRecientes;
    }
    //Evalua la tendencia.
    public void evaluarTendencia() {
        SistemaParking.getInstancia().evaluarTendencia(this);
    }
    //Calcula y retorna el valor del factor demanda.
    public double obtenerValorFactorDemanda(int cantidadUT) {
        return this.tendencia.calcularFactorDemanda(cantidadUT, this);
    }
    //Retorna la cantidad de cocheras disponibles para estacionar.
    public long getCantidadCocherasDisponibles() {
        long cantidadNoOcupadas = cocheras.stream()
                .filter(cochera -> !cochera.estaOcupada())
                .count();
        return cantidadNoOcupadas;
    }
    //Retorna la cantidad de estadias registradas en el parking.
    public int obtenerCantidadEstadias() {
        int cantidadEstadias = 0;
        for (Cochera c : cocheras) {
            cantidadEstadias = cantidadEstadias + c.obtenerCantidadEstadias();
        }
        return cantidadEstadias;
    }
    //Retorna el total facturado por ese parking
    public double obtenerTotalFacturado() {
        double total = 0;
        for (Cochera c : cocheras) {
            total = total + c.obtenerTotalFacturado();
        }
        return total;
    }
    //Retorna el total facturado de multas por ese parking
    public Double obtenerTotalFacturadoMultas() {
        Double total = 0.00;
        for (Cochera c : cocheras) {
            total = total + c.obtenerTotalFacturadoMultas();
        }
        return total;
    }
    //Retorna un Map<String, Integer> el cual sera el nombre de la Etiqueta y la cantidad de cocheras disponibles para cada etiqueta.
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
    
    //AVISAR
    public void setTendencia(Tendencia ten) {
        this.tendencia = ten;
        this.avisar(Observable.Eventos.CAMBIO_TENDENCIA);
    }
    public void actualizarValorTarifa(int pos, Double nuevoValor) throws ParkingException{
           this.tarifas.get(pos).actualizarPrecio(nuevoValor);
           this.avisar(Observable.Eventos.PARKING_CAMBIO);
    }
    public void avisarCambioEstadoEstadia() {
        this.avisar(Observable.Eventos.INGRESO_EGRESO_ESTADIA);
    }
    public void avisarAnomalia(Estadia estadiaConAnomalia){
        this.ultimaEstadiaConAnomalia = estadiaConAnomalia;
        this.avisar(Observable.Eventos.ANOMALIA_REGISTRADA );
    }
    
    //GETTERS Y SETTERS
    public Estadia getUltimaEstadiaConAnomalia() {return ultimaEstadiaConAnomalia;}
    public void setUltimaEstadiaConAnomalia(Estadia ultimaEstadiaConAnomalia) {this.ultimaEstadiaConAnomalia = ultimaEstadiaConAnomalia;}
    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {this.etiquetas = etiquetas;}
    public ArrayList<Etiqueta> getEtiquetas(){return this.etiquetas;}
    public Tendencia getTendencia() {return this.tendencia;}
    public int getCapacidad() {return this.getCocheras().size();}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public ArrayList<Cochera> getCocheras() {return cocheras;}
    public void setCocheras(ArrayList<Cochera> cocheras) {this.cocheras = cocheras;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public void setTarifas(ArrayList<Tarifa> tarifas) {this.tarifas = tarifas;}
    public ArrayList<Tarifa> getTarifas() {return this.tarifas;}
    public Double getFactorDemanda() {return this.factorDemanda;}
}
