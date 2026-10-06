// Autores: Alejandro Garcia Lavandera y Manuel Arribas Martinez

import java.util.ArrayList;
import java.util.List;

public class Solucion extends SolucionAbs {

    // ---------------------------------------------------------------
    // Etapa 2: listas de adyacencia -> Theta(Nc + Nt)
    // ---------------------------------------------------------------
    @Override
    @SuppressWarnings("unchecked")
    public List<Tramo>[] CreaAdyacencia() {
        int n = cruces.size();
        List<Tramo>[] ady = new List[n];
        for (int u = 0; u < n; u++) {
            ady[u] = new ArrayList<>();
        }
        for (Tramo t : tramos) {
            ady[t.ori].add(t);
        }
        return ady;
    }

    // ---------------------------------------------------------------
    // Etapa 3: primer problema -> Theta(Ns * Nc)
    // ---------------------------------------------------------------
    @Override
    public void Problema1() {
        int n = cruces.size();
        for (Servicio s : servicios) {
            double dMin = Double.POSITIVE_INFINITY;
            int uMin = -1;
            for (int u = 0; u < n; u++) {
                Cruce c = cruces.get(u);
                double dist = DistQ2(s.lon, s.lat, c.lon, c.lat);
                if (dist < dMin) {
                    dMin = dist;
                    uMin = u;
                }
            }
            s.setCruce(uMin);
        }
    }

    // ---------------------------------------------------------------
    // Dijkstra con ListaOrd como conjunto Q
    // ---------------------------------------------------------------
    @Override
    public double[] Dijkstra(List<Tramo>[] ady, int origen) {
        int n = ady.length;
        double[] dist = new double[n];
        ListaOrd q = new ListaOrd(dist, n);

        for (int u = 0; u < n; u++) {
            dist[u] = Double.POSITIVE_INFINITY;
            q.anadir(u);
        }
        dist[origen] = 0;
        q.actualizar(origen);

        while (!q.estaVacia()) {
            int u = q.extraerMin();
            for (Tramo t : ady[u]) {
                int v = t.des;
                double alt = dist[u] + t.tpo;
                if (alt < dist[v]) {
                    dist[v] = alt;
                    q.actualizar(v);   // v, no origen
                }
            }
        }
        return dist;
    }

    // ---------------------------------------------------------------
    // Etapa 4: segundo problema
    // ---------------------------------------------------------------
    @Override
    public void Problema2(List<Tramo>[] ady) {
        int m = servicios.size();
        for (Nucleo p : nucleos) {
            double[] dist = Dijkstra(ady, p.indCruce);
            double tpoMin = Double.POSITIVE_INFINITY;
            int indMin = -1;
            for (int i = 0; i < m; i++) {
                int v = servicios.get(i).getCruce();
                if (dist[v] < tpoMin) {
                    tpoMin = dist[v];
                    indMin = i;
                }
            }
            p.setServicio(indMin, tpoMin);
        }
    }
}
