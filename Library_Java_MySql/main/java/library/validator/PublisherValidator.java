package library.validator;

import library.model.Publisher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class PublisherValidator implements AppValidator<Publisher>{

    public void validate(Publisher publisher) {

        if (publisher == null) {
            throw new ValidationFailedException("Publisher is null!");
        } else if (StringUtils.isBlank(publisher.getPublisher())) {
            throw new ValidationFailedException("Publisher not specified");
        }

    }
}
