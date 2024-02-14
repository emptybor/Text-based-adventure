package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextField;

import main.GameState;

public class UI {
	
	public KeyHandler keyH = new KeyHandler(this);
	public MouseHandler mouseH = new MouseHandler(this);
	
	public ProjectPanel pp;
	public Graphics2D g2;
	
	public String alertText = "";
	public String fightText = "";
	
	public String messageChoice[] = new String[4];
	public String alertChoice[] = new String[4];
	public String fightChoice[] = new String[4];
	
	Font maruMonica;
	
	Color oldColor;
	Font oldFont;
	Stroke oldStroke;
	
	public JTextField playerNameTextField;
	
	public UI(ProjectPanel pp) {
		this.pp = pp;
	}
	
	public void setup() {

		this.g2 = pp.g2;
		
		try {
			
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			
			}catch(FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		g2.setFont(maruMonica);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(1.5)));
		
		setupTextField();
		
	}
	
	public void setupTextField() {
		
		playerNameTextField = new JTextField();
		playerNameTextField.setBounds((pp.screenWidth / 2) - pp.tileSizeX * 2, pp.tileSizeY * 3, pp.tileSizeX * 4, (int)(pp.tileSizeY * 0.8));
		playerNameTextField.setFont(g2.getFont().deriveFont(fontScale(2)));
		playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
		playerNameTextField.setBackground(Color.BLACK);
		playerNameTextField.setForeground(Color.WHITE);
		playerNameTextField.setBorder(null);
		playerNameTextField.setCaretColor(new Color(0, 0, 0, 0));
		playerNameTextField.setVisible(true);
		playerNameTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pp.ui.keyH.setKeyPressed(false);
				pp.inputH.setEnterPressed(true);
				}});
		
		pp.add(playerNameTextField);

	}
	
	public void draw(Graphics2D g2) {
		
		if(pp.gameState == GameState.TitleScreenState) {
		drawTitleScreen();	
		}
		if(pp.gameState == GameState.SetupState) {
		drawSetup();
		}
		if(pp.gameState == GameState.OptionsState) {
		drawPaused();
		}
		if(pp.gameState == GameState.InventoryState) {
		drawInventory();
		}
		if(pp.gameState == GameState.AlertState) {
		drawAlert();
		drawAlertChoice();
	    }
		if(pp.gameState == GameState.DialogueState) {
        drawMessage();
        drawPlayerStats();
        }
        if(pp.gameState == GameState.ChoiceState) {
        drawMessage();
        drawMessageChoice();
        drawPlayerStats();
        }
        if(pp.gameState == GameState.FightState) {
        drawFight();
    	drawFightChoice();
    	drawPlayerStats();
        }
        if(pp.gameState == GameState.LoseState) {
        drawLose();
        }
        if(pp.gameState == GameState.TransitionState) {
        drawLose();
        drawTransition();
        }
                
	}
	
	public void drawTitleScreen() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
		g2.setFont(g2.getFont().deriveFont(fontScale(3)));
		g2.setColor(Color.BLACK);
		g2.drawString("Text Based Adventure",getXForCenteredText("Text Based Adventure"), pp.tileSizeY * 2);
		
		g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
		g2.drawString("Press enter to play...",getXForCenteredText("Press enter to play..."), pp.tileSizeY * 5);
		
		g2.setFont(oldFont);
		g2.setColor(oldColor);
		
	}
	
	public void drawSetup() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
		oldStroke = g2.getStroke();
		
		playerNameTextField.repaint();
		if(playerNameTextField.hasFocus() == false) {
			playerNameTextField.requestFocus();
		}
		
		g2.setColor(Color.BLACK);
		
		g2.fillRoundRect((pp.screenWidth / 2) - pp.tileSizeX * 3, pp.tileSizeY * 2, pp.tileSizeX * 6, pp.tileSizeY * 2, 35, 35);
		
		playerNameTextField.repaint();

		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		
		g2.drawRoundRect(((pp.screenWidth / 2) - pp.tileSizeX * 3) + 5, (pp.tileSizeY * 2) + 5, (pp.tileSizeX * 6) - 10, (pp.tileSizeY * 2) - 10, 25, 25);
		
		g2.setFont(g2.getFont().deriveFont(fontScale(2)));
		g2.drawString("Enter your name", (pp.screenWidth / 2) - (getXStringLength("Enter your name") / 2), (int)(pp.tileSizeY * 2.6));
		
		playerNameTextField.repaint();
		
		g2.setFont(oldFont);
		g2.setStroke(oldStroke);
		g2.setColor(oldColor);
		
	}
	
	public void drawPaused() {
		
		if(pp.optionsSubstate == 0) {
			drawOptions();
		}
		if(pp.optionsSubstate == 1) {
			drawSettings();
		}
		if(pp.optionsSubstate == 2) {
			drawControls();
		}
		
	}
	
	public void drawOptions() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
		oldStroke = g2.getStroke();
		
		g2.setColor(Color.BLACK);
		
		g2.fillRoundRect((pp.screenWidth / 2) - (int) (pp.tileSizeX * 2.5), pp.tileSizeY / 2, pp.tileSizeX * 5, pp.tileSizeY * 6, 35, 35);
		
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		
		g2.drawRoundRect(((pp.screenWidth / 2) - (int) (pp.tileSizeX * 2.5)) + 5, (pp.tileSizeY / 2) + 5, (pp.tileSizeX * 5) - 10, (pp.tileSizeY * 6) - 10, 25, 25);
		
		double x = pp.screenWidth / 2;
		double y = pp.tileSizeY / 2;
		
		g2.setColor(Color.WHITE);
		
		g2.setFont(g2.getFont().deriveFont(fontScale(3.5)));
				
		y += pp.tileSizeY;
		
		g2.drawString("OPTIONS", (int) x - (getXStringLength("OPTIONS") / 2), (int) y);
		
		g2.setFont(g2.getFont().deriveFont(fontScale(2.4)));
		
		y += pp.tileSizeY * 0.9;
		
		g2.drawString("RESUME", (int) x - (getXStringLength("RESUME") / 2), (int) y);
		
		y += pp.tileSizeY * 0.9;
		
		g2.drawString("SETTINGS", (int) x - (getXStringLength("SETTINGS") / 2), (int) y);
		
		y += pp.tileSizeY * 0.9;
		
		g2.drawString("RELOAD", (int) x - (getXStringLength("RELOAD") / 2), (int) y);
		
		y += pp.tileSizeY * 0.9;
		
		g2.drawString("NEW", (int) x - (getXStringLength("NEW") / 2), (int) y);
		
		y += pp.tileSizeY * 0.9;
		
		g2.drawString("END", (int) x - (getXStringLength("END") / 2), (int) y);
		
		x = pp.screenWidth / 2 - pp.tileSizeX * 2;
	    y = (pp.tileSizeY * 2.4) + ((pp.tileSizeY * 0.9) * (pp.commandNum - 1));
	    
	    g2.setColor(Color.WHITE);
	    g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
	    
	    if(pp.commandNum > 0 && pp.commandNum <= 5) {
	    
	    g2.drawString(">", (int) x, (int) y);
		
	    }
	    
		g2.setFont(oldFont);
		g2.setStroke(oldStroke);
		g2.setColor(oldColor);
	}
	
	public void drawSettings() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
		oldStroke = g2.getStroke();
		
		g2.setColor(Color.BLACK);
		
		g2.fillRoundRect((pp.screenWidth / 2) - (int) (pp.tileSizeX * 2.5), pp.tileSizeY / 2, pp.tileSizeX * 5, pp.tileSizeY * 6, 35, 35);
		
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		
		g2.drawRoundRect(((pp.screenWidth / 2) - (int) (pp.tileSizeX * 2.5)) + 5, (pp.tileSizeY / 2) + 5, (pp.tileSizeX * 5) - 10, (pp.tileSizeY * 6) - 10, 25, 25);
		
		double x = pp.screenWidth / 2;
		double y = pp.tileSizeY / 2;
		
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(fontScale(3.5)));
		
		y += pp.tileSizeY;
		
		g2.drawString("SETTINGS", (int) x - (getXStringLength("SETTINGS") / 2), (int) y);
		
		g2.setFont(g2.getFont().deriveFont(fontScale(2)));
				
		y += pp.tileSizeY;
		
		g2.drawString("FullScreen ON/OFF", (int) (x - pp.tileSizeX * 1.8), (int) y);
		
		g2.setStroke(new BasicStroke(2));
		
		if(pp.fullScreenOn == true) {
			
			g2.setColor(Color.WHITE);
			g2.fillRect((int) (x + pp.tileSizeX * 1.5), (int) y - getYStringLength("FullScreen ON/OFF") + 2, getYStringLength("FullScreen ON/OFF"), getYStringLength("FullScreen ON/OFF"));
		}
		if(pp.fullScreenOn == false) {
			g2.drawRect((int) (x + pp.tileSizeX * 1.5), (int) y - getYStringLength("FullScreen ON/OFF") + 2, getYStringLength("FullScreen ON/OFF"), getYStringLength("FullScreen ON/OFF"));
		}
		
		y += pp.tileSizeY;
		
		g2.drawString("Controls", (int) (x - pp.tileSizeX * 1.8), (int) y);
		
		y += pp.tileSizeY;
		
		g2.setFont(g2.getFont().deriveFont(fontScale(2.4)));
		
		g2.drawString("BACK", (int) x - (getXStringLength("BACK") / 2), (int) y);
		
		x = pp.screenWidth / 2 - pp.tileSizeX * 2.2;
	    y = (pp.tileSizeY * 2.5) + (pp.tileSizeY * (pp.commandNum - 1));
	    
	    g2.setColor(Color.WHITE);
	    g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
	    
	    //Beim Erweitern von den Settings auch "3" aendern
	    if(pp.commandNum > 0 && pp.commandNum <= 3) {
	    
	    g2.drawString(">", (int)x, (int)y);
	    
	    }
		
		g2.setFont(oldFont);
		g2.setStroke(oldStroke);
		g2.setColor(oldColor);

			
	}
	
	public void drawControls() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
		oldStroke = g2.getStroke();
		
		g2.setColor(Color.BLACK);
		
		g2.fillRoundRect((pp.screenWidth / 2) - (int) (pp.tileSizeX * 2.5), pp.tileSizeY / 2, pp.tileSizeX * 5, pp.tileSizeY * 6, 35, 35);
		
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));
		
		g2.drawRoundRect(((pp.screenWidth / 2) - (int) (pp.tileSizeX * 2.5)) + 5, (pp.tileSizeY / 2) + 5, (pp.tileSizeX * 5) - 10, (pp.tileSizeY * 6) - 10, 25, 25);
		
		double x = pp.screenWidth / 2;
		double y = pp.tileSizeY / 2;
		
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(fontScale(3.5)));
		
		y += pp.tileSizeY;
		
		g2.drawString("CONTROLS", (int) x - (getXStringLength("CONTROLS") / 2), (int) y);
		
		g2.setFont(g2.getFont().deriveFont(fontScale(2.4)));
		
		y = pp.tileSizeY * 6.2;
		
		g2.drawString("BACK", (int) x - (getXStringLength("BACK") / 2), (int) y);
		
		x = pp.screenWidth / 2 - pp.tileSizeX * 1.2;
	    y = (pp.tileSizeY * 5.2) + (pp.tileSizeY * (pp.commandNum));
	    
	    g2.setColor(Color.WHITE);
	    g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
	    
	    //Beim Erweitern von den Settings auch "3" aendern
	    if(pp.commandNum == 1) {
	    
	    g2.drawString(">", (int)x, (int)y);
	    
	    }
		
		g2.setFont(g2.getFont().deriveFont(fontScale(2)));
		g2.setStroke(new BasicStroke(2));
		
		x = pp.tileSizeX * 3;
		y = pp.tileSizeY * 2.5;
		g2.drawString("UP", (int) x, (int) y);
		
		y += pp.tileSizeY / 1.4;
		
		g2.drawString("DOWN", (int) x, (int) y);
		
		y += pp.tileSizeY / 1.4;
		
		g2.drawString("ENTER", (int) x, (int) y);
		
		y += pp.tileSizeY / 1.4;
		
		g2.drawString("OPTIONS", (int) x, (int) y);
		
		y += pp.tileSizeY / 1.4;
		
		g2.drawString("INVENTORY", (int) x, (int) y);
		
		String text = "";
		
		x = pp.tileSizeX * 6;
		y = pp.tileSizeY * 2.5;
		text = "W";
		g2.drawString(text, (int)x, (int)y);
		g2.drawRect((int)x - 3, (int)y - getYStringLength(text), getXStringLength(text) + 6, getYStringLength(text) + 3);

		x += pp.tileSizeX/ 2;
		text = "\u25B3";
		g2.drawString(text, (int)x, (int)y);
		g2.drawRect((int)x - 3, (int)y - getYStringLength(text), getXStringLength(text) + 6, getYStringLength(text) + 3);
		
		x = pp.tileSizeX * 6;
		y += pp.tileSizeY / 1.4;
		text = "S";
		g2.drawString(text, (int)x + 2, (int)y);
		g2.drawRect((int)x - 3, (int)y - getYStringLength("W"), getXStringLength("W") + 6, getYStringLength("W") + 3);

		x += pp.tileSizeX / 2;
		text = "\u25BD";
		g2.drawString(text, (int)x, (int)y);
		g2.drawRect((int)x - 3, (int)y - getYStringLength(text), getXStringLength(text) + 6, getYStringLength(text) + 3);

		x = pp.tileSizeX * 6;
		y += pp.tileSizeY / 1.4;
		text = "ENTER";
		g2.drawString(text, (int)x + 2, (int)y);
		g2.drawRect((int)x - 3, (int)y - getYStringLength(text), getXStringLength(text) + 10, getYStringLength(text) + 3);

		y += pp.tileSizeY / 1.4;
		text = "ESC";
		g2.drawString(text, (int)x + 2, (int)y);
		g2.drawRect((int)x - 3, (int)y - getYStringLength(text), getXStringLength(text) + 10, getYStringLength(text) + 3);

		y += pp.tileSizeY / 1.4;
		text = "E";
		g2.drawString(text, (int)x + 2, (int)y);
		g2.drawRect((int)x - 3, (int)y - getYStringLength(text), getXStringLength(text) + 10, getYStringLength(text) + 3);

		
		g2.setFont(oldFont);
		g2.setStroke(oldStroke);
		g2.setColor(oldColor);
		
		
	}
	
	public void drawInventory() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
		oldStroke = g2.getStroke();
		
		g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(4)));

	    double x = pp.screenWidth / 2 - pp.tileSizeX * 4;
	    double y = pp.tileSizeY;
	    
	    g2.drawString(pp.player.getName() + "'s Inventory", getXForCenteredText(pp.player.getName() + "'s Inventory"), (int) y);
	    
	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));
	    
	    y += pp.tileSizeY;
	    
	    for(int i = 0; i < pp.inventory.items.length; i++) {
	    	if(pp.inventory.items[i] != null) {
	    		drawInventoryRow(i, y, x);
	    		if(pp.inventory.items[i].getAmount() > 0) {
	    		y += pp.tileSizeY * 0.85;
	    		}
	    	}
	    }

		x = pp.screenWidth / 2 - pp.tileSizeX * 4.5;
	    y = (pp.tileSizeY * 2.5) + (pp.tileSizeY * ((pp.commandNum - 1) * 0.85));
		
		g2.setColor(Color.BLACK);
	    if(pp.commandNum > 0 && pp.commandNum <= pp.inventory.getLength()) {
		g2.drawString(">", (int) x, (int) y);
	    }
	    else {
	    	drawEmptyInventory();
	    }
		
		g2.setFont(oldFont);
		g2.setStroke(oldStroke);
		g2.setColor(oldColor);
		
	}
	
	public void drawInventoryRow(int index, double y, double x) {
		
		if(pp.inventory.getAmount(pp.inventory.items[index].getName()) != 0) {
			
			g2.setColor(new Color(20, 20, 20));
		    g2.fillRoundRect((int) x, (int) y, pp.tileSizeX * 8, (int)(pp.tileSizeY * 0.8), 25, 25);
		    g2.setColor(Color.WHITE);
		    g2.drawRoundRect((int) x + 4, (int) y + 4, pp.tileSizeX * 8 - 8, (int)(pp.tileSizeY * 0.8) - 8, 15, 15);
		    
		    x += pp.tileSizeX / 4;
		    y += pp.tileSizeY / 1.7;
			
			g2.drawString("Use: " + pp.inventory.items[index].getName() + " x" + pp.inventory.items[index].getAmount(), (int)x, (int)y);
			
			if(pp.inventory.items[index].getImage() != null) {
				
				x += pp.tileSizeX * 7;
				y -= pp.tileSizeY * 0.4;
				
				g2.drawImage(pp.inventory.items[index].getImage(), (int) x, (int) y, pp.tileSizeX / 2, pp.tileSizeY / 2, null);
			}
			
			if(pp.inventory.items[index].isEquiped() == true) {
				
				x += pp.tileSizeX * 6;
				
				g2.drawString("[EQUIPED]", (int) x, (int) y);
			}
			
		}
		
	}
	
	public void drawEmptyInventory() {
		
	}
	
	public void drawMessage() {
		
		oldFont = g2.getFont();
	    oldColor = pp.getBackground();

	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(5));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));

	    double x = pp.screenWidth / 2 - pp.tileSizeX * 4;
	    double y = pp.screenHeight - pp.tileSizeY * 2.15;

	    g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, pp.screenHeight - (int)(pp.tileSizeY * 2.6), pp.tileSizeX * 8, (int)(pp.tileSizeY * 1.8), 35, 35);
	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 5, pp.screenHeight - (int)(pp.tileSizeY * 2.6) + 5, pp.tileSizeX * 8 - 10, (int)(pp.tileSizeY * 1.8) - 10, 25, 25);

	    x += pp.tileSizeX / 4;
	    y += pp.tileSizeY / 8;
	    
	    for(int i = pp.animH.shownTextColumn; i < pp.animH.currentColumn; i++) {
    		if(pp.animH.splittedMessageText[i] != null) {
    		g2.drawString(pp.animH.splittedMessageText[i], (int) x, (int) y);
    		y += getYStringLength(pp.animH.splittedMessageText[i]);
    		}
    	}
	    
	    if(pp.animH.splittedMessageText[this.pp.animH.currentColumn] != null) {
    	g2.drawString(pp.animH.splittedMessageText[pp.animH.currentColumn].substring(0, pp.animH.messageCounter), (int)x, (int)y);
	    }
	    
	    if(pp.animH.tempColumn % 3 == 0 && pp.animH.tempColumn != 0) {
    	g2.drawString(">", (int) x + (int) (pp.tileSizeX * 7.2), (int) y - (int) (pp.tileSizeY / 2.5));
    	
    	}drawName();
	    
	    g2.setFont(oldFont);
	    g2.setColor(oldColor);
	}

	
	public void drawAlert() {
		
			g2.setColor(new Color(20, 20, 20));
		    g2.setStroke(new BasicStroke(5));
		    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));

		    double x = pp.screenWidth / 2 - pp.tileSizeX * 4;
		    double y = pp.screenHeight - pp.tileSizeY * 2.15;

		    g2.setColor(new Color(20, 20, 20));
		    g2.fillRoundRect((int) x, pp.screenHeight - (int)(pp.tileSizeY * 2.6), pp.tileSizeX * 8, (int)(pp.tileSizeY * 1.7), 35, 35);
		    g2.setColor(Color.WHITE);
		    g2.drawRoundRect((int) x + 5, pp.screenHeight - (int)(pp.tileSizeY * 2.6) + 5, pp.tileSizeX * 8 - 10, (int)(pp.tileSizeY * 1.7) - 10, 25, 25);

		    x += pp.tileSizeX / 4;
		    y += pp.tileSizeY / 8;
		    String[] text = alertText.split("\n");
		    
		    for(int i = 0; i < text.length; i++) {
		    	if(text[i] != null) {
		    		g2.drawString(text[i], (int) x, (int) y);
		    		y += getYStringLength(text[i]);
		    	}
		    	
		    }
		
	}
	
	public void drawFight() {
		
		g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));
	    
	    //Enemy Life
	    double x = pp.screenWidth / 2 - pp.tileSizeX * 4;
	    double y = pp.screenHeight / 2 - pp.tileSizeY * 2.15;
	    
	    g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, pp.screenHeight / 2 - (int)(pp.tileSizeY * 2.6), pp.tileSizeX * 8, (int)(pp.tileSizeY * 0.3), 15, 15);
	    
	    drawEnemyLife(x, y);
	    
	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 3, pp.screenHeight / 2 - (int)(pp.tileSizeY * 2.6) + 3, pp.tileSizeX * 8 - 6, (int)(pp.tileSizeY * 0.3) - 6, 10, 10);

	    //Remaining time
	    g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, pp.screenHeight / 2 - (int)(pp.tileSizeY * 2.1), pp.tileSizeX * 8, (int)(pp.tileSizeY * 0.3), 15, 15);
	    
	    drawTimeCount(x, y);

	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 3, pp.screenHeight / 2 - (int)(pp.tileSizeY * 2.1) + 3, pp.tileSizeX * 8 - 6, (int)(pp.tileSizeY * 0.3) - 6, 10, 10);
	    
	    g2.setStroke(new BasicStroke(5));
	    x = pp.screenWidth / 2 - pp.tileSizeX * 4;
	    y = pp.screenHeight - pp.tileSizeY * 2.15;

	    g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, pp.screenHeight - (int)(pp.tileSizeY * 2.6), pp.tileSizeX * 8, (int)(pp.tileSizeY * 1.7), 35, 35);
	    
	    g2.setColor(Color.WHITE);

	    g2.drawRoundRect((int) x + 5, pp.screenHeight - (int)(pp.tileSizeY * 2.6) + 5, pp.tileSizeX * 8 - 10, (int)(pp.tileSizeY * 1.7) - 10, 25, 25);

	    x += pp.tileSizeX / 4;
	    y += pp.tileSizeY / 8;
	    String[] text = fightText.split("\n");
	    
	    for(int i = 0; i < text.length; i++) {
	    	if(text[i] != null) {
	    		g2.drawString(text[i], (int) x, (int) y);
	    		y += getYStringLength(text[i]);
	    	}
	    	
	    }
	
}
	
	public void drawTimeCount(double x, double y) {
		
	    g2.setColor(new Color((int)(pp.animH.timeCounter), (int)(pp.animH.timeLimit - pp.animH.timeCounter), 0));
	    
	    g2.fillRoundRect((int) x + 3, pp.screenHeight / 2 - (int)(pp.tileSizeY * 2.1) + 3, (int)(pp.tileSizeX * pp.animH.timeCounter / (pp.animH.timeLimit / 8)) - 6, (int)(pp.tileSizeY * 0.3) - 6, 5, 5);

	}
	
	public void drawEnemyLife(double x, double y) {
		
		
	    g2.setColor(new Color(100, 150, 0));
	    
	    g2.fillRoundRect((int) x + 3, pp.screenHeight / 2 - (int)(pp.tileSizeY * 2.6) + 3, (int) (pp.fightH.currentEnemy.getHP() * ((pp.tileSizeX * 8) / pp.fightH.currentEnemy.getMaxHP())) - 6, (int) (pp.tileSizeY * 0.3) - 6, 15, 15);
	}
	
	public void drawMessageChoice() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
	    oldStroke = g2.getStroke();

	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));

	    double x = pp.screenWidth / 2 - pp.tileSizeX * 4;
	    double y = pp.tileSizeY;
	    
	    drawChoiceRow(messageChoice[0], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(messageChoice[1], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(messageChoice[2], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(messageChoice[3], x, y);
	    x = pp.screenWidth / 2 - pp.tileSizeX * 4.5;
	    y = (pp.tileSizeY * 1.5) + (pp.tileSizeY * ((pp.commandNum - 1) * 0.85));
	    g2.setColor(Color.BLACK);
	    g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
	    
	    if(pp.commandNum > 0 && pp.commandNum <= pp.stageH.checkChoiceLength()) {
	    g2.drawString(">", (int) x, (int) y);
	    }
	    
	    g2.setColor(oldColor);
	    g2.setFont(oldFont);
	    g2.setStroke(oldStroke);
	    
	}
	
	public void drawAlertChoice() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
	    oldStroke = g2.getStroke();

	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));

	    double x = pp.screenWidth / 2 - pp.tileSizeX * 4;
	    double y = pp.tileSizeY;
	    
	    drawChoiceRow(alertChoice[0], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(alertChoice[1], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(alertChoice[2], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(alertChoice[3], x, y);
	    x = pp.screenWidth / 2 - pp.tileSizeX * 4.5;
	    y = (pp.tileSizeY * 1.6) + (pp.tileSizeY * ((pp.commandNum - 1) * 0.85));
	    g2.setColor(Color.BLACK);
	    g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
	    
	    if(pp.commandNum > 0 && pp.commandNum <= pp.alertH.checkChoiceLength()) {
	    g2.drawString(">", (int) x, (int) y);
	    }
	    
	    g2.setColor(oldColor);
	    g2.setFont(oldFont);
	    g2.setStroke(oldStroke);
		
	}
	
	public void drawFightChoice() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
	    oldStroke = g2.getStroke();

	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));

	    double x = pp.screenWidth / 2 - pp.tileSizeX * 4;
	    double y = pp.tileSizeY * 2.5;
	    
	    drawChoiceRow(fightChoice[0], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(fightChoice[1], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(fightChoice[2], x, y);
	    y += pp.tileSizeY * 0.85;
	    drawChoiceRow(fightChoice[3], x, y);
	    
	    x = pp.screenWidth / 2 - pp.tileSizeX * 4.5;
	    y = (pp.tileSizeY * 3.05) + (pp.tileSizeY * ((pp.commandNum - 1) * 0.85));
	    
	    g2.setColor(Color.BLACK);
	    g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
	    
	    if(pp.commandNum > 0 && pp.commandNum <= pp.fightH.checkChoiceLength()) {
	    g2.drawString(">", (int) x, (int) y);
	    }
	    
	    g2.setColor(oldColor);
	    g2.setFont(oldFont);
	    g2.setStroke(oldStroke);
		
	}
	
	public void drawChoiceRow(String choice, double x, double y) {
		
		if(choice != null) {
		
		g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, (int) y, pp.tileSizeX * 8, (int)(pp.tileSizeY * 0.8), 25, 25);
	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 4, (int) y + 4, pp.tileSizeX * 8 - 8, (int)(pp.tileSizeY * 0.8) - 8, 15, 15);
	    
	    x += pp.tileSizeX / 4;
	    y += pp.tileSizeY / 1.7;
		
		g2.drawString(choice, (int)x, (int)y);
		
		}
	}
	
	public void drawLose() {
		
		oldFont = g2.getFont();
		oldColor = pp.getBackground();
	    oldStroke = g2.getStroke();

	    g2.setColor(new Color(20, 20, 20));
	    g2.setStroke(new BasicStroke(3));
	    
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(6)));
	    
	    g2.drawString("YOU LOST", getXForCenteredText("YOU LOST"), pp.tileSizeY * 2);

	    g2.setFont(g2.getFont().deriveFont(Font.BOLD, fontScale(2)));

	    double x = (pp.screenWidth / 2) - pp.tileSizeX;
	    double y = pp.tileSizeY * 3;
	    
		g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, (int) y, pp.tileSizeX * 2, (int)(pp.tileSizeY * 0.8), 25, 25);
	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 4, (int) y + 4, pp.tileSizeX * 2 - 8, (int)(pp.tileSizeY * 0.8) - 8, 15, 15);
	    		
		g2.drawString("RESTART", getXForCenteredText("RESTART"), (int) (y + pp.tileSizeY / 1.7));
		
		y += pp.tileSizeY * 0.85;
				
		g2.setColor(new Color(20, 20, 20));
	    g2.fillRoundRect((int) x, (int) y, pp.tileSizeX * 2, (int)(pp.tileSizeY * 0.8), 25, 25);
	    g2.setColor(Color.WHITE);
	    g2.drawRoundRect((int) x + 4, (int) y + 4, pp.tileSizeX * 2 - 8, (int)(pp.tileSizeY * 0.8) - 8, 15, 15);
	    
		g2.drawString("END", getXForCenteredText("END"), (int) (y + pp.tileSizeY / 1.7));
		
	    x = (pp.screenWidth / 2) - pp.tileSizeX * 1.5;
	    y = (pp.tileSizeY * 3.5) + (pp.tileSizeY * ((pp.commandNum - 1) * 0.85));
	    g2.setColor(Color.BLACK);
	    g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
	    
	    if(pp.commandNum > 0 && pp.commandNum <= 2) {
	    g2.drawString(">", (int) x, (int) y);
	    }
	    
	    g2.setColor(oldColor);
	    g2.setFont(oldFont);
	    g2.setStroke(oldStroke);
		
	}

	
	
	
	
	public void drawName() {
		
		oldFont = g2.getFont();
	    oldColor = pp.getBackground();
	    oldStroke = g2.getStroke();
		
		if(pp.stageH.name != null) {
			
			double x = pp.screenWidth / 2  + pp.tileSizeX * 4 - getXStringLength(pp.stageH.name + "   ");
		    double y = pp.screenHeight - (pp.tileSizeY * 3.35);
		    
			g2.setStroke(new BasicStroke(3));
			g2.setColor(new Color(20, 20, 20));
		    g2.fillRoundRect((int) x, (int) y, getXStringLength(pp.stageH.name + "   "), (int)(pp.tileSizeY * 0.8), 25, 25);
		    g2.setColor(Color.WHITE);
		    g2.drawRoundRect((int) x + 4, (int) y + 4, getXStringLength(pp.stageH.name + "   ") - 8, (int)(pp.tileSizeY * 0.8) - 8, 15, 15);
		    
		    x += pp.tileSizeX / 5;
		    y += pp.tileSizeY / 1.8;
			
		    g2.drawString(pp.stageH.name, (int)x, (int) y);
		    
		    g2.setColor(oldColor);
		    g2.setFont(oldFont);
		    g2.setStroke(oldStroke);

			
		}
			
	}
	
	public void drawPlayerStats() {
		
			pp.ui.oldColor = pp.getBackground();
			pp.ui.oldStroke = g2.getStroke();
			pp.ui.oldFont = g2.getFont();
			
			g2.setColor(Color.BLACK);
			g2.setFont(g2.getFont().deriveFont(fontScale(2.5)));
			
			double x = pp.tileSizeX;
			double y = pp.tileSizeY * 0.8;
			
			g2.drawString("Level: " + pp.player.getLevel() , (int) x, (int) y);
			
			x = pp.tileSizeX * 3;
			
			g2.drawString("HP: " + pp.player.getHP() + "/" + pp.player.getMaxHP() , (int) x, (int) y);
			
			x = pp.tileSizeX * 5.5;
			
			
			if(pp.player.currentWeapon.equals("")) {
			g2.drawString("WEAPON: /", (int) x, (int) y);
			}
			else {
			g2.drawString("WEAPON: " + pp.player.currentWeapon, (int) x, (int) y);
			}
			
		    g2.setColor(pp.ui.oldColor);
		    g2.setStroke(pp.ui.oldStroke);
		    g2.setFont(pp.ui.oldFont);
		    
	}
	
	public void drawTransition() {
		
		pp.ui.oldColor = pp.getBackground();
		pp.ui.oldStroke = g2.getStroke();
		pp.ui.oldFont = g2.getFont();
		
		int colorValue = 255 - (int) (pp.animH.transitionCounter / pp.animH.transitionLimit * 255);
		
		g2.setColor(new Color(0, 0, 0, colorValue));
	
		g2.fillRect(0, 0, pp.screenWidth, pp.screenHeight);
		
		g2.setColor(pp.ui.oldColor);
	    g2.setStroke(pp.ui.oldStroke);
	    g2.setFont(pp.ui.oldFont);
	}
	
	public void clear() {
		
		g2.setColor(oldColor);
		g2.fillRect(0, 0, pp.screenWidth, pp.screenHeight);
		
	}
	
	public float fontScale(double value) {
		return (float) value * ((int)(pp.tileSizeX / 5));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		 return pp.screenWidth / 2 - length / 2;
		
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
