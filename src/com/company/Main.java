package com.company;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        JsonBuilder avraham = null;
        //try
        {
            avraham = new JsonBuilder(new File("try"));
            System.out.println(avraham);
//            System.out.println(avraham.get("issue").get("Ketura").get(2));
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
}}
