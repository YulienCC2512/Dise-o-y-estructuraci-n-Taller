package com.mycompany.ejercicio1;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class HttpServer {

    private static List<String> nombres = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35000);
        System.out.println("HTTP server started on port 35000...");

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                handleClient(clientSocket);
            }
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStream out = clientSocket.getOutputStream();

        String requestLine = in.readLine();
        if (requestLine == null) return;

        System.out.println("Request: " + requestLine);

        StringTokenizer tokens = new StringTokenizer(requestLine);
        String method = tokens.nextToken();
        String path = tokens.nextToken();

        String query = "";
        int contentLength = 0;
        String line;
        while (!(line = in.readLine()).isEmpty()) {
            if (line.startsWith("Content-Length:")) {
                contentLength = Integer.parseInt(line.replace("Content-Length:", "").trim());
            }
        }

        if ("POST".equalsIgnoreCase(method) && contentLength > 0) {
            char[] body = new char[contentLength];
            in.read(body, 0, contentLength);
            query = new String(body);
            System.out.println("POST Body: " + query);
        }

        if ("GET".equalsIgnoreCase(method) && path.contains("?")) {
            query = path.substring(path.indexOf("?") + 1);
            path = path.substring(0, path.indexOf("?"));
        }

        String response;
        String mimeType = "text/html";

        // Manejo de endpoints tipo API
        if (path.startsWith("/app")) {
            if (path.endsWith("/postInfo") && method.equalsIgnoreCase("GET")) {
                if (query.startsWith("name=")) {
                    String name = query.replace("name=", "");
                    nombres.add(name);
                }
                response = "{\"status\":\"ok\"}";
                mimeType = "application/json";

            } else if (path.endsWith("/getInfo") && method.equalsIgnoreCase("GET")) {
                String ultimo = nombres.isEmpty() ? "" : nombres.get(nombres.size() - 1);
                response = "{\"name\":\"" + ultimo + "\"}";
                mimeType = "application/json";

            } else {
                response = "{\"error\":\"Invalid API endpoint\"}";
                mimeType = "application/json";
            }
        }
        // Archivos estáticos
        else {
            if (path.equals("/")) path = "/index.html";
            response = readFile("www" + path);
            mimeType = guessMime(path);
        }

        String httpResponse = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: " + mimeType + "\r\n"
                + "Content-Length: " + response.getBytes().length + "\r\n"
                + "\r\n"
                + response;

        out.write(httpResponse.getBytes());
        out.flush();
    }

    private static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return "<h1>404 Not Found</h1>";
        }
    }

    private static String guessMime(String path) {
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        return "text/html";
    }
}
