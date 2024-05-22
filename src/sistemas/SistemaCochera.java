package sistemas;

import dominio.Cochera;
import java.util.ArrayList;

public class SistemaCochera {
    private ArrayList<Cochera> cocheras = new ArrayList<>();
    
    private static SistemaCochera instancia = new SistemaCochera();

    public static SistemaCochera getInstancia() {
        return instancia;
    }
    
    private SistemaCochera() {
    }

    public void agregarCochera(Cochera cochera) {
        cocheras.add(cochera);
    }
    
    public ArrayList<Cochera> obtenerCocheras(){
        return this.cocheras;
    }

    public Cochera obtenerCocheraPorCodigo(String codCochera) {
        for (Cochera cochera : cocheras) {
            if (cochera.getCodigo().equals(codCochera)) {
                return cochera;
            }
        }
        return null;
    }
}
