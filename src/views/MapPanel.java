package views;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private Image mapa;

    public MapPanel() {

        setBackground(Color.WHITE);

        mapa = new ImageIcon("resources/map/Mapa.jpeg").getImage();

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        if(mapa != null){

            g.drawImage(mapa,0,0,getWidth(),getHeight(),this);

    }

    }

}