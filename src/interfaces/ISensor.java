package interfaces;

import dominio.Cochera;
import dominio.Vehiculo;

public interface ISensor {
    public void ingreso(Vehiculo vehiculo, Cochera cochera);

    public void egreso(Vehiculo vehiculo, Cochera cochera);
}
