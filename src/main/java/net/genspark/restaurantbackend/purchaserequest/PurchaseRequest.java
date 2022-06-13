package net.genspark.restaurantbackend.purchaserequest;

public class PurchaseRequest {

    private String date;

    private double price;

    private String username;

    public PurchaseRequest(String date, double price, String username) {
        this.date = date;
        this.price = price;
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
