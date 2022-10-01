package Dandi;

import java.awt.*;
import javax.swing.*;
import java.util.Date;


public class TimeDemo extends JFrame implements Runnable {

    Thread clock;

    public static void main(String[] args) {
    TimeDemo td =new TimeDemo();
    td.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //点击可见窗口右上角的红叉关闭
     }

    public TimeDemo(){
     super("java多线程数字时钟"); //继承父类构造方法，这里相当于Font("java多线程数字时钟");
setTitle("电子时钟");   //这个则是子类的
     this.setFont(new Font("Times New Roman",Font.BOLD,60));    //设置字体大小
     this.go();       //自定义go方法,用于以后开启线程
     setBounds(400,300,300,100);
     this.setVisible(true);
    }

     public void go(){
    stop();
     if(clock==null){
   //线程执行的主题作为Thread类构造方法的参数。
     clock=new Thread(this);
     clock.start();         //开启线程,实现run方法
    }
}

    public void run() {
       while(true)      //让线程一直进行
    {
//repain()方法是来控制Graphics类的paint()方法的，repain()方法执行一次，即让paint()方法执行一次
           repaint();   
         try{
           Thread.sleep(1000);      //参数是毫秒,1秒即1000毫秒
         }catch(InterruptedException e){}
       }
     }

    public void stop(){
     clock=null;
   }

    public void paint(Graphics g){
      String s="";
      Date now=new Date();
      int hour=now.getHours();                                           
      int minute=now.getMinutes();
      int second=now.getSeconds();
      s=hour+":"+minute+":"+second;
      g.setColor(Color.green);
      Dimension dim=getSize();
      g.fillRect(0, 0, dim.width, dim.height);
      g.setColor(Color.red);
      g.drawString(s, 20, 80);
    }
}