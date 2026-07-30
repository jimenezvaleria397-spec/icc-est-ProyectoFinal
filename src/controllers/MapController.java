package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import models.MapPoint;
import models.VisualizationMode;
import structures.graphs.Graph;
import structures.graphs.PathFinder;
import structures.graphs.PathResult;
import structures.implementations.BFSPathFinder;
import structures.implementations.DFSPathFinder;
import structures.node.Node;
import views.MapPanel;

public class MapController {

    private final Graph<MapPoint> graph;
    private final MapPanel mapPanel;

    private MapPoint seleccionado1;
    private MapPoint seleccionado2;

    private MapPoint inicio;
    private MapPoint destino;

    private int ultimoX;
    private int ultimoY;
    private boolean existeUltimoClic;

    public MapController(Graph<MapPoint> graph, MapPanel mapPanel) {

        this.graph = graph;
        this.mapPanel = mapPanel;

        this.mapPanel.setController(this);
    }

    // =====================================================
    // CLIC EN EL MAPA
    // =====================================================

    public void onMapClick(int x, int y) {

        ultimoX = x;
        ultimoY = y;
        existeUltimoClic = true;

        MapPoint punto = buscarNodoCercano(x, y);

        if (punto == null) {
            seleccionado1 = null;
            seleccionado2 = null;
            return;
        }

        if (seleccionado1 == null) {

            seleccionado1 = punto;
            seleccionado2 = null;

        } else if (seleccionado1.equals(punto)) {

            seleccionado1 = punto;
            seleccionado2 = null;

        } else {

            seleccionado2 = punto;
        }
    }

    private MapPoint buscarNodoCercano(int x, int y) {

        for (Node<MapPoint> nodo : graph.getNodes()) {

            MapPoint punto = nodo.getData();

            double distancia = Math.sqrt(
                    Math.pow(x - punto.getX(), 2)
                            + Math.pow(y - punto.getY(), 2));

            if (distancia <= 15) {
                return punto;
            }
        }

        return null;
    }

    // =====================================================
    // INICIO Y DESTINO
    // =====================================================

    public void marcarInicio() {

        if (seleccionado1 == null) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Haz clic sobre un nodo primero.");

            return;
        }

        inicio = seleccionado1;
        mapPanel.setInicio(inicio);

        JOptionPane.showMessageDialog(
                mapPanel,
                "Inicio seleccionado: " + inicio.getId());

