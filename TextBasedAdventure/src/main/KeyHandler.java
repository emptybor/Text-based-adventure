package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	ProjectPanel gp;
	
	public KeyHandler(ProjectPanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();		
		
		if(gp.gameState == gp.titleScreenState) {
		
		switch(code) {

		case KeyEvent.VK_ENTER:
			gp.enterPressed = true;
			break;
			
		}
		}
		
		else if(gp.gameState == gp.setupState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				gp.enterPressed = true;
				break;
				
			}
			}
		
		else if(gp.gameState == gp.dialogueState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				gp.enterPressed = true;
				break;
				
			}
			}
		
		else if(gp.gameState == gp.choiceState) {
			switch(code) {
			
			case KeyEvent.VK_ENTER:
				gp.enterPressed = true;
				break;
			case KeyEvent.VK_UP:
				gp.subState--;
				if(gp.subState < 1) {
					gp.subState = gp.stageH.checkChoiceLength();
				}
				break;
			case KeyEvent.VK_DOWN:
				gp.subState ++;
				if(gp.subState > gp.stageH.checkChoiceLength()) {
					gp.subState = 1;
				}
				
			case KeyEvent.VK_W:
				gp.subState--;
				if(gp.subState < 1) {
					gp.subState = gp.stageH.checkChoiceLength();
				}
				break;
			case KeyEvent.VK_S:
				gp.subState ++;
				if(gp.subState > gp.stageH.checkChoiceLength()) {
					gp.subState = 1;
				}
			}
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		switch(code) {
		
		case KeyEvent.VK_ENTER:
			gp.enterPressed = false;
			break;

		
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
