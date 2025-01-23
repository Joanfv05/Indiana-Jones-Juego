package views;

import model.entities.Partida;

import java.util.List;

public class ListarPartidas {

    public void mostrarPartidas(List<Partida> partidas) {
        System.out.println("===== Listado de Partidas =====");
        for (Partida partida : partidas) {
            System.out.println(partida.toString());
        }
        System.out.println("===============================");
    }
}
