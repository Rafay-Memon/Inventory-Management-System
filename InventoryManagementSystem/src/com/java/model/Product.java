package com.java.model;

/**
 * Represents a product in the inventory with details like ID, name, price, and quantity.
 * This class provides getters and setters to access and modify the product details.
 */
public class Product {

    private final int id;
    private String name;
    private int price;
    private int quantity;

    /**
     * Constructs a new product with the given details.
     * 
     * @param id       the unique identifier for the product
     * @param name     the name of the product
     * @param price    the price of the product
     * @param quantity the quantity of the product in inventory
     */
    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the unique identifier of the product.
     * 
     * @return the product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the product.
     * 
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the product.
     * 
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the quantity of the product in the inventory.
     * 
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the name of the product.
     * 
     * @param name the new name for the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the product.
     * 
     * @param price the new price for the product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the product in the inventory.
     * 
     * @param quantity the new quantity for the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
