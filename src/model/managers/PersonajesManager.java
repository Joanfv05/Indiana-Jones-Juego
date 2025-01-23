package model.managers;

import model.entities.Coordenadas;
import model.entities.Escenario;
import model.entities.Escorpion;
import model.entities.Serpiente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonajesManager {

    private String[][] laberinto;
    private List<Serpiente> serpientes;
    private List<Escorpion> escorpiones;

    public PersonajesManager(String[][] laberinto) {
        this.laberinto = laberinto;
        this.serpientes = new ArrayList<>();
        this.escorpiones = new ArrayList<>();
    }

    public void moverSerpientes() {
        moverPersonajesMalignos(Escenario.SERPIENTE);
    }

    public void moverEscorpiones() {
        moverPersonajesMalignos(Escenario.ESCORPION);
    }

    private void moverPersonajesMalignos(String tipoPersonaje) {
        Random random = new Random();
        int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                if (laberinto[i][j].equals(tipoPersonaje)) {
                    boolean movido = false;

                    for (int intentos = 0; intentos < 3 && !movido; intentos++) {
                        int[] direccion = direcciones[random.nextInt(direcciones.length)];
                        int nuevoX = i + direccion[0];
                        int nuevoY = j + direccion[1];

                        if (esMovimientoValido(nuevoX, nuevoY) && laberinto[nuevoX][nuevoY].equals(Escenario.VACIO)) {
                            laberinto[i][j] = Escenario.VACIO;
                            laberinto[nuevoX][nuevoY] = tipoPersonaje;
                            actualizarPosicionMaligno(tipoPersonaje, i, j, nuevoX, nuevoY);
                            movido = true;
                        }
                    }

                    if (!movido) {
                        for (int[] direccion : direcciones) {
                            int nuevoX = i + direccion[0];
                            int nuevoY = j + direccion[1];

                            if (esMovimientoValido(nuevoX, nuevoY) && laberinto[nuevoX][nuevoY].equals(Escenario.VACIO)) {
                                laberinto[i][j] = Escenario.VACIO;
                                laberinto[nuevoX][nuevoY] = tipoPersonaje;
                                actualizarPosicionMaligno(tipoPersonaje, i, j, nuevoX, nuevoY);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < laberinto.length && y >= 0 && y < laberinto[0].length;
    }

    public void establecerPosicionesSerpientes() {
        Serpiente[] serpientesArray = {
                new Serpiente(new Coordenadas(7, 1)),
                new Serpiente(new Coordenadas(3, 6)),
                new Serpiente(new Coordenadas(10, 11))
        };

        establecerPosicionesPersonajesMalignos(Escenario.SERPIENTE, serpientesArray);
    }

    public void establecerPosicionesEscorpiones() {
        Escorpion[] escorpionesArray = {
                new Escorpion(new Coordenadas(8, 13)),
                new Escorpion(new Coordenadas(13, 1))
        };

        establecerPosicionesPersonajesMalignos(Escenario.ESCORPION, escorpionesArray);
    }

    private void establecerPosicionesPersonajesMalignos(String tipoPersonaje, Object[] personajesArray) {
        for (Object personaje : personajesArray) {
            if (personaje instanceof Serpiente && tipoPersonaje.equals(Escenario.SERPIENTE)) {
                Serpiente serpiente = (Serpiente) personaje;
                int x = serpiente.getPosicionX();
                int y = serpiente.getPosicionY();

                if (esMovimientoValido(x, y) && laberinto[x][y].equals(Escenario.VACIO)) {
                    laberinto[x][y] = tipoPersonaje;
                    actualizarPosicionMaligno(tipoPersonaje, -1, -1, x, y);
                    serpientes.add(serpiente);
                } else {
                    System.out.println("No se puede colocar " + tipoPersonaje + " en la posición: " + x + ", " + y);
                }
            } else if (personaje instanceof Escorpion && tipoPersonaje.equals(Escenario.ESCORPION)) {
                Escorpion escorpion = (Escorpion) personaje;
                int x = escorpion.getPosicionX();
                int y = escorpion.getPosicionY();

                if (esMovimientoValido(x, y) && laberinto[x][y].equals(Escenario.VACIO)) {
                    laberinto[x][y] = tipoPersonaje;
                    actualizarPosicionMaligno(tipoPersonaje, -1, -1, x, y);
                    escorpiones.add(escorpion);
                } else {
                    System.out.println("No se puede colocar " + tipoPersonaje + " en la posición: " + x + ", " + y);
                }
            }
        }
    }

    private void actualizarPosicionMaligno(String tipoPersonaje, int posXAnterior, int posYAnterior, int posXNuevo, int posYNuevo) {
        if (tipoPersonaje.equals(Escenario.SERPIENTE)) {
            if (posXAnterior >= 0 && posYAnterior >= 0) {
                serpientes.removeIf(serpiente -> serpiente.getPosicionX() == posXAnterior && serpiente.getPosicionY() == posYAnterior);
            }
            serpientes.add(new Serpiente(new Coordenadas(posXNuevo, posYNuevo)));
        } else if (tipoPersonaje.equals(Escenario.ESCORPION)) {
            if (posXAnterior >= 0 && posYAnterior >= 0) {
                escorpiones.removeIf(escorpion -> escorpion.getPosicionX() == posXAnterior && escorpion.getPosicionY() == posYAnterior);
            }
            escorpiones.add(new Escorpion(new Coordenadas(posXNuevo, posYNuevo)));
        }
    }
}
