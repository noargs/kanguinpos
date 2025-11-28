package com.swingpos.config;

import com.swingpos.utils.PosLog;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.awt.*;
import java.io.File;

public class AppConfig {

    private static final String APP_NAME = "SwingPOS";
    private static final String APP_VERSION = "1.7";

    private static final String RESOURCE_DIR = "/resources";
    private static final String FONT_DIR = "/fonts/";
    private static final String IMAGES_DIR = "/images/";
    private static final String ICONS_DIR = "/icons/";

    public static final String CURRENCY = "€";

    private static final String APP_LOGO = "logo.png";
    private static final String APP_ICON = "icon.png";
    private static final String APP_ICON62 = "icon62.png";

    public static final String ONE   = "1.png";
    public static final String TWO   = "2.png";
    public static final String THREE = "3.png";
    public static final String FOUR  = "4.png";
    public static final String FIVE  = "5.png";
    public static final String SIX   = "6.png";
    public static final String SEVEN = "7.png";
    public static final String EIGHT = "8.png";
    public static final String NINE  = "9.png";
    public static final String ZERO  = "0.png";
    public static final String UP_ARROW  = "up.png";
    public static final String DOWN_ARROW  = "down.png";
    public static final String MINUS  = "minus.png";
    public static final String PLUS  = "add_user.png";
    public static final String DELETE  = "delete.png";
    private static final String CLEAR = "clear.png";
    public static final String NEXT_SIDE = "next.png";
    public static final String PREVIOUS_SIDE = "previous.png";
    private static final String SHUT_DOWN = "shutdown.png";
    public static final String COOKING_INSTRUCTION = "cooking-instruction.png";
    public static final String MISC_ICO = "misc40.png";
    public static final String COMMENT_ICO = "comment40.png";
    private static final String INFO = "info24.png";
    public static final String SHUT_DOWN_WHITE = "shutdown_white.png";
    public static final String LOG_OUT = "logout.png";
    public static final String CLOCK_IN = "clock_out.png";
    public static final String ORDER = "order.png";
    public static final String ADMIN_ICO = "admin.png";
    public static final String OTHER_ADMIN = "other_functions.png";
    public static final String KITCHEN_ICO = "kitchen.png";
    public static final String HOME_ICO = "home.png";
    public static final String CHEF = "chef.png";
    private static final String POS = "pos.png";
    private static final String CUSTOMER_LOGO = "kh_logo.jpg";

    private static final String DEFAULT_CURRENCY = "€";

    public static final String EUR1 = "1euro.png";
    public static final String EUR2 = "2euro.png";
    public static final String EUR5 = "5euro.png";
    public static final String EUR10 = "10euro.png";
    public static final String EUR20 = "20euro.png";
    public static final String EUR50 = "50euro.png";

    private static final String FONT_DJB_DIGITAL = "DJB_Get_Digital.ttf";
    private static final String FONT_PC = "PC.ttf";
    private static final String FONT_STRADOS = "StardosStencil-Regular.ttf";
    private static final String FONT_ICELAND = "Iceland-Regular.ttf";
    private static final String COMFORTA_BOLD = "Comfortaa_Bold.ttf";
    private static final String COMFORTA_REGULAR = "Comfortaa_Regular.ttf";
    private static final String FONT_JERVINHO = "JervinhoRegular.ttf";

    public static final int IND_PRICE_IDENTIFIER = 1;
    public static final int MEAL_PRICE_IDENTIFIER = 2;

    public static final String MEAL = "M";

    private static int MINIMUM_WIDTH = 800;
    private static int MINIMUM_HEIGHT = 600;
    private static int PREF_WIDTH = 800;
    private static int PREF_HEIGHT = 600;

    private static int D_MINIMUM_WIDTH = 480;
    private static int D_MINIMUM_HEIGHT = 200;

    private static int DEFAULT_CATEGORY_PAGE_SIZE = 5;
    private static int DEFAULT_PRODUCT_PAGE_SIZE = 15;

