package Bll.validators;

import Model.Client;

public class ClientNameValidator implements Validator<Client>{

    /**
     * The function verifies if the client has a proper name given to it
     * @param client client whose name should be verified
     * @param i parameter to check whether to verify in case of insert or update
     */

    @Override
    public void validate(Client client,int i) {

        if(client.getName().isEmpty()==true){
            throw new IllegalArgumentException("Name is not valid!");
        }
    }
}
