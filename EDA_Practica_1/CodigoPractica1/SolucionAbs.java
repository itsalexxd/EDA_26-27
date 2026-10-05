import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * Esquema de una solución de la primera práctica
 * Se debe crear la clase Solucion que debe heredar de esta clase e implementar
 * sus métodos abstractos
 */
public abstract class SolucionAbs {
    
    // Datos del modelo
    List<Cruce> cruces;       // Lista de cruces (nodos) de la red de carreteras
    List<Tramo> tramos;       // Lista de tramos (aristas) de la red de carreteras
    List<Nucleo> nucleos;     // Lista de nucleos de población
    List<Servicio> servicios; // Lista de puntos de posible servicio
       
    /**
     * Lee las listas de cruces, tramos, nucleos y servicios de la zona que se analiza
     * @param dir   Directorio donde se encuentran los ficheros
     * @throws IOException
     */
    public void LeeFicheros(String dir) throws IOException {
        cruces = Files.lines(Path.of(dir, "cruces.txt")).map(lin -> new Cruce(lin)).toList();
        tramos = Files.lines(Path.of(dir, "tramos.txt")).map(lin -> new Tramo(lin)).toList();
        nucleos = Files.lines(Path.of(dir, "nucleos.txt")).map(lin -> new Nucleo(lin)).toList();
        servicios = Files.lines(Path.of(dir, "servicios.txt")).map(lin -> new Servicio(lin)).toList();        
    }
    
    public void EscribeResultados(String dir) throws IOException {
        Files.write(Path.of(dir,"res_ser.txt"), servicios.stream().map(s -> s.toSolucion()).toList(), StandardOpenOption.CREATE);
        Files.write(Path.of(dir,"res_nuc.txt"), nucleos.stream().map(x -> x.toSolucion()).toList(), StandardOpenOption.CREATE);
        
    }

    /**
     * Calcula la representación mediante listas de adyacencia del grafo que
     * representa la red de carreteras.
     * 
     * @return  Un array[0..n-1], donde n es el número de cruces, conteniendo
     * para cada cruce la lista de tramos que tienen ese cruce como origen.
     */
    public abstract List<Tramo>[] CreaAdyacencia();
    
    /**
     * Asigna el atributo {@link Servicio#indCruce} de los elementos de la lista
     * {@link #servicios}, de forma que contenga el indice (en la lista {@link #cruces})
     * del Cruce mas cercano geográficamente al servicio (usando la función
     * {@link #DistQ2(..)} para comparar distancias entre puntos)
     */
    public abstract void Problema1();
       
    /**
     * Calcula el camino óptimo (tiempo mínimo) desde el cruce origen a todos
     * los cruces, usando el algoritmo de Dijkstra.
     * 
     * @param ady   Un array[0..n-1], donde n es el número de cruces, conteniendo
     * para cada cruce la lista de tramos que tienen ese cruce como origen.
     * @param origen    Indice del cruce origen
     * @return  Un array[0..n-1] que almacena el tiempo del camino óptimo desde
     * el cruce origen al cruce con ese índice en la lista de cruces
     */
    public abstract double[] Dijkstra(List<Tramo>[] ady, int origen);

    /**
     * Asigna los atributos {@link Nucleo#indServicio} y {@link Nucleo#tpoServicio} de 
     * los elemenos de la lista {@link #nucleos}, de forma que indiquen el punto de servicio
     * más cercano por carretera para cada nucleo de población.
     * 
     * Se utilizará la función {@link #Dijkstra(..)} para calcular el tiempo mínimo
     * desde cada nucleo a todos los puntos de servicio, de forma que pueda escogerse
     * el servicio con mas cercano al nucleo.
     *       
     * @param ady   La lista de adyacencia que representa la red de carreteras
     */
    public abstract void Problema2(List<Tramo>[] ady);
    
    /**
     * Estimación de la distancia (en unidades sin especificar) al cuadrado
     * entre dos puntos geográficos, teniendo (parcialmente) en cuenta la
     * distorsión por latitud
     * 
     * Su uso es para comprobar la cercanía entre puntos, no para obtener
     * distancias reales.
     * 
     * @param lon1  Longitud geográfica del primer punto
     * @param lat1  Latitud geográfica del primer punto
     * @param lon2  Longitud geográfica del segundo punto
     * @param lat2  Latitud geográfica del segundo punto
     * @return  Cuadrado de la distancia en unidades sin especificar
     */
    public static double DistQ2(double lon1, double lat1,
                                double lon2, double lat2) {
        double dlon = lon2-lon1;
        double dlat = lat2-lat1;
        double corr = Math.cos((lat1+lat2)*Math.PI/360.0);
        return corr*corr*dlon*dlon + dlat*dlat;        
    }
}
