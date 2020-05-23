package com.company;

import java.text.NumberFormat;
import java.text.ParseException;

public class JsonNumber extends JsonValue {
    private Number k;

    public JsonNumber(String k) throws ParseException, NumberFormatException {
        try{                            //we send suspected JasonMumer to it's builder and perform checks
            string_check(k);            // with string_check method
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Double d;
        String new_k = k;
        boolean negflag = false;
        boolean Dflag = false;
        if (k.startsWith("-")) { //detect if number is negative
            negflag = true;
            new_k = k.substring(1);
        }
        if (new_k.contains("-") || new_k.contains("+")) Dflag = true;  //if it is we work on positive part
        if (Dflag) {
            d = new Double(new_k);
            this.k = d;
        } else this.k = NumberFormat.getInstance().parse(new_k);  //we detect the exact class of the number

        String value = this.k.getClass().getSimpleName();   //and parse it to corresponding Java Class
        if (negflag) {
            if (value.equals("Long")) {
                this.k = this.k.longValue() * (-1);
            }
            if (value.equals("Double")) {
                this.k = this.k.doubleValue() * (-1);
            }
        }
    }

    private int string_check(String str) throws JsonSyntaxException {
        int Ecounter = 0;
        int SignCounter = 0;
        int EIndex = 0;
        char first = str.charAt(0);
        if(str.equals("0") || str.equals("-0"))//checking if the number is zero just finish and let the builder act
            return 1;

        if ((first != '-') && (!Character.isDigit(first))) {   //we allow only - or number to be the first element
            throw new JsonSyntaxException("First char of number has to be digit or '-'");
        }
        if (first == '0' && (str.charAt(1) != '.')) {               //if it is zero we expect the dot right after
            { throw new JsonSyntaxException("After '0' expected '.'");
            }
        }
        if ((first == '-') && (str.charAt(1)=='0') && (str.charAt(2)!='.')) //taking care of negative floats
            throw new JsonSyntaxException("zero in the beggining of whole part");
        for (int i = 1; i < str.length(); i++) {
            char test = str.charAt(i);          //we allow only E or e letters
            if (Character.isAlphabetic(test) && test!='E' && test!='e'){
                throw new JsonSyntaxException("Letters except 'e'/'E' for powers of ten are inappropriate");
            }
            if ((test == 'E') || test == 'e') { // we don't want more that one E/e and also catching its index to
                EIndex = i;                     //to handle + - for positive/negative powers of 10
                Ecounter++;
            }
            if (Ecounter > 1) throw new JsonSyntaxException("Too much e/E's in number");
            if ((test == '-') || (test == '+')) {
                SignCounter++;
            }
            if (SignCounter > 1) throw new JsonSyntaxException("Too much signs in the number");
        }//since we allow E/e we have to check that it's used only once
        if (SignCounter == 1) {
            char sign = str.charAt(EIndex+1);//checking that + - of ten powers is in its right place
            if((Ecounter != 1) || ((sign != '-') && (sign != '+'))){
                throw new JsonSyntaxException("Sign in the middle of number");
            }
        }return 1;
    }

    @Override
    //JsonNumber has not get methods at all
    public JsonValue get(int i) throws JsonQueryException {
        throw new JsonQueryException("JsonNumber cannot resolve method 'get(int)'");
    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        throw new JsonQueryException("JsonNumber cannot resolve method 'get(String)'");
    }
    //useing toString of Java's Number
    @Override
    public String toString() {
        return k.toString();
    }
}