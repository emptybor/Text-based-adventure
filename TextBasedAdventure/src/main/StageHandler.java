package main;

public class StageHandler {
	
	ProjectPanel pp;
	
	public int stageNum = 0;
	public int choiceNum = 0;
	
	public String name;
	
	public StageHandler(ProjectPanel pp) {
		
		this.pp = pp;
	}
	
	public void setStartDialogue() {
				
		stageNum = 1;
		
		name = "Castle";
		
		pp.ui.messageText = "You are in front of the gate of the castle."
							+ "\nA guard blocks your way."
							+ "\nWhat do you do?";
		
		pp.ui.choice[0] = "Ask the guard";
		pp.ui.choice[1] = "Hit the guard";
		pp.ui.choice[2] = "Go Back";
		
		pp.gameState = pp.dialogueState;
		
	}
	
	public void checkChoice() {
		
		switch(stageNum) {
		
		case 0:
			setStartDialogue();
			break;
		
		case 1:
			switch(choiceNum) {
			case 1: setGuardDialogue(); break;
			case 2: setGuardHit(); break;
			case 3: goBackOfCastle(); break;
			default: break;
			}
			break;
		case 2:
			switch(choiceNum) {
			case 1: setGuardDialogue(); break;
			case 2: goBackToCastle(); break;
			default: break;
			}
			break;
		case 3:
			switch(choiceNum) {
			case 1: goBackToCastle(); break;
			default: break;
			}
			break;
		case 4:
			switch(choiceNum) {
			case 4: goBackToCastle(); break;
			default: break;
			}
			break;
			
		default: break;
		}
		
	}

	private void goBackToCastle() {
		stageNum = 0;
		checkChoice();
	}

	private void setGuardHit() {
		System.out.println("Hit!");
		
		stageNum = 3;
		
		name = "[Guard]";
		
		pp.ui.messageText = "<<Krzzzkt>>\nWHAT are you doing?!?";
		
		pp.player.setDamage();
		
		pp.ui.choice[0] = "Go Back";

		pp.gameState = pp.dialogueState;
	}

	private void setGuardDialogue() {
		System.out.println("Dialogue!");
		
		stageNum = 2;
		
		name = "[Guard]";
		
		pp.ui.messageText = "I don't know you.\nCome back, when you're more known.";
		
		pp.ui.choice[0] = "Ask the guard";
		pp.ui.choice[1] = "Go Back";

		pp.gameState = pp.dialogueState;
	}
	
	public void goBackOfCastle() {
		
		stageNum = 4;
		
		name = "Cross";
		
		pp.ui.messageText = "You're on a cross.\nWhere do you want to go?";
		
		pp.ui.choice[0] = "Go Ahead";
		pp.ui.choice[1] = "Go Left";
		pp.ui.choice[2] = "Go Right";
		pp.ui.choice[3] = "Go Back";

		pp.gameState = pp.dialogueState;
		
	}
	
	public int checkChoiceLength() {
		
		int choiceLengthCounter = 0;
		
		for(int i = 0; i < pp.ui.choice.length; i++) {
			
			if(pp.ui.choice[i] != null) {
				choiceLengthCounter++;
			}
		}
		
		return choiceLengthCounter;
	}
	
	public void prepareForNextStage() {
		
		pp.ui.messageText = "";
		pp.ui.shownMessage = "";
		
		name = null;
		
		for(int i = 0; i < pp.ui.choice.length; i++) {
			pp.ui.choice[i] = null;
		}
		
		pp.ui.messageSpeedCounter = 0;
		pp.ui.messageCounter = 0;
		
		pp.ui.currentColumn = 0;
		pp.ui.shownTextColumn = 0;
		
		pp.ui.messageCompleted = false;
		
		pp.subState = 1;
	}
	
	

}
