package Bll.validators;

import Model.Product;

public class ProductStockValidator implements Validator<Product>{

    /**
     * The function verifies if the product has a proper stock given to it
     * @param product product whose stock should be verified
     * @param i parameter to check whether to verify in case of insert or update
     */

    @Override
    public void validate(Product product, int i) {
        if(product.getStock()==0 && i==0){
            throw new IllegalArgumentException("Stock is not valid!");
        }
    }
}
