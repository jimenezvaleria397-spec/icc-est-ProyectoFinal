package persistence;

import models.MapPoint;
import structures.graphs.Graph;

public class FileGraphRepository implements GraphRepository {

    private String path;

    public FileGraphRepository(String path) {
        this.path = path;
    }

    @Override
public Graph<MapPoint> load() {

    return new Graph<>();

}

@Override
public void save(Graph<MapPoint> graph) {

    }
}