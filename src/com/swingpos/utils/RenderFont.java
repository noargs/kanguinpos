package com.swingpos.utils;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class RenderFont {

    public Font customFont(String font, int style, float size) throws FontFormatException, IOException
    {

        InputStream is = this.getClass().getResourceAsStream(font);
       // InputStream is = System.class.getResourceAsStream(font);
        Font f = Font.createFont(Font.TRUETYPE_FONT, is);
        return f.deriveFont(style, size);
    }
}
