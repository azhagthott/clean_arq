package com.appy.android.appycore.presentation.util;

/**
 * Created by Francisco Barrios 19/10/2018
 */
public abstract class StringValidator implements Validator {
    public boolean isEmpty(String value) {
        return value != null && value.trim().isEmpty();
    }
}