package BusinessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

    /**
     * Base product constructor. Sets the initial base product
     * @param name the name of the product
     * @param price the price of the product
     * @param fat the fat of the product
     * @param protein the protein of the product
     * @param sodium the sodium of the product
     * @param calories the calories of the product
     * @param rating the rating of the product
     */
    public BaseProduct(String name, int price, int fat, int protein, int sodium, int calories, float rating) {

        this.price=price;
        this.name=name;
        this.rating=rating;
        this.calories=calories;
        this.fat=fat;
        this.protein=protein;
        this.sodium=sodium;
    }

    public BaseProduct() {

    }
}

