package Bll;

import Bll.validators.ProductPriceValidator;
import Bll.validators.ProductStockValidator;
import Bll.validators.ProductNameValidator;
import Bll.validators.Validator;
import DAO.ProductDAO;
import Model.Product;
import View.ProductView;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO = new ProductDAO();

    /**
     * The function sets up a list of validators where it inserts the various validators
     * for the product such as product name, price or stock
     */

    public void setupValidators(){

        this.validators = new ArrayList<Validator<Product>>();
        this.validators.add(new ProductNameValidator());
        this.validators.add(new ProductPriceValidator());
        this.validators.add(new ProductStockValidator());
    }

    /**
     * The function sets up the the DAO of the product with the productView and then inserts the product
     * into the mysql table
     * @param productView the user interface for the product operations
     */

    public void insertOp(ProductView productView){

        productDAO.setupProductDAO(productView);
        productDAO.insertProduct(validators);
    }

    /**
     * The function searches for the name of the product in the table and returns it if it is in
     * the table and then calls the delete product method to delete it
     * @param productView the user interface for the product operations
     */

    public void deleteOp(ProductView productView){

        Product p;
        p = findProductByName(productView.getName());
        productDAO.deleteProduct(p);
    }

    /**
     * The function searches for the name of the product in the table and returns it if it is in
     * the table and then verifies if there are fields that need updating and if they are the
     * updateProduct method is called with the new product with the updated data
     * @param productView the user interface for the client operations
     */

    public void updateOp(ProductView productView) {

        Product p;
        p = findProductByName(productView.getUpdateName());

        if(productView.getName().isEmpty()==false){
            p.setName(productView.getName());
        }
        if(productView.getStock().isEmpty()==false){
            p.setStock(Integer.parseInt(productView.getStock()));
        }
        if(productView.getPrice().isEmpty()==false){
            p.setPrice(Integer.parseInt(productView.getPrice()));
        }

        productDAO.updateProduct(p,validators);
    }

    /**
     * The function calls the showProducts method in the productDAO in order to show the table
     * @param productView the user interface for the client operations
     */

    public void showOp(ProductView productView) throws SQLException {
        productDAO.showProducts(productView);
    }

    /**
     * The function calls the showProducts method in the productDAO in order to show the table
     * @param name the string from the user interface of the name of the product that needs to be searched for in
     *             the table of products
     * @return returns the product with the found name
     */

    public Product findProductByName(String name) {
        Product p = productDAO.findByName(name);
        if (p == null) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "The product with name " + name + " was not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return p;
    }
}

