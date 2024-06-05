package dominio;

public class Tarifa {
    private double  precioPorUT;
    private TipoVehiculo  tipoVehiculo;


    public  Tarifa(double precioPorUT, TipoVehiculo tv) {
        this.precioPorUT = precioPorUT;
        this.tipoVehiculo = tv;
        this.tipoVehiculo.agregarTarifa(this);
    }
    
    public double getPrecioPorUT(){
        return this.precioPorUT;
    }
    
    public TipoVehiculo getTipoVehiculo(){
        return this.tipoVehiculo;
    }
    public void actualizarPrecio(double nuevoValor)throws ParkingException{
         if (nuevoValor < 0) throw new ParkingException("Valor Invalido. El precio debe ser igual o mayor a cero.");
        double precioPromedio = this.tipoVehiculo.calcularPromedio();
        if (nuevoValor >= 2 * precioPromedio) throw new ParkingException("Valor demasiado alto. El sistema no permite dispersión de precios por encima del 100%. Ingrese un valor menor a <2x>.");
        this.precioPorUT = nuevoValor;
    }
}
