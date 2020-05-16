package com.company;

class JsonQueryException extends Exception {
    String error;

    public JsonQueryException(String error) {
        this.error = error;
    }
}

