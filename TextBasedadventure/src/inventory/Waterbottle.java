package inventory;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.ProjectPanel;

public class Waterbottle extends Item{
	
	public static String itemName = "waterbottle";
	
	private Image bottle_Filled;
	private Image bottle_Empty;

	public Waterbottle(ProjectPanel pp) {
		super(pp);
		
		try {
			bottle_Filled = ImageIO.read(getClass().getResourceAsStream("/item/full_waterbottle.png"));
			bottle_Empty = ImageIO.read(getClass().getResourceAsStream("/item/empty_waterbottle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setName(itemName);
		setImage(bottle_Filled);
		
		setState(true);
		
	}
	
	public void use() {
		
		
		if(isState() == true) {
			setState(false);
			setImage(bottle_Empty);
		pp.player.addHP(2);
		}
		
	}
	
	public void checkState() {
		
		if(isState() == true) {
			setImage(bottle_Filled);
		}
		if(isState() == false) {
			setImage(bottle_Empty);
		}
	}
}
