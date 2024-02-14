package main;

import gui.ProjectPanel;

public class StageHandler {
	
	ProjectPanel pp;
	
	public int stageNum = 0;
	public int nextStageNum = 0;
	public int savedStageNum = 0;
	public int choiceNum = 0;
	
	public boolean[] isExplored = new boolean[100];
	
	public String name;
	
	public StageHandler(ProjectPanel pp) {
		
		this.pp = pp;
	}
	
	public void setStartDialogue() {
		
		prepareForNextStage();
				
		stageNum = 2;
		
		name = "Castle";
		
		pp.animH.messageText = "You are in front of the gate of the castle."
							+ "\nA guard blocks your way."
							+ "\nWhat do you do?";
		
		pp.ui.messageChoice[0] = "Ask the guard";
		pp.ui.messageChoice[1] = "Hit the guard";
		pp.ui.messageChoice[2] = "Go Back";
		
		pp.gameState = GameState.DialogueState;
		
	}
	
	public void checkChoice() {
				
		switch(stageNum) {
		
		case 1:
			setStartDialogue();
			break;
		
		case 2:
			switch(choiceNum) {
			case 1: setGuardDialogue(); break;
			case 2: setGuardHit(); break;
			case 3: goBackOfCastle(); break;
			default: break;
			}
			break;
		case 3:
			switch(choiceNum) {
			case 1: setGuardDialogue(); break;
			case 2: goBackToCastle(); break;
			default: break;
			}
			break;
		case 4:
			switch(choiceNum) {
			case 1: goBackToCastle(); break;
			default: break;
			}
			break;
		case 5:
			switch(choiceNum) {
			case 1: break;
			case 2: goLeftOfCross1(); break;
			case 3: goRightOfCross1(); break;
			case 4: goBackToCastle(); break;
			default: break;
			}
			break;
		case 6:
			switch(choiceNum) {
			case 1: pp.fightH.currentEnemy = pp.enemyH.enemies[0]; entryFight(); nextStageNum = 10; break;
			default: break;
			}
			break;
		case 7:
			switch(choiceNum) {
			case 1: goRightOfCross1(); break;
			default: break;
			}
			break;
		case 8:
			switch(choiceNum) {
			case 1: goRightOfCross1(); break;
			default: break;
			}
			break;
		case 9:
			switch(choiceNum) {
			case 1: save(); break;
			case 2: drinkWater(); break;
			case 3: fillUpWater(); break;
			case 4: goBackOfCastle(); break;
			default: break;
			}
			break;
		case 10:
			switch(choiceNum) {
			case 1: goToBreeFromCross1(); break;
			}
			break;
		case 11:
			switch(choiceNum) {
			case 1: goToBreeFromCross1(); break;
			}
			break;
		case 12:
			switch(choiceNum) {
			case 1: goToMarket(); break;
			case 2: goToTownhall(); break;
			case 3: readVillageSignFromBree(); break;
			case 4: goBackOfCastle(); break;
			}
			break;
		case 13:
			switch(choiceNum) {
			case 1: goToBreeFromCross1(); break;
			}
			break;
			
		default: break;
		}
		
	}

	private void goRightOfCross1() {
		
		prepareForNextStage();
		
		if(isExplored(7) == true) {
			obtainItem(1, 1);
		}
		else if(isExplored(8) == true) {
			waterBottleDialog();
		}
		else {
			
			stageNum = 9;
			
			name = "Spring";
			
			pp.animH.messageText = "You are arrived on a spring.\nHere you can recover yourself.";
			
			pp.ui.messageChoice[0] = "Save";
			pp.ui.messageChoice[1] = "Drink";
			pp.ui.messageChoice[2] = "Fill up your waterbottle";
			pp.ui.messageChoice[3] = "Go Back";


			pp.gameState = GameState.DialogueState;
			
			
		}
	}
	
	private void goLeftOfCross1() {
		
		prepareForNextStage();
		
		stageNum = 6;

		if(isExplored(stageNum) == true) {
		
		pp.animH.messageText = "You are in front of a Palantia."
				+ "\nBe careful these monsters are"
				+ "\nvery harmful!";

		pp.ui.messageChoice[0] = "Enter";
		
		}else {

		stageNum = 12;
		checkLoadState();
		}
		
	}

	private void goBackToCastle() {
		
		prepareForNextStage();
		stageNum = 1;
		checkChoice();
	}
	
	private void setGuardDialogue() {
		
		prepareForNextStage();
		
		System.out.println("Dialogue!");
		
		stageNum = 3;
		
		name = "Guard";
		
		pp.animH.messageText = "I don't know you.\nCome back, when you're more known.";
		
		pp.ui.messageChoice[0] = "Ask the guard";
		pp.ui.messageChoice[1] = "Go Back";

		pp.gameState = GameState.DialogueState;
	}

	private void setGuardHit() {
		
		prepareForNextStage();
		
		System.out.println("Hit!");
		
		stageNum = 4;
		
		name = "[Guard]";
		
		pp.animH.messageText = "<<Krzzzkt>>\nWHAT are you doing?!?";
		
		pp.ui.messageChoice[0] = "Go Back";
		
		pp.player.appendDamage(1);
		
		if(pp.player.getHP() > 0) {

		pp.gameState = GameState.DialogueState;
		
		}
	}

	
	
