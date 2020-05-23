package com.company;


import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonValue {
    public List<JsonValue> a;

    public JsonArray() {
        a = new ArrayList(); //create new Arraylist when call to builder of JsonArray
    }

    public void add(JsonValue val) {
         a.add(val);
    } //add method to JsonArray (same method like Java's ArrayList)

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        if((i>=0) && (i<this.a.size())){ //check if the wanted index is exist in our JsonArray
            return a.get(i); //use 'get' method of Java's Array
        }
        else{
            throw new JsonQueryException("JsonList index out of range");
        }

    }

    @Override
    public JsonValue get(String s) throws JsonQueryException { //Array don have 'get(string)' method
        throw new JsonQueryException("JsonArray cannot resolve method 'get(String)'");

    }

    @Override
    public String toString() { //make new string of the array like wanted format
        String buf = "[";
        for (int i=0; i<a.size(); i++) {
            if (i!=0) buf+= ',';
            buf += "<" + a.get(i)  + ">";
        }
        buf += "]";
        return buf;
    }
}

