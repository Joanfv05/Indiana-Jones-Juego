package model.entities;

public abstract class Personajes {

    protected String nombre;
    protected int vida;
    protected Coordenadas coordenadas;

    public Personajes(String nombre, int vida,Coordenadas coordenadas) {
        this.nombre = nombre;
        this.vida = vida;
        this.coordenadas = coordenadas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public abstract void restarVida(int danyo);
}

