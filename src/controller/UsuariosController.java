package controller;

import model.entities.Usuario;
import model.exception.MaximoIntentosAlcanzadosException;
import model.managers.UsuarioManager;
import views.GestorIO;

public class UsuariosController {

    UsuarioManager usuarioManager = new UsuarioManager();

    public Usuario login() throws MaximoIntentosAlcanzadosException {

        int contador = 0;
        do {
            Usuario usuario = new Usuario(GestorIO.getString("Username"), GestorIO.getString("Password"));
            boolean usuarioExiste = usuarioManager.comprobacionUsuarios(usuario);
            boolean contraseñaCorrecta = usuarioManager.comprobacionContraseña(usuario);

            if (usuarioExiste && contraseñaCorrecta) {
                return usuario;
            } else if (usuarioExiste && !contraseñaCorrecta) {
                contador++;
                System.out.println("Error, la contraseña introducida es errónea");
            } else if (!usuarioExiste && contraseñaCorrecta) {
                contador++;
                System.out.println("Error, el usuario introducido no existe");
            } else {
                contador++;
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } while (contador < 3);

        if (contador == 3) {
            throw new MaximoIntentosAlcanzadosException ();
        }
        return null;
    }
}

