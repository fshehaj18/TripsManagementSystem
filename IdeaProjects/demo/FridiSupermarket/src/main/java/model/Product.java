package model;

import java.io.Serializable;

public class Product implements Serializable {
    private String barcode;
    private String name;
    private String category;
    private String supplier;
    private double boughtPrice;
    private DateFormat expiryDate;
    private double sellPrice;
    private int quantity;

    public Product(String barcode, String name, String category, String supplier, double boughtPrice, DateFormat expiryDate, double sellPrice, int quantity) {
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.supplier = supplier;
        this.boughtPrice = boughtPrice;
        this.expiryDate = expiryDate;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    public DateFormat getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(DateFormat expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
