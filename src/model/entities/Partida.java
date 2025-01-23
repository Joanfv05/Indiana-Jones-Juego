package model.entities;

public class Partida {
    private int numeroPartida;
    private Usuario usuario;
    private int gemasPuntos;
    private long tiempo;

    public Partida(int numeroPartida, Usuario usuario, int gemasPuntos, long tiempo) {
        this.numeroPartida = numeroPartida;
        this.usuario = usuario;
        this.gemasPuntos = gemasPuntos;
        this.tiempo = tiempo;
    }

    public int getNumeroPartida() {
        return numeroPartida;
    }

    public void setNumeroPartida(int numeroPartida) {
        this.numeroPartida = numeroPartida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPuntos() {
        return gemasPuntos;
    }

    public void setPuntos(int puntos) {
        this.gemasPuntos = gemasPuntos;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public void setGemasPuntos(int gemasPuntos) {
        this.gemasPuntos = gemasPuntos;
    }

    @Override
    public String toString() {
        return numeroPartida + " - " + usuario +" - "+ gemasPuntos + " puntos - " + tiempo + " seg";
    }

}
