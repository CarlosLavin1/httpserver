package com.carlos.httpserver;

import com.carlos.httpserver.config.ConfigurationManager;
import com.carlos.httpserver.config.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("using port: " + conf.getPort() + " using webroot: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // read
            String html = "<html><head><title>Java HTTP Server</title></head><body><h1>Hello World!</h1></body></html>";
            final String CRLF = "\n\r";
            // status line : HTTP_version response_code response_message
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF + // header
                    CRLF + html + CRLF + CRLF;
            outputStream.write(response.getBytes());

            //write

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
