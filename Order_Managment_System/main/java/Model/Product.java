package Model;

public class Product {

    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * Id getter
     * @return returns the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Id setter
     * @param id sets the id of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Name getter
     * @return returns the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name sets the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Price getter
     * @return returns the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price sets the price of the product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Stock getter
     * @return returns the stock of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Stock setter
     * @param stock sets the stock of the product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}

