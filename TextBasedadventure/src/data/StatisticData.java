package data;

import java.io.Serializable;

import inventory.Item;

public class StatisticData implements Serializable{
	
	public int playerLevel;
	public int playerMaxHP;
	public int playerHP;
	public int playerDamage;
	public String playerName;
	
	public int stageNum;	
	public boolean isSetup;
	
	public boolean[] isExplored;
	public boolean[] itemIsEquiped = new boolean[100];
	public int[] itemAmount = new int[100];
	
}
