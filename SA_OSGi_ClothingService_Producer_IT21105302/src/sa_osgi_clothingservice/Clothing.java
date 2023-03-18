package sa_osgi_clothingservice;

public class Clothing {
	private int id;
	private String brand;
	private String type;
	private String size;
	private double price;
	
	public Clothing(int id,String brand, String type, String size, double price) {
		this.id = id;
		this.brand = brand;
		this.type = type;
		this.size = size;
		this.price = price;
	}
	public Clothing(String brand, String type, String size, double price) {
		this.brand = brand;
		this.type = type;
		this.size = size;
		this.price = price;
	}

	public Clothing() {
		// TODO Auto-generated constructor stub
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
