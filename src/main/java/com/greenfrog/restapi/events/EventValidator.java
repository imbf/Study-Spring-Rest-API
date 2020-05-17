package com.greenfrog.restapi.events;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDTO eventDTO, Errors errors) {
        if (eventDTO.getMaxPrice() > eventDTO.getMaxPrice() && eventDTO.getMaxPrice() != 0) {
            errors.rejectValue("basePrice", "wrongValue", "BasePrice is Wrong");
            errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is Wrong");
        }

        LocalDateTime endEventDateTime = eventDTO.getEndEventDateTime();
        if (endEventDateTime.isBefore(eventDTO.getBeginEventDateTime()) || endEventDateTime.isBefore(eventDTO.getCloseEnrollmentDateTime()) || endEventDateTime.isBefore(eventDTO.getBeginEnrollmentDateTime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is WrongValue");
        }

        // TODO beginEventDateTime
        // TODO CLoseEnrollmentDateTime
    }
}

