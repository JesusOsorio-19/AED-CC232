package uni.aed.maze;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(5, 5); // Ejemplo: crea un laberinto de 5x5

        maze.limpia(); // Reinicia el laberinto

        MazeCell current = maze.obtenCeldaDeEntrada();
        MazeCell exit = maze.obtenCeldaDeSalida();

        Stack<MazeCell> stack = new Stack<>();
        stack.push(current);

        while (current != exit) {
            current = maze.obtenCeldaAleatoria(current);
            if (current != null) {
                current.estableceVisitada(true);
                stack.push(current);
            } else {    
                current = stack.pop();
            }
        }

        System.out.println("Ruta Solución:");
        Stack<MazeCell> solutionStack = new Stack<>();
        solutionStack.addAll(stack);

        while (!solutionStack.isEmpty()) {
            System.out.println(solutionStack.pop());
        }


    }
}
