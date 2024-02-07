package inventory;

import gui.ProjectPanel;

public class Item {
	
	ProjectPanel pp;
	private String name;
	private int amount;

	public Item(ProjectPanel pp) {
		
		this.pp = pp;
		
		setName("");
		setAmount(0);
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
	

}
