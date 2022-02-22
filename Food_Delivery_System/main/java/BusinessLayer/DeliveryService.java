package BusinessLayer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Observable;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private Map<Order,ArrayList<MenuItem>> orderMenuMap = new HashMap<Order,ArrayList<MenuItem>>();
    private ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    private  ArrayList<Order> orderList = new ArrayList<Order>();

    /**
     * menu getter
     * @return returns the menu of the delivery service
     */
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    /**
     * orderMenuMap getter
     * @return returns the orderMenuMap of the delivery service
     */
    public Map<Order, ArrayList<MenuItem>> getOrderMenuMap() {
        return orderMenuMap;
    }

    /**
     * menu setter
     * @param menu sets the menu of the delivery service
     */
    public void setMenu(ArrayList<MenuItem> menu) {

        for(MenuItem b: menu) {

            if (searchByName(b.getName()) == null)
                this.menu.add(b);
        }
    }

    /**
     * orderList getter
     * @return returns the order list of the delivery service
     */
    public ArrayList<Order> getOrderList() { return orderList; }

    /**
     * function initializes the main components of the delivery service
     */
    public void initialize() {

        orderMenuMap = new HashMap<Order,ArrayList<MenuItem>>();
        menu = new ArrayList<MenuItem>();
        orderList = new ArrayList<Order>();
    }

    /**
     * Function returns the maximum index of the list of orders
     * @return returns the maximum id of the order
     */
    public int getOrderIndex(){

        try{
            int max=0;

            if(orderList.isEmpty())
                return 0;

            for(Order c: orderList){

                if(orderList.indexOf(c)>max)
                    max=orderList.indexOf(c);
            }

            return max+1;
        } catch(NullPointerException e){
            return 0;
        }
    }

    /**
     * Function adds the menu item m to the menu
     * @param m sets the menu item to be added to the menu
     */
    @Override
    public void createMenuItem(MenuItem m){

        assert m!=null;
        menu.add(m);
        assert isWellFormed(menu,m);
    }

    /**
     * Function checks that the added menu item is the same as the one that was supposed to be added
     * @param m sets the menu item to be checked
     * @param menu the menu of the delivery service
     * @return returns true or false based on the comparisons
     */
    public boolean isWellFormed(ArrayList<MenuItem> menu, MenuItem m){

        if(menu.isEmpty()) return false;
        MenuItem aux =searchByName(m.getName());
        if(aux==null) return false;
        if(aux.getName().compareTo(m.getName())!=0) return false;
        if(aux.getFat()!=m.getFat()) return false;
        if(aux.getSodium()!=m.getSodium()) return false;
        if(aux.getPrice()!=m.getPrice()) return false;
        if(aux.getProtein()!=m.getProtein()) return false;
        if(aux.getRating()!=m.getRating()) return false;
        if(aux.getCalories()!=m.getCalories()) return false;

        return true;
    }

    /**
     * Function searches for the menu item with the name given as parameter
     * @param name the name of the menu item that must be found
     * @return returns the menu item with the given name
     */
    public MenuItem searchByName(String name) {


        Iterator<MenuItem> iterator = menu.iterator();

        while(iterator.hasNext()){

            MenuItem aux = iterator.next();
            String v=aux.getName();
            if(v.compareTo(name)==0){
                return aux;
            }
        }

        return null;
    }

    /**
     * Function removes the menu item aux from the menu
     * @param aux the menu item to be removed
     */
    @Override
    public void deleteMenuItem(MenuItem aux) {

        assert aux!=null;
        int a=menu.size();

        menu.remove(aux);

        assert menu.size()==a-1;
    }

    /**
     * Function edits the menu item aux by comparing it with the base product bp2
     * @param aux the menu item to be edited
     * @param bp2 the base product to compare the menu item to
     */
    @Override
    public void editMenuItem(MenuItem aux, BaseProduct bp2) {

        assert aux!=null;
        assert bp2!=null;
        if(aux.getName().compareTo(bp2.getName())!=0){
            String s = bp2.getName();
            aux.setName(s);
        }
        if(aux.getPrice()!=bp2.getPrice()){
            int newPrice = bp2.getPrice();
            aux.setPrice(newPrice);
        }
        if(aux.getSodium()!=bp2.getSodium()){
            int newSodium = bp2.getSodium();
            aux.setSodium(newSodium);
        }
        if(aux.getFat()!=bp2.getFat()){
            int newFat = bp2.getFat();
            aux.setFat(newFat);
        }
        if(aux.getCalories()!=bp2.getCalories()){
            int newCalories = bp2.getCalories();
            aux.setCalories(newCalories);
        }
        if(aux.getRating()!=bp2.getRating()){
            float newRating = bp2.getRating();
            aux.setRating(newRating);
        }
        if(aux.getProtein()!=bp2.getProtein()){
            int newProtein = bp2.getProtein();
            aux.setProtein(newProtein);
        }
        assert aux==bp2;
    }

    /**
     * Function filters the menu and gets only the items with the specified data
     * @param pName the name of the product for filtering
     * @param pPrice the price of the product for filtering
     * @param pFat the fat of the product for filtering
     * @param pProtein the protein of the product for filtering
     * @param pSodium the sodium of the product for filtering
     * @param pCalories the calories of the product for filtering
     * @param pRating the rating of the product for filtering
     * @return the array list with the filtered data
     */
    public ArrayList<MenuItem> getFilteredMenu(String pName, String pCalories, String pFat, String pPrice,
                                               String pProtein, String pSodium, String pRating) {
        List<MenuItem> filtered1;List<MenuItem> filtered2;
        List<MenuItem> filtered3;List<MenuItem> filtered4;
        List<MenuItem> filtered5;List<MenuItem> filtered6; List<MenuItem> filtered7;

        if(pName!=null){
            filtered1= menu.stream().filter(menuItem -> menuItem.getName().contains(pName))
                    .collect(Collectors.toList()); }
        else filtered1 = menu;

        if(pCalories.isEmpty()) filtered2 = filtered1;
        else { int a=Integer.parseInt(pCalories);
            filtered2= filtered1.stream().filter(menuItem -> menuItem.getCalories()==a).collect(Collectors.toList());}

        if(pFat.isEmpty()) filtered3 = filtered2;
        else { int a=Integer.parseInt(pFat);
            filtered3= filtered2.stream().filter(menuItem -> menuItem.getFat()==a).collect(Collectors.toList());}

        if(pPrice.isEmpty()) filtered4 = filtered3;
        else { int a=Integer.parseInt(pPrice);
            filtered4= filtered3.stream().filter(menuItem -> menuItem.getPrice()==a).collect(Collectors.toList());}

        if(pProtein.isEmpty()) filtered5 = filtered4;
        else { int a=Integer.parseInt(pProtein);
            filtered5= filtered4.stream().filter(menuItem -> menuItem.getProtein()==a).collect(Collectors.toList());}

        if(pSodium.isEmpty()) filtered6 = filtered5;
        else { int a=Integer.parseInt(pSodium);
            filtered6= filtered5.stream().filter(menuItem -> menuItem.getSodium()==a).collect(Collectors.toList());}

        if(pRating.isEmpty()) filtered7 = filtered6;
        else { float a=Float.parseFloat(pRating);
            filtered7= filtered6.stream().filter(menuItem -> menuItem.getRating()==a).collect(Collectors.toList());}

        return (ArrayList<MenuItem>) filtered7;
    }

    /**
     * Function adds the order o to the order list
     * @param o the order to be added to the order list
     */

    @Override
    public void createOrder(Order o) {

        assert o != null;
        int aux=orderList.size();

        orderList.add(o);

        assert aux+1==orderList.size();

        setChanged();
        notifyObservers();
    }

    /**
     * Function computes the price of the order by adding the prices of
     * each product in the composite product list
     * @param compositeOrder the order whose price must be calculated
     * @return the price of the order
     */
    public int priceCalculator(ArrayList<MenuItem> compositeOrder) {

        int price = 0;
        Iterator<MenuItem> iterator = compositeOrder.iterator();
        while (iterator.hasNext()) {

            MenuItem curentItem = iterator.next();
            price += curentItem.getPrice();
        }
        return price;
    }

    /**
     * Function adds the order o and the list of products as an item to the hashmap
     * @param o the order to be added to the hash map
     * @param compositeOrder the list of items to be added to the map
     */
    @Override
    public void createMenuMap(Order o, ArrayList<MenuItem> compositeOrder) {

        assert o != null;
        assert compositeOrder != null;
        int aux=orderMenuMap.size();

        orderMenuMap.put(o, compositeOrder);

        assert orderMenuMap.size()==aux+1;
    }

    /**
     * Function generates a bill based on the order data and the list of products
     * @param o the order
     * @param compositeOrder the list of items
     */
    public void createBill(Order o, ArrayList<MenuItem> compositeOrder) throws IOException {

        FileWriter myWriter = new FileWriter("Bill.txt");
        Integer aux1=o.getOrderId(); Integer aux2=o.getClientId(); Integer aux3=o.getPrice();
        myWriter.append("OrderId is: "); myWriter.append(aux1.toString());myWriter.append("\n");
        myWriter.append("ClientId is: "); myWriter.append(aux2.toString());myWriter.append("\n");
        myWriter.append("Price is: "); myWriter.append(aux3.toString());myWriter.append("\n");
        myWriter.append("Products are:\n");
        for(MenuItem m: compositeOrder){
            myWriter.append(m.getName()); myWriter.append("\n");
        }
        myWriter.close();
    }
}

