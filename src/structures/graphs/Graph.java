package structures.graphs;

import structures.node.Node;

import java.util.*;

public class Graph<T> {

    private final Map<Node<T>, Set<Node<T>>> graph;

    public Graph() {
        graph = new LinkedHashMap<>();
    }

    public void add(T data) {

        Node<T> node = new Node<>(data);

        graph.putIfAbsent(node, new LinkedHashSet<>());

    }

    public boolean contains(T data) {
        return graph.containsKey(new Node<>(data));
    }

    public void remove(T data) {

        Node<T> node = new Node<>(data);

        graph.remove(node);

        for (Set<Node<T>> vecinos : graph.values()) {
            vecinos.remove(node);
        }

    }

    public void addEdge(T origen, T destino) {

        Node<T> o = new Node<>(origen);
        Node<T> d = new Node<>(destino);

        graph.putIfAbsent(o, new LinkedHashSet<>());
        graph.putIfAbsent(d, new LinkedHashSet<>());

        graph.get(o).add(d);
        graph.get(d).add(o);

    }

    public void removeEdge(T origen, T destino) {

        Node<T> o = new Node<>(origen);
        Node<T> d = new Node<>(destino);

        if (graph.containsKey(o))
            graph.get(o).remove(d);

        if (graph.containsKey(d))
            graph.get(d).remove(o);

    }

    public Map<Node<T>, Set<Node<T>>> getGraph() {
        return graph;
    }

    public Set<Node<T>> getNodes() {
        return graph.keySet();
    }
    public Set<Node<T>> getNeighbors(T data) {

    Node<T> node = new Node<>(data);

    return graph.getOrDefault(node, new LinkedHashSet<>());

    }
}