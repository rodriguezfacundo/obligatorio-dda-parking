package dominio;

import java.util.Date;

public class Anomalia {

    private String codigoError;
    private Estadia estadia;
    private Date fechaCreacion;
    
    public Anomalia(String codigoError, Estadia estadia){
        this.codigoError = codigoError;
        this.estadia = estadia;
        this.fechaCreacion = new Date();
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }
    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
