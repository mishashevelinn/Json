package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
//*Recognizes the specific kind of JasonValue from txt file using peek and next methods.
public class JsonBuilder extends JsonValue {
    private final CharScanner sc; //we use it for scan methods
    private final JsonValue v; //


    public JsonBuilder(File f) throws FileNotFoundException, JsonSyntaxException {
        sc = new CharScanner(f);
        this.v = parseValue();
    }

    public JsonValue parseValue() throws JsonSyntaxException {
        if (sc.hasNext()) {
            char c = sc.peek();
            if (c == '"') {
                return parseString();   //["asd", 1]
            }
            if (c == '[') {
                return parseArray();
            }
            if (Character.isDigit(c) || c == '-') {
                return parseNumber();
            }
            if (c == '{') {
                return parseObject();
            }
        }
        throw new JsonSyntaxException("First char of one of values in invalid");
    }

    public JsonArray parseArray() throws JsonSyntaxException {
        JsonArray list = new JsonArray();
        int counter = 0;
        sc.next();
        while (sc.peek() != ']') {
            list.add(parseValue());
            if (sc.peek() == ',') {
                sc.next();
            }
            counter++;
            if(counter>10000)throw new JsonSyntaxException("']' expected");
        }
        sc.next();
        return list;
    }

    public JsonObject parseObject() throws JsonSyntaxException {
        int counter = 0;
        JsonObject dic = new JsonObject();
        char c;
        sc.next();
        String key = "";
        while ((c = sc.peek()) != '}') {
            if (c == ' ' || c == '"') {
                sc.next();
            } else if (sc.peek() != ':') {
                key += sc.next();
            } else {
                sc.next();
                dic.put(key, parseValue()); //TODO return value from put (מצגת על אוספים)?
                key = "";
            }
            if ((sc.peek()) == ',') {
                sc.next();
            }
            counter++;
            if(counter>10000) throw new JsonSyntaxException("'}' expected");
        }

        sc.next();
        return dic;
    }

    public JsonString parseString() throws JsonSyntaxException {
        String str = "";
        int charCounter = 0;
        char c;
        sc.next();
        while ((c = sc.next()) != '"') {
            if(c == '\\'){
                c = sc.next();
            }
            str += c;
            charCounter++;
            if(charCounter>10000){
                throw new JsonSyntaxException("Illegal line and in string literal");
            }
        }
        return new JsonString(str);
    }

    public JsonNumber parseNumber() throws JsonSyntaxException {
        String s = "";
        //s+= sc.next();
        char c;
        while (sc.hasNext() && (Character.isDigit(c = sc.peek()) || Character.isAlphabetic(c) || c == '.' || c == '-' || c=='+' || c == 'e' || c == 'E')) {
            c = sc.next();
            s += c;
        }
        try {
            return new JsonNumber(s);
        }
        catch (NumberFormatException | ParseException e){
            throw new JsonSyntaxException("Number format error");
        }
    }

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        return v.get(i);
    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        try {
            return v.get(s);
        }
        catch (NullPointerException e){
            throw new JsonQueryException("Key Error");
        }
    }

    @Override
    public String toString() {
        return "" + v;
    }
}