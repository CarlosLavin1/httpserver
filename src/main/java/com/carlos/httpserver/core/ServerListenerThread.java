package com.carlos.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{
    private int port;
    private String webRoot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
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
