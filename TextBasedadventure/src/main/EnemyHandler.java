package main;

import entity.Enemy;
import entity.Entity;
import gui.ProjectPanel;

public class EnemyHandler {
	
	ProjectPanel pp;
	
	public Enemy[] enemies = new Enemy[100];

	public EnemyHandler(ProjectPanel pp) {
		super();
		this.pp = pp;
		
		setup();
	}
	
	public void setup() {
		
		int i = 0;
		
		enemies[i] = new Enemy(pp);
		enemies[i].setName("Goblin");
		enemies[i].setDamage(2);
		enemies[i].setMaxHP(5);
		enemies[i].setHP(enemies[i].getMaxHP());
		enemies[i].setExp(5);
		
		i++;
	}
	

}
