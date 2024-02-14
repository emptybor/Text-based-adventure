package inventory;

import java.io.IOException;

import javax.imageio.ImageIO;

import gui.ProjectPanel;

public class Axe extends Item{
	
	public static String itemName = "Axe";
	
	private boolean isEquiped;

	public Axe(ProjectPanel pp) {
		super(pp);
		
		setName(itemName);
		
	}
	
	public void use() {
		
		if(isEquiped == true) {
			pp.player.currentWeapon = "";
			isEquiped = false;
		}
		else {
			pp.player.currentWeapon = getName();
			isEquiped = true;
		}
		
	}

}
