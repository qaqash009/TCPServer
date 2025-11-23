package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Throwable {


        ServerSocket outFirstServerSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("yeni socket ve ya musteri gozleyir ");
            Socket connection = outFirstServerSocket.accept();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String messageClient = reader.readLine();
            writeResponse(connection.getOutputStream(), messageClient);
            System.out.println("musterinin mesaji: " + messageClient);
        }
    }

    private static void writeResponse(OutputStream ous, String s) throws Throwable {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Server: TCPServer/2009-09-09\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length" + s.length() + "\r\n"
                + "Connection:close\r\n\r\n" +
                 "<h1>" + s + "</h1>";
        String result = response;
        System.out.println(result);
        ous.write(response.getBytes());
        ous.flush();
        ous.close();

    }

    public static String readReguest(InputStream sin) throws IOException {
        InputStreamReader ist = new InputStreamReader(sin);
        BufferedReader reader = new BufferedReader(ist);
        String msg = "";
        while (true) {
            String s = reader.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            }else {
                msg = msg + s + "\r\n";
            }
            System.out.println("Server reguest: " + s);
            System.out.println();
        }
        return msg;
    }


//    public static byte[] readResponse(DataInputStream din) throws Exception {
//        InputStreamReader ist = new InputStreamReader(din);
//    }
}