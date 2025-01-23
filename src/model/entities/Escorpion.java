package model.entities;

public class Escorpion extends Personajes {

    public Escorpion(Coordenadas coordenadas) {
        super("Escorpion", 1, coordenadas);
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
