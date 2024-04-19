package com.iot.socket;

import com.iot.pojo.staticQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class socketServer {
    public  void server(){
        int port = 8090;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // 接受客户端连接
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client connected");

                // 读取客户端发送的数据
                /*ServerSocket的accept()方法：这个方法会等待并接受一个连接。如果没有客户端尝试连接，这个方法会阻塞，直到一个连接被接受。
BufferedReader的readLine()方法：这个方法会从输入流中读取一行数据。如果输入流中没有更多的数据（例如，客户端没有发送数据，或者所有数据都已经被读取），
这个方法会阻塞，直到有新的数据可以读取*/
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String line;
                    long count=0;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(count++);
                        if(staticQueue.stop==1) {
                            staticQueue.addData(line);
                            System.out.println("采集: " + line);
                        }else{
                            System.out.println("无效数据");
                        }
                    }
                }
                /*//读取客户端发送的所有数据
                这个函数在客户端关闭连接并且所有数据都已经发送完毕后才会输出接收到的数据。这是因为Scanner的hasNext()方法会阻塞，直到它可以
                确定是否还有更多的输入。如果客户端保持连接打开并且不发送任何新的数据，hasNext()方法会一直阻塞。只有当客户端关闭连接，或者发送
                了一些新的数据，hasNext()方法才能确定是否还有更多的输入，然后函数才会继续执行并输出接收到的数据。
                try (Scanner scanner = new Scanner(new InputStreamReader(socket.getInputStream()))) {
                    scanner.useDelimiter("\\A");
                    if (scanner.hasNext()) {
                        System.out.println("Received: " + scanner.next());
                    }
                }*/
               // socket.close(); // 关闭连接
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
