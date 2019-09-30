package com.appy.android.appycore.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MoneyFormat {

    private static String setZerosAndTHB(String amount) {
        double val;
        if(amount.equals("")){
            val = 0;
        }else {
            val = Double.parseDouble(amount);
        }

        NumberFormat f = NumberFormat.getCurrencyInstance(Locale.getDefault());
        f.setCurrency(Currency.getInstance("THB"));
        String formatted = f.format(val);
        return formatted.replace("THB", "THB ");
    }

    public static String formatTHB(String amount) {
        return setZerosAndTHB(amount);
    }
}
