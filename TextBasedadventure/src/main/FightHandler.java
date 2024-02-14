package main;

import java.util.Random;

import entity.Enemy;
import gui.ProjectPanel;

public class FightHandler {
	
	ProjectPanel pp;
	
	public int stageNum = 0;
	public int choiceNum = 0;
	
	public String name;
	public String attackDirection;
	public String playerAction;
	public String enemyAction;
	public String strength;
	
	public int strengthValue;
	
	public boolean blocked;
	
	public Enemy currentEnemy;
	
	public FightHandler(ProjectPanel pp) {
		
		this.pp = pp;
	}
	
	public void checkChoice() {
		
		switch(stageNum) {
		
		case 1:
			playerAttacks(); break;
		case 2:
			switch(choiceNum) {
			case 1: attackLeft(); break;
			case 2: attackRight(); break;
			case 3: attackLeg(); break;
			default: break;
			}
			break;
		case 3:
			enemyAttacks(); break;
		case 4:
			switch(choiceNum) {
			case 1: dogdeLeft(); break;
			case 2: dodgeRight(); break;
			case 3: block(); break;
			default: break;
			}
			break;
		}
		
	}
	
	private void playerAttacks() {
		
		prepareForFight();
		getRandomEnemyAction();
		
		stageNum = 2;
		
		pp.ui.fightText = currentEnemy.getName() + " goes " + enemyAction + "!" + isEnemyStumbling();
		
		//Immer Max 3 Auswahlmöglichkeiten

		pp.ui.fightChoice[0] = "Attack Left!";
		pp.ui.fightChoice[1] = "Attack Right!";
		pp.ui.fightChoice[2] = "Attack Leg!";
		
		pp.animH.timeLimit = 200;
		
	}

	private void attackLeg() {
		playerAction = "Leg";
		currentEnemy.appendDamage(calcEnemyDamage());
		
		if(enemyAction.equals("Back")) {
			currentEnemy.setStumble(true);
		}
		stageNum = 3;
	}

	private void attackLeft() {
		playerAction = "Left";
		currentEnemy.appendDamage(calcEnemyDamage());
		stageNum = 3;
	}

	private void attackRight() {
		playerAction = "Right";
		currentEnemy.appendDamage(calcEnemyDamage());
		stageNum = 3;
	}
	
	private void enemyAttacks() {
				
		prepareForFight();
		
		getRandomAttackDirection();
		getAttackStrength();
		
		stageNum = 4;
		
		pp.ui.fightText = currentEnemy.getName() + " attacks!\nThe " + strength + "attack comes from the " + enemyAction + "!";
		
		//Immer Max 3 Auswahlmöglichkeiten

		pp.ui.fightChoice[0] = "Dodge Left!";
		pp.ui.fightChoice[1] = "Dodge Right!";
		pp.ui.fightChoice[2] = "Block!";
		
		pp.animH.timeLimit = 200;
		
	}
	
	private void dogdeLeft() {
		playerAction = "Left";
		pp.player.appendDamage(calcPlayerDamage());
		stageNum = 1;
	}
	
	private void dodgeRight() {
		playerAction = "Right";
		pp.player.appendDamage(calcPlayerDamage());
		stageNum = 1;
	}

	private void block() {
		playerAction = "Block";
		pp.player.appendDamage(calcPlayerDamage());
		stageNum = 1;
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
	
	private void getRandomAttackDirection() {
		
		Random random = new Random();
		int num = random.nextInt(3);
		
		String text = "";
		
		switch(num) {
		case 0: text = "Top"; break;
		case 1: text = "Left"; break;
		case 2: text = "Right"; break;
		default: break;
		}
		enemyAction = text;
	}
	
	private void getRandomEnemyAction() {
		
		Random random = new Random();
		int num = random.nextInt(3);
		
		String text = "";
		
		switch(num) {
		case 0: text = "Left"; break;
		case 1: text = "Right"; break;
		case 2: text = "Back"; break;
		default: break;
		}
		enemyAction = text;
	}
	
	private void getAttackStrength() {
		
		Random random = new Random();
		int num = random.nextInt(2);
		
		String text = "";
		
		switch(num) {
		case 0: text = ""; break;
		case 1: text = "heavy "; break;
		default: break;
		}
		strengthValue = num;
		strength = text;
	}

	private int calcEnemyDamage() {
		
		int damage = 0;
		
		switch(enemyAction) {
		case "Left":
			switch(playerAction) {
			case "Leg": break;
			case "Left": damage = pp.player.attackDamage(); break;
			case "Right": break;
			default: break;
			}
			break;
		case "Right": 
			switch(playerAction) {
			case "Leg": break;
			case "Left": break;
			case "Right": damage = pp.player.attackDamage(); break;
			default: break;
			}
			break;
		case "Back":
			switch(playerAction) {
			case "Leg": damage = pp.player.attackDamage();  break;
			case "Left": break;
			case "Right": break;
			default: break;
			}
			break;
		default: break;
		}
		
		return damage;
	}
	
	private int calcPlayerDamage() {
		
		int damage = 0;
		
		switch(playerAction) {
		case "Left":
			switch(enemyAction) {
			case "Top": break;
			case "Left": damage = currentEnemy.getDamage(); break;
			case "Right": break;
			default: break;
			}
			break;
		case "Right": 
			switch(enemyAction) {
			case "Top": break;
			case "Left": break;
			case "Right": damage = currentEnemy.getDamage(); break;
			default: break;
			}
			break;
		case "Block":

			if(strengthValue == 0) {
			currentEnemy.setStumble(true);
			}
			if(strengthValue == 1){
			damage = currentEnemy.getDamage();
			}
			break;	
		}
		
		if(strengthValue == 1) {
			damage *= 2;
		}
		
		return damage;
	}
	
	private String isEnemyStumbling() {
		
		if(currentEnemy.isStumble() == true) {
			return "\n" + currentEnemy.getName() + " is stumbling!";
		}
		return "";
	}

	public void endFight() {
		
		pp.commandNum = 1;
		
		if(currentEnemy.getHP() <= 0) {
		pp.stageH.isExplored[pp.stageH.stageNum] = true;
		pp.stageH.stageNum = pp.stageH.nextStageNum;
		pp.stageH.checkLoadState();
		}
	}
	
	public void prepareForFight() {
		
		pp.ui.fightText = "";
		
		for(int i = 0; i < pp.ui.alertChoice.length; i++) {
			pp.ui.alertChoice[i] = null;
		}
		
	}
}
