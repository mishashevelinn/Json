package com.company;

public class JsonString extends JsonValue {
    private String s;

    public JsonString(String s) {
        this.s = s;
    }

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        throw new JsonQueryException("JsonString cannot resolve method 'get(int)'");

    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        throw new JsonQueryException("JsonString cannot resolve method 'get(String)'");

    }

    @Override
    public String toString() {
        return s;
    }
}
