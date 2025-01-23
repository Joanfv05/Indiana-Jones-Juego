package controller;

import model.entities.*;
import model.managers.EscenarioManager;
import views.ListarLaberinto;
import model.managers.PartidaManager;
import views.ListarPartidas;
import views.GestorIO;

public class JuegoController {

    private Usuario usuario;
    private EscenarioManager escenarioManager;
    private ListarLaberinto vista;
    private PartidaManager partidaManager;
    private ListarPartidas vistaPartidas;
    private Partida partidaActual;
    private long tiempoInicio;
    private long tiempoFin;
    private boolean sesionIniciada;

    public JuegoController(Escenario escenario) {
        this.usuario = null;
        this.escenarioManager = new EscenarioManager(escenario);
        this.vista = new ListarLaberinto(escenario.getLaberinto());
        this.sesionIniciada = false;
        this.partidaManager = new PartidaManager();
        this.vistaPartidas = new ListarPartidas();
        this.partidaActual = null;
    }

    public void setUsuario(Usuario usuario) {
        if (sesionIniciada) {
            GestorIO.print("La sesión ya está iniciada con el usuario: " + this.usuario.getNombre());
        } else {
            if (usuario == null) {
                this.usuario = new Usuario("Anónimo");
                GestorIO.print("Iniciando juego como Anónimo.");
            } else {
                this.usuario = usuario;
                GestorIO.print("Bienvenido: " + usuario.getNombre());
            }
            this.sesionIniciada = true;
        }
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }


    public void iniciarJuego() {

            int numeroPartida = partidaManager.obtenerNumeroSiguientePartida();
            partidaActual = new Partida(numeroPartida, this.usuario, 0, 0);

            vista.mostrarEscenario(escenarioManager.getLaberinto());

            tiempoInicio = System.currentTimeMillis();

        do {
            vista.mostrarVida(escenarioManager.getContadorVidas());
            vista.mostrarGemasTotales(escenarioManager.getContadorGemas());
            System.out.println();

            String movimiento;
            boolean movimientoValido;
            do{
                movimiento = GestorIO.getString("Ingrese movimiento (WASD)").toLowerCase();

                movimientoValido = false;
                switch (movimiento) {
                    case "w":
                        escenarioManager.moverArriba();
                        break;
                    case "a":
                        escenarioManager.moverIzquierda();
                        break;
                    case "s":
                        escenarioManager.moverAbajo();
                        break;
                    case "d":
                        escenarioManager.moverDerecha();
                        break;
                    default:
                        GestorIO.print("Movimiento no válido.");
                        continue;
                }
                movimientoValido = true;
            } while (!movimientoValido);

                vista.mostrarEscenario(escenarioManager.getLaberinto());

            } while (!escenarioManager.noHayGemasRestantes() && escenarioManager.getContadorVidas() > 0);

        if (escenarioManager.getContadorVidas() <= 0) {
            GestorIO.print("Has perdido. Fin del juego");
        } else {
            vista.mostrarGameOver();
            vista.mostrarGemasTotales(escenarioManager.getContadorGemas());
        }

            tiempoFin = System.currentTimeMillis();
            long tiempoTranscurrido = tiempoFin - tiempoInicio;
            long tiempoTranscurridoSegundos = tiempoTranscurrido / 1000;
            vista.mostrarTiempo(tiempoTranscurridoSegundos);

            partidaActual.setGemasPuntos(escenarioManager.getContadorGemas());
            partidaActual.setTiempo(tiempoTranscurridoSegundos);

            partidaManager.registrarPartida(partidaActual);

            usuario = null;
            sesionIniciada = false;
            partidaActual = null;
            escenarioManager.reiniciarEscenario();

        vistaPartidas.mostrarPartidas(partidaManager.obtenerRanking());

    }

}