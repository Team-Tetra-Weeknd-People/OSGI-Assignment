package sa_osgi_clothingservice;

public class Clothing {
	private String brand;
	private String type;
	private String size;
	private double price;
	
	public Clothing(String brand, String type, String size, double price) {
		this.brand = brand;
		this.type = type;
		this.size = size;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}