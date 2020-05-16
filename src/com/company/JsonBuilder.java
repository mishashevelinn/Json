package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class JsonBuilder extends JsonValue {
    private final CharScanner sc;
    private final JsonValue v;


    public JsonBuilder(File f) throws FileNotFoundException, ParseException {
        sc = new CharScanner(f);
        this.v = parseValue();
    }

    public JsonValue parseValue() throws ParseException {
        while (sc.hasNext()) {
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
        return null;   //TODO EXCEPTION???
    }

    public JsonArray parseArray() throws ParseException {
        JsonArray list = new JsonArray(); //TODO check other implementations of List
        sc.next();
        while (sc.peek() != ']') {
            list.add(parseValue());
            if (sc.peek() == ',') {
                sc.next();
            }
        }
        sc.next();
        return list;
    }

    public JsonObject parseObject() throws ParseException {
        JsonObject dic = new JsonObject();
        char c;     //TODO מחיקת אגף ימין לא משפיעה על תוצאה
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
        }
        sc.next();
        return dic;
    }

    public JsonString parseString() {
        String str = "";
        char c = sc.next();
        while ((c = sc.next()) != '"') {
            str += c;
        }
        return new JsonString(str);
    }

    public JsonNumber parseNumber() throws ParseException {
        String s = "";
        //s+= sc.next();
        char c;
        while (sc.hasNext() && (Character.isDigit(c = sc.peek()) || c == '.' || c == '-' || c == 'e' || c == 'E')) {
            c = sc.next();
            s += c;
        }
        return new JsonNumber(s);
    }

    @Override
    public JsonValue get(int i) {
        return null;
    }

    @Override
    public JsonValue get(String s) {

        return null;
    }

    @Override
    public String toString() {
        return "" + v;
    }
}