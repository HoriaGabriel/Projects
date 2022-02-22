package Bll.validators;

import Model.Product;

public class ProductNameValidator implements Validator<Product>{

    /**
     * The function verifies if the product has a proper name given to it
     * @param product product whose name should be verified
     * @param i parameter to check whether to verify in case of insert or update
     */

    @Override
    public void validate(Product product,int i) {

        if(product.getName().isEmpty()==true && i==0){
            throw new IllegalArgumentException("Name is not valid!");
        }
    }
}
