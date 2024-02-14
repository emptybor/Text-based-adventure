package main;

import gui.ProjectPanel;

public class AlertHandler {
	
	ProjectPanel pp;
	
	public int stageNum = 0;
	public int choiceNum = 0;
	
	public String name;
	
	public AlertHandler(ProjectPanel pp) {
		
		this.pp = pp;
	}
	
	public void checkChoice() {
		
		switch(stageNum) {
		
		case 0:
			pp.gameState = GameState.OptionsState;
			break;
		
		case 1:
			switch(choiceNum) {
			case 1: pp.resetGame(); break;
			case 2: pp.gameState = GameState.OptionsState; break;
			}
			break;
		case 2:
			switch(choiceNum) {
			case 1: pp.reload(); break;
			case 2: pp.gameState = GameState.OptionsState; break;
			}
			break;
			
		default: break;
		}
		
	}
	
	public void changeFullScreenOnAlert() {
		
		if(pp.fullScreenOn == true) {
			pp.fullScreenOn = false;
		}
		else {
			pp.fullScreenOn = true;
		}
		
		pp.loadSave.saveConfig();
		
		prepareForAlert();
		
		stageNum = 0;
		
		pp.ui.alertText = "The fullScreen will change up\nby the next restart.";

		pp.ui.alertChoice[0] = "Enter";
		
		pp.gameState = GameState.AlertState;

	}
	
	public void reloadAlert() {
		
		stageNum = 2;
		
		prepareForAlert();
				
		pp.ui.alertText = "Do you really want to reload your\ncurrent game?";

		pp.ui.alertChoice[0] = "Yes";
		pp.ui.alertChoice[1] = "No";

		pp.gameState = GameState.AlertState;
		
		
	}
	
	public void resetGameAlert() {
		
		stageNum = 1;
		
		prepareForAlert();
				
		pp.ui.alertText = "Do you really want to delete your\ncurrent game?";

		pp.ui.alertChoice[0] = "Yes";
		pp.ui.alertChoice[1] = "No";

		pp.gameState = GameState.AlertState;

	}
	
	public int checkChoiceLength() {
		
		int choiceLengthCounter = 0;
		
		for(int i = 0; i < pp.ui.alertChoice.length; i++) {
			
			if(pp.ui.alertChoice[i] != null) {
				choiceLengthCounter++;
			}
		}
		
		return choiceLengthCounter;
	}
	
	public void prepareForAlert() {
				
		name = null;
		pp.ui.alertText = "";
		
		for(int i = 0; i < pp.ui.alertChoice.length; i++) {
			pp.ui.alertChoice[i] = null;
		}
		
	}
	
	

}
