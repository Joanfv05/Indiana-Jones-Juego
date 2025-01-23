package model.entities;

public class Gema {
    private Coordenadas coordenadas;
    private String tipo;

    public Gema(Coordenadas coordenadas, String tipo) {
        this.coordenadas = coordenadas;
        this.tipo = tipo;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
