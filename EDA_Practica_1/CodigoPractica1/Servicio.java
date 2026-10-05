/**
 * Representa una posible localización de un servicio sanitario
 */
public final class Servicio {
    // 
    public final double lon;    // Longitud geográfica (grados)
    public final double lat;    // Latitud geográfica (grados)
    public final String nombre; // Nombre del servicio
    // Datos asignados en el primer problema
    private int indCruce;  // Indice del cruce de la red de carreteras más cercano geográficamente

    public Servicio(String lin) {
        String[] trz = lin.split(";");
        lon = Double.parseDouble(trz[0]);
        lat = Double.parseDouble(trz[1]);
        nombre = trz[2];
    }
    
    // Getters y Setters de los datos asignados      
    public void setCruce(int ind) { indCruce = ind; }    
    public int getCruce() { return indCruce; }
    
    // Fichero solución
    public String toSolucion() { return String.format("%d", indCruce); }    
}
