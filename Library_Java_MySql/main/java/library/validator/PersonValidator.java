package library.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;
import library.model.Person;

@Component
public class PersonValidator implements AppValidator<Person> {

    @Override
    public void validate(Person person) {
        if (person == null) {
            throw new ValidationFailedException("Member must not be null!");
        } else if (StringUtils.isBlank(person.getName())) {
            throw new ValidationFailedException("Member full name not specified");
        } else if (StringUtils.isBlank(person.getEmail())) {
            throw new ValidationFailedException("Member email not specified");
        } else if (StringUtils.isBlank(person.getSection())) {
            throw new ValidationFailedException("Member password not specified");
        }

        if (person.getEmail().endsWith("@gmail") || person.getEmail().endsWith("@yahoo")){

        } else{
            throw new ValidationFailedException("Not proper mail format");
        }
    }

    public void validateExistence(Person person) {
        if (person == null) {
            throw new ValidationFailedException("Member does not exist!");
        }
    }
}
