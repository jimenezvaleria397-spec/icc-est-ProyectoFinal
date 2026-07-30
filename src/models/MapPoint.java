package models;

import java.util.Objects;

public class MapPoint {

    private String id;
    private int x;
    private int y;

    public MapPoint(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MapPoint otro = (MapPoint) obj;

        return Objects.equals(id, otro.id);
    }

    @Override
    public String toString() {
        return id;
    }
}