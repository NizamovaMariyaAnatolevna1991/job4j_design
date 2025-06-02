package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String requestLine = input.readLine();
                    System.out.println("Получен запрос: " + requestLine);

                    String msg = extractParameter(requestLine, "msg");

                    if ("Exit".equals(msg)) {
                        output.flush();
                        System.out.println("Получен сигнал завершения работы. Сервер останавливается...");
                        server.close();
                        break;
                    }

                    if ("Hello".equals(msg)) {
                        output.write("HTTP/1.1 200 Hello\r\n\r\n".getBytes());
                    } else {
                        output.write(msg.getBytes());
                        output.flush();
                        System.out.println("Отправлен ответ: " + msg);

                        for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                            System.out.println(string);
                        }
                    }
                    output.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception ", e);
        }
    }

    private static String extractParameter(String requestLine, String paramName) {
        if (requestLine == null || !requestLine.contains("?")) {
            return null;
        }

        String queryString = requestLine.split("\\?")[1].split(" ")[0];

        String[] params = queryString.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2 && keyValue[0].equals(paramName)) {
                return keyValue[1];
            }
        }
        return null;
    }
}
