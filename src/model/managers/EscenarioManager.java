package model.managers;

import model.entities.Coordenadas;
import model.entities.Escenario;
import model.entities.IndianaJones;
import views.ListarLaberinto;

public class EscenarioManager {

    private Escenario escenario;
    private int contadorGemas;
    private int contadorVidas;
    private ListarLaberinto vista;
    private IndianaJones indianaJones;
    private PersonajesManager personajesManager;

    public EscenarioManager(Escenario escenario) {
        this.escenario = escenario;
        this.contadorGemas = 0;
        this.contadorVidas = 3;
        this.vista = new ListarLaberinto(escenario.getLaberinto());
        this.indianaJones = new IndianaJones("Indiana Jones", new Coordenadas(1, 1));
        this.personajesManager = new PersonajesManager(escenario.getLaberinto());
    }

    public void reiniciarEscenario() {
        this.indianaJones = new IndianaJones("Indiana Jones", new Coordenadas(1, 1));
        this.escenario = new Escenario(15, 15, indianaJones);
        this.personajesManager = new PersonajesManager(escenario.getLaberinto());
        this.contadorGemas = 0;
        this.contadorVidas = 3;

    }

    public String[][] getLaberinto() {
        return escenario.getLaberinto();
    }

    public int getContadorGemas() {
        return contadorGemas;
    }

    public int setContadorGemas(int contadorGemas) {
        return this.contadorGemas = contadorGemas;
    }

    public int getContadorVidas() {
        return contadorVidas;
    }

    public void moverDerecha() {
        mover(0, 1);
    }

    public void moverIzquierda() {
        mover(0, -1);
    }

    public void moverArriba() {
        mover(-1, 0);
    }

    public void moverAbajo() {
        mover(1, 0);
    }

    public boolean isGema(int x, int y) {
        String casilla = escenario.getCasilla(x, y);
        return casilla.equals(Escenario.GEMA) || casilla.equals(Escenario.GEMA_ROJA);
    }


    public boolean isSerpiente(int x, int y) {
        return escenario.getCasilla(x, y).equals(Escenario.SERPIENTE);
    }

    public boolean isEscorpion(int x, int y) {
        return escenario.getCasilla(x, y).equals(Escenario.ESCORPION);
    }

    public boolean isVidaExtra(int x, int y) {
        return escenario.getCasilla(x, y).equals(Escenario.VIDA_EXTRA);
    }

    public void moverEscorpiones() {
        personajesManager.moverEscorpiones();
    }

    public void moverSerpientes() {
        personajesManager.moverSerpientes();
    }

    private void mover(int dx, int dy) {
        String[][] laberinto = escenario.getLaberinto();
        int filas = escenario.getFilas();
        int columnas = escenario.getColumnas();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j].equals(Escenario.INDIANA_JONES)) {
                    int nuevoX = i + dx;
                    int nuevoY = j + dy;

                    if (nuevoX >= 0 && nuevoX < filas && nuevoY >= 0 && nuevoY < columnas) {
                        if (laberinto[nuevoX][nuevoY].equals(Escenario.VACIO)) {
                            laberinto[i][j] = Escenario.VACIO;
                            laberinto[nuevoX][nuevoY] = Escenario.INDIANA_JONES;
                            indianaJones.getCoordenadas().setX(nuevoX);
                            indianaJones.getCoordenadas().setY(nuevoY);
                        } else if (isGema(nuevoX, nuevoY)) {
                            contadorGemas(laberinto, nuevoX, nuevoY);
                            laberinto[i][j] = Escenario.VACIO;
                            laberinto[nuevoX][nuevoY] = Escenario.INDIANA_JONES;
                            indianaJones.getCoordenadas().setX(nuevoX);
                            indianaJones.getCoordenadas().setY(nuevoY);
                        } else if (isSerpiente(nuevoX, nuevoY)) {
                            danyoSerpiente(laberinto, i, j);
                        } else if (isEscorpion(nuevoX, nuevoY)) {
                            danyoEscorpion(laberinto, i, j);
                        } else if (isVidaExtra(nuevoX, nuevoY)) {
                            recogerVidaExtra();
                            laberinto[i][j] = Escenario.VACIO;
                            laberinto[nuevoX][nuevoY] = Escenario.INDIANA_JONES;
                            indianaJones.getCoordenadas().setX(nuevoX);
                            indianaJones.getCoordenadas().setY(nuevoY);
                        }

                        moverEscorpiones();
                        moverSerpientes();
                        escenario.realizarMovimiento();
                        return;
                    }

                }
            }
        }
    }

    private void contadorGemas(String[][] laberinto, int nuevoX, int nuevoY) {
        if (laberinto[nuevoX][nuevoY].equals(Escenario.GEMA_ROJA)) {
            contadorGemas += 2;
        } else {
            contadorGemas++;
        }
    }

    private void danyoSerpiente(String[][] laberinto, int i, int j) {
        contadorVidas--;
        if (contadorVidas <= 0) {
            laberinto[i][j] = Escenario.VACIO;
        } else {
            resetPosicionIndianaJones();
        }
    }

    private void danyoEscorpion(String[][] laberinto, int i, int j) {
        contadorVidas -= 2;
        if (contadorVidas <= 0) {
            laberinto[i][j] = Escenario.VACIO;
        } else {
            resetPosicionIndianaJones();
        }
    }

    private void resetPosicionIndianaJones() {
        String[][] laberinto = escenario.getLaberinto();
        for (int i = 0; i < escenario.getFilas(); i++) {
            for (int j = 0; j < escenario.getColumnas(); j++) {
                if (laberinto[i][j].equals(Escenario.INDIANA_JONES)) {
                    laberinto[i][j] = Escenario.VACIO;
                }
            }
        }
        laberinto[1][1] = Escenario.INDIANA_JONES;
    }

    public boolean noHayGemasRestantes() {
        for (int i = 0; i < escenario.getFilas(); i++) {
            for (int j = 0; j <escenario.getColumnas(); j++) {
                if (escenario.getCasilla(i, j).equals(Escenario.GEMA) || escenario.getCasilla(i, j).equals(Escenario.GEMA_ROJA)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void recogerVidaExtra() {
        escenario.setVidaExtraRecogida(true);
        escenario.eliminarVidaExtra();
        contadorVidas++;
    }

}