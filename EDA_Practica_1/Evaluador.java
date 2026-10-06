// Autores: Alejandro Garcia Lavandera y Manuel Arribas Martinez

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
 
public class Evaluador {
 
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("Directorio de datos: ");
        String dir = sc.nextLine().trim();
        System.out.print("Numero de repeticiones: ");
        int reps = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Escribir resultados a fichero (s/n): ");
        boolean escribe = sc.nextLine().trim().equalsIgnoreCase("s");
 
        // Tiempos acumulados (nanosegundos) de cada etapa
        long tLectura = 0, tAdy = 0, tP1 = 0, tP2 = 0;
        Solucion sol = null;
 
        for (int r = 1; r <= reps; r++) {
            sol = new Solucion();
 
            long t0 = System.nanoTime();
            sol.LeeFicheros(dir);
            long t1 = System.nanoTime();
            List<Tramo>[] ady = sol.CreaAdyacencia();
            long t2 = System.nanoTime();
            sol.Problema1();
            long t3 = System.nanoTime();
            sol.Problema2(ady);
            long t4 = System.nanoTime();
 
            tLectura += t1 - t0;
            tAdy     += t2 - t1;
            tP1      += t3 - t2;
            tP2      += t4 - t3;
 
            System.out.printf("Repeticion %d: lectura=%.3f ms, adyacencia=%.3f ms, "
                    + "problema1=%.3f ms, problema2=%.3f ms%n",
                    r, (t1 - t0) / 1e6, (t2 - t1) / 1e6,
                    (t3 - t2) / 1e6, (t4 - t3) / 1e6);
        }
 
        System.out.println();
        System.out.println("=== Tiempos medios (" + reps + " repeticiones) ===");
        System.out.printf("Lectura de ficheros : %.3f ms%n", tLectura / 1e6 / reps);
        System.out.printf("Listas adyacencia   : %.3f ms%n", tAdy / 1e6 / reps);
        System.out.printf("Primer problema     : %.3f ms%n", tP1 / 1e6 / reps);
        System.out.printf("Segundo problema    : %.3f ms%n", tP2 / 1e6 / reps);
 
        if (escribe) {
            sol.EscribeResultados(dir);
            System.out.println("Resultados escritos en " + dir);
        }
    }
}
