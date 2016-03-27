/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppt;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Administrator
 */
public class PPT {
    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
     
       
        Server();
    
    }
    
  /**
   * 
   * @throws AWTException
   * @throws InterruptedException
   * @throws SocketException
   * @throws IOException 
   */
    public static void Server() throws AWTException, InterruptedException, SocketException, IOException {
        // TODO code application logic here
    DatagramSocket ds = new DatagramSocket(11751);
    
    while(true){
        byte[] buf = new byte[1024];
    DatagramPacket dp = new DatagramPacket(buf, buf.length);
    ds.receive(dp);
    String ip = dp.getAddress().getHostAddress();
    String data = new String(dp.getData(),0,dp.getLength());
    int port = dp.getPort();
        switch (data) {
            case "next":
                SendNextPage();
                System.out.println("ip="+ip+"  "+"port="+port+"执行操作下一页 ");
                break;
            case "pre":
                SendPerPage();
                System.out.println("ip="+ip+"  "+"port="+port+"执行操作上一页 ");
                break;
            default:
                System.err.println("ip="+ip+"  "+"port="+port+" "+"data"+" "+"这是一个错误的非法信息");
                break;
        }
    }
     //  ds.close();
      
    }
    public static void  SendNextPage() throws AWTException{
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_RIGHT);
        
    }
    public static void  SendPerPage() throws AWTException{
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_LEFT);
        
    }
    
    
}
