package model.entities;

import model.managers.PersonajesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Escenario {

    public static final String MURO = "\uD83E\uDDF1";
    public static final String GEMA = "\uD83D\uDC8E";
    public static final String GEMA_ROJA = "\uD83D\uDD34";
    public static final String INDIANA_JONES = "\uD83C\uDFC3\u200D\u27A1\uFE0F";
    public static final String SERPIENTE = "\uD83D\uDC0D";
    public static final String ESCORPION = "\uD83E\uDD82";
    public static final String VACIO = "\u2B1C";
    public static final String VIDA_EXTRA = "\uD83D\uDC9A";

    private static final int MAX_MOVIMIENTOS_PARA_VIDA_EXTRA = 15; // 15 movimientos en vez de 5 para que tengas tiempo de recoger la vida extra

    private int filas;
    private int columnas;
    private String[][] laberinto;
    private List<Gema> gemas;
    private IndianaJones indianaJones;
    private PersonajesManager personajesManager;
    private Coordenadas vidaExtraPosicion;
    private boolean vidaExtraRecogida;
    private int contadorMovimientos;

    public Escenario(int filas, int columnas, IndianaJones indianaJones) {
        this.filas = filas;
        this.columnas = columnas;
        this.laberinto = new String[filas][columnas];
        this.gemas = new ArrayList<>();
        this.indianaJones = indianaJones;
        this.personajesManager = new PersonajesManager(laberinto);
        this.vidaExtraPosicion = null;
        this.vidaExtraRecogida = false;
        this.contadorMovimientos = 0;
        crearEscenario();
    }

    public String[][] crearEscenario() {
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                if (i == 0 || i == filas - 1 || j == 0 || j == columnas - 1) {
                    laberinto[i][j] = Escenario.MURO;
                } else {
                    laberinto[i][j] = Escenario.VACIO;
                }
            }
        }

        laberinto[indianaJones.getCoordenadas().getX()][indianaJones.getCoordenadas().getY()] = Escenario.INDIANA_JONES;

        generacionMuros();
        personajesManager.establecerPosicionesSerpientes();
        personajesManager.establecerPosicionesEscorpiones();

        generarVidaExtra();
        generarGemas();

        return laberinto;
    }

    public void realizarMovimiento() {
        contadorMovimientos++;
        if (contadorMovimientos == MAX_MOVIMIENTOS_PARA_VIDA_EXTRA && !vidaExtraRecogida && vidaExtraPosicion != null) {
            eliminarVidaExtra();
        }
    }

    public String[][] getLaberinto() {
        return laberinto;
    }

    public String getCasilla(int x, int y) {
        return laberinto[x][y];
    }

    public void setCasilla(int x, int y, String elemento) {
        laberinto[x][y] = elemento;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Coordenadas getVidaExtraPosicion() {
        return vidaExtraPosicion;
    }

    public void setVidaExtraRecogida(boolean vidaExtraRecogida) {
        this.vidaExtraRecogida = vidaExtraRecogida;
    }

    public void setVidaExtraPosicion(Coordenadas vidaExtraPosicion) {
        this.vidaExtraPosicion = vidaExtraPosicion;
    }

    public boolean isVidaExtraRecogida() {
        return vidaExtraRecogida;
    }

    public void eliminarVidaExtra() {
        laberinto[vidaExtraPosicion.getX()][vidaExtraPosicion.getY()] = VACIO;
        vidaExtraPosicion = null;
        vidaExtraRecogida = true;
    }

    private void generarGemas() {
        Random random = new Random();
        int cantidadGemas = 3;
        int gemasAzules = 2;

        while (cantidadGemas > 0) {
            int x = random.nextInt(filas);
            int y = random.nextInt(columnas);

            if (puedeColocarElemento(x, y)) {
                String tipoGema;
                if (gemasAzules > 0) {
                    tipoGema = Escenario.GEMA;
                    gemasAzules--;
                } else {
                    tipoGema = Escenario.GEMA_ROJA;
                }
                laberinto[x][y] = tipoGema;
                gemas.add(new Gema(new Coordenadas(x, y), tipoGema));
                cantidadGemas--;
            }
        }
    }

    private void generarVidaExtra() {
        Random random = new Random();
        int intentos = 0;
        int maxIntentos = 100;

        while (intentos < maxIntentos) {
            int x = random.nextInt(filas);
            int y = random.nextInt(columnas);

            if (puedeColocarElemento(x, y)) {
                laberinto[x][y] = Escenario.VIDA_EXTRA;
                vidaExtraPosicion = new Coordenadas(x, y);
                return;
            }

            intentos++;
        }
    }

    private boolean puedeColocarElemento(int x, int y) {
        return laberinto[x][y].equals(Escenario.VACIO)
                && !laberinto[x][y].equals(Escenario.SERPIENTE)
                && !laberinto[x][y].equals(Escenario.ESCORPION)
                && !esAdyacenteAElemento(x, y, Escenario.GEMA);
    }

    private boolean esAdyacenteAElemento(int x, int y, String elemento) {
        for (int i = Math.max(0, x - 3); i <= Math.min(filas - 1, x + 3); i++) {
            for (int j = Math.max(0, y - 3); j <= Math.min(columnas - 1, y + 3); j++) {
                if (laberinto[i][j].equals(elemento)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void generacionMuros() {
        for (int i = 0; i < 5; i++) {
            laberinto[i][4] = Escenario.MURO;
        }
        for (int i = 0; i < 3; i++) {
            laberinto[3][i] = Escenario.MURO;
        }
        for (int i = 0; i < 4; i++) {
            laberinto[6][i] = Escenario.MURO;
        }
        for (int i = 6; i < 9; i++) {
            laberinto[4][i] = Escenario.MURO;
        }
        for (int i = 7; i < 9; i++) {
            laberinto[7][i] = Escenario.MURO;
        }
        for (int i = 7; i < 15; i++) {
            laberinto[9][i] = Escenario.MURO;
        }
        for (int i = 9; i < 13; i++) {
            laberinto[i][2] = Escenario.MURO;
        }
        for (int i = 5; i < 9; i++) {
            laberinto[11][i] = Escenario.MURO;
        }
        for (int i = 11; i < 13; i++) {
            laberinto[11][i] = Escenario.MURO;
        }
        for (int i = 11; i < 13; i++) {
            laberinto[12][i] = Escenario.MURO;
        }
        for (int i = 2; i <= 5; i++) {
            laberinto[i][11] = Escenario.MURO;
            laberinto[i][12] = Escenario.MURO;
        }
        laberinto[8][4] = Escenario.MURO;
        laberinto[2][7] = Escenario.MURO;
        laberinto[1][7] = Escenario.MURO;
        laberinto[13][9] = Escenario.MURO;
    }
}
