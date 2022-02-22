package library.validator;

import library.model.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class BookValidator implements AppValidator<Book> {

    public void validate(Book book){

        if(book==null){
            throw new ValidationFailedException("Book is null!");
        } else if (StringUtils.isBlank(book.getTitle())) {
            throw new ValidationFailedException("Book title not specified");
        } else if(book.getGenre() == null){
            throw new ValidationFailedException("Book genre not specified");
        } else if(book.getPublisher() == null){
            throw new ValidationFailedException("Book publisher not specified");
        } else if(StringUtils.isBlank(book.getISBN())){
            throw new ValidationFailedException("Book isbn not specified");
        }

    }


}
