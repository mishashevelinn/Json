package com.company;

import java.util.HashMap;
import java.util.Map;

public class JsonObject extends JsonValue {
    private Map<String, JsonValue> o; // Map contains only string key and jsonvalue value

    public JsonObject() {
        o = new HashMap<String, JsonValue>(); // create new HashMap when call to JsonObject's builder
    }

    public Object put(String s, JsonValue Jv) {
        return o.put(s, Jv); // 'put' method same as Java's map method
    }

    @Override
    public Object get(int i) throws JsonQueryException {// no index to map so 'get(int)' method wont work
        throw new JsonQueryException("JsonObject cannot resolve method 'get(int)'");

    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        if (!o.containsKey(s))  //if user tries to access key witch doesn't exists, throw Key Error
            throw new JsonQueryException("Json Key error");
        else
        return o.get(s);
    }

    @Override
    public String toString() { // return new string of JsonObject by wanted format
        String buf = new String();
        buf += "{";
        for (String key : o.keySet()) {
            buf += "<" + key + ":" + o.get(key) + ">";

        }
        buf += "}";
        return buf;
    }
}
