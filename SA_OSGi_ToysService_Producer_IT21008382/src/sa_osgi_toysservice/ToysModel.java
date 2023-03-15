package sa_osgi_toysservice;

public class ToysModel {
	private int id;
	private String type;
	private String ageGroup;
	private double price;
	
	public ToysModel() {
		super();
	}
	
	public ToysModel(int id, String type, String ageGroup, double price) {
		super();
		this.id = id;
		this.type = type;
		this.ageGroup = ageGroup;
		this.price = price;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
