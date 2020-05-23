package com.company;

public class JsonString extends JsonValue {
    private String s;

    public JsonString(String s) {
        this.s = s;
    } //JsonString's builder get string and make it JsonString

    @Override
    public Object get(int i) throws JsonQueryException { //string dont have 'get' method
        throw new JsonQueryException("JsonString cannot resolve method 'get(int)'");

    }

    @Override
    public JsonValue get(String s) throws JsonQueryException { //string dont have 'get' method
        throw new JsonQueryException("JsonString cannot resolve method 'get(String)'");

    }

    @Override
    public String toString() {
        return s;
    }
}
