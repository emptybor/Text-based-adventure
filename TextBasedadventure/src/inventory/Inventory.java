package inventory;

import java.awt.Image;

import gui.ProjectPanel;

public class Inventory {
	
	ProjectPanel pp;
	
	public Item[] items = new Item[100];
	
	private int length;
	
	public Inventory(ProjectPanel pp) {
		
		this.pp = pp;
		setup();
		
	}
	
	public void setup() {
		
		items[0] = new Apple(pp);
		items[0].setAmount(1);
		
		items[1] = new Waterbottle(pp);
		items[1].setAmount(0);
		
		items[2] = new Plum(pp);
		items[2].setAmount(1);
		
		items[3] = new Steak(pp);
		items[3].setAmount(0);
		
		items[4] = new Sword(pp);
		items[4].setAmount(0);
		
		items[5] = new Axe(pp);
		items[5].setAmount(0);
		
		setLength(3);
		
	}
	
	public void use(String name) {
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null) {
				if(items[i].getName().equals(name)) {
						items[i].use();
				}
			}
		}
	}
	
	public boolean reduce(String name) {
		
		for(int i = 0; i < items.length; i++) {
			if(items[i].getName().equals(name)) {
				if(items[i].getAmount() > 0) {
				items[i].setAmount(items[i].getAmount() - 1);
				return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	public int getAmount(String name) {
		
		for(int i = 0; i < items.length; i++) {
			if(items[i].getName().equals(name)) {
				return items[i].getAmount();
			}
		}
		return 0;
	}
	
	public Image getImage(String itemName) {
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null) {
				if(items[i].getName().equals(itemName)) {
					return items[i].getImage();
				}
			}
		}
		
		return null;
	}
	
	public int commandNumToItemIndex(int commandNum) {
		
		for(int i = 0; i < items.length; i++) {
			
			if(items[i].getAmount() > 0) {
				if(commandNum > 1) {
					commandNum--;
				}
				else {
					return i;
				}
			}
		}
		return 999;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void checkLength() {
		
		length = 0;
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null) {
				if(items[i].getAmount() > 0) {
				length++;
				}
			}
		}
	}
	
	
	
	public void clear() {
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null) {
				items[i] = null;
			}
		}
	}

}
