package structures.implementations;

import java.util.*;

import structures.graphs.Graph;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.node.Node;

public class DFSPathFinder<T> implements PathFinder<T> {

    @Override
    public PathResult<T> find(Graph<T> graph, T start, T end) {

        Stack<Node<T>> pila = new Stack<>();

        Map<Node<T>, Node<T>> padre = new HashMap<>();

        Set<Node<T>> visitados = new LinkedHashSet<>();

        Node<T> inicio = new Node<>(start);
        Node<T> destino = new Node<>(end);

        pila.push(inicio);

        while (!pila.isEmpty()) {

            Node<T> actual = pila.pop();

            if (visitados.contains(actual))
                continue;

            visitados.add(actual);

            if (actual.equals(destino))
                break;

            List<Node<T>> vecinos = new ArrayList<>(graph.getGraph().get(actual));

            Collections.reverse(vecinos);

            for (Node<T> vecino : vecinos) {

                if (!visitados.contains(vecino)) {

                    padre.put(vecino, actual);
                    pila.push(vecino);

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

        return new PathResult<>(visitadosFinal, new LinkedHashSet<>(ruta));

    }

}