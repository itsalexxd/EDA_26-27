import java.util.Locale;

/**
 * Representa un núcleo de población
 */
public final class Nucleo {
    public final int indCruce;   // Indice del cruce desde donde accede este nucleo a la red de carreteras
    public final String nombre;  // Nombre del nucleo de población
    // Datos asignados en el segundo problema
    private int indServicio;     // Indice del punto de servicio más cercano por carretera (sentido Nucleo -> Servicio)
    private double tpoServicio;  // Tiempo que se tarda en llegar al servicio asignado

    public Nucleo(String lin) {
        String[] trz = lin.split(";");
        indCruce = Integer.parseInt(trz[0]);
        nombre = trz[1];
    }
    
    // Getters y Setters de los datos asignados    
    public void setServicio(int ind, double tpo) {
        indServicio = ind;
        tpoServicio = tpo;
    }
    
    public int getIndServicio() { return indServicio; }    
    public double getTpoServicio() { return tpoServicio; }
    
    // Fichero solución
    public String toSolucion() { return String.format(Locale.US, "%d;%.3f", indServicio, tpoServicio); }        
}    
