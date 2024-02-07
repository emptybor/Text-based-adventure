package main;

import gui.ProjectFrame;
import gui.ProjectPanel;

public class InputHandler {
	
	ProjectPanel pp;
	
	private boolean enterPressed = false;
	private boolean escPressed = false;
	private boolean ePressed = false;
	
	public InputHandler(ProjectPanel pp) {
		this.pp = pp;
	}
	
	public void checkInput() {
		
		if(pp.gameState == GameState.TitleScreenState) {
			
			if(checkEnterPressed() == true) {
				if(pp.isSetup == false) {
				pp.gameState = GameState.SetupState;
				}else {
					pp.stageH.prepareForNextStage();
					if(pp.stageH.stageNum == 2 || pp.stageH.stageNum == 1) {
					pp.stageH.setStartDialogue();
					}
				else {
					pp.stageH.checkLoadState();
				}
			}
			}
			}
		
			if(pp.gameState == GameState.SetupState) {
				
				if(checkEnterPressed() == true) {
					if(pp.ui.playerNameTextField.getText().isEmpty() == false) {
					pp.isSetup = true;
					pp.player.name = pp.ui.playerNameTextField.getText();
					ProjectFrame.window.requestFocus();
					pp.remove(pp.ui.playerNameTextField);
					pp.stageH.setStartDialogue();
				}
				}
			}
			
			if(pp.gameState == GameState.OptionsState) {
								
				if(pp.optionsSubstate == 0) {
					
					if(checkEnterPressed() == true) {
						
						switch(pp.commandNum) {
						
						case 1: pp.commandNum = 1; pp.gameState = pp.tempGameState; break;
						case 2: pp.commandNum = 1; pp.optionsSubstate = 1; break;
						case 3: if(pp.gameState != GameState.FightState) {pp.loadSave.save();} break;
						case 4: pp.alertH.resetGameAlert(); break;
						case 5: pp.exit(); break;
						default: break;
						}
					}
					
					if(checkESCPressed() == true) {
						
						pp.commandNum = 1;
						pp.gameState = pp.tempGameState;
					}
					
					checkCommandNum(5);
					
				}
				
				if(pp.optionsSubstate == 1) {
					
					if(checkEnterPressed() == true) {
						
						switch(pp.commandNum) {
						
						case 1: pp.alertH.changeFullScreenOnAlert(); break;
						case 2: pp.optionsSubstate = 2; pp.commandNum = 1; break;
						case 3: pp.optionsSubstate = 0; pp.commandNum = 1; break;
						default: break;
						}
					}
					
					if(checkESCPressed() == true) {
						
						pp.commandNum = 1;
						pp.gameState = pp.tempGameState;
				    	pp.tempGameState = null;
					}
					
					checkCommandNum(3);
					
				}
				
				if(pp.optionsSubstate == 2) {
					
					if(checkEnterPressed() == true) {

						switch(pp.commandNum) {
						case 1: pp.optionsSubstate = 1; pp.commandNum = 1; break;
						default: break;
						}
					}
					
					if(checkESCPressed() == true) {
						
						pp.optionsSubstate = 0;
						pp.gameState = pp.tempGameState;
				    	pp.tempGameState = null;
					}
					
					checkCommandNum(1);
					
				}
				
			}
			
			if(pp.gameState == GameState.AlertState) {
				
				checkCommandNum(pp.alertH.checkChoiceLength());
				
					if(checkEnterPressed() == true) {
						
						pp.alertH.choiceNum = pp.commandNum;
		    	    	pp.commandNum = 1;
		    	    	pp.alertH.prepareForAlert();
		    	    	pp.alertH.checkChoice();
						
					}
						    		
		    		
		    		if(checkESCPressed() == true) {
		    			
		    			pp.commandNum = 1;
		    	    	pp.gameState = pp.tempGameState;
		    	    }
			}
			
			if(pp.gameState == GameState.InventoryState) {
				if(checkEPressed() == true) {
					pp.commandNum = 1;
					pp.gameState = pp.tempGameState;
				}
				
				if(checkEnterPressed() == true) {
					if(pp.inventory.getLength() > 0) {
					pp.inventory.use(pp.inventory.items[pp.inventory.commandNumToItemIndex(pp.commandNum)].getName());
					pp.inventory.checkLength();
					}
				}
				
				checkCommandNum(pp.inventory.getLength());
				
			}
			
			if(pp.gameState == GameState.DialogueState) {
				
				pp.animH.showMessage(pp.tileSizeX * 1.26, pp.tileSizeY * 5.72);
				
				if(pp.animH.tempColumn % 3 == 0 && pp.animH.tempColumn != 0) {
		    		
		    		if(checkEnterPressed() == true) {
		    			pp.animH.shownTextColumn = pp.animH.currentColumn;
		    			pp.animH.tempColumn = 0;
		    		}
		    		if(checkESCPressed() == true) {
		    	    	pp.tempGameState = pp.gameState;
		    	    	pp.gameState = GameState.OptionsState;
		    	    }
				}
				
				if(pp.animH.currentColumn == pp.animH.splittedMessageText.length - 1) {
			    	if(pp.animH.messageCounter > pp.animH.splittedMessageText[pp.animH.currentColumn].length() - 1) {
		    			pp.tempGameState = pp.gameState;
		    			pp.gameState = GameState.ChoiceState;
			    	}
		    	}
		    		
		    		if(checkESCPressed() == true) {
		    			pp.tempGameState = pp.gameState;
		    			pp.gameState = GameState.OptionsState;
			    }
		    		if(checkEPressed() == true) {
		    	    	pp.commandNum = 1;
		    	    	pp.tempGameState = pp.gameState;
		    	    	pp.gameState = GameState.InventoryState;
		    	    }
		    	
			}
		    
	        if(pp.gameState == GameState.ChoiceState) {
	        	
				pp.animH.showMessage(pp.tileSizeX * 1.26, pp.tileSizeY * 5.72);

	        	if(checkEnterPressed() == true) {
	        		
	    	    	pp.stageH.choiceNum = pp.commandNum;
	    	    	pp.commandNum = 1;
	    	    	pp.stageH.prepareForNextStage();
	    	    	pp.stageH.checkChoice();
	    	    }
	    	    if(checkESCPressed() == true) {
	    	    	pp.commandNum = 1;
	    	    	pp.tempGameState = pp.gameState;
	    	    	pp.gameState = GameState.OptionsState;
	    	    }
	    	    if(checkEPressed() == true) {
	    	    	pp.commandNum = 1;
	    	    	pp.tempGameState = pp.gameState;
	    	    	pp.gameState = GameState.InventoryState;
	    	    }
	    	    
	    	    checkCommandNum(pp.stageH.checkChoiceLength());
	        }
	        
	        if(pp.gameState == GameState.FightState) {
				
				checkCommandNum(pp.fightH.checkChoiceLength());
				
					if(checkEnterPressed() == true) {
						
						pp.fightH.choiceNum = pp.commandNum;
		    	    	pp.commandNum = 1;
		    	    	pp.fightH.prepareForAlert();
		    	    	pp.fightH.checkChoice();
						
					}
						    		
		    		
		    		if(checkESCPressed() == true) {
		    			pp.commandNum = 1;
		    	    	pp.gameState = GameState.OptionsState;
		    	    }
			}
	        if(pp.gameState == GameState.TransitionState) {
	        }
		
	}
	
