package entity;

import main.ProjectPanel;

public class Entity {
	
	public int hp;
	public int damage;
	public String name;
	
	public ProjectPanel pp;
	
	public Entity(ProjectPanel pp) {
		this.pp = pp;
	}
}
