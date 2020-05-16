package com.company;

public class JsonNumber extends JsonValue {
    private Integer k;
    private Float q;
    public JsonNumber(String k) {
        int i = Integer.parseInt(k);
        this.k = i;

    }


    public JsonNumber neg() {
        JsonNumber negJN = new JsonNumber(String.valueOf(k.intValue() * (-1)));
        return negJN;
    }


    @Override
    public JsonNumber get(int i) {
        Integer num = i;
        JsonNumber jnum = new JsonNumber(num.toString());
        return jnum;
    }

    @Override
    public JsonValue get(String s) {
        return null;
    }

    public Float getFloatQ() {
        return q;
    }

    @Override
    public String toString() {
        return k.toString();
    }
}
