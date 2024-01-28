package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ProjectFrame{
	
	public static JFrame window;
	
	public ProjectFrame(int width, int height){
		super();
		
		window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Text based adventure");
		window.setSize(width, height);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
//		window.setUndecorated(true); Fuer FullScreen
		
		ProjectPanel gp = new ProjectPanel(width, height);
		gp.setVisible(true);
		window.setContentPane(gp);
		gp.setLayout(null);
		
		window.addKeyListener(gp.keyH);
		
		window.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				gp.startGameThread();
			}
		});
	}
}
