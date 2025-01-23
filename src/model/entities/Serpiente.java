package model.entities;

public class Serpiente extends Personajes {

    public Serpiente(Coordenadas coordenadas) {
        super("Serpiente", 1, coordenadas);
    }

    @Override
    public void restarVida(int danyo) {
        vida -= danyo;
    }

    public int getVida() {
        return vida;
    }

    public int getPosicionX() {
        return coordenadas.getX();
    }

    public int getPosicionY() {
        return coordenadas.getY();
    }

    public void setPosicionX(int x) {
        coordenadas.setX(x);
    }

    public void setPosicionY(int y) {
        coordenadas.setY(y);
    }
}