package com.swingpos.config;

import org.apache.commons.configuration.PropertiesConfiguration;

public class TerminalConfig {

    static final String FULLSCREEN_MODE = "fullscreen_mode";
    private static PropertiesConfiguration config = AppConfig.getConfig();

    public static boolean isFullscreenMode()
    {
        return config.getBoolean(FULLSCREEN_MODE, false);
    }


}
