package menu;

import controller.JuegoController;
import controller.UsuariosController;
import model.entities.Coordenadas;
import model.entities.Escenario;
import model.entities.IndianaJones;
import model.entities.Usuario;
import model.exception.MaximoIntentosAlcanzadosException;
import views.GestorIO;


public class Menu {

    final static int OPCION_SALIR = 3;

    private UsuariosController userController;
    private JuegoController juegoController;
    private IndianaJones indianaJones;


    public Menu() {
        userController = new UsuariosController();
        indianaJones = new IndianaJones("Indiana Jones", new Coordenadas(1, 1));
        Escenario escenario = new Escenario(15, 15, indianaJones);
        juegoController = new JuegoController(escenario);
    }

    public void iniciar() throws MaximoIntentosAlcanzadosException {
        int opcionSeleccionada = 0;

        do {
            System.out.println();
            System.out.println("INDIANA JONES Y LA RECOLETA DE GEMAS");
            System.out.println("====================================");
            mostrarOpciones();
            opcionSeleccionada = solicitarOpcion();
            ejecutarOpcion(opcionSeleccionada);
        } while (opcionSeleccionada != OPCION_SALIR);
    }

    private void mostrarOpciones() {
        System.out.println("1. Establecer usuario (login)");
        System.out.println("2. Jugar al Indiana Jones");
        System.out.println("3. Salir");
    }

    private int solicitarOpcion() {
        return GestorIO.getInt("Seleccione una opción [1-3]", 1, 3);
    }

    private void ejecutarOpcion(int opcion) throws MaximoIntentosAlcanzadosException {

        try {
            switch (opcion) {
                case 1:
                    Usuario usuario = userController.login();
                    juegoController.setUsuario(usuario);
                    break;
                case 2:
                    if (!juegoController.isSesionIniciada()) {
                        juegoController.setUsuario(null);
                    }
                    juegoController.iniciarJuego();
                    break;
                case 3:
                    System.out.println("Hasta la próxima, gracias por jugar!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (MaximoIntentosAlcanzadosException miae) {
            miae.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}






