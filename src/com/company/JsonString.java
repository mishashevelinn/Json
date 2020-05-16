package com.company;

public class JsonString extends JsonValue {
    private String s;

    public JsonString(String s) {
        this.s = s;
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
        return "<"+s+">";
    }
}
