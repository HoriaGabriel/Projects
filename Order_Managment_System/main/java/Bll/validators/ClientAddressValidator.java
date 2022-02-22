package Bll.validators;

import Model.Client;

public class ClientAddressValidator implements Validator<Client>{

    /**
     * The function verifies if the client has a proper address given to it
     * @param client client whose address should be verified
     * @param i parameter to check whether to verify in case of insert or update
     */

    @Override
    public void validate(Client client,int i) {

        if(client.getAddress().isEmpty()==true && i==0){
            throw new IllegalArgumentException("Address is not valid!");
        }
    }
}
