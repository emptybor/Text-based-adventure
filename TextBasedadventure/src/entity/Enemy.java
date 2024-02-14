package entity;

import gui.ProjectPanel;

public class Enemy extends Entity{
	
	private boolean stumble;
	
	public Enemy(ProjectPanel pp) {
		super(pp);
	}
	
	public void appendDamage(int damage) {
		
		double stumbleValue = 1;
		if(stumble == true) {
			stumble = false;
			stumbleValue = 2;
		}
		setHP(getHP() - (int) (damage * stumbleValue));
	}

	public boolean isStumble() {
		return stumble;
	}

	public void setStumble(boolean stumble) {
		this.stumble = stumble;
	}
	
	
		
}
