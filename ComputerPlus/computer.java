package ComputerPlus;

public class computer {
	public static void main(String[] args) {
		Memory m = new Memory(200,2);
		System.out.println("�����ڴ�ļ�ǮΪ:"+m.TotalPrice()+"��");
		m.show("samsung");
		SSD s = new SSD(500,1);
		System.out.println("����Ӳ�̵ļ�ǮΪ:"+s.TotalPrice()+"��");
		s.show("lenovo");
	}
}
