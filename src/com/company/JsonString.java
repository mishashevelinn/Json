package com.company;

public class JsonString extends JsonValue {
    private String s;

    public JsonString(String s) {
        this.s = s;
    }
    //Json string does not have indexes, so throwing error if one tries to access it
    @Override
    public JsonValue get(int i) throws JsonQueryException {
        throw new JsonQueryException("JsonString cannot resolve method 'get(int)'");

    }
    //neater string access is impossible
    @Override
    public JsonValue get(String s) throws JsonQueryException {
        throw new JsonQueryException("JsonString cannot resolve method 'get(String)'");

    }

    @Override
    public String toString() {
        return s;
    }
}
