package com.java.controller;

import com.java.model.ProductModel;
import com.java.view.ProductView;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * The ProductController class handles user interactions with the ProductView
 * and communicates with the ProductModel to perform operations like adding, updating,
 * deleting, and retrieving product data from the database.
 */
public class ProductController {
    private final ProductModel model;
    private final ProductView view;

    /**
     * Constructor for the ProductController.
     * Initializes the controller by associating it with the ProductModel and ProductView.
     * Sets up action listeners for the view buttons and loads the initial table data.
     *
     * @param model The ProductModel instance that handles database operations.
     * @param view The ProductView instance that handles user interface components.
     */
    public ProductController(ProductModel model, ProductView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    /**
     * Initializes the controller by setting up action listeners for the buttons
     * and loading initial product data into the table.
     */
    private void initController() {
        view.btnAdd.addActionListener(e -> addProduct());
        view.btnUpdate.addActionListener(e -> updateProduct());
        view.btnDelete.addActionListener(e -> deleteProduct());
        view.btnClear.addActionListener(e -> clearFields());
        loadTableData();
    }

    /**
     * Handles the action of adding a new product to the database.
     * Collects the input data from the view, validates it, and passes it to the model.
     * Displays a success or error message based on the outcome.
     */
    private void addProduct() {
        try {
            String pname = view.pnameText.getText();
            int pprice = Integer.parseInt(view.ppriceText.getText());
            int pqty = Integer.parseInt(view.pqtyText.getText());
            int result = model.addProduct(pname, pprice, pqty);
            if (result > 0) view.showMessage("Product added successfully!");
            loadTableData();
        } catch (NumberFormatException | SQLException e) {
            view.showMessage("Error adding product: " + e.getMessage());
        }
    }

    /**
     * Handles the action of updating an existing product in the database.
     * Retrieves the selected product's ID, collects the updated data, and passes it to the model.
     * Displays a success or error message based on the outcome.
     */
    private void updateProduct() {
        try {
            int selectedRow = view.productTable.getSelectedRow();
            if (selectedRow == -1) {
                view.showMessage("Please select a product to update.");
                return;
            }

            int pid = Integer.parseInt(view.productTable.getValueAt(selectedRow, 0).toString());
            String pname = view.pnameText.getText();
            int pprice = Integer.parseInt(view.ppriceText.getText());
            int pqty = Integer.parseInt(view.pqtyText.getText());

            int result = model.updateProduct(pid, pname, pprice, pqty);
            if (result > 0) view.showMessage("Product updated successfully!");
            loadTableData();
        } catch (NumberFormatException | SQLException e) {
            view.showMessage("Error updating product: " + e.getMessage());
        }
    }

    /**
     * Handles the action of deleting a selected product from the database.
     * Retrieves the product's ID and passes it to the model for deletion.
     * Displays a success or error message based on the outcome.
     */
    private void deleteProduct() {
        try {
            int selectedRow = view.productTable.getSelectedRow();
            if (selectedRow == -1) {
                view.showMessage("Please select a product to delete.");
                return;
            }

            int pid = Integer.parseInt(view.productTable.getValueAt(selectedRow, 0).toString());
            int result = model.deleteProduct(pid);
            if (result > 0) view.showMessage("Product deleted successfully!");
            loadTableData();
        } catch (NumberFormatException | SQLException e) {
            view.showMessage("Error deleting product: " + e.getMessage());
        }
    }

    /**
     * Loads product data from the database into the table in the view.
     * Retrieves all products from the model and updates the table with the retrieved data.
     */
    private void loadTableData() {
        try {
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Price", "Quantity"}, 0);
            model.getAllProducts().forEach(product -> tableModel.addRow(new Object[]{
                product.getId(), product.getName(), product.getPrice(), product.getQuantity()
            }));
            view.updateTable(tableModel);
        } catch (SQLException e) {
            view.showMessage("Error loading data: " + e.getMessage());
        }
    }

    /**
     * Clears the input fields in the view and deselects the product table selection.
     */
    private void clearFields() {
        view.pnameText.setText("");
        view.ppriceText.setText("");
        view.pqtyText.setText("");
        view.productTable.clearSelection();
    }
}
