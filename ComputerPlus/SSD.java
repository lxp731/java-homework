package ComputerPlus;

public class SSD extends Goods{
	private String brand;
	SSD(double price,int account) {
		super(price,account);
	}
	@Override
	public double TotalPrice() {
		return super.TotalPrice();
	}
	void show(String brand) {
		super.show(512);
		System.out.println("SSD��Ʒ��Ϊ"+brand);
	}
}
