package com.company;

import java.util.HashMap;
import java.util.Map;

public class JsonObject extends JsonValue {
    private Map<String, JsonValue> o;

    public JsonObject() {
        o = new HashMap<String, JsonValue>() {
        };

    }

    public Object put(String s, JsonValue Jv) {
        return o.put(s, Jv);
    }

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        throw new JsonQueryException("JsonObject cannot resolve method 'get(int)'");

    }

    @Override
    public JsonValue get(String s) {
        return o.get(s);
    }

    @Override
    public String toString() {
        String buf = new String();
        buf += "{";
        for (String key : o.keySet()) {
            buf += "<" + key + ":" + o.get(key) + ">";

        }
        buf += "}";
        return buf;
    }
}
