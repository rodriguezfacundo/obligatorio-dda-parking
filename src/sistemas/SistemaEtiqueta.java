package sistemas;

import dominio.Etiqueta;
import interfaces.IEtiquetable;
import java.util.ArrayList;
import java.util.Collections;

public class SistemaEtiqueta<T extends IEtiquetable> {
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    
    public void agregarEtiqueta(Etiqueta etiqueta){
        etiquetas.add(etiqueta);
    }
    
    public ArrayList<Etiqueta> obtenerEtiquetas(){
        return this.etiquetas;
    }
    
    public void asignarEtiquetas(ArrayList<T> listaEntidad) {
        int cantidad = listaEntidad.size() * 20 / 100; // el 20% de la lista que reciba por parametro debera tener etiquetas

        Collections.shuffle(listaEntidad);//Para que me desordene la lista

        for (int i = 0; i < cantidad; i++) {
            T entidad = listaEntidad.get(i);
            if (!this.etiquetas.isEmpty()) {
                int indiceEtiqueta = (int) (Math.random() * this.etiquetas.size());
                entidad.agregarEtiqueta(this.etiquetas.get(indiceEtiqueta));
            }
        }
    }
    
}
