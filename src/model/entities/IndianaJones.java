package model.entities;

public class IndianaJones extends Personajes {

    public IndianaJones(String nombre, Coordenadas coordenadas) {
        super(nombre, 3, coordenadas);
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

}

