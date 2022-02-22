package DAO;

import Bll.validators.Validator;
import Model.Product;
import View.ProductView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.ConnectionFactory;
import javax.swing.*;

public class ProductDAO extends AbstractDAO<Product>{

    private Product product = new Product();

    /**
     * The function sets up the the DAO of the product by putting the data from the user interface in the
     * products data and the id is setup by selecting the maximum id from the table and then adding 1
     * @param productView the user interface for the product operations
     */

    public void setupProductDAO(ProductView productView) {

        product.setName(productView.getName());
        try{
            product.setPrice(Integer.parseInt(productView.getPrice()));
            product.setStock(Integer.parseInt(productView.getStock()));
        } catch(NumberFormatException e){
            e.printStackTrace();
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
        }
        StringBuilder qC = new StringBuilder();
        qC.append("SELECT max(id) from schooldb.product");
        Connection con = ConnectionFactory.getConnection();
        int clientId = 0;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(qC.toString());
            while (rs.next()) {
                clientId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        product.setId(clientId+1);
    }

    /**
     * The function goes through the list of validators and verifies every condition for the product
     * @param product the product that needs to be validated
     * @param validators the list of validators
     * @param i parameter to check if the method is called in the context of inserting or updating
     * @return returns 1 if all is okay with the data and zero otherwise
     */

    public int validation(Product product, List<Validator<Product>> validators,int i){

        try{
            for(Validator v2: validators){

                v2.validate(product,i);
            }
        } catch(IllegalArgumentException e){
            e.printStackTrace();
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Improper input format", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        return 1;
    }

    /**
     * The function calls the validation method to check if the product is okay and if it is then
     * the insert in the abstract class will be called.
     * @param validators the list of validators for the product
     */

    public void insertProduct(List<Validator<Product>> validators){
        if(validation(product,validators,0)==1) {
            insert(product);
        }
    }

    /**
     * The function calls the delete method in the abstract class
     * @param p the product to be deleted
     */

    public void deleteProduct(Product p){
        delete(p);
    }

    /**
     * The function calls the validation method to check if the product is okay and if it is then
     * the update in the abstract class will be called.
     * @param validators the list of validators for the product
     * @param p the product to be updated
     */

    public void updateProduct(Product p, List<Validator<Product>> validators) {

        if(validation(p,validators,1)==1){
            update(p);
        }
    }

    /**
     * The function selects everything from the table and calls the createObject method in order to make
     * a list of products out of the result and then creates a JTable with the list then shows the table
     * @param productView the user interface of the product
     */

    public void showProducts(ProductView productView) throws SQLException{

        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * from schooldb.product";
        PreparedStatement statement = null;
        statement = con.prepareStatement(sql);

        ResultSet rs = null;
        rs = statement.executeQuery();
        List<Product> pList = new ArrayList<>();
        pList = createObjects(rs);
        JTable table = findAll(pList);

        JScrollPane tableContainer = new JScrollPane(table);
        tableContainer.setBounds(320,50,300,200);
        productView.add(tableContainer);
    }
}

