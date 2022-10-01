package ComputerPlus;

public class Memory extends Goods{
	private String brand;
	Memory(double price,int account) {
		super(price,account);
	}
	@Override
	public double TotalPrice() {
		return super.TotalPrice();
	}
	void show(String brand) {
		super.show(16);
		System.out.println("MemoryµÄÆ·ÅÆÎª"+brand);
	}
}
