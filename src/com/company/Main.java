package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.text.ParseException;


public class Main {
    public static void main( String[] args )
    {
        JsonBuilder test = null;
        try
        {
            test = new JsonBuilder( new File( "test.txt"));
            System.out.println( test );
            System.out.println( test.get( "isAlive" ));
        }
        catch( JsonSyntaxException e )
        {
            e.printStackTrace();
        }
        catch( JsonQueryException e )
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }}