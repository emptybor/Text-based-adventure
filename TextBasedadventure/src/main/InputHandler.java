
package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import gui.ProjectFrame;
import gui.ProjectPanel;

public class InputHandler {
	
	ProjectPanel pp;
	
	private boolean spacePressed = false;
	private boolean enterPressed = false;
	private boolean escPressed = false;
	private boolean ePressed = false;
	private boolean tPressed = false;
	
	public InputHandler(ProjectPanel pp) {
		this.pp = pp;
	}
	
	public void checkInput() {
		
		if(pp.gameState == GameState.TitleScreenState) {
			
			if(checkEnterPressed() == true) {
				if(pp.isSetup == false) {
					setupTextField();
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
					pp.player.setName(pp.ui.playerNameTextField.getText());
					pp.loadSave.saveStats();
					pp.loadSave.saveConfig();
					ProjectFrame.window.requestFocus();
					pp.removeAll();
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
						case 3: pp.alertH.reloadAlert(); break;
						case 4: pp.alertH.resetGameAlert(); break;
						case 5: pp.exit(); break;
						default: break;
						}
					}
					
					if(checkESCPressed() == true) {
						
						pp.commandNum = 1;
						pp.gameState = pp.tempGameState;
					}
					
					if(checkSPACEPressed() == true) {
						pp.commandNum++;
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
					
					if(checkSPACEPressed() == true) {
						pp.commandNum++;
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
					
					if(checkSPACEPressed() == true) {
						pp.commandNum++;
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
		    		
		    		if(checkSPACEPressed() == true) {
						pp.commandNum++;
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
					pp.inventory.checkEquippedItem();
					pp.inventory.checkLength();
					}
				}
				
				if(checkSPACEPressed() == true) {
					pp.commandNum++;
				}
				
				checkCommandNum(pp.inventory.getLength());
				
			}
			
			if(pp.gameState == GameState.StatisticState) {
				
				checkCommandNum(1);
				
				if(checkEnterPressed() == true) {
					pp.commandNum = 1;
					pp.gameState = pp.tempGameState;
				}
				if(checkTPressed() == true) {
					pp.commandNum = 1;
					pp.gameState = pp.tempGameState;
				}
				
			}
			
			if(pp.gameState == GameState.DialogueState) {
				
				pp.animH.subState = 3;
				pp.animH.showAnimation();
				
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
		    			pp.commandNum = 1;
		    			pp.tempGameState = pp.gameState;
		    			pp.gameState = GameState.OptionsState;
			    }
		    		if(checkEPressed() == true) {
		    	    	pp.commandNum = 1;
		    	    	pp.tempGameState = pp.gameState;
		    	    	pp.gameState = GameState.InventoryState;
		    	    }
		    		
		    		if(checkTPressed() == true) {
		    			pp.commandNum = 1;
		    			pp.tempGameState = pp.gameState;
		    			pp.gameState = GameState.StatisticState;
			    }
		    	
			}
		    
	        if(pp.gameState == GameState.ChoiceState) {
	        	
				pp.animH.showMessage();

	        	if(checkEnterPressed() == true) {
	        		
	    	    	pp.stageH.choiceNum = pp.commandNum;
	    	    	pp.commandNum = 1;
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
	    	    if(checkTPressed() == true) {
	    	    	pp.commandNum = 1;
	    			pp.tempGameState = pp.gameState;
	    			pp.gameState = GameState.StatisticState;
	    	    }
	    	    
	    	    if(checkSPACEPressed() == true) {
					pp.commandNum++;
				}
	    	    
	    	    checkCommandNum(pp.stageH.checkChoiceLength());
	        }
	        
	        if(pp.gameState == GameState.FightState) {
	        	
				checkCommandNum(pp.fightH.checkChoiceLength());
				
					if(checkEnterPressed() == true || pp.animH.showTimeCount() == true) {
						
						pp.animH.resetTimeCount();
						pp.fightH.choiceNum = pp.commandNum;
		    	    	pp.commandNum = 1;
		    	    	pp.fightH.prepareForFight();
		    	    	pp.fightH.checkChoice();
						
					}
						    		
		    		
		    		if(checkESCPressed() == true) {
		    			pp.commandNum = 1;
		    	    	pp.gameState = GameState.OptionsState;
		    	    }
		    		if(checkTPressed() == true) {
		    			pp.commandNum = 1;
		    			pp.tempGameState = pp.gameState;
		    			pp.gameState = GameState.StatisticState;
		    		}
		    		
		    		if(checkSPACEPressed() == true) {
						pp.commandNum++;
					}
		    		
		    		if(pp.fightH.currentEnemy.getHP() <= 0 || pp.player.getHP() <= 0) {
		    			pp.fightH.endFight();
		    		}
			}
	        
	        if(pp.gameState == GameState.LoseState) {
	        	
	        	checkCommandNum(2);
				
				if(checkEnterPressed() == true) {
						    	    	
	    	    	switch(pp.commandNum) {
	    	    	case 1: pp.reload(); break;
	    	    	case 2: pp.exit(); break;
	    	    	default: break;
	    	    	}
	    	    	
	    	    	pp.commandNum = 1;
					
				}
					    		
	    		
	    		if(checkESCPressed() == true) {
	    			pp.exit();
	    	    }
	    		
	    		if(checkSPACEPressed() == true) {
					pp.commandNum++;
				}
	        	
	        }
	        
	        if(pp.gameState == GameState.TransitionState) {
	        	
	        	pp.commandNum = 0;
	        	pp.animH.subState = 2;
	        	
	        	if(pp.animH.showAnimation() == true) {
	        		pp.commandNum = 1;
	        		pp.gameState = pp.animH.nextGameState;
	        		pp.animH.nextGameState = null;
	        	}
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
	
	public void setTPressed(boolean value) {
		
		if(value == true) {
			if(pp.ui.keyH.isKeyPressed() == false) {
				pp.ui.keyH.setKeyPressed(true);
				tPressed = value;
			}
		}
		else if(value == false) {
			tPressed = value;
		}
	}
	
	public void setSPACEPressed(boolean value) {
		
		if(value == true) {
			if(pp.ui.keyH.isKeyPressed() == false) {
				pp.ui.keyH.setKeyPressed(true);
				spacePressed = value;
			}
		}
		else if(value == false) {
			spacePressed = value;
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
	
	public boolean checkTPressed() {
		if(tPressed == true) {
			tPressed = false;
			return true;
		}
		else {
			return false;
		}
		}
	
	public boolean checkSPACEPressed() {
		if(spacePressed == true) {
			spacePressed = false;
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
	
	public void setupTextField() {
		
		pp.ui.playerNameTextField = new JTextField();
		pp.ui.playerNameTextField.setBounds((pp.screenWidth / 2) - pp.tileSizeX * 2, pp.tileSizeY * 3, pp.tileSizeX * 4, (int)(pp.tileSizeY * 0.8));
		pp.ui.playerNameTextField.setFont(pp.ui.g2.getFont().deriveFont(pp.ui.fontScale(2)));
		pp.ui.playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
		pp.ui.playerNameTextField.setBackground(Color.BLACK);
		pp.ui.playerNameTextField.setForeground(Color.WHITE);
		pp.ui.playerNameTextField.setBorder(null);
		pp.ui.playerNameTextField.setCaretColor(new Color(0, 0, 0, 0));
		Cursor invisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(new byte[0], "invisibleCursor").getImage(),new Point(0, 0),"invisibleCursor");
		pp.ui.playerNameTextField.setCursor(invisibleCursor);
		pp.ui.playerNameTextField.setVisible(true);
		pp.ui.playerNameTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pp.ui.keyH.setKeyPressed(false);
				setEnterPressed(true);
				}});
		
		pp.add(pp.ui.playerNameTextField);
	}
}
