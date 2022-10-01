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
		System.out.println("内存花费"+m.getprice()+"元。");
		System.out.println("硬盘花费"+s.getprice()+"元。");
		System.out.println("总共花费"+(m.getprice()+s.getprice())+"元。");
	}
}
