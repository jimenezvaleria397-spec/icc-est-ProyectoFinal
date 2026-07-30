package views;

import controllers.MapController;
import models.MapPoint;
import structures.graphs.Graph;
import structures.node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends JPanel {

    private Image mapa;

    private Graph<MapPoint> graph;

    private MapController controller;

    private MapPoint inicio;

    private MapPoint destino;

    private List<MapPoint> visitados;

    private List<MapPoint> ruta;

    public MapPanel(Graph<MapPoint> graph) {

        this.graph = graph;

        visitados = new ArrayList<>();

        ruta = new ArrayList<>();

        setBackground(Color.WHITE);

        ImageIcon icono = new ImageIcon(getClass().getResource("/resources/maps/mapa.jpeg"));

        mapa = icono.getImage();

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (controller != null) {

                    controller.onMapClick(e.getX(), e.getY());

                }

            }

        });

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Dibujar el mapa
        if (mapa != null) {
            g.drawImage(mapa, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2 = (Graphics2D) g;

        // ===============================
        // Dibujar conexiones del grafo
        // ===============================
        g2.setColor(Color.GRAY);

        for (Node<MapPoint> nodo : graph.getNodes()) {

            MapPoint origen = nodo.getData();

            for (Node<MapPoint> vecino : graph.getNeighbors(origen)) {

                MapPoint destino = vecino.getData();

                g2.drawLine(origen.getX(), origen.getY(), destino.getX(), destino.getY());

            }
        }

        // ===============================
        // Dibujar ruta encontrada
        // ===============================
        if (ruta != null && ruta.size() > 1) {

            Stroke anterior = g2.getStroke();

            g2.setStroke(new BasicStroke(4));

            g2.setColor(Color.RED);

            for (int i = 0; i < ruta.size() - 1; i++) {

                MapPoint a = ruta.get(i);
                MapPoint b = ruta.get(i + 1);

                g2.drawLine(a.getX(), a.getY(), b.getX(), b.getY());

            }

            g2.setStroke(anterior);

        }

        // ===============================
        // Dibujar nodos
        // ===============================
        for (Node<MapPoint> nodo : graph.getNodes()) {

            MapPoint p = nodo.getData();

            if (inicio != null && p.equals(inicio)) {

                g2.setColor(Color.GREEN);

            } else if (destino != null && p.equals(destino)) {

                g2.setColor(Color.ORANGE);

            } else if (visitados.contains(p)) {

                g2.setColor(Color.BLUE);

            } else {

                g2.setColor(Color.RED);

            }

            g2.fillOval(p.getX() - 6, p.getY() - 6, 12, 12);

            g2.setColor(Color.BLACK);

            g2.drawString(p.getId(), p.getX() + 8, p.getY() - 8);

        }

    }

    public void setController(MapController controller) {

        this.controller = controller;

    }

    public void setInicio(MapPoint inicio) {

        this.inicio = inicio;

        repaint();

    }

    public void setDestino(MapPoint destino) {

        this.destino = destino;

        repaint();

    }

    public void agregarVisitado(MapPoint punto) {

        visitados.add(punto);

        repaint();

    }

    public void mostrarRutaFinal(List<MapPoint> ruta) {

        this.ruta = ruta;

        repaint();

    }

    public void limpiarRecorrido() {

        visitados.clear();

        ruta.clear();

        repaint();

    }
}