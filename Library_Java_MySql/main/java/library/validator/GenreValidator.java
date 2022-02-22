package library.validator;

import library.model.Genre;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class GenreValidator implements AppValidator<Genre>{

    public void validate(Genre genre){

        if(genre==null){
            throw new ValidationFailedException("Genre is null!");
        } else if (StringUtils.isBlank(genre.getGenre())) {
            throw new ValidationFailedException("Genre not specified");
        }

    }
}
