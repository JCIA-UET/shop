package uet.jcia.shop.model;

public class Category extends Item {
	
	public Category() {
		
	}
	
	public Category(int id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
