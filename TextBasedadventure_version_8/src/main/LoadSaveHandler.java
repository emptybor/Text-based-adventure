package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.DataStorage;
import gui.ProjectPanel;

public class LoadSaveHandler {
	
	ProjectPanel pp;

	public LoadSaveHandler(ProjectPanel pp) {
		this.pp = pp;
		
	}
	public void save() {
		
		DataStorage ds = new DataStorage();

		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			ds.playerHP = pp.player.hp;
			ds.playerDamage = pp.player.damage;
			ds.playerName = pp.player.name;
			ds.stageNum = pp.stageH.stageNum;
			ds.fullScreenOn = pp.fullScreenOn;
			ds.isSetup = pp.isSetup;
			oos.writeObject(ds);
			
		} catch (Exception e) {
			System.out.println("Save Exception!");
		}
	}
	public void load() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			DataStorage ds = (DataStorage)ois.readObject();
			
			pp.player.hp = ds.playerHP;
			pp.player.damage = ds.playerDamage;
			pp.player.name = ds.playerName;
			pp.stageH.stageNum = ds.stageNum;
			pp.fullScreenOn = ds.fullScreenOn;
			pp.isSetup = ds.isSetup;
			
		} catch (Exception e) {
			System.out.println("load Exception!");
			e.printStackTrace();
		}
		
	}

}
