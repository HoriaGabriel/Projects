package Bll.validators;

import Model.Product;

public class ProductPriceValidator implements Validator<Product>{

    /**
     * The function verifies if the product has a proper price given to it
     * @param product product whose price should be verified
     * @param i parameter to check whether to verify in case of insert or update
     */

    @Override
    public void validate(Product product, int i) {

        if(product.getPrice()==0 && i==0){
            throw new IllegalArgumentException("Price is not valid!");
        }
    }
}
