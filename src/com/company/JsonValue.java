package com.company;

public abstract class JsonValue {
    public abstract JsonValue get(int i) throws JsonQueryException;

    public abstract JsonValue get(String s) throws JsonQueryException;

}