    public static final String AUTH_VIEW = "auth";
    public static final String HOME_VIEW = "home";
    public static final String TICKET_VIEW = "ticket";
    public static final String PAY_VIEW = "pay";

    // Receipt
    public static final int RECEIPT_FONT_SIZE = 11;
    public static final int DEFAULT_ITEM_NAME_LENGTH = 23; //23


    private static PropertiesConfiguration config;

    static {
        try {
            File configFile = new File("swingpos.config.properties"); //$NON-NLS-1$
            if(!configFile.exists()) {
                configFile.createNewFile();
            }

            config = new PropertiesConfiguration(configFile);
            config.setAutoSave(true);

        } catch (Exception e) {
            PosLog.error(AppConfig.class, e.getMessage());
        }
    }

    public static PropertiesConfiguration getConfig() {
        return config;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return config.getInt(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        config.setProperty(key, value);
    }

    public static String getString(String key) {
        return config.getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }

    public static void put(String key, boolean value) {
        config.setProperty(key, value);
    }

    public static void put(String key, String value) {
        config.setProperty(key, value);
    }

    //// App Fonts ////
    public static String getFontPath()
    {
        return RESOURCE_DIR + FONT_DIR;
    }

    public static String getComfortaBoldFont()
    {
        return getFontPath() + COMFORTA_BOLD;
    }
    public static String getComfortaRegularFont()
    {
        return getFontPath() + COMFORTA_REGULAR;
    }
    public static String getDJBDigitalFont()
    {
        return getFontPath() + FONT_DJB_DIGITAL;
    }
    public static String getPCFont()
    {
        return getFontPath() + FONT_PC;
    }
    public static String getFontJervinho()
    {
        return getFontPath() + FONT_JERVINHO;
    }
    public static String getFontStrados()
    {
        return getFontPath() + FONT_STRADOS;
    }
    public static String getFontIceland()
    {
        return getFontPath() + FONT_ICELAND;
    }


    //// App Images ////
    public static String getImagePath()
    {
        return RESOURCE_DIR + IMAGES_DIR;
    }


    //// App Icons /////
    public static String getIconPath()
    {
        return RESOURCE_DIR + ICONS_DIR;
    }
    public static String getIconAppIcon()
    {
        return APP_ICON;
    }
    public static String getIconAppIcon62()
    {
        return APP_ICON62;
    }
    public static String getIconAppLogo()
    {
        return APP_LOGO;
    }
    public static String getIconClear()
    {
        return CLEAR;
    }
    public static String getIconNextSide()
    {
        return NEXT_SIDE;
    }
    public static String getIconShutDown()
    {
        return SHUT_DOWN;
    }
    public static String getIconInfo()
    {
        return INFO;
    }
    public static String getImageCustomerLogo() {
        return CUSTOMER_LOGO;
    }
    public static String getIconPOS() {
        return POS;
    }
    public static String getIconOrder() {
        return ORDER;
    }
    public static String getIconAdmin()
    {
        return ADMIN_ICO;
    }
    public static String getIconKitchen()
    {
        return KITCHEN_ICO;
    }
    public static String getIconClockIn()
    {
        return CLOCK_IN;
    }
    public static String getIconLogOut()
    {
        return LOG_OUT;
    }



    /// App Properties ////
    public static Dimension getMinimumWidthAndHeight()
    {
        return new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT);
    }

    public static Dimension getPreferredWidthAndHeight()
    {
        return new Dimension(PREF_WIDTH, PREF_HEIGHT);
    }

    public static Dimension getDialogMinimumSize()
    {
        return new Dimension(D_MINIMUM_WIDTH, D_MINIMUM_HEIGHT);
    }
    public static int getDefaultCategoryPageSize()
    {
        return DEFAULT_CATEGORY_PAGE_SIZE;
    }

    public static int getDefaultProductPageSize()
    {
        return DEFAULT_PRODUCT_PAGE_SIZE;
    }
    public static String getDefaultCurrency()
    {
        return DEFAULT_CURRENCY;
    }

}

