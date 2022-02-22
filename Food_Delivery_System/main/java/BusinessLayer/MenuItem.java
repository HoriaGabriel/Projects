package BusinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    public String name;
    public int price;
    public float rating;
    public int calories;
    public int protein;
    public int fat;
    public int sodium;

    /**
     * Price getter
     * @return returns the price of the menu item
     */
    public int getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price sets the price of the menu item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Name getter
     * @return returns the name of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name sets the name of the menu item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Rating getter
     * @return returns the rating of the menu item
     */
    public float getRating() {
        return rating;
    }

    /**
     * Rating setter
     * @param rating sets the rating of the menu item
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Calories getter
     * @return returns the calories of the menu item
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Calories setter
     * @param calories sets the calories of the menu item
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Protein getter
     * @return returns the protein of the menu item
     */
    public int getProtein() {
        return protein;
    }

    /**
     * Protein setter
     * @param protein sets the protein of the menu item
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * Fat getter
     * @return returns the fat of the menu item
     */
    public int getFat() {
        return fat;
    }

    /**
     * Fat setter
     * @param fat sets the fat of the menu item
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**
     * Sodium getter
     * @return returns the sodium of the menu item
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * Sodium setter
     * @param sodium sets the sodium of the menu item
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

}

