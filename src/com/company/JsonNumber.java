package com.company;

public class JsonNumber extends JsonValue {
    private Number k;

    public JsonNumber(String k){
        int i= Integer.parseInt(k);
        this.k=i;
    }

    public JsonNumber neg() {
        JsonNumber negJN = new JsonNumber(String.valueOf(k.intValue() * (-1)));
        return negJN;
    }


    @Override
    public JsonValue get(int i) {
        return null;
    }

    @Override
    public JsonValue get(String s) {
        return null;
    }

    @Override
    public String toString() {
        return k.toString();
    }
}
