package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GameState;

public class KeyHandler implements KeyListener{
	
	UI ui;
	private boolean keyPressed = false;
	
	public KeyHandler(UI ui) {
		this.ui = ui;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(ui.pp.gameState == GameState.TitleScreenState) {
		
		switch(code) {

		case KeyEvent.VK_ENTER:
			ui.pp.inputH.setEnterPressed(true);
			break;
			
		}
		}
		
		else if(ui.pp.gameState == GameState.SetupState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				ui.pp.inputH.setEnterPressed(true);
				break;
				
			}
			}
		
		else if(ui.pp.gameState == GameState.OptionsState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				ui.pp.inputH.setEnterPressed(true);
				break;
			case KeyEvent.VK_ESCAPE:
				ui.pp.inputH.setESCPressed(true);
				break;
				
			case KeyEvent.VK_UP:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_DOWN:
				ui.pp.commandNum ++;
				break;
			case KeyEvent.VK_W:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_S:
				ui.pp.commandNum ++;
				break;
			}
		}
		
		else if(ui.pp.gameState == GameState.InventoryState) {
			switch(code) {
			
			case KeyEvent.VK_ENTER:
				ui.pp.inputH.setEnterPressed(true);
				break;
			case KeyEvent.VK_E:
				ui.pp.inputH.setEPressed(true);
				break;
			case KeyEvent.VK_UP:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_DOWN:
				ui.pp.commandNum ++;
				break;
			case KeyEvent.VK_W:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_S:
				ui.pp.commandNum ++;
				break;
			
			}
		}
		
		else if(ui.pp.gameState == GameState.AlertState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				ui.pp.inputH.setEnterPressed(true);
				break;
			case KeyEvent.VK_ESCAPE:
				ui.pp.inputH.setESCPressed(true);
				break;
			case KeyEvent.VK_UP:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_DOWN:
				ui.pp.commandNum ++;
				break;
			case KeyEvent.VK_W:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_S:
				ui.pp.commandNum ++;
				break;
				
			}
			}
		
		else if(ui.pp.gameState == GameState.DialogueState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				ui.pp.inputH.setEnterPressed(true);
				break;
			case KeyEvent.VK_ESCAPE:
				ui.pp.inputH.setESCPressed(true);
				break;
			case KeyEvent.VK_E:
				ui.pp.inputH.setEPressed(true);
				break;
				
			}
			}
		
		
		
		else if(ui.pp.gameState == GameState.ChoiceState) {
			switch(code) {
			
			case KeyEvent.VK_ENTER:
				ui.pp.inputH.setEnterPressed(true);
				break;
			case KeyEvent.VK_ESCAPE:
				ui.pp.inputH.setESCPressed(true);
				break;
			case KeyEvent.VK_E:
				ui.pp.inputH.setEPressed(true);
				break;
			case KeyEvent.VK_UP:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_DOWN:
				ui.pp.commandNum++;
				break;
			case KeyEvent.VK_W:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_S:
				ui.pp.commandNum++;
				break;
			}
		}
		
		else if(ui.pp.gameState == GameState.FightState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				ui.pp.inputH.setEnterPressed(true);
				break;
			case KeyEvent.VK_ESCAPE:
				ui.pp.inputH.setESCPressed(true);
				break;
			case KeyEvent.VK_UP:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_DOWN:
				ui.pp.commandNum ++;
				break;
			case KeyEvent.VK_W:
				ui.pp.commandNum--;
				break;
			case KeyEvent.VK_S:
				ui.pp.commandNum ++;
				break;
				
			}
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		setKeyPressed(false);
		
		int code = e.getKeyCode();
		
		switch(code) {
		
		case KeyEvent.VK_ENTER:
			ui.pp.inputH.setEnterPressed(false);
			break;
		case KeyEvent.VK_ESCAPE:
			ui.pp.inputH.setESCPressed(false);
			break;
		case KeyEvent.VK_E:
			ui.pp.inputH.setESCPressed(false);
			break;
		
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	public boolean isKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(boolean keyPressed) {
		this.keyPressed = keyPressed;
	}

}
