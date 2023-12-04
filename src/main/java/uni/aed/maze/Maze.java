package uni.aed.maze;
import java.util.*;
    
public class Maze {
    private int numReglones;
    private int numColumnas;
    private MazeCell[][] celdas;
    private MazeCell celdaDeEntrada;
    private MazeCell celdaDeSalida;

    // Constructor
    public Maze(int numReglones, int numColumnas) {
        this.numReglones = numReglones;
        this.numColumnas = numColumnas;
        this.celdas = new MazeCell[numReglones][numColumnas];
        for (int i = 0; i < numReglones; i++) {
            for (int j = 0; j < numColumnas; j++) {
                celdas[i][j] = new MazeCell(i, j);
            }
        }
        this.celdaDeEntrada = celdas[0][0]; // Celda de entrada en la esquina superior izquierda
        this.celdaDeSalida = celdas[numReglones-1][numColumnas-1]; // Celda de salida en la esquina inferior derecha
    }

    public void limpia() {
        for (int i = 0; i < numReglones; i++) {
            for (int j = 0; j < numColumnas; j++) {
                celdas[i][j].estableceVisitada(false);
            }
        }
    }

    public int obtenContadorColumna() {
        return numColumnas;
    }

    public int obtenContadorReglon() {
        return numReglones;
    }

    public MazeCell obtenCeldaDeEntrada() {
        return celdaDeEntrada;
    }

    public MazeCell obtenCeldaDeSalida() {
        return celdaDeSalida;
    }

    public MazeCell obtenSiguienteCelda(MazeCell celdaActual) {
    int numeReglones = celdaActual.obtenNumReglon();
    int numeColumnas = celdaActual.obtenNumColumna();

    if (numeReglones > 0 && !celdas[numeReglones-1][numeColumnas].esVisitable() && !celdaActual.hayPared(MazeCell.NORTE)) {
        return celdas[numeReglones-1][numeColumnas];
    }
    if (numeReglones < numReglones-1 && !celdas[numeReglones+1][numeColumnas].esVisitable() && !celdaActual.hayPared(MazeCell.SUR)) {
        return celdas[numeReglones+1][numeColumnas];
    }
    if (numeColumnas > 0 && !celdas[numeReglones][numeColumnas-1].esVisitable() && !celdaActual.hayPared(MazeCell.OESTE)) {
        return celdas[numeReglones][numeColumnas-1];
    }
    if (numeColumnas < numColumnas-1 && !celdas[numeReglones][numeColumnas+1].esVisitable() && !celdaActual.hayPared(MazeCell.ESTE)) {
        return celdas[numeReglones][numeColumnas+1];
    }

    return null; 
}

    public MazeCell obtenCeldaAleatoria(MazeCell celdaActual) {
        
        if (celdaActual == null) {
            return null; // Asegúrate de manejar el caso donde celdaActual es nula
        }

        int numeReglones = celdaActual.obtenNumReglon();
        int numeColumnas = celdaActual.obtenNumColumna();
        Random random = new Random();
        ArrayList<MazeCell> visitableCells = new ArrayList<>();

        if (numeReglones > 0 && !celdas[numeReglones-1][numeColumnas].esVisitable() && !celdaActual.hayPared(MazeCell.NORTE)) {
            visitableCells.add(celdas[numeReglones-1][numeColumnas]);
        }
        if (numeReglones < numReglones-1 && !celdas[numeReglones+1][numeColumnas].esVisitable() && !celdaActual.hayPared(MazeCell.SUR)) {
            visitableCells.add(celdas[numeReglones+1][numeColumnas]);
        }
        if (numeColumnas > 0 && !celdas[numeReglones][numeColumnas-1].esVisitable() && !celdaActual.hayPared(MazeCell.OESTE)) {
            visitableCells.add(celdas[numeReglones][numeColumnas-1]);
        }
        if (numeColumnas < numColumnas-1 && !celdas[numeReglones][numeColumnas+1].esVisitable() && !celdaActual.hayPared(MazeCell.ESTE)) {
            visitableCells.add(celdas[numeReglones][numeColumnas+1]);
        }

        if (visitableCells.isEmpty()) {
            return null;
        } 
        else {
            // Asegúrate de excluir las celdas que ya han sido visitadas
            while (!visitableCells.isEmpty()) {
                int randomIndex = random.nextInt(visitableCells.size());
                MazeCell selectedCell = visitableCells.get(randomIndex);
                if (!selectedCell.esVisitable()) {
                    visitableCells.remove(randomIndex);
                } else {
                    return selectedCell;
                }
            }
            return null;
        }
    }



}
