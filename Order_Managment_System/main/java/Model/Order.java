package Model;

public class Order {

    private int id;
    private int clientId;
    private int productId;
    private int orderQuantity;

    /**
     * clientId getter
     * @return returns the id of the client
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * clientId setter
     * @param clientId sets the id of the client
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * productId getter
     * @return returns the id of the product
     */
    public int getProductId() {
        return productId;
    }

    /**
     * productId setter
     * @param productId sets the id of the product
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * orderQuantity getter
     * @return returns the orderQuantity of the product
     */
    public int getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * orderQuantity setter
     * @param orderQuantity sets the id of the order
     */
    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     * Id getter
     * @return returns the id of the order
     */
    public int getId() {
        return id;
    }

    /**
     * id setter
     * @param id sets the id of the order
     */
    public void setId(int id) {
        this.id = id;
    }
}