	public void setEnterPressed(boolean value) {
		
		if(value == true) {
			if(pp.ui.keyH.isKeyPressed() == false) {
				pp.ui.keyH.setKeyPressed(true);
				enterPressed = value;
			}
		}
		else if(value == false) {
			enterPressed = value;
		}
	}
	
	public void setESCPressed(boolean value) {
		
		if(value == true) {
			if(pp.ui.keyH.isKeyPressed() == false) {
				pp.ui.keyH.setKeyPressed(true);
				escPressed = value;
			}
		}
		else if(value == false) {
			escPressed = value;
		}
	}
	
	public void setEPressed(boolean value) {
		
		if(value == true) {
			if(pp.ui.keyH.isKeyPressed() == false) {
				pp.ui.keyH.setKeyPressed(true);
				ePressed = value;
			}
		}
		else if(value == false) {
			ePressed = value;
		}
	}
	
	public boolean checkEnterPressed() {
		if(enterPressed == true) {
			enterPressed = false;
			return true;
		}
		else {
			return false;
		}
		}
	
	public boolean checkESCPressed() {
		if(escPressed == true) {
			escPressed = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkEPressed() {
		if(ePressed == true) {
			ePressed = false;
			return true;
		}
		else {
			return false;
		}
		}
	
	public void checkCommandNum(int length) {

		if(pp.commandNum < 1) {
			pp.commandNum = length;
		}
		
		if(pp.commandNum > length) {
			pp.commandNum = 1;
		}
	}
}
