package library.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;
import library.model.Librarian;

@Component
public class LibrarianValidator implements AppValidator<Librarian> {

    public void validate(Librarian librarian){

        if(librarian==null){
            throw new ValidationFailedException("Login failed!");
        } else if (StringUtils.isBlank(librarian.getName())) {
            throw new ValidationFailedException("Librarian full name not specified");
        } else if (StringUtils.isBlank(librarian.getPassword())) {
            throw new ValidationFailedException("Librarian password not specified");
        } else if (StringUtils.isBlank(librarian.getAddress())) {
            throw new ValidationFailedException("Librarian address not specified");
        } else if(StringUtils.isBlank(librarian.getPhoneNumber())){
            throw new ValidationFailedException("Librarian phone number not specified");
        }

    }

}
