package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ProjectFrame{
	
	public static JFrame window;
	
	public ProjectFrame(int width, int height){
		super();
		
		window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Text based adventure");
		Image image = new ImageIcon("icon.png").getImage();
		window.setIconImage(image);
		window.setSize(width, height);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setUndecorated(true);
		window.repaint();
		
		ProjectPanel gp = new ProjectPanel(width, height);
		
		if(gp.fullScreenOn == false) {
		window.setUndecorated(false);
		window.repaint();
		}
		gp.setVisible(true);
		window.setContentPane(gp);
		gp.setLayout(null);
		
		window.addKeyListener(gp.ui.keyH);
		window.addMouseListener(gp.ui.mouseH);
		window.addMouseMotionListener(gp.ui.mouseH);
		
		window.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				gp.startGameThread();
			}
		});
	}
}
