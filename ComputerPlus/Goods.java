package ComputerPlus;

public class Goods {
	private double price;
	private int size;
	private int account;
	Goods(double price,int account) {
		this.price=price;
		this.account=account;
	}
	public double TotalPrice() {
		return price*account;
	}
	void show(int size) {
		System.out.println("ÈÝÁ¿Îª£º"+size+"G¡£");
	}
}
