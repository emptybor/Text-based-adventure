package main;

import entity.Enemy;
import gui.ProjectPanel;

public class FightHandler {
	
	ProjectPanel pp;
	
	public int stageNum = 0;
	public int choiceNum = 0;
	
	public String name;
	
	Enemy currentEnemy;
	
	public FightHandler(ProjectPanel pp) {
		
		this.pp = pp;
	}
	
	public void checkChoice() {
		
		switch(stageNum) {
		
		case 1:
			startFight(); break;
		case 2:
			switch(choiceNum) {
			
			case 1: startFight();
			}
			
		}
		
	}
	
	private void startFight() {
		
		pp.ui.fightText = "The fight begins!";
		
		//Immer Max 3 Auswahlmöglichkeiten

		pp.ui.fightChoice[0] = "Attack Head";
		pp.ui.fightChoice[1] = "Attack Hip";
		pp.ui.fightChoice[2] = "Attack Leg";
		
	}
	
	public int checkChoiceLength() {
		
		int choiceLengthCounter = 0;
		
		for(int i = 0; i < pp.ui.fightChoice.length; i++) {
			
			if(pp.ui.fightChoice[i] != null) {
				choiceLengthCounter++;
			}
		}
		
		return choiceLengthCounter;
	}
	
	public void prepareForAlert() {
		
		name = null;
		pp.ui.fightText = "";
		
		for(int i = 0; i < pp.ui.alertChoice.length; i++) {
			pp.ui.alertChoice[i] = null;
		}
		
	}
}
