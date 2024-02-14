package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import gui.ProjectPanel;
import main.GameState;

public class Player extends Entity{
	
	public String currentWeapon = "";
	ProjectPanel pp;
	
	public Player(int hp, int damage, ProjectPanel pp) {
		super(pp);
		
		this.pp = pp;
		setMaxHP(hp);
		setHP(getMaxHP());
		setDamage(damage);
		setLevel(1);
		
	}
	
	public int attackDamage() {
		
		double attackDamage = getDamage();
		
		switch(currentWeapon) {
		case "": break;
		case "Sword": attackDamage *= 1.5; break;
		case "Axe": attackDamage *= 1.5; break;
		default: break;
		}
		
		return (int) attackDamage;
	}
	
	public void appendDamage(int damage) {
		if(getHP() - damage >= 0) {
		setHP(getHP() - damage);
		}
		else {
			setHP(0);
		}
		if(getHP() <= 0) {
			pp.commandNum = 1;
			pp.animH.transitionLimit = 300;
			pp.animH.nextGameState = GameState.LoseState;
			pp.gameState = GameState.TransitionState;
		}
	}
}
