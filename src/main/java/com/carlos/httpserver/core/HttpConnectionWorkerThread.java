package com.carlos.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private Socket socket;
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    public HttpConnectionWorkerThread(Socket socket) {
     this.socket = socket;
    }
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            // read
            String html = "<html><head><title>Java HTTP Server</title></head><body><h1>Hello World!</h1></body></html>";
            final String CRLF = "\n\r";
            // status line : HTTP_version response_code response_message
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF + // header
                    CRLF + html + CRLF + CRLF;
            outputStream.write(response.getBytes());


            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Connection processing finished");
        } catch (IOException e){
            LOGGER.error("Problem with communication", e);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
