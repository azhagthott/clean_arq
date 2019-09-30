package com.appy.android.appycore.presentation.util;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

/**
 * Created by Francisco Barrios 19/10/2018
 */
public class EmailValidator extends StringValidator {
    private Pattern pattern = Pattern.compile("^.+@.+\\..+$");

    @Override
    public boolean isValid(@NonNull String value) {
        return pattern.matcher(value).matches();
    }
}
