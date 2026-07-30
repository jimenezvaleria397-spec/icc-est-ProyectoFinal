package structures.node;

import java.util.Objects;

public class Node<T> {

    private T data;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Node<?> otro = (Node<?>) obj;

        return Objects.equals(data, otro.data);
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}