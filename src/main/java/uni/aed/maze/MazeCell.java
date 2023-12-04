package uni.aed.maze;

public class MazeCell {
    private int numRenglones;
    private int numColumnas;
    private boolean visitado;
    private boolean[] pared; // Representa las paredes (Norte, Sur, Este, Oeste)

    // Constructor
    public MazeCell(int numRenglones, int numColumnas) {
        this.numRenglones = numRenglones;
        this.numColumnas = numColumnas;
        this.visitado = false;
        this.pared = new boolean[]{true, true, true, true}; // Todas las paredes inicialmente en pie
    }

    // Métodos getters y setters
    public int obtenNumColumna() {
        return numColumnas;
    }

    public int obtenNumReglon() {
        return numRenglones;
    }

    public boolean esVisitable() {
        return visitado;
    }

    public boolean hayPared(int lado) {
        return pared[lado];
    }

    public void derribaPared(int lado) {
        pared[lado] = false;
    }

    public void estableceVisitada(boolean estado) {
        visitado = estado;
    }

    // Constantes para representar los lados de la celda
    public static final int NORTE = 0;
    public static final int SUR = 1;
    public static final int ESTE = 2;
    public static final int OESTE = 3;
}
