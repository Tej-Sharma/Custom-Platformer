package com.Tejas.platformer.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.Tejas.platformer.main.LevelHandler;

/**
 * Uses WASD format
 * Helper class to get all the keys pressed and then pass this information to the 
 * 'LevelHandler' class
 * @author tejas
 *
 */
public class KeyInput extends KeyAdapter {
	LevelHandler handler;
	
	public KeyInput(LevelHandler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ObjectID.Player)
			{
				if(key == KeyEvent.VK_D) temp.setVelX(5);
				if(key == KeyEvent.VK_A) temp.setVelX(-5);
				if(key == KeyEvent.VK_SPACE && !temp.isJumping())
				{
					temp.setJumping(true);
					temp.setVelY(-10);
				}
				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
			
			
		
	}
	public void keyReleased(KeyEvent e)
	{		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ObjectID.Player)
			{
				if(key == KeyEvent.VK_D) temp.setVelX(0);
				if(key == KeyEvent.VK_A) temp.setVelX(0);
				
			}
		}

	}

}
