package com.carlos.httpserver;

import com.carlos.httpserver.config.ConfigurationManager;
import com.carlos.httpserver.config.Configuration;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("using port: " + conf.getPort() + " using webroot: " + conf.getWebroot());
    }
}
