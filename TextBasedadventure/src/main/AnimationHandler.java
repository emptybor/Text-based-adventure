package main;

import gui.ProjectPanel;

public class AnimationHandler {
	
	public String messageText = "";
	public String[] splittedMessageText = new String[100];
	public String shownMessage = "";
	
	public String alertText = "";
	public String fightText = "";

	public int messageSpeed = 2;
	
	public int messageSpeedCounter = 0;
	public int messageCounter = 0;
	
	public int currentColumn = 0;
	public int shownTextColumn = 0;
	public int tempColumn;
	
	public double timeCounter;
	public int timeLimit;
	
	public double transitionCounter;
	public double transitionLimit;
	
	public boolean messageCompleted = false;
	
	public int subState = 0;
	
	public GameState nextGameState;
	
	ProjectPanel pp;
	
	public AnimationHandler(ProjectPanel pp) {
		super();
		this.pp = pp;
	}
	
	public boolean showAnimation() {
		
		boolean tempBoolean = false;
		
		switch(subState) {
		case 1: tempBoolean = showTimeCount(); break;
		case 2: tempBoolean = showTransition(); break;
		case 3: showMessage(); break;

		default: break;
		}
		
		return tempBoolean;
	}
	
	public boolean showTimeCount() {
		
		timeCounter++;
		
		if(timeCounter > timeLimit) {
			timeCounter = 0;
			return true;
		}
		
		return false;
		
	}
	
	public void resetTimeCount() {
		
		timeCounter = 0;
		timeLimit = 0;
		
		subState = 0;
	}
	public void showMessage() {
		
		splittedMessageText = messageText.split("\n");	    
    	
    	if(messageCounter == splittedMessageText[currentColumn].length()) {
    		if(currentColumn < splittedMessageText.length - 1) {
    		currentColumn++;
    		tempColumn = currentColumn;
    		
    		messageCounter = 0;
    		
    		
    		}
    	}
    	
    	if(messageCounter > splittedMessageText[currentColumn].length() - 1) {
    		messageCounter = splittedMessageText[currentColumn].length();
    			messageCompleted = true;
    	}
    	
    	messageSpeedCounter++;
    	
    	if(tempColumn % 3 == 0 && tempColumn != 0) {
    		
    	}else {
    		
    		if(messageSpeedCounter % messageSpeed == 0) {
    			if(messageCounter < splittedMessageText[currentColumn].length()) {
    			messageCounter++;
    			}
    	}
    	}
	}
	
	public void resetMessage() {
		
		currentColumn = 1;
		messageCounter = 0;
		messageSpeedCounter = 0;
		
		subState = 0;

	}
	
	public boolean showTransition() {
		
		transitionCounter++;
		
		if(transitionCounter > transitionLimit) {
			
			resetTransition();
			pp.commandNum = 1;
			return true;
		}
		
		return false;
	}
	
	public void resetTransition() {
		
		transitionCounter = 0;
		transitionLimit = 0;
		
		subState = 0;
	}

	

}
