package views;

import javax.swing.*;

import models.MapPoint;
import structures.graphs.Graph;
import structures.node.Node;

import java.awt.*;

public class MapPanel extends JPanel {

    private Image mapa;
    private Graph<MapPoint> graph;

    public MapPanel(Graph<MapPoint> graph) {

        this.graph = graph;

        setBackground(Color.WHITE);

        ImageIcon icono = new ImageIcon(getClass().getResource("/resources/maps/mapa.jpeg"));
        mapa = icono.getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (mapa != null) {

            g.drawImage(mapa, 0, 0, getWidth(), getHeight(), this);
            g.setColor(Color.RED);

            for (Node<MapPoint> nodo : graph.getNodes()) {

                MapPoint p = nodo.getData();

                g.fillOval(
                        p.getX() - 5,
                        p.getY() - 5,
                        10,
                        10);

            }

        }

    }

}