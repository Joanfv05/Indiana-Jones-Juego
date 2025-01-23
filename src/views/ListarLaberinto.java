package views;

public class ListarLaberinto {
   private String [][] laberinto;

    public ListarLaberinto(String[][] laberinto) {
        this.laberinto = laberinto;
    }

    public void mostrarEscenario(String [][] laberinto) {

        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                System.out.print(laberinto[i][j]);
            }
            System.out.println();
        }
    }

    public void mostrarVida(int vida) {
        System.out.println("VIDA: " + vida);
    }

    public void mostrarGemasTotales(int puntos) {
        System.out.println("PUNTOS TOTALES: " + puntos);
    }

    public void mostrarTiempo(long tiempo) {
        System.out.println("TIEMPO: " + tiempo + " seg");
    }

    public void mostrarGameOver() {
        System.out.println("Has ganado. ¡Enhorabuena!");
    }

}
