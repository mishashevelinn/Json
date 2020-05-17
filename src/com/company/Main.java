package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.text.ParseException;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        String s="554";
//        Double b=new Double(s);
//
//        System.out.println(b);
//        System.out.println( b.getClass().getSimpleName());
        JsonBuilder avraham = null;
        //       try
        //     {
        avraham = new JsonBuilder(new File("try"));
        System.out.println(avraham);
//          //  System.out.println(avraham.get("issue").get("Ketura").get(2));
//        }
//        catch( SyntaxException e )
//        {
//            e.printStackTrace();
//        }
//        //catch( QueryException e )
//        {
//            e.printStackTrace();
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
    }
}
