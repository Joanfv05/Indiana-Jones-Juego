package model.entities;

public class Usuario {

    private String nombre;
    private String contrasena;

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contrasena = contraseña;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return nombre;
    }



}
