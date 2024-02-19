package inventory;

import gui.ProjectPanel;

public class Sword extends Item{
	
	public static String itemName = "Sword";
	
	public Sword(ProjectPanel pp) {
		super(pp);
		
		setName(itemName);
		
	}
	
	public void use() {
		
		if(isEquiped() == true) {
			pp.player.currentWeapon = "";
			setEquiped(false);
		}
		else {
			pp.player.currentWeapon = getName();
			setEquiped(true);
		}
		
	}

}
