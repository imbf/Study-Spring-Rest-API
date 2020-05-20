package com.greenfrog.restapi.events;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDTO eventDTO, Errors errors) {
        if (eventDTO.getMaxPrice() < eventDTO.getBasePrice() && eventDTO.getMaxPrice() != 0) {
            errors.reject("wrongPrices", "Values fo prices are wrong ");
        }

        LocalDateTime endEventDateTime = eventDTO.getEndEventDateTime();
        if (endEventDateTime.isBefore(eventDTO.getBeginEventDateTime()) || endEventDateTime.isBefore(eventDTO.getCloseEnrollmentDateTime()) || endEventDateTime.isBefore(eventDTO.getBeginEnrollmentDateTime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is WrongValue");
        }

        // TODO beginEventDateTime
        // TODO CLoseEnrollmentDateTime
    }
}

