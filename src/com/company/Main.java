package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.text.ParseException;


public class Main {
    public static void main( String[] args )
    {
        JsonBuilder avraham = null;
        try
        {
            avraham = new JsonBuilder( new File( "try"));
            System.out.println( avraham );
            System.out.println( avraham.get( "issue" ).get( "Ketura" ).get(2 ) );
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