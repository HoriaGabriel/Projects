package library.validator;

import library.model.Reservation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import library.exception.ValidationFailedException;

@Component
public class ReservationValidator implements AppValidator<Reservation>{

    public void validate(Reservation reservation){

        if(reservation==null){
            throw new ValidationFailedException("reservation is null!");
        } else if (StringUtils.isBlank(reservation.getStatus())) {
            throw new ValidationFailedException("Status not specified");
        }
    }

}
