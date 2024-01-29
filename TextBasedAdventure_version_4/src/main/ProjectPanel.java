package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Player;
import gui.UI;

public class ProjectPanel extends JPanel{
	
	public Player player = new Player(15, 1, this);
	public UI ui = new UI(this);
	public BufferedImage image;
	public Graphics2D g2;
	public int screenWidth;
	public int screenHeight;
	public int FPS = 60;
	public Thread gameThread;
	
	public KeyHandler keyH = new KeyHandler(this);
	public MouseHandler mouseH = new MouseHandler(this);
	public StageHandler stageH = new StageHandler(this);
	
	public boolean enterPressed = false;
	public boolean isSetup = false;
	
	public int tileSize;
	
	public int gameState = 0;
	public final int titleScreenState = 1;
	public final int setupState = 2;
	public final int optionsState = 3;
	public final int dialogueState = 4;
	public final int choiceState = 5;
	public final int transitionState = 6;
	public final int fightState = 7;
	
	public int subState = 1;

	public ProjectPanel(int width, int height) {
		
		this.screenWidth = width;
		this.screenHeight = height;
		
		setup();
		
	}
	
	public void setup() {
		
//		setFullScreen(); Fuer FullScreen
		
		image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)image.getGraphics();
		
		tileSize = screenWidth / 10;
		
		setBackground(new Color(240, 240, 240));
		gameState = titleScreenState;
	}
	
	public void startGameThread() {
		
		image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = image.createGraphics();
		ui.setup();
		//Effizientere Zeichnung bei Shape Objekten
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//Effizientere Zeichnung bei Linien Objekten
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//Effizientere Zeichnung von Texten
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		
		gameThread = new Thread(new Runnable(){
			
			@Override
			public void run() {
				
				double drawInterval = 1000000000 / FPS;
				double delta = 0;
				long lastTime = System.nanoTime();
				long currentTime;
				long timer = 0;
				int drawCount = 0;
				
				while(gameThread != null) {
					currentTime = System.nanoTime();
					
					delta += (currentTime - lastTime) / drawInterval;
					timer += (currentTime - lastTime);
					lastTime = currentTime;
					
					if(delta >= 1) {
						
					update();
					drawToScreen();
					ui.clear();
					drawGame();
					delta--;
					drawCount++;
					}
					
					if(timer >= 1000000000) {
						drawCount = 0;
						timer = 0;
						}
				}
			}
		});
		gameThread.start();
	}
	
	public void drawToScreen() {
		
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, screenWidth, screenHeight, null);
		g.dispose();
	}

	public void update() {}
	
	public void drawGame() {
		
		//UI zeichnen
		ui.draw(g2);
	}
	public void render() {
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}
				
	
	public void restart() {
		
	}
	
	public void exit() {
		ui.g2.dispose();
		System.exit(0);
	}
			
	public Image setImage(String text) {
		
		ImageIcon imgico = new ImageIcon(text);
		Image img = imgico.getImage();
		return img.getScaledInstance(72, 75,  java.awt.Image.SCALE_SMOOTH);
	}
	
	public void setFullScreen() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(ProjectFrame.window);
		
		screenWidth = ProjectFrame.window.getWidth();
		screenHeight = ProjectFrame.window.getHeight();
		
}
}