        limpiarSeleccion();
    }

    public void marcarDestino() {

        if (seleccionado1 == null) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Haz clic sobre un nodo primero.");

            return;
        }

        destino = seleccionado1;
        mapPanel.setDestino(destino);

        JOptionPane.showMessageDialog(
                mapPanel,
                "Destino seleccionado: " + destino.getId());

        limpiarSeleccion();
    }

    // =====================================================
    // AGREGAR NODO
    // =====================================================

    public void agregarNodo() {

        if (!existeUltimoClic) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Primero haz clic en el lugar del mapa donde deseas agregar el nodo.");

            return;
        }

        String id = JOptionPane.showInputDialog(
                mapPanel,
                "Ingrese el ID del nuevo nodo:");

        if (id == null || id.trim().isEmpty()) {
            return;
        }

        id = id.trim();

        MapPoint nuevo = new MapPoint(id, ultimoX, ultimoY);

        if (graph.contains(nuevo)) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Ya existe un nodo con el ID " + id);

            return;
        }

        graph.add(nuevo);
        mapPanel.repaint();

        JOptionPane.showMessageDialog(
                mapPanel,
                "Nodo " + id + " agregado correctamente.");
    }

    // =====================================================
    // ELIMINAR NODO
    // =====================================================

    public void eliminarNodo() {

        if (seleccionado1 == null) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Haz clic sobre el nodo que deseas eliminar.");

            return;
        }

        MapPoint nodoEliminado = seleccionado1;

        graph.remove(nodoEliminado);

        if (nodoEliminado.equals(inicio)) {
            inicio = null;
            mapPanel.setInicio(null);
        }

        if (nodoEliminado.equals(destino)) {
            destino = null;
            mapPanel.setDestino(null);
        }

        limpiarSeleccion();
        mapPanel.limpiarRecorrido();
        mapPanel.repaint();

        JOptionPane.showMessageDialog(
                mapPanel,
                "Nodo " + nodoEliminado.getId() + " eliminado.");
    }

    // =====================================================
    // CONECTAR DOS NODOS
    // =====================================================

    public void conectarNodos() {

        if (seleccionado1 == null || seleccionado2 == null) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Haz clic sobre dos nodos diferentes y después presiona Conectar.");

            return;
        }

        graph.addEdge(seleccionado1, seleccionado2);

        JOptionPane.showMessageDialog(
                mapPanel,
                "Conexión creada entre "
                        + seleccionado1.getId()
                        + " y "
                        + seleccionado2.getId());

        limpiarSeleccion();
        mapPanel.repaint();
    }

    // =====================================================
    // ELIMINAR CONEXIÓN
    // =====================================================

    public void eliminarConexion() {

        if (seleccionado1 == null || seleccionado2 == null) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Haz clic sobre los dos nodos de la conexión que deseas eliminar.");

            return;
        }

        graph.removeEdge(seleccionado1, seleccionado2);

        JOptionPane.showMessageDialog(
                mapPanel,
                "Conexión eliminada entre "
                        + seleccionado1.getId()
                        + " y "
                        + seleccionado2.getId());

        limpiarSeleccion();
        mapPanel.repaint();
    }

    // =====================================================
    // EJECUTAR BFS O DFS
    // =====================================================

    public void ejecutar(String algoritmo, VisualizationMode modo) {

        if (inicio == null || destino == null) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "Debes marcar un nodo de inicio y un nodo de destino.");

            return;
        }

        if (inicio.equals(destino)) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "El inicio y el destino no pueden ser el mismo nodo.");

            return;
        }

        PathFinder<MapPoint> buscador;

        if ("BFS".equalsIgnoreCase(algoritmo)) {
            buscador = new BFSPathFinder<>();
        } else {
            buscador = new DFSPathFinder<>();
        }

        long tiempoInicial = System.nanoTime();

        PathResult<MapPoint> resultado =
                buscador.find(graph, inicio, destino);

        long tiempoFinal = System.nanoTime();

        double tiempoMs =
                (tiempoFinal - tiempoInicial) / 1_000_000.0;

        mapPanel.limpiarRecorrido();

        if (resultado.getPath().isEmpty()) {

            JOptionPane.showMessageDialog(
                    mapPanel,
                    "No existe una ruta entre "
                            + inicio.getId()
                            + " y "
                            + destino.getId()
                            + ".\n\n"
                            + "Tiempo: "
                            + String.format("%.4f", tiempoMs)
                            + " ms\n"
                            + "Visitados: "
                            + resultado.getVisitados().size());

            return;
        }

        if (modo == VisualizationMode.EXPLORATION) {

            animar(resultado);

        } else {

            mapPanel.mostrarRutaFinal(resultado.getPath());
        }

        JOptionPane.showMessageDialog(
                mapPanel,
                "Algoritmo: " + algoritmo
                        + "\nTiempo: "
                        + String.format("%.4f", tiempoMs)
                        + " ms"
                        + "\nVisitados: "
                        + resultado.getVisitados().size()
                        + "\nRuta: "
                        + resultado.getPath());
    }

    private void animar(PathResult<MapPoint> resultado) {

        List<MapPoint> listaVisitados =
                new ArrayList<>(resultado.getVisitados());

        Timer timer = new Timer(250, null);

        final int[] indice = {0};

        timer.addActionListener(evento -> {

            if (indice[0] < listaVisitados.size()) {

                mapPanel.agregarVisitado(
                        listaVisitados.get(indice[0]));

                indice[0]++;

            } else {

                timer.stop();
                mapPanel.mostrarRutaFinal(resultado.getPath());
            }
        });

        timer.start();
    }

    // =====================================================
    // LIMPIAR
    // =====================================================

    public void limpiar() {

        inicio = null;
        destino = null;

        limpiarSeleccion();

        mapPanel.setInicio(null);
        mapPanel.setDestino(null);
        mapPanel.limpiarRecorrido();
        mapPanel.repaint();
    }

    private void limpiarSeleccion() {

        seleccionado1 = null;
        seleccionado2 = null;
    }
}