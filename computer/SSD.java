package computer;

public class SSD {
	private String brand;
	private double price;
	private int size;
	
	public SSD(double price) {
		brand="samsung";
		this.price=price;
		size=500;
	}
	public String getbrand() {
		return brand;
	}
	
	public double getprice() {
		return price;
	}
	public double getsize() {
		return size;
	}
	public void ssd() {
		System.out.println(brand+"的"+size+"G的固态硬盘买到了。");
	}
}
