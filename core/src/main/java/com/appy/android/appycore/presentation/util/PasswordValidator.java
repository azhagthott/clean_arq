package com.appy.android.appycore.presentation.util;

/**
 * Created by Francisco Barrios 19/10/2018
 */
public class PasswordValidator extends StringValidator {
    private int MIN_CHARACTERS = 4;

    public PasswordValidator(int minCharacters) {
        this.MIN_CHARACTERS = minCharacters;
    }

    @Override
    public boolean isValid(String value) {
        return !isEmpty(value) && value.length() >= MIN_CHARACTERS;
    }
}