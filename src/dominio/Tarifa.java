package dominio;

public class Tarifa {
    private double  precioPorUT;
    private TipoVehiculo  tipoVehiculo;


    public  Tarifa(double precioPorUT, TipoVehiculo tv) {
        this.precioPorUT = precioPorUT;
        this.tipoVehiculo = tv;
    }
    
    public double getPrecioPorUT(){
        return this.precioPorUT;
    }
}
