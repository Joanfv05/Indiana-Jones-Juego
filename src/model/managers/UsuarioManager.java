package model.managers;

import model.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private final List<Usuario> usuarios;

    public UsuarioManager() {
        this.usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Joan","1234"));
        init();
    }

    public boolean comprobacionUsuarios(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(usuario.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobacionContraseña(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getContrasena().equals(usuario.getContrasena())) {
                return true;
            }
        }
        return false;
    }

    public void add(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void remove(Usuario usuario) {
        this.usuarios.remove(usuario);
    }

    private void init() {
        this.add(new Usuario("Joan", "1234"));
        this.add(new Usuario("Oscar", "1234"));
        this.add(new Usuario("Paco", "1234"));
        this.add(new Usuario("u1", "1234"));
        this.add(new Usuario("u2", "1234"));
        this.add(new Usuario("u3", "1234"));
        this.add(new Usuario("u4", "1234"));
    }

}

