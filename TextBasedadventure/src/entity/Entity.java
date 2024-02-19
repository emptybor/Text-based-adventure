package entity;

import gui.ProjectPanel;

public class Entity {
	
	private int hp;
	private int maxHP;
	private int damage;
	private int level;
	private int exp;
	private int nextExp;
	private double range;
	private double size;
	private String name;
	
	public ProjectPanel pp;
	
	public Entity(ProjectPanel pp) {
		this.pp = pp;
	}

	public int getHP() {
		return hp;
	}

	public void setHP(int hp) {
		this.hp = hp;
	}
	
	public void addHP(int hp) {
		if(this.hp + hp <= maxHP) {
		this.hp += hp;
		}
		else {
		this.hp = maxHP;
		}
	}
	
	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void appendDamage(int damage) {
		setHP(getHP() - damage);
	}
	
	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public void addLevel() {
		level++;
		if(hp == maxHP) {	
			maxHP += 20;
			hp = maxHP;
		}
		else {
		maxHP += 20;
		}
	}
	
	public boolean checkLevel() {
		
		if(exp >= nextExp) {
			
			while(exp >= nextExp) {
			exp -= nextExp;
			nextExp += 10;
			addLevel();
			}
			return true;
			
		}
		
		return false;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	public void addExp(int exp) {
		this.exp += exp;
	}

	public int getNextExp() {
		return nextExp;
	}

	public void setNextExp(int nextExp) {
		this.nextExp = nextExp;
	}
}
