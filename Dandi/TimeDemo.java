package Dandi;

import java.awt.*;
import javax.swing.*;
import java.util.Date;


public class TimeDemo extends JFrame implements Runnable {

    Thread clock;

    public static void main(String[] args) {
    TimeDemo td =new TimeDemo();
    td.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����ɼ��������Ͻǵĺ��ر�
     }

    public TimeDemo(){
     super("java���߳�����ʱ��"); //�̳и��๹�췽���������൱��Font("java���߳�����ʱ��");
setTitle("����ʱ��");   //������������
     this.setFont(new Font("Times New Roman",Font.BOLD,60));    //���������С
     this.go();       //�Զ���go����,�����Ժ����߳�
     setBounds(400,300,300,100);
     this.setVisible(true);
    }

     public void go(){
    stop();
     if(clock==null){
   //�߳�ִ�е�������ΪThread�๹�췽���Ĳ�����
     clock=new Thread(this);
     clock.start();         //�����߳�,ʵ��run����
    }
}

    public void run() {
       while(true)      //���߳�һֱ����
    {
//repain()������������Graphics���paint()�����ģ�repain()����ִ��һ�Σ�����paint()����ִ��һ��
           repaint();   
         try{
           Thread.sleep(1000);      //�����Ǻ���,1�뼴1000����
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