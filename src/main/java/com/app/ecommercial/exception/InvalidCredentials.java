package com.app.ecommercial.exception;

import com.app.ecommercial.util.dictionary.ExceptionDictionary;

public class InvalidCredentials extends Exception {
    public InvalidCredentials() {
        super(ExceptionDictionary.InvalidCredentials);
    }
}
