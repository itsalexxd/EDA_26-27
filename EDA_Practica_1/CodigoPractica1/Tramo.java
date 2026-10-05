/**
 * Representa un tramo (arista) de la red (grafo) de carreteras
 * Un tramo conecta un cruce origen con un cruce destino
 */
public final class Tramo {
    public final int ori;     // Indice (en la lista de cruces) del cruce origen
    public final int des;     // Indice del cruce destino
    public final double tpo;  // Tiempo (minutos) que se tarda en recorrer el tramo

    public Tramo(String lin) {
        String[] trz = lin.split(";");
        ori = Integer.parseInt(trz[0]);
        des = Integer.parseInt(trz[1]);
        tpo = Double.parseDouble(trz[2])/60; // En el fichero son segundos
    }
}    
