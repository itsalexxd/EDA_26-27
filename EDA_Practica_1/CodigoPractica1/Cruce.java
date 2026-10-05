/**
 * Representa un cruce (nodo) de la red (grafo) de carreteras
 * Es un punto donde se unen dos o más tramos o bien comienza o termina un 
 * único tramo.
 */
public final class Cruce {
    public final double lon;  // Longitud geográfica (grados)
    public final double lat;  // Latitud geográfica (grados)

    public Cruce(String lin) {
        String[] trz = lin.split(";");
        lon = Double.parseDouble(trz[0]);
        lat = Double.parseDouble(trz[1]);
    }
}
