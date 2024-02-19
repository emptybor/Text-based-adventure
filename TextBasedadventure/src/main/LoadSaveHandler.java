package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.ConfigData;
import data.StatisticData;
import gui.ProjectPanel;

public class LoadSaveHandler {
	
	ProjectPanel pp;

	public LoadSaveHandler(ProjectPanel pp) {
		this.pp = pp;
		
	}
	public void saveStats() {
		
		StatisticData ds = new StatisticData();

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("../stats.dat")));

			ds.playerLevel = pp.player.getLevel();
			ds.playerMaxHP = pp.player.getMaxHP();
			ds.playerHP = pp.player.getHP();
			ds.playerDamage = pp.player.getDamage();
			ds.playerName = pp.player.getName();
			ds.stageNum = pp.stageH.stageNum;
			pp.stageH.savedStageNum = pp.stageH.stageNum;
			ds.isSetup = pp.isSetup;
			ds.isExplored = pp.stageH.isExplored;
			
			
			for(int i = 0; i < pp.inventory.items.length; i++) {
				if(pp.inventory.items[i] != null) {
				ds.itemAmount[i] = pp.inventory.items[i].getAmount();
				if(pp.inventory.items[i].isEquiped() == true) {
					ds.itemIsEquiped[i] = pp.inventory.items[i].isEquiped();
				}
				}
			}
			oos.writeObject(ds);
			
		} catch (Exception e) {
			System.out.println("Save Exception!");
			e.printStackTrace();
		}
	}
	
	public void saveConfig() {
		
		ConfigData ds = new ConfigData();

		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../config.dat"));

			ds.fullScreenOn = pp.fullScreenOn;
			ds.isSetup = pp.isSetup;
			
			oos.writeObject(ds);
			
		} catch (Exception e) {
			System.out.println("Save Exception!");
			e.printStackTrace();
		}
		
		
	}
	
	public void loadStats() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("../stats.dat")));
			
			StatisticData ds = (StatisticData)ois.readObject();
					
			pp.player.setLevel(ds.playerLevel);
			pp.player.setMaxHP(ds.playerMaxHP);
			pp.player.setHP(ds.playerHP);
			pp.player.setDamage(ds.playerDamage);
			pp.player.setName(ds.playerName);
			pp.stageH.stageNum = ds.stageNum;
			pp.stageH.savedStageNum = pp.stageH.stageNum;
			pp.isSetup = ds.isSetup;
			pp.stageH.isExplored = ds.isExplored;
			
			for(int i = 0; i < ds.itemAmount.length; i++) {
				if(pp.inventory.items[i] != null) {
				pp.inventory.items[i].setAmount(ds.itemAmount[i]);
				if(ds.itemIsEquiped[i] == true) {
					pp.inventory.items[i].use();
				}
				}
			}
			
			pp.inventory.checkLength();

			
		} catch (Exception e) {
			System.out.println("load Exception!");
			e.printStackTrace();
		}
		
	}
	
	public void loadConfig() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("../config.dat")));
			
			ConfigData ds = (ConfigData)ois.readObject();
					
			pp.fullScreenOn = ds.fullScreenOn;
			pp.isSetup = ds.isSetup;
			
		} catch (Exception e) {
			System.out.println("load Exception!");
			e.printStackTrace();
		}
		
	}

}
