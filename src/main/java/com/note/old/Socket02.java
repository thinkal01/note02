package com.note.old;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket02 {
    public void client() throws IOException {
        // 创建发送端的Socket对象
        Socket s = new Socket("192.168.12.92", 8888);

        // 获取输出流，写数据
        OutputStream os = s.getOutputStream();
        os.write("hello,tcp,我来了".getBytes());

        // 获取输入流
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);// 阻塞
        String client = new String(bys, 0, len);
        System.out.println("client:" + client);

        // 释放资源
        s.close();
    }

    public void server() throws IOException {
        // 创建接收端的Socket对象
        ServerSocket ss = new ServerSocket(8888);

        // 监听客户端连接。返回一个对应的Socket对象
        // 侦听并接受到此套接字的连接。此方法在连接传入之前一直阻塞
        Socket s = ss.accept();

        // 获取输入流，读取数据显示在控制台
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys); // 阻塞式方法
        String str = new String(bys, 0, len);

        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip + "---" + str);

        // 获取输出流
        OutputStream os = s.getOutputStream();
        os.write("数据已经收到".getBytes());

        // 释放资源
        s.close();
        // ss.close(); //这个不应该关闭
    }

    public void client02() throws IOException {
        // 创建客户端Socket对象
        Socket s = new Socket("192.168.12.92", 22222);
        // 键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 把通道内的流给包装一下
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line;
        while ((line = br.readLine()) != null) {
            // 键盘录入数据要自定义结束标记
            if ("886".equals(line)) {
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        // Socket提供了一个终止，通知服务器
        s.shutdownOutput();

        // 接收反馈
        BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String client = brClient.readLine(); // 阻塞

        // 释放资源
        br.close();
        s.close();
    }

    public void server02() throws IOException {
        // 创建服务器Socket对象
        ServerSocket ss = new ServerSocket(22222);
        // 监听客户端连接
        Socket s = ss.accept();
        // 包装通道内容的流
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // 封装文本文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("a.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        // 给出反馈
        BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bwServer.write("文件上传成功");
        bwServer.newLine();
        bwServer.flush();

        // 释放资源
        bw.close();
        s.close();
    }

    public void uploadImg() throws IOException {
        // 创建客户端Socket对象
        Socket s = new Socket("192.168.12.92", 19191);

        // 封装图片文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("林青霞.jpg"));
        // 封装通道内的流
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());

        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
            bos.flush();
        }

        s.shutdownOutput();

        // 读取反馈
        InputStream is = s.getInputStream();
        byte[] bys2 = new byte[1024];
        int len2 = is.read(bys2);
        String client = new String(bys2, 0, len2);

        // 释放资源
        bis.close();
        s.close();
    }

    public void uploadServer() throws IOException {
        // 创建服务器Socket对象
        ServerSocket ss = new ServerSocket(19191);

        // 监听客户端连接
        Socket s = ss.accept();

        // 封装通道内流
        BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
        // 封装图片文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("mn.jpg"));

        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
            bos.flush();
        }

        // 给一个反馈
        OutputStream os = s.getOutputStream();
        os.write("图片上传成功".getBytes());

        bos.close();
        s.close();
    }

    public void uploadServer2() throws IOException {
        // 创建服务器Socket对象
        ServerSocket ss = new ServerSocket(11111);

        while (true) {
            Socket s = ss.accept();
            new Thread(new UserThread(s)).start();
        }
    }
}

class UserThread implements Runnable {
    private Socket s;

    public UserThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            // 封装通道内的流
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // 为了防止名称冲突
            String newName = System.currentTimeMillis() + ".java";
            BufferedWriter bw = new BufferedWriter(new FileWriter(newName));

            String line = null;
            while ((line = br.readLine()) != null) { // 阻塞
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            // 给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bwServer.write("文件上传成功");
            bwServer.newLine();
            bwServer.flush();

            // 释放资源
            bw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
