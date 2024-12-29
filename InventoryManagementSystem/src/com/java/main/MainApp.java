package com.java.main;

import com.java.controller.ProductController;
import com.java.model.ProductModel;
import com.java.view.ProductView;

/**
 * The MainApp class is the entry point of the application.
 * It initializes the Model, View, and Controller for the product management system,
 * and sets the view to be visible.
 */
public class MainApp {
    
    /**
     * The main method that serves as the entry point for the application.
     * It initializes the ProductView, ProductModel, and ProductController, 
     * and makes the view visible to the user.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an instance of the ProductView
        ProductView view = new ProductView();
        
        // Create an instance of the ProductModel
        ProductModel model = new ProductModel();
        
        // Create an instance of the ProductController, passing the model and view
        ProductController controller = new ProductController(model, view);
        
        // Make the ProductView visible to the user
        view.setVisible(true);
    }
}
