package com.Tejas.platformer.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	/**
	 * Create a simple window using JFrame for the game to render to
	 * @param width
	 * @param height
	 * @param title
	 * @param game
	 */
	public Window(int w, int h, String title, Main game)
	{
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		
		// Set the window's features
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); 
		
		game.start();
		
		
	}
}
