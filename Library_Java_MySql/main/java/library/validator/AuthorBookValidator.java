package library.validator;

import library.model.AuthorBook;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class AuthorBookValidator implements AppValidator<AuthorBook> {

    public void validate(AuthorBook authorbook){

        /*if(authorbook==null){
            throw new ValidationFailedException("AuthorBook is null!");
        } else if (StringUtils.isBlank(authorbook.getAuthorsID())) {
            throw new ValidationFailedException("Book title not specified");
        } else if(book.getGenre() == null){
            throw new ValidationFailedException("Book genre not specified");
        } else if(book.getPublisher() == null){
            throw new ValidationFailedException("Book publisher not specified");
        } else if(StringUtils.isBlank(book.getISBN())){
            throw new ValidationFailedException("Book isbn not specified");
        }*/

    }
}
