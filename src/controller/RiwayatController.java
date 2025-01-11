package controller;

import controller.HistoryController;
import view.HistoryFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.HistoryFrame;

public class RiwayatController {
    public static void main(String[] args) {
        try {
            // Set system Look and Feel
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.WARNING, "Failed to set Look and Feel", e);
        }

        // Run GUI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // Create and initialize the GUI
                HistoryFrame historyFrame = new HistoryFrame();
                historyFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                // Initialize the controller
                new HistoryController(historyFrame);

                // Make the GUI visible
                historyFrame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, "Error during GUI initialization", e);
            }
        });
    }
}
