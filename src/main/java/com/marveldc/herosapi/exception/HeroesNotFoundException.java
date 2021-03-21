package com.marveldc.herosapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HeroesNotFoundException extends Exception {

    public HeroesNotFoundException(String id) {
        super(String.format("Heroes with id %s not found in the system.", id));
    }
}
