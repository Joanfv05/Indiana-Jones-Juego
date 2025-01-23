package model.managers;

import model.entities.Partida;

import model.entities.Partida;

import java.util.ArrayList;
import java.util.List;

public class PartidaManager {
    private final List<Partida> partidas;

    public PartidaManager() {
        this.partidas = new ArrayList<>();
    }

    public void registrarPartida(Partida partida) {
        partidas.add(partida);
    }

    public List<Partida> obtenerRanking() {
        ordenarPartidasPorGemasYTiempo();

        List<Partida> ranking = new ArrayList<>();
        for (int i = 0; i < Math.min(3, partidas.size()); i++) {
            Partida partida = partidas.get(i);
            partida.setNumeroPartida(i + 1);
            ranking.add(partidas.get(i));
        }

        return ranking;
    }

    private void ordenarPartidasPorGemasYTiempo() {
        for (int i = 0; i < partidas.size() - 1; i++) {
            for (int j = 0; j < partidas.size() - i - 1; j++) {
                Partida partidaActual = partidas.get(j);
                Partida partidaSiguiente = partidas.get(j + 1);
                if (partidaActual.getPuntos() < partidaSiguiente.getPuntos()) {
                    intercambiar(j, j + 1);
                } else if (partidaActual.getPuntos() == partidaSiguiente.getPuntos()) {
                    if (partidaActual.getTiempo() > partidaSiguiente.getTiempo()) {
                        intercambiar(j, j + 1);
                    }
                }
            }
        }
    }

    private void intercambiar(int i, int j) {
        Partida temp = partidas.get(i);
        partidas.set(i, partidas.get(j));
        partidas.set(j, temp);
    }

    public int obtenerNumeroSiguientePartida() {
        return partidas.size() + 1;
    }
}