package structures.implementations;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import structures.graphs.Graph;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.node.Node;

public class BFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graph<T> graph, T start, T end) {

        Queue<T> cola = new LinkedList<>();
        Set<T> visitados = new LinkedHashSet<>();
        Map<T, T> padre = new HashMap<>();

        cola.offer(start);
        visitados.add(start);

        while (!cola.isEmpty()) {

            T actual = cola.poll();

            if (Objects.equals(actual, end)) {
                break;
            }

            Set<Node<T>> vecinos = graph.getNeighbors(actual);

            if (vecinos == null) {
                continue;
            }

            for (Node<T> nodoVecino : vecinos) {

                T vecino = nodoVecino.getData();

                if (!visitados.contains(vecino)) {

                    visitados.add(vecino);
                    padre.put(vecino, actual);
                    cola.offer(vecino);
                }
            }
        }

        if (!visitados.contains(end)) {
            return PathResult.sinRuta(visitados);
        }

        LinkedList<T> ruta = new LinkedList<>();

        T actual = end;

        while (actual != null) {

            ruta.addFirst(actual);

            if (Objects.equals(actual, start)) {
                break;
            }

            actual = padre.get(actual);
        }

        return new PathResult<>(visitados, ruta);
    }
}