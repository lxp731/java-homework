package computer;

public class Computer {
	double total_price;
	public static void main(String[] args) {
		Memory m=new Memory(256);
		m.memory();
		SSD s=new SSD(500);
		s.ssd();
		Tools t =new Tools();
		t.tools();
		System.out.println("�ڴ滨��"+m.getprice()+"Ԫ��");
		System.out.println("Ӳ�̻���"+s.getprice()+"Ԫ��");
		System.out.println("�ܹ�����"+(m.getprice()+s.getprice())+"Ԫ��");
	}
}
