package structures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PathResult<T> {

    private Set<T> visitados;
    private List<T> path;

    public PathResult(Set<T> visitados, List<T> path) {
        this.visitados = visitados;
        this.path = path;
    }

    public Set<T> getVisitados() {
        return visitados;
    }

    public List<T> getPath() {
        return path;
    }

    public static <T> PathResult<T> sinRuta(Set<T> visitados) {
        return new PathResult<>(visitados, new ArrayList<>());
    }

}