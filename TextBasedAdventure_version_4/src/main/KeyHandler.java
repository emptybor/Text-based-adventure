package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	ProjectPanel pp;
	
	public KeyHandler(ProjectPanel pp) {
		this.pp = pp;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();		
		
		if(pp.gameState == pp.titleScreenState) {
		
		switch(code) {

		case KeyEvent.VK_ENTER:
			pp.enterPressed = true;
			break;
			
		}
		}
		
		else if(pp.gameState == pp.setupState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				pp.enterPressed = true;
				break;
				
			}
			}
		
		else if(pp.gameState == pp.dialogueState) {
			
			switch(code) {

			case KeyEvent.VK_ENTER:
				pp.enterPressed = true;
				break;
				
			}
			}
		
		else if(pp.gameState == pp.choiceState) {
			switch(code) {
			
			case KeyEvent.VK_ENTER:
				pp.enterPressed = true;
				break;
			case KeyEvent.VK_UP:
				pp.subState--;
				if(pp.subState < 1) {
					pp.subState = pp.stageH.checkChoiceLength();
				}
				break;
			case KeyEvent.VK_DOWN:
				pp.subState ++;
				if(pp.subState > pp.stageH.checkChoiceLength()) {
					pp.subState = 1;
				}
				
			case KeyEvent.VK_W:
				pp.subState--;
				if(pp.subState < 1) {
					pp.subState = pp.stageH.checkChoiceLength();
				}
				break;
			case KeyEvent.VK_S:
				pp.subState ++;
				if(pp.subState > pp.stageH.checkChoiceLength()) {
					pp.subState = 1;
				}
			}
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		switch(code) {
		
		case KeyEvent.VK_ENTER:
			pp.enterPressed = false;
			break;

		
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
