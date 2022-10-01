package ComputerPlus;

public class computer {
	public static void main(String[] args) {
		Memory m = new Memory(200,2);
		System.out.println("购买内存的价钱为:"+m.TotalPrice()+"。");
		m.show("samsung");
		SSD s = new SSD(500,1);
		System.out.println("购买硬盘的价钱为:"+s.TotalPrice()+"。");
		s.show("lenovo");
	}
}
