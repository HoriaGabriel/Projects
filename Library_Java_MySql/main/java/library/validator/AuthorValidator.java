package library.validator;

import library.model.Author;;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class AuthorValidator implements AppValidator<Author>{

    public void validate(Author author){

        if(author==null){
            throw new ValidationFailedException("Author is null!");
        } else if (StringUtils.isBlank(author.getName())) {
            throw new ValidationFailedException("Author name not specified");
        }

    }
}
