package com.carlos.httpserver;

import com.carlos.httpserver.config.ConfigurationManager;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
    }
}
