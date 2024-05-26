package dominio;

import java.util.ArrayList;

public class Parking {
        private static int contadorID = 1;
        private int id;
        private String nombre;
        private String direccion;
        private ArrayList<Cochera> cocheras = new ArrayList<>();
        private ArrayList<Tarifa> tarifas = new ArrayList<>();
        private ArrayList<Tendencia> tendencias= new ArrayList<>();
        private double factorDemanda;

        private int cantidadIngresos = 0;
        private int cantidadEgresos = 0;
        public Parking(String nombre, String direccion, Tarifa tarifa, double factor) {
            this.id = contadorID++;
            this.nombre = nombre;
            this.direccion = direccion;
            this.factorDemanda = factor;//El factor de demanda es un valor de entre 0.25 y 10 que se establece en cada parking, el factor de demanda inicial es 1
        }

        public Parking(String nombre, String direccion, int cantidadCocheras, Double factorDemanda) {
            this.id = this.contadorID;
            this.nombre = nombre;
            this.direccion = direccion;
            this.contadorID++;
            this.factorDemanda = factorDemanda;
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

        private boolean esCantidadCocherasAceptable(int cantidadCocheras){
            return cantidadCocheras >= 50 && cantidadCocheras <= 100;
        }
        
        public void setTarifas(ArrayList<Tarifa> tarifas){
            this.tarifas = tarifas;
        }
    
        private ArrayList<Cochera> agregarCocheras(int cantidadCocheras){
        ArrayList<Cochera> cocherasNuevas = new ArrayList<>();
        if(esCantidadCocherasAceptable(cantidadCocheras)){
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
        
        public Double getFactorDemanda(){
            return this.factorDemanda;
        }
        
        public double getPrecioTipoVehiculo(TipoVehiculo tipo){
            for(Tarifa tarifa: this.tarifas){
                if(tarifa.getTipoVehiculo().equals(tipo)){
                    return tarifa.getPrecioPorUT();
                }
            }
            return 0d;
        }
        
        public int getCapacidad(){
            return this.getCocheras().size();
        }
        
        public int getCantidadCocherasOcupadas(){
            int cantidad = 0;
            for(Cochera cochera:this.cocheras){
                if(cochera.estaOcupada()){
                    cantidad += 1;
                }
            }
            return cantidad;
        }
        
        public void sumarUnIngreso(){
            this.cantidadIngresos += 1;
        }
        
        public void sumarUnEgreso(){
            this.cantidadEgresos += 1;
        }
        
        public int diferenciaEntreIngresoYEgresos(){
            return this.cantidadIngresos - this.cantidadEgresos;
        }
        
        public ArrayList<Tendencia> getTendencias(){
            return this.tendencias;
        }
        
        public double obtenerValorFactorDemanda(int cantidadUT){
            for(Tendencia ten:this.tendencias){
                this.factorDemanda = ten.calcularFactorDemanda(this.factorDemanda, this.getCantidadCocherasOcupadas(), 
                        this.getCapacidad(), this.diferenciaEntreIngresoYEgresos(), cantidadUT);
            }
            return this.factorDemanda;
        }
}
