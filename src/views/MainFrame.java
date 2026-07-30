package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.MapController;
import models.MapPoint;
import models.VisualizationMode;
import structures.graphs.Graph;

public class MainFrame extends JFrame {

    private Graph<MapPoint> graph;

    private JPanel panelLateral;
    private JPanel panelInferior;
    private MapPanel mapPanel;

    private MapController controller;

    private JComboBox<String> cmbAlgoritmo;
    private JComboBox<String> cmbModo;

    private JButton btnEjecutar;
    private JButton btnLimpiar;
    private JButton btnInicio;
    private JButton btnDestino;
    private JButton btnAgregarNodo;
    private JButton btnEliminarNodo;
    private JButton btnConectar;
    private JButton btnEliminarConexion;

    private JLabel lblResultado;

    public MainFrame() {

        configurarVentana();

        graph = new Graph<>();

        cargarMapa();

        mapPanel = new MapPanel(graph);

        controller = new MapController(graph, mapPanel);

        crearPanelLateral();
        crearPanelInferior();
        configurarEventos();

        add(panelLateral, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarVentana() {

        setTitle("Proyecto Final - Grafos");

        setSize(1200, 750);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(900, 600));
    }

    private void cargarMapa() {

        // =================================================
        // CREACIÓN DE NODOS
        // =================================================

        MapPoint n1 = new MapPoint("N1", 758, 340);
        MapPoint n2 = new MapPoint("N2", 686, 653);
        MapPoint n3 = new MapPoint("N3", 602, 473);
        MapPoint n4 = new MapPoint("N4", 525, 504);
        MapPoint n5 = new MapPoint("N5", 577, 579);
        MapPoint n6 = new MapPoint("N6", 463, 562);
        MapPoint n7 = new MapPoint("N7", 504, 466);
        MapPoint n8 = new MapPoint("N8", 400, 433);
        MapPoint n9 = new MapPoint("N9", 361, 483);
        MapPoint n10 = new MapPoint("N10", 313, 465);
        MapPoint n11 = new MapPoint("N11", 344, 424);
        MapPoint n12 = new MapPoint("N12", 340, 525);
        MapPoint n13 = new MapPoint("N13", 283, 499);
        MapPoint n14 = new MapPoint("N14", 213, 521);
        MapPoint n15 = new MapPoint("N15", 181, 453);
        MapPoint n16 = new MapPoint("N16", 218, 253);
        MapPoint n17 = new MapPoint("N17", 268, 142);
        MapPoint n18 = new MapPoint("N18", 235, 210);
        MapPoint n19 = new MapPoint("N19", 87, 167);
        MapPoint n20 = new MapPoint("N20", 141, 112);
        MapPoint n21 = new MapPoint("N21", 289, 100);
        MapPoint n22 = new MapPoint("N22", 435, 162);
        MapPoint n23 = new MapPoint("N23", 399, 207);
        MapPoint n24 = new MapPoint("N24", 524, 290);
        MapPoint n25 = new MapPoint("N25", 644, 178);
        MapPoint n26 = new MapPoint("N26", 755, 235);

        MapPoint n29 = new MapPoint("N29", 277, 266);
        MapPoint n30 = new MapPoint("N30", 291, 235);
        MapPoint n31 = new MapPoint("N31", 391, 257);
        MapPoint n32 = new MapPoint("N32", 445, 266);
        MapPoint n33 = new MapPoint("N33", 595, 215);
        MapPoint n34 = new MapPoint("N34", 553, 191);
        MapPoint n35 = new MapPoint("N35", 461, 277);
        MapPoint n36 = new MapPoint("N36", 484, 344);
        MapPoint n37 = new MapPoint("N37", 460, 389);
        MapPoint n38 = new MapPoint("N38", 537, 406);
        MapPoint n39 = new MapPoint("N39", 391, 443);
        MapPoint n40 = new MapPoint("N40", 647, 569);

        // =================================================
        // AGREGAR NODOS AL GRAFO
        // =================================================

        graph.add(n1);
        graph.add(n2);
        graph.add(n3);
        graph.add(n4);
        graph.add(n5);
        graph.add(n6);
        graph.add(n7);
        graph.add(n8);
        graph.add(n9);
        graph.add(n10);
        graph.add(n11);
        graph.add(n12);
        graph.add(n13);
        graph.add(n14);
        graph.add(n15);
        graph.add(n16);
        graph.add(n17);
        graph.add(n18);
        graph.add(n19);
        graph.add(n20);
        graph.add(n21);
        graph.add(n22);
        graph.add(n23);
        graph.add(n24);
        graph.add(n25);
        graph.add(n26);
        graph.add(n29);
        graph.add(n30);
        graph.add(n31);
        graph.add(n32);
        graph.add(n33);
        graph.add(n34);
        graph.add(n35);
        graph.add(n36);
        graph.add(n37);
        graph.add(n38);
        graph.add(n39);
        graph.add(n40);

        // =================================================
        // CONEXIONES DE LA PARTE SUPERIOR IZQUIERDA
        // =================================================

        graph.addEdge(n19, n20);
        graph.addEdge(n20, n17);
        graph.addEdge(n17, n21);

        graph.addEdge(n17, n18);
        graph.addEdge(n18, n16);

        graph.addEdge(n16, n29);
        graph.addEdge(n29, n30);
        graph.addEdge(n30, n23);
        graph.addEdge(n23, n22);

        // =================================================
        // CONEXIONES DE LA PARTE SUPERIOR DERECHA
        // =================================================

        graph.addEdge(n22, n25);
        graph.addEdge(n25, n26);
        graph.addEdge(n26, n1);

        graph.addEdge(n22, n34);
        graph.addEdge(n34, n33);
        graph.addEdge(n33, n25);

        // =================================================
        // CONEXIONES DE LA ZONA CENTRAL
        // =================================================

        graph.addEdge(n23, n31);
        graph.addEdge(n31, n32);
        graph.addEdge(n32, n35);
        graph.addEdge(n35, n36);
        graph.addEdge(n36, n24);

        graph.addEdge(n35, n37);
        graph.addEdge(n37, n39);
        graph.addEdge(n39, n8);

        graph.addEdge(n36, n37);
        graph.addEdge(n37, n38);
        graph.addEdge(n38, n7);

        // =================================================
        // CONEXIONES DE LA PARTE INFERIOR
        // =================================================

        graph.addEdge(n7, n8);

        graph.addEdge(n8, n11);
        graph.addEdge(n11, n10);
        graph.addEdge(n10, n13);
        graph.addEdge(n13, n14);
        graph.addEdge(n14, n15);

        graph.addEdge(n13, n12);
        graph.addEdge(n12, n6);
        graph.addEdge(n6, n5);
        graph.addEdge(n5, n2);

        graph.addEdge(n7, n4);
        graph.addEdge(n4, n3);

        graph.addEdge(n11, n9);
        graph.addEdge(n9, n12);

        graph.addEdge(n6, n40);
        graph.addEdge(n40, n5);
    }

    private void crearPanelLateral() {

        panelLateral = new JPanel();

        panelLateral.setPreferredSize(new Dimension(220, 700));

        panelLateral.setLayout(
                new BoxLayout(panelLateral, BoxLayout.Y_AXIS));

        cmbAlgoritmo = new JComboBox<>(
                new String[]{"BFS", "DFS"});

        cmbModo = new JComboBox<>(
                new String[]{"EXPLORATION", "FINAL_PATH"});

        btnEjecutar = new JButton("Ejecutar");
        btnLimpiar = new JButton("Limpiar");

        btnInicio = new JButton("Marcar Inicio");
        btnDestino = new JButton("Marcar Destino");

        btnAgregarNodo = new JButton("Agregar Nodo");
        btnEliminarNodo = new JButton("Eliminar Nodo");

        btnConectar = new JButton("Conectar");
        btnEliminarConexion = new JButton("Eliminar Conexión");

        Dimension tamanioElemento = new Dimension(180, 30);

        cmbAlgoritmo.setMaximumSize(tamanioElemento);
        cmbModo.setMaximumSize(tamanioElemento);

        btnEjecutar.setMaximumSize(tamanioElemento);
        btnLimpiar.setMaximumSize(tamanioElemento);

        btnInicio.setMaximumSize(tamanioElemento);
        btnDestino.setMaximumSize(tamanioElemento);

        btnAgregarNodo.setMaximumSize(tamanioElemento);
        btnEliminarNodo.setMaximumSize(tamanioElemento);

        btnConectar.setMaximumSize(tamanioElemento);
        btnEliminarConexion.setMaximumSize(tamanioElemento);

        cmbAlgoritmo.setAlignmentX(CENTER_ALIGNMENT);
        cmbModo.setAlignmentX(CENTER_ALIGNMENT);

        btnEjecutar.setAlignmentX(CENTER_ALIGNMENT);
        btnLimpiar.setAlignmentX(CENTER_ALIGNMENT);

        btnInicio.setAlignmentX(CENTER_ALIGNMENT);
        btnDestino.setAlignmentX(CENTER_ALIGNMENT);

        btnAgregarNodo.setAlignmentX(CENTER_ALIGNMENT);
        btnEliminarNodo.setAlignmentX(CENTER_ALIGNMENT);

        btnConectar.setAlignmentX(CENTER_ALIGNMENT);
        btnEliminarConexion.setAlignmentX(CENTER_ALIGNMENT);

        panelLateral.add(Box.createVerticalStrut(15));

        panelLateral.add(new JLabel("Algoritmo:"));
        panelLateral.add(Box.createVerticalStrut(5));
        panelLateral.add(cmbAlgoritmo);

        panelLateral.add(Box.createVerticalStrut(10));

        panelLateral.add(new JLabel("Visualización:"));
        panelLateral.add(Box.createVerticalStrut(5));
        panelLateral.add(cmbModo);

        panelLateral.add(Box.createVerticalStrut(20));

        panelLateral.add(btnEjecutar);
        panelLateral.add(Box.createVerticalStrut(5));
        panelLateral.add(btnLimpiar);

        panelLateral.add(Box.createVerticalStrut(20));

        panelLateral.add(btnInicio);
        panelLateral.add(Box.createVerticalStrut(5));
        panelLateral.add(btnDestino);

        panelLateral.add(Box.createVerticalStrut(20));

        panelLateral.add(btnAgregarNodo);
        panelLateral.add(Box.createVerticalStrut(5));
        panelLateral.add(btnEliminarNodo);
        panelLateral.add(Box.createVerticalStrut(5));
        panelLateral.add(btnConectar);
        panelLateral.add(Box.createVerticalStrut(5));
        panelLateral.add(btnEliminarConexion);
    }

    private void crearPanelInferior() {

        panelInferior = new JPanel();

        lblResultado = new JLabel("Esperando ejecución...");

        panelInferior.add(lblResultado);
    }

    private void configurarEventos() {

        btnInicio.addActionListener(e -> {

            controller.marcarInicio();

            lblResultado.setText(
                    "Nodo de inicio seleccionado.");
        });

        btnDestino.addActionListener(e -> {

            controller.marcarDestino();

            lblResultado.setText(
                    "Nodo de destino seleccionado.");
        });

        btnEjecutar.addActionListener(e -> {

            String algoritmo =
                    (String) cmbAlgoritmo.getSelectedItem();

            String modoSeleccionado =
                    (String) cmbModo.getSelectedItem();

            VisualizationMode modo =
                    VisualizationMode.valueOf(modoSeleccionado);

            controller.ejecutar(algoritmo, modo);

            lblResultado.setText(
                    "Ejecución terminada con " + algoritmo + ".");
        });

        btnLimpiar.addActionListener(e -> {

            controller.limpiar();

            lblResultado.setText(
                    "Mapa limpiado. Seleccione inicio y destino.");
        });

        btnAgregarNodo.addActionListener(e -> {

            controller.agregarNodo();

            lblResultado.setText(
                    "Operación de agregar nodo ejecutada.");
        });

        btnEliminarNodo.addActionListener(e -> {

            controller.eliminarNodo();

            lblResultado.setText(
                    "Operación de eliminar nodo ejecutada.");
        });

        btnConectar.addActionListener(e -> {

            controller.conectarNodos();

            lblResultado.setText(
                    "Operación de conexión ejecutada.");
        });

        btnEliminarConexion.addActionListener(e -> {

            controller.eliminarConexion();

            lblResultado.setText(
                    "Operación de eliminar conexión ejecutada.");
        });
    }
}