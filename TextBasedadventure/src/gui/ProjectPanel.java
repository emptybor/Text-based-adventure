package gui;
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
import inventory.Inventory;
import main.AlertHandler;
import main.AnimationHandler;
import main.EnemyHandler;
import main.FightHandler;
import main.GameState;
import main.InputHandler;
import main.LoadSaveHandler;
import main.StageHandler;

public class ProjectPanel extends JPanel{
	
	public BufferedImage image;
	public Graphics2D g2;
	public int FPS = 60;
	public Thread gameThread;
	
	public UI ui = new UI(this);
	public Player player = new Player(15, 1, this);
	public Inventory inventory = new Inventory(this);
	public StageHandler stageH = new StageHandler(this);
	public FightHandler fightH = new FightHandler(this);
	public AlertHandler alertH = new AlertHandler(this);
	public EnemyHandler enemyH = new EnemyHandler(this);
	public AnimationHandler animH = new AnimationHandler(this);
	public LoadSaveHandler loadSave = new LoadSaveHandler(this);
	public InputHandler inputH = new InputHandler(this);
	
	public boolean isSetup = false;
	public boolean fullScreenOn = true;
	
	public int tileSizeX;
	public int tileSizeY;
	public int screenWidth;
	public int screenHeight;
	
	public GameState gameState;
	public GameState tempGameState;
	
	public int optionsSubstate = 0;
	public int commandNum = 1;
	public int subState = 1;

	public ProjectPanel(int width, int height) {
		
		this.screenWidth = width;
		this.screenHeight = height;
		
		setup();
		
	}
	
	public void setup() {
		
		loadSave.loadStats();
		loadSave.loadConfig();
		
		//Hier StageNum ändern, um Anfangsstage zu verändern
		//Hier admin Modifikationen
		
		if(fullScreenOn == true) {
		setFullScreen();
		}
		
		image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)image.getGraphics();
		
		tileSizeX = screenWidth / 10;
		tileSizeY = (int)(screenHeight / 7.7);
		
		
		setBackground(new Color(230, 230, 230));
		gameState = GameState.TitleScreenState;
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

	public void update() {
		
		inputH.checkInput();
	}
	
	public void drawGame() {
		
		//UI zeichnen
		ui.clear();
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
	
	public void setFullScreen() {
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(ProjectFrame.window);
		
		screenWidth = ProjectFrame.window.getWidth();
		screenHeight = ProjectFrame.window.getHeight();
		
	}
	
	public void reload() {
		
		loadSave.loadStats();
		loadSave.loadConfig();
		stageH.checkLoadState();
		animH.resetTimeCount();
		commandNum = 1;
	}
	
	public void resetGame() {
		
		inventory.clear();
		inventory.setup();
		
		player.setLevel(1);
		player.setMaxHP(15);
		player.setMaxHP(player.getHP());
		player.setDamage(1);
		player.setName("");
		
		animH.resetMessage();
		animH.resetTimeCount();
		ui.setupTextField();
		
		stageH.prepareForNextStage();
		
		for(int i = 0; i < stageH.isExplored.length; i++) {
			if(stageH.isExplored[i] == true) {
				stageH.isExplored[i] = false;
			}
		}
		
		fightH.prepareForFight();
		
		isSetup = false;
		
		loadSave.loadStats();
		
		gameState = GameState.TitleScreenState;
		
		
	}
}
