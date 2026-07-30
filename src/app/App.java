package app;

import javax.swing.SwingUtilities;
import views.MainFrame;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainFrame ventana = new MainFrame();
            ventana.setVisible(true);
        });

    }

}
