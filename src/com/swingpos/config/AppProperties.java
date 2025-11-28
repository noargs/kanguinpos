package com.swingpos.config;

import com.swingpos.utils.PosLog;
import org.apache.commons.configuration.PropertiesConfiguration;

public class AppProperties {

    private static PropertiesConfiguration properties;

    static {
        try {
            properties = new PropertiesConfiguration(AppProperties.class.getResource("/app.properties"));
        } catch (Exception e) {
            PosLog.error(AppProperties.class, e.getMessage());
        }
    }

    public static String getVersion() {
        return properties.getString("swingpos.version");

    }

    public static String getAppName() {
        return properties.getString("app.name");
    }

    public static String getAppVersion() {
        return properties.getString("app.version");
    }

    public static String getDevDir() {
        return properties.getString("app.dev.dir");
    }
    public static String getDatabaseDir() {
        return properties.getString("app.database.dir");
    }
    public static String getDatabaseName() {
        return properties.getString("app.database");
    }
    public static String getDatabaseUser() {
        return properties.getString("app.database.user");
    }
    public static String getDatabasePassword() {
        return properties.getString("app.database.password");
    }


}
