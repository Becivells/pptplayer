package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private EditText ip_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        ip_text= (EditText) findViewById(R.id.ipAddr);
        Button NextButton=(Button)findViewById(R.id.Nextbutton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNext(ip_text.getText().toString());
              //  sendNext("192.168.23.1");
            }
        });
        Button preButton = (Button)findViewById(R.id.prebutton);
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendPre(ip_text.getText().toString());
                  //  sendPre("192.168.23.1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
public static void  sendNext(String ip){
    try {
        send(ip,"next");
    } catch (IOException e) {
        e.printStackTrace();
    }

}
    public  static void sendPre(String ip) throws IOException {
           send(ip,"pre");
    }
    public static void send(String ip,String sendStr) throws SocketException, UnknownHostException, IOException{
            DatagramSocket client = new DatagramSocket();
           // String sendStr = "next";
            byte[] sendBuf;
            sendBuf = sendStr.getBytes();
            InetAddress addr = InetAddress.getByName(ip);
            int port = 11751;
            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf ,sendBuf.length , addr , port);
            client.send(sendPacket);
            byte[] recvBuf = new byte[100];

            client.close();


    }
}

