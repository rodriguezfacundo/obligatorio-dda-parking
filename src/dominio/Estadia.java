package dominio;

import java.util.Date;

public class Estadia {
    private Date fechaEntrada;
    private int horaEntrada;
    private Date fechaSalida;
    private int horaSalida;
    private float valorFacturado;

    public Estadia(Date fechaEntrada, int horaEntrada, Date fechaSalida, int horaSalida, float valorFacturado) {
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.valorFacturado = valorFacturado;
    }


    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public float getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(float valorFacturado) {
        this.valorFacturado = valorFacturado;
    }
}
