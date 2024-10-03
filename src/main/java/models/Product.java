package models;

public class Product implements Comparable<Product> {
	private int id;
	private String name, description, type;
	private byte[] image;
	private double price;
	private int amount;

	public Product(int id, String name, String description, String type, byte[] image, double price, int amount) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.image = image;
		this.price = price;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(Product o) {
		if (o.getPrice() < getPrice())
			return 1;
		if (o.getPrice() == getPrice())
			return 0;
		return -1;
	}
}
