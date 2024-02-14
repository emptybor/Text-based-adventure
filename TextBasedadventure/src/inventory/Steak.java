package inventory;

import gui.ProjectPanel;

public class Steak extends Item{
	
	public static String itemName = "steak";

	public Steak(ProjectPanel pp) {
		super(pp);
		
		setName(itemName);
		
	}
	
	public void use() {
		
	}
	
	public boolean reduce() {
		
		addAmount(-1);
		
		if(getAmount() > 0) {
			return true;
		}
		else {
			setAmount(0);
			return false;
		}
	}

}
