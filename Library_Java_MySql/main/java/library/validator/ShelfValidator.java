package library.validator;

import library.model.Shelf;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class ShelfValidator implements AppValidator<Shelf>{

    public void validate(Shelf shelf){

        if(shelf==null){
            throw new ValidationFailedException("Shelf is null!");
        } else if (StringUtils.isBlank(shelf.getShelf())) {
            throw new ValidationFailedException("Shelf not specified");
        }

    }
}
