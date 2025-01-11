package main;

import view.CategoryView;
import controller.CategoryController;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                System.out.println("Starting application");
                CategoryView view = new CategoryView();
                CategoryController controller = new CategoryController(view);
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}