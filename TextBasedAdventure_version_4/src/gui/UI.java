package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;

import main.ProjectFrame;
import main.ProjectPanel;

public class UI {
	
	public ProjectPanel gp;
	public Graphics2D g2;
	
	public String messageText = "";
	public String shownMessage = "";
	
	public int messageSpeed = 2;
	
	public int messageSpeedCounter = 0;
	public int messageCounter = 0;
	
	public int currentColumn = 0;
	public int shownTextColumn = 0;
	public int tempColumn;
	
	public boolean messageCompleted = false;
	
	public String choice[] = new String[4];
	
	Font maruMonica;
	
	public Color oldColor;
	public Font oldFont;
	public Stroke oldStroke;
	
	public JTextField playerNameTextField;
	
	public UI(ProjectPanel gp) {
		this.gp = gp;
	}
	
	public void setup() {

		this.g2 = gp.g2;
		maruMonica = new Font("x12y16pxMaruMonica", Font.BOLD, 15);
		g2.setFont(maruMonica);
		
		setupTextField();
		
	}
	
	public void setupTextField() {
		
		playerNameTextField = new JTextField();
		playerNameTextField.setBounds((gp.screenWidth / 2) - gp.tileSize * 2, gp.tileSize * 3, gp.tileSize * 4, (int)(gp.tileSize * 0.8));
		playerNameTextField.setFont(g2.getFont().deriveFont(20f));
		playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
		playerNameTextField.setBackground(Color.BLACK);
		playerNameTextField.setForeground(Color.WHITE);
		playerNameTextField.setBorder(null);
		playerNameTextField.setCaretColor(new Color(0, 0, 0, 0));
		playerNameTextField.setVisible(true);
		
		playerNameTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gp.enterPressed = true;
			}});
		
		gp.add(playerNameTextField);

	}
	
	public void draw(Graphics2D g2) {
		
		
		if(gp.gameState == gp.titleScreenState) {
		drawTitleScreen();	
		}
		if(gp.gameState == gp.setupState) {
		drawSetup();
		}
		if(gp.gameState == gp.dialogueState) {
        drawMessage();
        drawPlayerStats();
        }
        if(gp.gameState == gp.choiceState) {
        drawMessage();
        drawChoice();
        drawPlayerStats();
        }
        if(gp.gameState == gp.transitionState) {
        drawTransition();
        }
                
	}
	
	public void drawTitleScreen() {
		
		oldFont = g2.getFont();
		oldColor = gp.getBackground();
		g2.setFont(g2.getFont().deriveFont(30f));
		g2.setColor(Color.BLACK);
		g2.drawString("Text Based Adventure",getXForCenteredText("Text Based Adventure"), gp.tileSize * 2);
		
		g2.setFont(g2.getFont().deriveFont(25f));
		g2.drawString("Press enter to play...",getXForCenteredText("Press enter to play..."), gp.tileSize * 5);

		
		if(gp.enterPressed == true) {
			gp.enterPressed = false;
			
			if(gp.isSetup == false) {
			gp.gameState = gp.setupState;
			}
			else {
				gp.stageH.setStartDialogue();
			}
			
		}
		
		g2.setFont(oldFont);
		g2.setColor(oldColor);
		
	}
	
	public void drawSetup() {
		
		oldFont = g2.getFont();
		oldColor = gp.getBackground();
		oldStroke = g2.getStroke();
		
		playerNameTextField.repaint();
		if(playerNameTextField.hasFocus() == false) {
			playerNameTextField.requestFocus();
		}
		
		g2.setColor(Color.BLACK);
		
		g2.fillRoundRect((gp.screenWidth / 2) - gp.tileSize * 3, gp.tileSize * 2, gp.tileSize * 6, gp.tileSize * 2, 35, 35);
		
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		
		g2.drawRoundRect(((gp.screenWidth / 2) - gp.tileSize * 3) + 5, (gp.tileSize * 2) + 5, (gp.tileSize * 6) - 10, (gp.tileSize * 2) - 10, 25, 25);
		
		g2.setFont(g2.getFont().deriveFont(20f));
		g2.drawString("Enter your name", (gp.screenWidth / 2) - (getXStringLength("Enter your name") / 2), (int)(gp.tileSize * 2.6));
		
		if(gp.enterPressed == true) {
			gp.enterPressed = false;
			gp.isSetup = true;
			gp.player.name = playerNameTextField.getText();
			ProjectFrame.window.requestFocus();
			gp.remove(playerNameTextField);
			gp.stageH.setStartDialogue();
		}
		
		playerNameTextField.repaint();
		
		g2.setFont(oldFont);
		g2.setStroke(oldStroke);
		g2.setColor(oldColor);
		
	}
	
	public void drawMessage() {
		
		oldFont = g2.getFont();
	    oldColor = gp.getBackground();

	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(5));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));

	    double x = gp.screenWidth / 2 - gp.tileSize * 4;
	    double y = gp.screenHeight - gp.tileSize * 2.15;

	    g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, gp.screenHeight - (int)(gp.tileSize * 2.6), gp.tileSize * 8, (int)(gp.tileSize * 1.7), 35, 35);
	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 5, gp.screenHeight - (int)(gp.tileSize * 2.6) + 5, gp.tileSize * 8 - 10, (int)(gp.tileSize * 1.7) - 10, 25, 25);

	    x += gp.tileSize / 4;
	    y += gp.tileSize / 8;
	    String[] text = messageText.split("\n");
	    
	    	
	    	if(messageCounter == text[currentColumn].length()) {
	    		if(currentColumn < text.length - 1) {
	    		currentColumn++;
	    		tempColumn = currentColumn;
	    		
	    		messageCounter = 0;
	    		
	    		
	    		}
	    	}
	    	for(int i = shownTextColumn; i < currentColumn; i++) {
	    		if(text[i] != null) {
	    		g2.drawString(text[i], (int) x, (int) y);
	    		y += getYStringLength(text[i]);
	    		}
	    	}
	    	if(messageCounter > text[currentColumn].length()) {
	    		messageCounter = text[currentColumn].length();
	    			messageCompleted = true;
	    			gp.gameState = gp.choiceState;
	    	}
	    	g2.drawString(text[currentColumn].substring(0, messageCounter), (int)x, (int)y);
	    	
	    	messageSpeedCounter++;
	    	
	    	if(tempColumn % 3 == 0 && tempColumn != 0) {
	    		g2.drawString(">", (int) x + (int) (gp.tileSize * 7.2), (int) y - (gp.tileSize / 3));
	    		if(gp.enterPressed == true) {
	    			gp.enterPressed = false;
	    			shownTextColumn = currentColumn;
	    			tempColumn = 0;
	    		}
	    	}else
	    	
	    	if(messageSpeedCounter % messageSpeed == 0) {
		    	messageCounter++;
		    }
	    
	    
	    
	    drawName();
	    
	    g2.setFont(oldFont);
	    g2.setColor(oldColor);
	}



	
	public void resetMessage() {
		
		currentColumn = 1;
		messageCounter = 0;
		messageSpeedCounter = 0;
	}
	
	public void drawChoice() {
		
		oldFont = g2.getFont();
		oldColor = gp.getBackground();
	    oldStroke = g2.getStroke();

	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));

	    double x = gp.screenWidth / 2 - gp.tileSize * 4;
	    double y = gp.tileSize;
	    
	    drawChoiceRow(0, x, y);
	    y += gp.tileSize * 0.85;
	    drawChoiceRow(1, x, y);
	    y += gp.tileSize * 0.85;
	    drawChoiceRow(2, x, y);
	    y += gp.tileSize * 0.85;
	    drawChoiceRow(3, x, y);
	    
	    x = gp.screenWidth / 2 - gp.tileSize * 4.5;
	    y = (gp.tileSize * 1.5) + (gp.tileSize * ((gp.subState - 1) * 0.85));
	    g2.setColor(Color.BLACK);
	    g2.setFont(g2.getFont().deriveFont(25f));
	    
	    g2.drawString(">", (int)x, (int)y);
	    
	    if(gp.enterPressed == true) {
	    	gp.enterPressed = false;
	    	gp.stageH.choiceNum = gp.subState;
	    	gp.stageH.prepareForNextStage();
	    	gp.stageH.checkChoice();
	    	g2.setColor(oldColor);
		    g2.setFont(oldFont);
	    }
	    
	    g2.setColor(oldColor);
	    g2.setFont(oldFont);
	    g2.setStroke(oldStroke);

		
	}
	
	public void drawTransition() {
		
	}
	
	public void clear() {
		
		g2.setColor(oldColor);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
	}
	
	public void drawChoiceRow(int index, double x, double y) {
		
		if(choice[index] != null) {
		
		g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, (int) y, gp.tileSize * 8, (int)(gp.tileSize * 0.8), 25, 25);
	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 4, (int) y + 4, gp.tileSize * 8 - 8, (int)(gp.tileSize * 0.8) - 8, 15, 15);
	    
	    x += gp.tileSize / 4;
	    y += gp.tileSize / 2;
		
		g2.drawString(choice[index], (int)x, (int)y);
		
		Area choiceRowArea = new Area(new Rectangle2D.Double((int) x, (int) y, gp.tileSize * 8, (int)(gp.tileSize * 0.8)));
	    
	    if(gp.mouseH.mouseInsideHover(choiceRowArea, 0) == true) {
	    	
	    	gp.subState = index + 1;
	    	
	    	if(gp.mouseH.mouseClicked == true) {
	    		gp.mouseH.mouseClicked();
	    		gp.stageH.choiceNum = gp.subState;
		    	gp.stageH.prepareForNextStage();
		    	gp.stageH.checkChoice();
		    	g2.setColor(oldColor);
			    g2.setFont(oldFont);
	    	}
	    }
		}
		
		
	}
	
	public void drawName() {
		
		oldFont = g2.getFont();
	    oldColor = gp.getBackground();
	    oldStroke = g2.getStroke();
		
		if(gp.stageH.name != null) {
			
			double x = gp.screenWidth / 2  + gp.tileSize * 4 - getXStringLength(gp.stageH.name + "   ");
		    double y = gp.screenHeight - (gp.tileSize * 3.35);
		    
			g2.setStroke(new BasicStroke(3));
			g2.setColor(new Color(20, 20, 20));
		    g2.fillRoundRect((int) x, (int) y, getXStringLength(gp.stageH.name + "   "), (int)(gp.tileSize * 0.8), 25, 25);
		    g2.setColor(Color.WHITE);
		    g2.drawRoundRect((int) x + 4, (int) y + 4, getXStringLength(gp.stageH.name + "   ")- 8, (int)(gp.tileSize * 0.8) - 8, 15, 15);
		    
		    x += gp.tileSize / 5;
		    y += gp.tileSize / 1.9;
			
		    g2.drawString(gp.stageH.name, (int)x, (int) y);
		    
		    g2.setColor(oldColor);
		    g2.setFont(oldFont);
		    g2.setStroke(oldStroke);

			
		}
			
	}
	
	public void drawPlayerStats() {
		
			gp.ui.oldColor = gp.getBackground();
			gp.ui.oldStroke = g2.getStroke();
			gp.ui.oldFont = g2.getFont();
			
			g2.setColor(Color.BLACK);
			g2.setFont(g2.getFont().deriveFont(25f));
			
			int x = gp.tileSize;
			int y = gp.tileSize / 2;

			g2.drawString("HP: " + gp.player.hp , x, y);
			
			x = gp.tileSize * 5;
			
			g2.drawString("WEAPON: " + gp.player.currentWeapon, x, y);
			
		    g2.setColor(gp.ui.oldColor);
		    g2.setStroke(gp.ui.oldStroke);
		    g2.setFont(gp.ui.oldFont);
		    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		 return gp.screenWidth / 2 - length / 2;
		
	}
	
	public int getXForAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		 return x;
		
	}
	
	public int getXStringLength(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = length;
		 return x;
		
	}
	
	public int getYStringLength(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		int y = length;
		 return y;
		
	}

}
