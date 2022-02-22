package BusinessLayer;

import java.util.ArrayList;
import java.util.Iterator;

public class CompositeProduct extends MenuItem{

    ArrayList<MenuItem> compList = new ArrayList<>();

    /**
     * comp list getter
     * @return returns the list of simple products making up the composite one
     */
    public ArrayList<MenuItem> getCompList() {
        return compList;
    }

    /**
     * comp list setter
     * @param compList sets the list of simple products making up the composite one
     */
    public void setCompList(ArrayList<MenuItem> compList) {
        this.compList = compList;
    }

    /**
     * The function calculates the price of the composite product
     * @return returns the computed price
     */
    public int priceCalculator(){
        int price=0;
        Iterator<MenuItem> iterator =compList.iterator();
        while(iterator.hasNext()){

            MenuItem curentItem = iterator.next();
            price+=curentItem.getPrice();
        }
        return price;
    }

    /**
     * The function calculates the fat of the composite product
     * @return returns the computed fat
     */
    public int fatCalculator(){
        int fat=0;
        Iterator<MenuItem> iterator =compList.iterator();
        while(iterator.hasNext()){

            MenuItem curentItem = iterator.next();
            fat+=curentItem.getFat();
        }
        return fat;
    }

    /**
     * The function calculates the sodium of the composite product
     * @return returns the computed sodium
     */
    public int sodiumCalculator(){
        int sodium=0;
        Iterator<MenuItem> iterator =compList.iterator();
        while(iterator.hasNext()){

            MenuItem curentItem = iterator.next();
            sodium+=curentItem.getSodium();
        }
        return sodium;
    }

    /**
     * The function calculates the protein of the composite product
     * @return returns the computed protein
     */
    public int proteinCalculator(){
        int protein=0;
        Iterator<MenuItem> iterator =compList.iterator();
        while(iterator.hasNext()){

            MenuItem curentItem = iterator.next();
            protein+=curentItem.getProtein();
        }
        return protein;
    }

    /**
     * The function calculates the calories of the composite product
     * @return returns the computed calories
     */
    public int caloriesCalculator(){
        int calories=0;
        Iterator<MenuItem> iterator =compList.iterator();
        while(iterator.hasNext()){

            MenuItem curentItem = iterator.next();
            calories+=curentItem.getCalories();
        }
        return calories;
    }

    /**
     * The function calculates the ratings of the composite product
     * @return returns the computed ratings
     */
    public float ratingCalculator(){
        float rating=0;
        Iterator<MenuItem> iterator =compList.iterator();
        while(iterator.hasNext()){

            MenuItem curentItem = iterator.next();
            rating+=curentItem.getRating();
        }
        return rating;
    }
}

