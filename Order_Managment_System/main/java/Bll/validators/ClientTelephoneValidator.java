package Bll.validators;

import Model.Client;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientTelephoneValidator implements Validator<Client>{

    /**
     * The function verifies if the client has a proper telephone given to it
     * @param client client whose telephone should be verified
     * @param i parameter to check whether to verify in case of insert or update
     */

    @Override
    public void validate(Client client,int i) {

        if(i==0){
            if(client.getTelephone().isEmpty()==true){
                throw new IllegalArgumentException("Telephone is not valid!");
            }

            String s = client.getTelephone();
            int aux = s.length();

            if(aux!=10 && client.getTelephone().isEmpty()==false){
                throw new IllegalArgumentException("Telephone is not valid!");
            }

            Pattern pattern = Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
            Matcher matcher = pattern.matcher(s);
            if(matcher.matches() && client.getTelephone().isEmpty()==false){ }
            else{
                throw new IllegalArgumentException("Telephone is not valid!");
            }
        }
    }
}

