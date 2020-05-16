package com.company;


import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonValue {
    public List<JsonValue> a;

    public JsonArray(){
        a = new ArrayList();
    }

    public boolean add(JsonValue val){
       return a.add(val);
    }

    @Override
    public JsonValue get(int i) {
        return a.get(i);
    }

    @Override
    public JsonValue get(String s) {
        return null;
    }

    @Override
    public String toString() {
        return "JsonArray" +
                 a;

    }
}

