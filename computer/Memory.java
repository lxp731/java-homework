package computer;

public class Memory {
	private String brand;
	private double price;
	private int size;
	
	public Memory(double price) {
		brand="lenovo";
		this.price=price;
		size=16;
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
	public void memory() {
		System.out.println(brand+"��"+size+"G�ڴ������ˡ�");
	}
}
