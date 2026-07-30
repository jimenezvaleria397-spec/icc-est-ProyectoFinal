package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import models.MapPoint;
import structures.graphs.Graph;

public class MainFrame extends JFrame {

    private JPanel panelLateral;
    private JPanel panelInferior;
    private MapPanel mapPanel;

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

        setTitle("Proyecto Final - Grafos");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Graph<MapPoint> graph = new Graph<>();
        mapPanel = new MapPanel(graph);

        crearPanelLateral();
        crearPanelInferior();

        add(panelLateral, BorderLayout.WEST);
        add(mapPanel, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

    }

    private void crearPanelLateral() {

        panelLateral = new JPanel();
        panelLateral.setPreferredSize(new Dimension(220, 700));
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));

        cmbAlgoritmo = new JComboBox<>(new String[] { "BFS", "DFS" });
        cmbModo = new JComboBox<>(new String[] { "EXPLORATION", "FINAL_PATH" });

        btnEjecutar = new JButton("Ejecutar");
        btnLimpiar = new JButton("Limpiar");

        btnInicio = new JButton("Marcar Inicio");
        btnDestino = new JButton("Marcar Destino");

        btnAgregarNodo = new JButton("Agregar Nodo");
        btnEliminarNodo = new JButton("Eliminar Nodo");

        btnConectar = new JButton("Conectar");
        btnEliminarConexion = new JButton("Eliminar Conexión");

        // AJusto el tamanio de los botones
        cmbAlgoritmo.setMaximumSize(new Dimension(180, 30));
        cmbModo.setMaximumSize(new Dimension(180, 30));

        btnEjecutar.setMaximumSize(new Dimension(180, 30));
        btnLimpiar.setMaximumSize(new Dimension(180, 30));

        btnInicio.setMaximumSize(new Dimension(180, 30));
        btnDestino.setMaximumSize(new Dimension(180, 30));

        btnAgregarNodo.setMaximumSize(new Dimension(180, 30));
        btnEliminarNodo.setMaximumSize(new Dimension(180, 30));

        btnConectar.setMaximumSize(new Dimension(180, 30));
        btnEliminarConexion.setMaximumSize(new Dimension(180, 30));

        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(cmbAlgoritmo);
        panelLateral.add(Box.createVerticalStrut(10));

        panelLateral.add(cmbModo);
        panelLateral.add(Box.createVerticalStrut(20));

        panelLateral.add(btnEjecutar);
        panelLateral.add(btnLimpiar);
        panelLateral.add(Box.createVerticalStrut(20));

        panelLateral.add(btnInicio);
        panelLateral.add(btnDestino);
        panelLateral.add(Box.createVerticalStrut(20));

        panelLateral.add(btnAgregarNodo);
        panelLateral.add(btnEliminarNodo);
        panelLateral.add(btnConectar);
        panelLateral.add(btnEliminarConexion);

    }

    private void crearPanelInferior() {

        panelInferior = new JPanel();

        lblResultado = new JLabel("Esperando ejecución...");

        panelInferior.add(lblResultado);

    }

}