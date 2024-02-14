package inventory;

import gui.ProjectPanel;

public class Plum extends Item{
	
	public static String itemName = "plum";

	public Plum(ProjectPanel pp) {
		super(pp);
		
		setName(itemName);
		
	}
	
	public void use() {
		
		if(reduce() == true) {
			pp.player.addHP(1);
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
