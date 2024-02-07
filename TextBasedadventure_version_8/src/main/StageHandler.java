package main;

import gui.ProjectPanel;

public class StageHandler {
	
	ProjectPanel pp;
	
	public int stageNum = 0;
	public int choiceNum = 0;
	
	public String name;
	
	public StageHandler(ProjectPanel pp) {
		
		this.pp = pp;
	}
	
	public void setStartDialogue() {
				
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
			case 1: goBackOfCastle(); break;
			default: break;
			}
			break;
		case 7:
			switch(choiceNum) {
			case 1: entryFight(); break;
			}
			break;
			
		default: break;
		}
		
	}
	
	private void goRightOfCross1() {
		
		stageNum = 6;
		
		pp.animH.messageText = "You obtained an steak!";
		
		pp.inventory.items[3].addAmount(1);
		pp.inventory.checkLength();
		
		pp.ui.messageChoice[0] = "Go Back";

		pp.gameState = GameState.DialogueState;
		
	}
	
	private void goLeftOfCross1() {
		
		stageNum = 7;
		
		pp.animH.messageText = "You are in front of a Palantia."
				+ "\nBe careful these monsters are"
				+ "\nvery harmful!";

		pp.ui.messageChoice[0] = "Enter";
		
	}

	private void goBackToCastle() {
		stageNum = 1;
		checkChoice();
	}
	
	private void setGuardDialogue() {
		System.out.println("Dialogue!");
		
		stageNum = 3;
		
		name = "[Guard]";
		
		pp.animH.messageText = "I don't know you.\nCome back, when you're more known.";
		
		pp.ui.messageChoice[0] = "Ask the guard";
		pp.ui.messageChoice[1] = "Go Back";

		pp.gameState = GameState.DialogueState;
	}

	private void setGuardHit() {
		System.out.println("Hit!");
		
		stageNum = 4;
		
		name = "[Guard]";
		
		pp.animH.messageText = "<<Krzzzkt>>\nWHAT are you doing?!?";
		
		pp.player.setDamage();
		
		pp.ui.messageChoice[0] = "Go Back";

		pp.gameState = GameState.DialogueState;
	}

	
	
	public void goBackOfCastle() {
		
		stageNum =  5;
		
		name = "Cross";
		
		pp.animH.messageText = "You're on a cross.\nWhere do you want to go?";
		
		pp.ui.messageChoice[0] = "Go Ahead";
		pp.ui.messageChoice[1] = "Go Left";
		pp.ui.messageChoice[2] = "Go Right";
		pp.ui.messageChoice[3] = "Go Back";

		pp.gameState = GameState.DialogueState;
		
	}
	
	public void entryFight() {
		
		pp.fightH.stageNum = 1;
		
		pp.fightH.prepareForAlert();
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
		case 6: goRightOfCross1(); break;
		
		}
	}
	
	

}
