package com.Tejas.platformer.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.Tejas.platformer.framework.GameObject;
import com.Tejas.platformer.framework.KeyInput;
import com.Tejas.platformer.framework.ObjectID;
import com.Tejas.platformer.object.Block;
import com.Tejas.platformer.object.Player;

public class Main extends Canvas implements Runnable {
	
	// Set basic variables
	private static final long serialVersionUID = 5552646216980501818L;
	private boolean running = false;
	private Thread thread;
	public static int WIDTH, HEIGHT;
	
	// Handles the current level of the game
	LevelHandler handler;
	// Outputs to the user the current display
	Camera camera;
	
	// Stores the current level
	private BufferedImage level;
	
	// Store a temporary object 
	GameObject object;
	
	Random rand = new Random();
	
	/**
	 * Set up game, load the level and set the key callback
	 */
	private void init()
	{
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		handler = new LevelHandler();
		
		camera = new Camera(0, 0);
		
		// Load in the file
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level1.png");
		
		// Create the level in a format the program can understand
		loadImageLevel(level);
		
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	
	// Generate the level based on the pixels loaded in
	// Each pixel corresponds to a different type of element
	// A blue pixel represents the player's spawn position
	// A white pixel represents a platform
	// And a red pixel represents 'death': the player touches it and dies
	// A black pixel represents empty space
	private void loadImageLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < h; xx++)
		{
			for(int yy = 0; yy < w; yy++)
			{
				// Get the RGB values of each pixel in order to evaluate what object
				// should be placed over there
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(xx*32, yy*32, ObjectID.Block));
				if(red == 0 && green == 0 && blue == 255) handler.addObject(new Player(xx*32, yy*32, xx*32, yy*32, ObjectID.Player, handler));
				if(red == 255 && green == 0 && blue == 0) handler.addObject(new Block(xx*32,yy*32, ObjectID.Dead));
			}
		}
	}
	
	
	// Use an asynchronous thread to run the game's loading
	public void run() {
		init();
		// Get focus on the window
		this.requestFocus();
		
		// Get FPS, tick (update) count as well as other logging data
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	
	}
	
	
	public synchronized void start()
	{
		if(running)
			return; // Already running
		
		running = true;
		thread = new Thread(this);
		thread.start(); 
	}
	
	
	// The update method
	private void tick()
	{
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++)
		{
			if(handler.object.get(i).getID() == ObjectID.Player)
				camera.tick(handler.object.get(i));
		}
		
	}
	
	// Render the game's contents using simple Graphics2D
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2D = (Graphics2D)g;
		
		
		////////\\\\\\\\
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2D.translate(camera.getX(), camera.getY());
		
		handler.render(g);
		
		g2D.translate(-camera.getX(), -camera.getY());
		
		////////\\\\\\\\\
		
		
		g.dispose();
		bs.show();
	}
	
	// Create a window upon launch
	public static void main(String args[])
	{
		new Window(800, 600, "This Platformer Game made by Tejas", new Main());
	}
	

}
