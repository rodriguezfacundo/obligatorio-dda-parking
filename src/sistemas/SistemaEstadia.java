package sistemas;

import dominio.Estadia;
import java.util.ArrayList;

public class SistemaEstadia {
    private ArrayList<Estadia> estadias = new ArrayList<>();
    
    public ArrayList<Estadia> obtenerEstadias(){
        return this.estadias;
    }

    public void agregarNuevaEstadia(Estadia estadiaNueva){
        this.estadias.add(estadiaNueva);
    }
}
