package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.ProjectPanel;

public class Player extends Entity{
	
	public String currentWeapon = "";
	ProjectPanel pp;
	
	public Player(int hp, int damage, ProjectPanel pp) {
		super(pp);
		
		this.pp = pp;
		this.hp = hp;
		this.damage = damage;
		
		currentWeapon = "/";
		
	}
	
	public void setupWeapon() {
		
		int tempDamage = damage;
		
		switch(currentWeapon) {
		case "Knife": tempDamage *= (int) 1.5; break;
		case "Sword": tempDamage *= (int) 2; break;
		case "Long Sword": tempDamage *= (int) 2.5; break;
		default: break;
		}
		
		damage = tempDamage;
	}
	
	public void setDamage() {
		hp--;
	}

}
