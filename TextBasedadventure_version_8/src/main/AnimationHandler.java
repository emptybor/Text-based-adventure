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
	
	public boolean messageCompleted = false;
	
	ProjectPanel pp;
	
	public AnimationHandler(ProjectPanel pp) {
		super();
		this.pp = pp;
	}
	
	public void showMessage(double x, double y) {
		
		splittedMessageText = messageText.split("\n");	    
    	
    	if(messageCounter == splittedMessageText[currentColumn].length()) {
    		if(currentColumn < splittedMessageText.length - 1) {
    		currentColumn++;
    		tempColumn = currentColumn;
    		
    		messageCounter = 0;
    		
    		
    		}
    	}
    	for(int i = shownTextColumn; i < currentColumn; i++) {
    		if(splittedMessageText[i] != null) {
    		y += pp.ui.getYStringLength(splittedMessageText[i]);
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
	}

	

}
