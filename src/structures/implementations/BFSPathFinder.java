package structures.implementations;

import java.util.*;

import structures.graphs.Graph;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.node.Node;

public class BFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graph<T> graph, T start, T end) {

        Queue<Node<T>> cola = new LinkedList<>();

        Map<Node<T>, Node<T>> padre = new HashMap<>();

        Set<Node<T>> visitados = new LinkedHashSet<>();

        Node<T> inicio = new Node<>(start);
        Node<T> destino = new Node<>(end);

        cola.offer(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {

            Node<T> actual = cola.poll();

            if (actual.equals(destino))
                break;

            for (Node<T> vecino : graph.getGraph().get(actual)) {

                if (!visitados.contains(vecino)) {

                    visitados.add(vecino);
                    padre.put(vecino, actual);
                    cola.offer(vecino);

                }

            }

        }

        if (!visitados.contains(destino)) {

            Set<T> recorrido = new LinkedHashSet<>();

            for (Node<T> nodo : visitados)
                recorrido.add(nodo.getData());

            return PathResult.sinRuta(recorrido);

        }

        LinkedList<T> ruta = new LinkedList<>();

        Node<T> actual = destino;

        while (actual != null) {

            ruta.addFirst(actual.getData());
            actual = padre.get(actual);

        }

        Set<T> visitadosFinal = new LinkedHashSet<>();

        for (Node<T> nodo : visitados)
            visitadosFinal.add(nodo.getData());

        return new PathResult<>(visitadosFinal, ruta);

    }

}