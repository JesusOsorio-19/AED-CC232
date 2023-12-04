package uni.aed.Algoritmo_Floyd_Warshall;


public class Pc5_FloydWarshall {
    
    // Constante para representar un valor infinito. Se usa para distancias iniciales entre nodos no conectados.
    public static final int INF = Integer.MAX_VALUE;
    
    // Método que implementa el algoritmo de Floyd-Warshall
    public void floydWarshall(int[][] graph) {
        int n = graph.length; //número de nodos en el grafo        
        int[][] dist = new int[n][n]; // Matriz que almacenará las distancias más cortas entre los nodos

        // Inicialización de la matriz de distancias con los valores iniciales del grafo
        inicializarMatrizDistancias(graph, dist, n);

        // Aplicación del algoritmo de Floyd-Warshall para calcular las distancias más cortas
        aplicarFloydWarshall(dist, n);

        // Impresión de la matriz de distancias resultante
        imprimirDistancias(dist, n);
    }

    
    private void inicializarMatrizDistancias(int[][] graph, int[][] dist, int n) {
        for (int i = 0; i < n; i++) {
            // Copia la fila i del grafo a la fila i de la matriz de distancias
            System.arraycopy(graph[i], 0, dist[i], 0, n);
        }
    }

    
    private void aplicarFloydWarshall(int[][] dist, int n) {
        // Iteración sobre todos los nodos intermedios k
        for (int k = 0; k < n; k++) {
            // Iteración sobre todos los nodos i
            for (int i = 0; i < n; i++) {
                // Iteración sobre todos los nodos j
                for (int j = 0; j < n; j++) {
                    // Comprobación y actualización de la distancia si se encuentra un camino más corto
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

        private void imprimirDistancias(int[][] dist, int n) {
        System.out.println("Matriz de distancias mínimas:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Si la distancia es infinita, imprime "INF". De lo contrario, imprime la distancia real.
                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        // Matriz de adyacencia que representa el grafo
        int[][] graph = {
            {  0,   10, INF,  30,  INF},
            { 10,    0,  20, INF,  INF},
            {INF,   20,   0,  10,   15},
            { 30,  INF,  10,   0,    5},
            {INF,  INF,  15,   5,    0}
        };

        // Creación de una instancia de la clase y ejecución del algoritmo de Floyd-Warshall
        Pc5_FloydWarshall fw = new Pc5_FloydWarshall();
        fw.floydWarshall(graph);
    }
}
