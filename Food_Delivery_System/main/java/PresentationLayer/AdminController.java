package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import DataLayer.DeliverySerializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class AdminController {

    String compositName;
    ReportView rView;

    /**
     * The function initializes the methods for the actionListeners of the administrator
     * @param  adminView the user interface of the administrator
     * @param  mainView the main user interface
     * @param d the delivery service where various operations are performed with the given data
     * @param compositeProduct a product made out of smaller items
     */
    public void adminViewListener(AdministratorView adminView, MainView mainView, DeliveryService d, ArrayList<MenuItem> compositeProduct) {

        backButtonMethod(adminView,mainView);

        reportButtonMethod(adminView,d);

        createProductMethod(adminView,d);

        deleteProductMethod(adminView,d);

        addProductMethod(adminView,d,compositeProduct);

        createCompositProductMethod(adminView,compositeProduct,d);

        editProductMethod(adminView,d);

        showProductMethod(adminView,d);
    }

    /**
     * The function creates a table which contains the various fields making up the product. The
     * function iterates through the menu found in the delivery service and takes all the fields
     * creating the table
     * @param  adminView the user interface of the administrator
     * @param d the delivery service where various operations are performed with the given data
     */
    private void showProductMethod(AdministratorView adminView, DeliveryService d) {

        adminView.showProductButtonListener(e->{

            Vector<String> l = new Vector<>();
            l.add("Name");l.add("Price");l.add("Rating");l.add("Calories");l.add("Protein");l.add("Fat");l.add("Sodium");
            DefaultTableModel myModel = new DefaultTableModel();
            myModel.setColumnIdentifiers(l);

            ArrayList<MenuItem> menu = d.getMenu();
            Iterator<MenuItem> iterator = menu.iterator();

            while(iterator.hasNext()){

                List<Object> obj = new ArrayList<>();
                MenuItem aux = iterator.next();
                obj.add(aux.getName());
                obj.add(aux.getPrice());
                obj.add(aux.getRating());obj.add(aux.getCalories());
                obj.add(aux.getProtein());obj.add(aux.getFat());
                obj.add(aux.getSodium());
                myModel.addRow(obj.toArray());
            }

            JTable myTable = new JTable(myModel);
            JScrollPane tableContainer = new JScrollPane(myTable);
            tableContainer.setBounds(320,50,500,300);
            adminView.add(tableContainer);
        });
    }

    /**
     * The function reads from the adminView the new data and creates a product with the newly found data
     * @param  adminView the user interface of the administrator
     * @param d the delivery service where various operations are performed with the given data
     */
    private void editProductMethod(AdministratorView adminView, DeliveryService d) {
        adminView.editProductButtonListener(e->{
            String referenceName = adminView.getProductNameTextField();
            MenuItem aux = d.searchByName(referenceName);
            int price; int fat; int protein; int sodium; int calories; float rating;
            String name = adminView.getProductNameTextField();
            if(adminView.getProductPriceTextField().isEmpty()){
                price= aux.getPrice();
            } else{
                price = Integer.parseInt(adminView.getProductPriceTextField()); }
            if(adminView.getProductFatTextField().isEmpty()){
                fat= aux.getFat();
            } else{
                fat = Integer.parseInt(adminView.getProductFatTextField()); }
            if(adminView.getProductProteinTextField().isEmpty()){
                protein= aux.getProtein();
            } else{
                protein = Integer.parseInt(adminView.getProductProteinTextField()); }
            if(adminView.getProductSodiumTextField().isEmpty()){
                sodium= aux.getSodium();
            } else{
                sodium = Integer.parseInt(adminView.getProductSodiumTextField()); }
            if(adminView.getProductCaloriesTextField().isEmpty()){
                calories= aux.getCalories();
            } else{
                calories = Integer.parseInt(adminView.getProductCaloriesTextField()); }
            if(adminView.getProductRatingTextField().isEmpty()){
                rating= aux.getRating();
            } else{
                rating = Float.parseFloat(adminView.getProductRatingTextField()); }
            if(aux instanceof BaseProduct) {
                BaseProduct bp2 = new BaseProduct(name, price, fat, protein, sodium, calories, rating);
                d.editMenuItem(aux,bp2);
            }
            DeliverySerializator.serialize(d);
        });
    }

    /**
     * The function creates a composit product using the list of simple products and then
     * adding the new product to the menu
     * @param  adminView the user interface of the administrator
     * @param d the delivery service where various operations are performed with the given data
     * @param compositeProduct a product made out of smaller items
     */
    private void createCompositProductMethod(AdministratorView adminView, ArrayList<MenuItem> compositeProduct, DeliveryService d) {

        adminView.createCompositButtonListener(e->{

            CompositeProduct cp = new CompositeProduct();
            compositName= adminView.getProductNameTextField();
            cp.setName(compositName);
            cp.setCompList(compositeProduct);
            int price=cp.priceCalculator();
            cp.setPrice(price);
            int fat=cp.fatCalculator();
            cp.setFat(fat);
            int sodium=cp.sodiumCalculator();
            cp.setSodium(sodium);
            int calories=cp.caloriesCalculator();
            cp.setCalories(calories);
            int protein=cp.proteinCalculator();
            cp.setProtein(protein);
            float rating=cp.ratingCalculator();
            cp.setRating(rating);
            d.createMenuItem(cp);
            DeliverySerializator.serialize(d);
        });
    }

    /**
     * The function adds the product selected from the mainView into the composite product list
     * @param  adminView the user interface of the administrator
     * @param d the delivery service where various operations are performed with the given data
     * @param compositeProduct a product made out of smaller items
     */
    private void addProductMethod(AdministratorView adminView, DeliveryService d, ArrayList<MenuItem> compositeProduct) {
        adminView.addButtonListener(e->{

            String name = adminView.chosenItem();
            MenuItem aux = d.searchByName(name);
            compositeProduct.add(aux);
            JTextArea aux2 = adminView.getItems();
            aux2.append(name);
            aux2.append("\n");
        });
    }

    /**
     * The function creates the user interface for creating reports
     * adding the new product to the menu
     * @param  adminView the user interface of the administrator
     * @param d the delivery service where various operations are performed with the given data
     */
    private void reportButtonMethod(AdministratorView adminView, DeliveryService d) {
        adminView.reportButtonListener(e->{
            adminView.setVisible(false);
            rView= new ReportView();
            rView.initialize(d);
            ReportController r = new ReportController();
            r.rViewActionListener(rView,d,adminView);
        });
    }

    /**
     * The function searches for the product with the given name and removes it from the menu list
     * adding the new product to the menu
     * @param  adminView the user interface of the administrator
     * @param d the delivery service where various operations are performed with the given data
     */
    private void deleteProductMethod(AdministratorView adminView, DeliveryService d) {

        adminView.deleteProductButtonListener(e->{

            String name=null;
            if(adminView.getProductNameTextField().isEmpty()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                name = adminView.getProductNameTextField();
                MenuItem aux = d.searchByName(name);
                d.deleteMenuItem(aux);
                DeliverySerializator.serialize(d);
            }
        });
    }

    /**
     * The function closes the admin view and returns to the main view
     *@param  adminView the user interface of the administrator
     *@param  mainView the main user interface
     */
    private void backButtonMethod(AdministratorView adminView, MainView mainView) {

        adminView.backButtonListener(e->{
            adminView.setVisible(false);
            mainView.setVisible(true);
        });
    }

    /**
     * The function takes the data from the adminView, creates a new Product and then adds it to the menu
     *@param  adminView the user interface of the administrator
     *@param d the delivery service where various operations are performed with the given data
     */
    private void createProductMethod(AdministratorView adminView, DeliveryService d) {

        adminView.createProductButtonListener(e->{

            String name=null; int price=0; int fat=0; int protein=0; int sodium=0; int calories=0; float rating=0;

            if(adminView.getProductNameTextField().isEmpty() || adminView.getProductPriceTextField().isEmpty() || adminView.getProductFatTextField().isEmpty()
                    || adminView.getProductProteinTextField().isEmpty() || adminView.getProductSodiumTextField().isEmpty() || adminView.getProductCaloriesTextField().isEmpty()
                    || adminView.getProductRatingTextField().isEmpty()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                name = adminView.getProductNameTextField();
                price = Integer.parseInt(adminView.getProductPriceTextField());
                fat = Integer.parseInt(adminView.getProductFatTextField());
                protein = Integer.parseInt(adminView.getProductProteinTextField());
                sodium = Integer.parseInt(adminView.getProductSodiumTextField());
                calories = Integer.parseInt(adminView.getProductCaloriesTextField());
                rating = Float.parseFloat(adminView.getProductRatingTextField());
            }
            if(name!=null && price!=0 && fat!=0 && protein!=0 && sodium!=0 && calories!=0 && rating!=0){
                BaseProduct bp = new BaseProduct(name,price,fat,protein,sodium,calories,rating);
                d.createMenuItem(bp);
                DeliverySerializator.serialize(d);
            }
        });
    }
}

