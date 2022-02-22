package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {

    private int orderId;
    private int clientId;
    private LocalDate orderDate;
    private int price;
    private int hour;

    /**
     * Price getter
     * @return returns the price of the order
     */
    public int getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price sets the price of the order
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Hour getter
     * @return returns the hour of the order
     */
    public int getHour() {
        return hour;
    }

    /**
     * Hour setter
     * @param hour sets the hour of the order
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * OrderId getter
     * @return returns the id of the order
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * OrderId setter
     * @param orderId sets the id of the order
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * ClientId getter
     * @return returns the id of the client
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * ClientId setter
     * @param clientId sets the id of the client
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * OrderDate getter
     * @return returns the order date of the client
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * OrderDate setter
     * @param orderDate sets the date of the order
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Hashcode maker
     * @return returns the hashcode of the map
     */
    @Override
    public int hashCode()
    {
        int hash = 117;
        hash =+ hash*7111 + 71*orderId+ 233*clientId;
        return hash;
    }

}

