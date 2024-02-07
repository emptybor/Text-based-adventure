package inventory;

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
		
		//ggf auch Klassen wie apple oder water erstellen, um dort die Methode "use" aufzurufen
		
		items[0] = new Item(pp);
		items[0].setName("apple");
		items[0].setAmount(1);
		
		items[1] = new Item(pp);
		items[1].setName("water");
		items[1].setAmount(2);
		
		items[2] = new Item(pp);
		items[2].setName("plum");
		items[2].setAmount(2);
		
		items[3] = new Item(pp);
		items[3].setName("steak");
		items[3].setAmount(0);
		
		setLength(3);
		
	}
	
	public void use(String name) {

		switch(name) {
		
		case "apple":
			if(reduce(name) == true) {pp.player.hp += 3;} break;
		case "water":
			if(reduce(name) == true) {pp.player.hp++;} break;
		case "plum":
			if(reduce(name) == true) {pp.player.hp += 2;} break;
		case "steak":
			if(reduce(name) == true) {pp.player.hp += 2;} break;
		default: break;
		
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
