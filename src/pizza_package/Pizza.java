package pizza_package;

import java.io.Serializable;

public class Pizza  implements Serializable {

	private int id;
    private String DesignPizz;
    private double price;
    private String image;
   
    public Pizza( ) {
	 
	}
    
	public Pizza(int id, String designPizz, double price) {
		super();
		this.id = id;
		this.DesignPizz = designPizz;
		this.price = price;
	}
	public Pizza(int id, String designPizz, double price,String image) {
		super();
		this.id = id;
		this.DesignPizz = designPizz;
		this.price = price;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", DesignPizz=" + DesignPizz + ", price=" + price + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignPizz() {
		return DesignPizz;
	}
	public void setDesignPizz(String designPizz) {
		DesignPizz = designPizz;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		// TODO Auto-generated method stub
		return image;
	}

}
