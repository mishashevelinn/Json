package com.company;


import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonValue {
    public List<JsonValue> a;

    public JsonArray() {
        a = new ArrayList();
    }

    public void add(JsonValue val) {
         a.add(val);
    }

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        if((i>=0) && (i<this.a.size())){
            return a.get(i);
        }
        else{
            throw new JsonQueryException("JsonList index out of range");
        }

    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        throw new JsonQueryException("JsonArray cannot resolve method 'get(String)'");

    }

    @Override
    public String toString() {
        String buf = "[";
        for (int i=0; i<a.size(); i++) {
            if (i!=0) buf+= ',';
            buf += "<" + a.get(i)  + ">";
        }
        buf += "]";
        return buf;
    }
}

