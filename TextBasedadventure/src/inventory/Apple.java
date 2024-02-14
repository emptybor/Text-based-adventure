package inventory;

import gui.ProjectPanel;

public class Apple extends Item{
	
	public static String itemName = "apple";

	public Apple(ProjectPanel pp) {
		super(pp);
		
		setName(itemName);
		
	}
	
	public void use() {
		
		if(reduce() == true) {
			pp.player.addHP(2);
		}
		
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