	public void goBackOfCastle() {
		
		prepareForNextStage();
		
		stageNum =  5;
		
		name = "Cross";
		
		pp.animH.messageText = "You're on a cross.\nWhere do you want to go?";
		
		pp.ui.messageChoice[0] = "Go Ahead";
		pp.ui.messageChoice[1] = "Go Left";
		pp.ui.messageChoice[2] = "Go Right";
		pp.ui.messageChoice[3] = "Go Back";

		pp.gameState = GameState.DialogueState;
		
	}
	
	private void waterBottleDialog() {
		
		prepareForNextStage();
		
		pp.animH.messageText = "This waterbottle can used to fill up your hp"
				+ "\nwhen it's filled."
				+ "\nYou can find it in your inventory [E]."
				+ "\nWhen you find springs, you can fill up once."
				+ "\nThere you can also save your game, to"
				+ "\ncreate a checkpoint!";
		
		pp.ui.messageChoice[0] = "Enter";
		
		pp.gameState = GameState.DialogueState;
		
	}
	
	private void drinkWater() {
		
		pp.player.addHP(2);
		
	}
	
	private void fillUpWater() {
		
		pp.inventory.items[1].setState(true);
		pp.inventory.items[1].checkState();
	}
	
	private void goToBreeFromCross1() {
		
		if(isExplored(10) == true) {
			winDialog();
		}
		else if(isExplored(11) == true){
			obtainItem(4, 1);

		}
		else {
			
			stageNum = 12;
			
			prepareForNextStage();
			
			name = "Bree";
			
			pp.animH.messageText = "You are in Bree!";
			
			pp.ui.messageChoice[0] = "Go to market";
			pp.ui.messageChoice[1] = "Go to townhall";
			pp.ui.messageChoice[2] = "Read the villagesign";
			pp.ui.messageChoice[3] = "Go Back";
			
			pp.gameState = GameState.DialogueState;
		}
	}
	
	private void readVillageSignFromBree() {
		
		stageNum = 13;
		
		prepareForNextStage();
		
		name = "Bree";
		
		pp.animH.messageText = "Welcome to Bree!\n";
		
		pp.ui.messageChoice[0] = "Go Back";
		
		pp.gameState = GameState.DialogueState;
		
	}

	private void goToTownhall() {
		
	}

	private void goToMarket() {
		
	}
	
	private void winDialog() {

		prepareForNextStage();
		
		pp.player.addLevel();
		
		pp.animH.messageText = "You won the fight against the " + pp.fightH.currentEnemy.getName() + "!"
				+ "\nYou level up! You are now level " + pp.player.getLevel() + "!";
		
		pp.ui.messageChoice[0] = "Enter";
		
		pp.gameState = GameState.DialogueState;
		
	}
	
	private void obtainItem(int index, int amount) {
		
		prepareForNextStage();
		
		pp.inventory.items[index].addAmount(amount);
		pp.inventory.checkLength();
		
		pp.animH.messageText = "You obtained a " + pp.inventory.items[index].getName() +"!";
		
		
		
		pp.ui.messageChoice[0] = "Enter";
		
		pp.gameState = GameState.DialogueState;
		
	}
	
	private boolean isExplored(int stageNum) {
		
		if(isExplored[stageNum] == false) {
			
		this.stageNum = stageNum;
	
		isExplored[stageNum] = true;
		
		return true;
		}else {
			return false;
		}
	}
	
	private void save() {
				
		pp.loadSave.saveStats();
		
	}
	
	public void entryFight() {
		
		prepareForNextStage();
		
		pp.fightH.stageNum = 1;
		
		pp.fightH.name = pp.fightH.currentEnemy.getName();
		
		pp.fightH.prepareForFight();
		pp.fightH.checkChoice();
		
		pp.gameState = GameState.FightState;
		pp.tempGameState = pp.gameState;
	}
	
	public int checkChoiceLength() {
		
		int choiceLengthCounter = 0;
		
		for(int i = 0; i < pp.ui.messageChoice.length; i++) {
			
			if(pp.ui.messageChoice[i] != null) {
				choiceLengthCounter++;
			}
		}
		
		return choiceLengthCounter;
	}
	
	
	
	public void prepareForNextStage() {
		
		pp.animH.messageText = "";
		pp.animH.shownMessage = "";
		
		name = null;
		
		for(int i = 0; i < pp.ui.messageChoice.length; i++) {
			pp.ui.messageChoice[i] = null;
		}
		for(int i = 0; i < pp.animH.splittedMessageText.length; i++) {
			pp.animH.splittedMessageText[i] = null;
		}
		
		pp.animH.messageSpeedCounter = 0;
		pp.animH.messageCounter = 0;
		
		pp.animH.currentColumn = 0;
		pp.animH.shownTextColumn = 0;
		pp.animH.tempColumn = 0;
		
		pp.animH.messageCompleted = false;
		
		pp.subState = 1;
	}
	
	public void checkLoadState() {
		
		switch(stageNum) {
		
		case 1: setStartDialogue(); break;
		case 2: setStartDialogue(); break;
		case 3: setGuardDialogue(); break;
		case 4: setGuardHit(); break;
		case 5: goBackOfCastle(); break;
		case 6: goLeftOfCross1(); break;
		case 7: goRightOfCross1(); break;
		case 8: goRightOfCross1(); break;
		case 9: goRightOfCross1(); break;
		case 10: goToBreeFromCross1(); break;
		case 11: goToBreeFromCross1(); break;
		case 12: goToBreeFromCross1(); break;

		
		}
	}
	
//	public boolean checkExplored(int stageNum) {
//		
//	}
	
	

}
