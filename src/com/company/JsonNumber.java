package com.company;

import java.text.NumberFormat;
import java.text.ParseException;

public class JsonNumber extends JsonValue {
    private Number k;

    public JsonNumber(String k) throws ParseException {
        String new_k = k;
        boolean negflag = false;
        if (k.startsWith("-")) {
            negflag = true;
            new_k = k.substring(1);
        }
        this.k = NumberFormat.getInstance().parse(new_k);
        String value = this.k.getClass().getSimpleName();
        if (negflag) {
            if (value == "Long") {
                this.k = this.k.longValue() * (-1);
            }
            if (value == "Double") {
                this.k = this.k.doubleValue() * (-1);
            }
            this.k = this.k.doubleValue() * (-1);
        }
    }

    @Override
    public JsonNumber get(int i) {
        return null;
    }

    @Override
    public JsonValue get(String s) {
        return null;
    }

    @Override
    public String toString() {
        return "" + k;
    }
}