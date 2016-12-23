package com.sduran.api.web.validator;

import com.sduran.api.web.request.MuseumRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MuseumValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return MuseumRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MuseumRequest museumRequest = (MuseumRequest)o;

        if (museumRequest.getCouncilDistrict()<0){
            errors.rejectValue("councilDistrict","field.invalid.value","Council District value can not be negative");
        }
    }
}
