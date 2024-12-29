package com.java.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * This class represents the GUI view for managing products in the Inventory Management System.
 * It allows the user to add, update, delete, and view product details in a table format.
 */
public class ProductView extends JFrame {
    
    // Text fields for product details input
    public JTextField pnameText, ppriceText, pqtyText;
    
    // Table to display products
    public JTable productTable;
    
    // Buttons for CRUD operations
    public JButton btnAdd, btnUpdate, btnDelete, btnClear;

    /**
     * Constructs the ProductView, initializes the GUI components, and sets up the layout.
     */
    public ProductView() {
        setTitle("Inventory Management System");
        setSize(900, 650);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Top Panel: For product name, price, and quantity input
        JPanel topPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
            "Product Information", 0, 0, new Font("Arial", Font.BOLD, 14), Color.BLUE));
        topPanel.setBackground(new Color(240, 248, 255));

        topPanel.add(createLabel("Product Name:"));
        pnameText = new JTextField();
        topPanel.add(pnameText);

        topPanel.add(createLabel("Product Price:"));
        ppriceText = new JTextField();
        topPanel.add(ppriceText);

        topPanel.add(createLabel("Product Quantity:"));
        pqtyText = new JTextField();
        topPanel.add(pqtyText);
        
        topPanel.setPreferredSize(new Dimension(getWidth(), 60));
        add(topPanel, BorderLayout.NORTH);

        // Center Panel: Table for displaying products
        productTable = new JTable();
        productTable.setRowHeight(25);
        productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
            "Product Table", 0, 0, new Font("Arial", Font.BOLD, 14), Color.BLUE));
        add(scrollPane, BorderLayout.CENTER);

        // Add row selection listener to fill fields with selected product data
        productTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                int selectedRow = productTable.getSelectedRow();
                pnameText.setText(productTable.getValueAt(selectedRow, 1).toString());
                ppriceText.setText(productTable.getValueAt(selectedRow, 2).toString());
                pqtyText.setText(productTable.getValueAt(selectedRow, 3).toString());
            }
        });

        // Bottom Panel: For CRUD operation buttons
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(new Color(245, 245, 245));

        btnAdd = createButton("Add Product", new Color(60, 179, 113));
        btnUpdate = createButton("Update Product", new Color(30, 144, 255));
        btnDelete = createButton("Delete Product", new Color(255, 69, 0));
        btnClear = createButton("Clear Fields", new Color(255, 215, 0));

        bottomPanel.add(btnAdd);
        bottomPanel.add(btnUpdate);
        bottomPanel.add(btnDelete);
        bottomPanel.add(btnClear);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a label with the specified text and default font styling.
     * 
     * @param text the text to be displayed on the label
     * @return a JLabel with the specified text
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    /**
     * Creates a button with the specified text and background color.
     * 
     * @param text the text to be displayed on the button
     * @param bgColor the background color of the button
     * @return a JButton with the specified text and background color
     */
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    /**
     * Updates the product table with the provided data model.
     * 
     * @param model the DefaultTableModel containing the product data
     */
    public void updateTable(DefaultTableModel model) {
        productTable.setModel(model);
    }

    /**
     * Displays a message to the user in a dialog box.
     * 
     * @param message the message to be displayed
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Gets the ID of the selected product in the table.
     * 
     * @return the product ID of the selected row, or -1 if no row is selected
     */
    public int getSelectedRowId() {
        if (productTable.getSelectedRow() != -1) {
            return Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(), 0).toString());
        }
        return -1; // No row selected
    }
}
