package com.swingpos.config;

import java.io.File;

public class Helper {

     public static String getAppPath() 
     {
        try {
            String path = new File(".").getCanonicalPath();
            return path;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
     
    public static boolean colorFieldIsEmpty(int color)
    {
    	if(color == 0) return true;
    	
    	return false;
    		
    }
}

