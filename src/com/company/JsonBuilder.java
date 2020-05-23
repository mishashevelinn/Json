package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
//*Recognizes the specific kind of JasonValue from txt file using peek and next methods.
public class JsonBuilder extends JsonValue {
    private final CharScanner sc; //we use it for scan methods
    private final JsonValue v; // value of jsonbuilder(json value)


    public JsonBuilder(File f) throws FileNotFoundException, JsonSyntaxException {
        sc = new CharScanner(f);
        this.v = parseValue(); //call parsevalue wich return specific jsonvalue (with value)
    }

    public JsonValue parseValue() throws JsonSyntaxException {
        //this func checkthe first letter of the string (jsonvaulue) and call to the correct parse
        if (sc.hasNext()) {
            char c = sc.peek();
            if (c == '"') {
                return parseString();   //call parseString
            }
            if (c == '[') {
                return parseArray(); // call parseArray
            }
            if (Character.isDigit(c) || c == '-') {
                return parseNumber(); // call parseNumber
            }
            if (c == '{') {
                return parseObject(); // call parseObject
            }
        } // if first char is none of the above, throw execption
        throw new JsonSyntaxException("First char of one of values in invalid");
    }

    public JsonArray parseArray() throws JsonSyntaxException {
        JsonArray list = new JsonArray(); //creat new JsonArray
        int counter = 0; // counter to avoid infinity while loop
        sc.next(); // scan first letter ('[') because not necessary for value
        while (sc.peek() != ']') { //scan the values inside the array
            list.add(parseValue()); //recall to parsevaule to know what value inside the array and add it to array
            if (sc.peek() == ',') {
                sc.next(); // scan ',' because not necessary for value
            }
            counter++;
            if(counter>100000)throw new JsonSyntaxException("']' expected"); // if loop goes over 100000 times, something went wrong
        }
        sc.next(); // scan ']' because not necessary for value (might be more values after array)
        return list;
    }

    public JsonObject parseObject() throws JsonSyntaxException {
        int counter = 0; // counter to avoid infinity while loop
        JsonObject dic = new JsonObject(); // create new Jsonobject
        char c;
        sc.next(); // scan '{' because not necessary for value
        String key = "";
        while ((c = sc.peek()) != '}') {
            if (c == ' ' || c == '"') {
                sc.next(); //scan unnecessary values
            } else if (sc.peek() != ':') {
                key += sc.next(); //scan key value
            } else {
                sc.next(); //scan ':' because not necessary for value
                dic.put(key, parseValue()); // add new key:value to json Object with parsevalue again (for the value)
                key = ""; // key string is ready for next key
            }
            if ((sc.peek()) == ',') {
                sc.next(); //scan ',' because not necessary for value
            }
            counter++;
            if(counter>100000) throw new JsonSyntaxException("'}' expected");// if loop goes over 100000 times something went wrong
        }

        sc.next(); //scan '}' because not necessary for next value
        return dic;
    }

    public JsonString parseString() throws JsonSyntaxException {
        String str = ""; // empty string to which we add to it char by char from scanner
        int charCounter = 0;
        char c;
        sc.next(); //scan '"' because unnecessary for value
        while ((c = sc.next()) != '"') { //our value is before the next '"'
            if(c == '\\'){
                c = sc.next(); // scan backslash so we can add the folowing char to our string
            }
            str += c; //add char to string
            charCounter++;
            if(charCounter>100000){ //if loop went more then 100000 times something went wrong..
                throw new JsonSyntaxException("Illegal line and in string literal");
            }
        }
        return new JsonString(str); //call the builder of Jsonstring with scanned string
    }

    public JsonNumber parseNumber() throws JsonSyntaxException {
        String s = ""; //make new string (of number) to send to JsonNumber builder
        char c;
        // number string can contain numbers / '.' / 'E' / 'e' / '-' / '+'
        while (sc.hasNext() && (Character.isDigit(c = sc.peek()) || Character.isAlphabetic(c) || c == '.' || c == '-' || c=='+' || c == 'e' || c == 'E')) {
            c = sc.next();
            s += c;
        }
        try {
            return new JsonNumber(s); //send the string of number to JsonNumber builder
        }
        catch (NumberFormatException | ParseException e){ //if JsonNumber's builder failed throw exception
            throw new JsonSyntaxException("Number format error");
        }
    }

    @Override
    public Object get(int i) throws JsonQueryException {
        return v.get(i); //get method, works depend on value
    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        try {
            return v.get(s); //get method, works depend on value
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