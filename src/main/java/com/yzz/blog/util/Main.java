package com.yzz.blog.util;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author zzy
 * @Date 2018/9/15 上午8:55
 */
public class Main {
    public static final int TCP_PORT = 80;
    public static String ip = "123.207.61.212";
    Socket s = null;
    String cmd = null;

    public static void main(String[] args) {
        new Main().connect();
    }

    public void connect(){
        try {
            s = new Socket(ip,TCP_PORT);
            System.out.println("连接成功");
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            do{
                cmd = JOptionPane.showInputDialog("输入命令：");
                dos.writeUTF(cmd);
            }while(!cmd.equals("over"));

            new Thread(new RecvThread()).start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private class RecvThread implements Runnable{
        @Override
        public void run() {
            DataInputStream dis;
            while(true){
                try {
                    dis = new DataInputStream(s.getInputStream());
                    String message = dis.readUTF();
                    JOptionPane.showMessageDialog(null, message);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
