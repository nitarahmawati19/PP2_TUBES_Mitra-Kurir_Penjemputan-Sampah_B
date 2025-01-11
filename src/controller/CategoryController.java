/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

/**
 *
 * @author Davina Putri Kusuma
 */

import view.CategoryView;
import view.HistoryFrame;

public class CategoryController {
    private CategoryView categoryView;
    private HistoryFrame historyFrame;

    public CategoryController(CategoryView view) {
        this.categoryView = view;
        System.out.println("CategoryController initialized");
        setupActions();
    }

    private void setupActions() {
        System.out.println("Setting up actions");
        
        // Gunakan btnaktivitas (sesuai nama di CategoryView)
        if (categoryView.getBtnaktivitas() != null) {
            System.out.println("Button found, adding listener");
            categoryView.getBtnaktivitas().addActionListener(e -> {
                System.out.println("Button clicked!");
                openHistoryFrame();
            });
        } else {
            System.out.println("Button is null!");
        }
    }

    private void openHistoryFrame() {
        System.out.println("Opening history frame");
        if (historyFrame == null) {
            historyFrame = new HistoryFrame();
            new HistoryController(historyFrame);
        }
        historyFrame.setLocationRelativeTo(categoryView);
        historyFrame.setVisible(true);
    }
}