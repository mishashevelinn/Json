package com.company;
//Inheriting from General Class Exception
public class JsonSyntaxException extends Exception {
    public JsonSyntaxException(String m) {
        super(m);
    }
}