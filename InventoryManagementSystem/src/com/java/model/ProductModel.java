package com.java.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is responsible for performing CRUD operations (Create, Read, Update, Delete)
 * on the product data stored in the MySQL database. It communicates with the database using JDBC.
 */
public class ProductModel {

    private final Connection conn;

    /**
     * Constructs a new ProductModel object and establishes a database connection.
     */
    public ProductModel() {
        conn = ConnectionProvider.getConnection();
    }

    /**
     * Fetches all products from the database.
     * 
     * @return a list of all products from the 'product' table
     * @throws SQLException if there is an error while accessing the database
     */
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            products.add(new Product(
                rs.getInt("pid"),
                rs.getString("pname"),
                rs.getInt("pprice"),
                rs.getInt("pqty")
            ));
        }
        return products;
    }

    /**
     * Adds a new product to the database.
     * 
     * @param pname the name of the product
     * @param pprice the price of the product
     * @param pqty the quantity of the product
     * @return the number of rows affected by the insert operation
     * @throws SQLException if there is an error while interacting with the database
     */
    public int addProduct(String pname, int pprice, int pqty) throws SQLException {
        String query = "INSERT INTO product (pname, pprice, pqty) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, pname);
        pst.setInt(2, pprice);
        pst.setInt(3, pqty);
        return pst.executeUpdate();
    }

    /**
     * Updates an existing product in the database.
     * 
     * @param pid the ID of the product to update
     * @param pname the new name of the product
     * @param pprice the new price of the product
     * @param pqty the new quantity of the product
     * @return the number of rows affected by the update operation
     * @throws SQLException if there is an error while interacting with the database
     */
    public int updateProduct(int pid, String pname, int pprice, int pqty) throws SQLException {
        String query = "UPDATE product SET pname = ?, pprice = ?, pqty = ? WHERE pid = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, pname);
        pst.setInt(2, pprice);
        pst.setInt(3, pqty);
        pst.setInt(4, pid);
        return pst.executeUpdate();
    }

    /**
     * Deletes a product from the database.
     * 
     * @param pid the ID of the product to delete
     * @return the number of rows affected by the delete operation
     * @throws SQLException if there is an error while interacting with the database
     */
    public int deleteProduct(int pid) throws SQLException {
        String query = "DELETE FROM product WHERE pid = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, pid);
        return pst.executeUpdate();
    }

}
