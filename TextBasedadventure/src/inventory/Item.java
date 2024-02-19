package inventory;

import java.awt.Image;

import gui.ProjectPanel;

public class Item {
	
	ProjectPanel pp;
	private int amount;
	private String name;
	
	private boolean state;
	private boolean isEquiped;
	
	private Image image;
	
	public static String itemName = "";

	public Item(ProjectPanel pp) {
		
		this.pp = pp;
		
		setName(itemName);
		setAmount(0);
	}
	
	public void use() {}
	
	public boolean reduce() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void addAmount(int amount) {
		this.amount += amount;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	public void checkState() {
		
	}

	public boolean isEquiped() {
		return isEquiped;
	}

	public void setEquiped(boolean isEquiped) {
		this.isEquiped = isEquiped;
	}
	

}
