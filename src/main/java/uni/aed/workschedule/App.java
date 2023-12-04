package uni.aed.workschedule;
import java.util.PriorityQueue;
import java.util.Random;
import uni.aed.linkedlistTDA.LinkedListTDA;

public class App {
    
    //Creamos una unica instancia de random y la reutilizamos. Ya que si no estaremos creando una nueva
    //instancia random cada vez que generemos un numero aleatorio,y esto no sería eficiente si generariamos
    //muchos numeros aleatorios en un periodo de tiempo corto.
    private static final Random randomGenerator = new Random();
    
    public static void main(String[] args) {
        
        // Creamos una instancia de ListaTDA
        LinkedListTDA lista = new LinkedListTDA();
        
        // Valores de entrada
        int M = 1000; // Minutos a simular
        int N = 5;// Número máximo de trabajos que la computadora puede manejar

        // Inicializar variables
        int tiempoEsperaMaximo = 0;
        int[] tiempoEsperaPorPrioridad = new int[N];
        int[] trabajosPorPrioridad = new int[N];
        Trabajo[] trabajosEjecucion = new Trabajo[N];

        // Cola de prioridad de trabajos
        PriorityQueue<Trabajo> colaPrioridad = new PriorityQueue<>(new ComparadorPrioridad());
                   
        // Simulación de la agenda de trabajo
        for (int minuto = 0; minuto < M; minuto++) {

            // Llegada de un nuevo trabajo
            Trabajo nuevoTrabajo = new Trabajo(generarPrioridad(),generarTiempoEjecucion(), minuto);
            colaPrioridad.offer(nuevoTrabajo);


            //agregar trabajo si existe espacio vacio
            boolean flagAdd = false;
            int index = 0;

            for (Trabajo t : trabajosEjecucion) {
                if(t == null){
                    flagAdd = true;
                    break;
                }
                index++;
            }
            if(flagAdd){
                Trabajo trabajoAsignado = colaPrioridad.poll();
                trabajosEjecucion[index] = trabajoAsignado;

                //comprobacion de tiempo de espera
                int tiempoEspera = minuto - trabajoAsignado.getTiempoLlegada();
                tiempoEsperaMaximo = Math.max(tiempoEsperaMaximo, tiempoEspera);

                //actualizacion de los trabajos por prioridad
                tiempoEsperaPorPrioridad[trabajoAsignado.getPrioridad()-1] += tiempoEspera;
                trabajosPorPrioridad[trabajoAsignado.getPrioridad()-1]++;
                
                
            }

            //actualizacion de los tiempos de ejecucion
            for (int i = 0; i < trabajosEjecucion.length; i++) {
                if(trabajosEjecucion[i] == null) continue;
                int tiempoEjecucion = trabajosEjecucion[i].getTiempoEjecucion() - 1;
                trabajosEjecucion[i].setTiempoEjecucion(tiempoEjecucion);

                if(tiempoEjecucion <= 0){
                    trabajosEjecucion[i] = null;
                }
            }
            
        }

        //-calculo de promedios e impresion de los resultados
        // Calcular el tiempo de espera promedio por prioridad
        double[] tiempoEsperaPromedioPorPrioridad = new double[N];

        for (int i = 0; i < N; i++) {
            tiempoEsperaPromedioPorPrioridad[i] = (double) tiempoEsperaPorPrioridad[i] /
            trabajosPorPrioridad[i];
        }

        // Imprimir resultados
        System.out.println("Resultados de la simulación:");
        System.out.println("Tiempo de espera promedio por prioridad:");

        for (int i = 0; i < N; i++) {
            System.out.println("Prioridad " + (i+1) + ": " +
            tiempoEsperaPromedioPorPrioridad[i]);
        }

        System.out.println("Tiempo de espera máximo: " + tiempoEsperaMaximo);
    }

    // Generar prioridad aleatoria entre 1 y 5
    private static int generarPrioridad() {
        return randomGenerator.nextInt(5) + 1;
    }

    // Generar tiempo de ejecución aleatorio entre 1 y 10 minutos
    private static int generarTiempoEjecucion() {
        return randomGenerator.nextInt(10) + 1;
    }

    
}