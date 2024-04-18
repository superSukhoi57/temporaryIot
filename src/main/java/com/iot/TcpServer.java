package com.iot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public void getTcpData() {
        int port = 6700; // 你可以选择任意一个可用的端口号

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已启动，正在监听端口 " + port);

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("客户端已连接：" + socket.getInetAddress());

                    // 读取客户端发送的数据
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("接收到的数据: " + line);
                    }
                } catch (IOException e) {
                    System.err.println("处理客户端连接时发生错误: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("启动服务器时发生错误: " + e.getMessage());
        }
    }
}