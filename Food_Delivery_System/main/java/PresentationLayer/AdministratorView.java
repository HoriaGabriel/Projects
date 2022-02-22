package PresentationLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class AdministratorView extends JFrame{

    private JTextField productNameTextField;
    private JTextField productPriceTextField;
    private JTextField productRatingTextField;
    private JTextField productFatTextField;
    private JTextField productSodiumTextField;
    private JTextField productCaloriesTextField;
    private JTextField productProteinTextField;
    private JButton createProductButton;
    private JButton deleteProductButton;
    private JButton editProductButton;
    private JButton createCompositButton;
    private JButton showProductButton;
    private JButton backButton;
    private JButton addButton;
    private JButton reportButton;
    private JComboBox<String> menuBox;
    private DeliveryService d;
    private JTextArea items;

    /**
     * Product Name getter
     * @return returns the string representing the product name
     */
    public String getProductNameTextField() {
        return productNameTextField.getText();
    }

    /**
     * Product Price getter
     * @return returns the string representing the product price
     */
    public String getProductPriceTextField() {
        return productPriceTextField.getText();
    }

    /**
     * Product Rating getter
     * @return returns the string representing the product rating
     */
    public String getProductRatingTextField() {
        return productRatingTextField.getText();
    }

    /**
     * Product Fat getter
     * @return returns the string representing the product fat
     */
    public String getProductFatTextField() {
        return productFatTextField.getText();
    }

    /**
     * Product Sodium getter
     * @return returns the string representing the product sodium
     */
    public String getProductSodiumTextField() {
        return productSodiumTextField.getText();
    }

    /**
     * Product Calories getter
     * @return returns the string representing the product calories
     */
    public String getProductCaloriesTextField() {
        return productCaloriesTextField.getText();
    }

    /**
     * Product Protein getter
     * @return returns the string representing the product protein
     */
    public String getProductProteinTextField() {
        return productProteinTextField.getText();
    }

    /**
     * Items getter
     * @return returns the items
     */
    public JTextArea getItems() { return items;}

    /**
     * The function initializes the view and sets up the user interface
     */
    public void initialize(DeliveryService d){

        this.d=d;
        this.setTitle("ClientPage");
        this.setSize(900, 650);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);

        this.setContentPane(panel);
        this.setVisible(true);
    }

    /**
     * The function initializes the panel by adding the elements needed for the parameters
     * @param  panel the panel where the buttons and text fields are put
     */
    private void initializeForm(JPanel panel) {

        JLabel introLabel = new JLabel("Administrator Operation");
        introLabel.setBounds(120,10,500,30);

        JLabel clientNameLabel = new JLabel("Product Price: ");
        clientNameLabel.setBounds(10,70,500,30);

        productPriceTextField = new JTextField();
        productPriceTextField.setBounds(95,70,100,30);

        JLabel productNameLabel = new JLabel("Product Name: ");
        productNameLabel.setBounds(10,120,500,30);

        productNameTextField = new JTextField();
        productNameTextField.setBounds(100,120,100,30);

        JLabel quantityLabel = new JLabel("Product Fat: ");
        quantityLabel.setBounds(10,170,500,30);

        productFatTextField = new JTextField();
        productFatTextField.setBounds(80,170,100,30);

        JLabel sodiumLabel = new JLabel("Product Sodium: ");
        sodiumLabel.setBounds(10,210,500,30);

        productSodiumTextField = new JTextField();
        productSodiumTextField.setBounds(110,210,100,30);

        JLabel caloriesLabel = new JLabel("Product Calories: ");
        caloriesLabel.setBounds(10,260,500,30);

        productCaloriesTextField = new JTextField();
        productCaloriesTextField.setBounds(110,260,100,30);

        JLabel proteinLabel = new JLabel("Product Protein: ");
        proteinLabel.setBounds(10,310,500,30);

        productProteinTextField = new JTextField();
        productProteinTextField.setBounds(110,310,100,30);

        JLabel ratingLabel = new JLabel("Product Rating: ");
        ratingLabel.setBounds(10,360,500,30);

        productRatingTextField = new JTextField();
        productRatingTextField.setBounds(110,360,100,30);

        createProductButton = new JButton("Create");
        createProductButton.setBounds(10,410,120,30);

        deleteProductButton = new JButton("Delete");
        deleteProductButton.setBounds(150,410,120,30);

        editProductButton = new JButton("Edit");
        editProductButton.setBounds(10,460,120,30);

        showProductButton = new JButton("Show");
        showProductButton.setBounds(150,460,120,30);

        createCompositButton = new JButton("Composit");
        createCompositButton.setBounds(10,510,120,30);

        backButton = new JButton("Return");
        backButton.setBounds(150,510,120,30);

        addButton = new JButton("Add");
        addButton.setBounds(10,560,120,30);

        reportButton = new JButton("Report");
        reportButton.setBounds(150,560,120,30);

        menuBox = new JComboBox<String>();
        menuBox.setBounds(360,400,120,30);

        ArrayList<MenuItem> list = d.getMenu();

        if(list!=null) {
            Iterator<MenuItem> it = list.iterator();

            while (it.hasNext()) {
                MenuItem curentItem = it.next();

                //if (curentItem instanceof BaseProduct)
                    menuBox.addItem(curentItem.getName());
            }
        }

        items = new JTextArea();
        items.setBounds(500,400,200,130);

        panel.add(introLabel);
        panel.add(clientNameLabel);
        panel.add(productPriceTextField);
        panel.add(productNameLabel);
        panel.add(productNameTextField);
        panel.add(quantityLabel);
        panel.add(productFatTextField);
        panel.add(sodiumLabel);
        panel.add(productSodiumTextField);
        panel.add(caloriesLabel);
        panel.add(productCaloriesTextField);
        panel.add(proteinLabel);
        panel.add(productProteinTextField);
        panel.add(ratingLabel);
        panel.add(productRatingTextField);
        panel.add(createProductButton);
        panel.add(deleteProductButton);
        panel.add(editProductButton);
        panel.add(showProductButton);
        panel.add(createCompositButton);
        panel.add(backButton);
        panel.add(addButton);
        panel.add(menuBox);
        panel.add(items);
        panel.add(reportButton);
    }

    /**
     * The function initializes the actionListener of the backButton
     * @param  actionListener the actionListener of the back button
     */

    public void backButtonListener(ActionListener actionListener){

        backButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the createProductButton
     * @param  actionListener the actionListener of the createProduct button
     */

    public void createProductButtonListener(ActionListener actionListener){

        createProductButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the deleteProductButton
     * @param  actionListener the actionListener of the delete product button
     */

    public void deleteProductButtonListener(ActionListener actionListener){

        deleteProductButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the editProductButton
     * @param  actionListener the actionListener of the edit product button
     */

    public void editProductButtonListener(ActionListener actionListener){

        editProductButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the shoeProductButton
     * @param  actionListener the actionListener of the show product button
     */

    public void showProductButtonListener(ActionListener actionListener){

        showProductButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the createCompositButton
     * @param  actionListener the actionListener of the create composit button
     */

    public void createCompositButtonListener(ActionListener actionListener){

        createCompositButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the addButton
     * @param  actionListener the actionListener of the add button
     */

    public void addButtonListener(ActionListener actionListener){

        addButton.addActionListener(actionListener);
    }

    /**
     * The function initializes the actionListener of the reportButton
     * @param  actionListener the actionListener of the report button
     */

    public void reportButtonListener(ActionListener actionListener){

        reportButton.addActionListener(actionListener);
    }

    /**
     * The function returns the items selected by the menu box
     * @return the selected item
     */

    public String chosenItem(){
        return (String) menuBox.getSelectedItem();
    }
}

