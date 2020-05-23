package com.Tejas.platformer.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Tejas.platformer.framework.GameObject;
import com.Tejas.platformer.framework.ObjectID;
import com.Tejas.platformer.main.LevelHandler;


/**
 * Handles all the activites of the Player class
 * @author tejas
 *
 */
public class Player extends GameObject{

	LevelHandler handler;
	
	private float width = 48, height = 96; // player dimensions
	private float gravity = 0.5f; // strength of the gravity acting on the player
	private final float MAX_SPEED = 10; // the maximum speed to which the player can move
	float rX, rY; // the INITIAL spawn position (needed when the player needs to be reset)
	
	public Player(float x, float y, float rX, float rY, ObjectID id, LevelHandler handler) {
		super(x, y, id);
		this.rX = rX;
		this.rY = rY;
		
		this.handler = handler;
	}
	
	
	// Based on the keys pressed, move the player's position
	// Also check for collisions
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if(falling || jumping)
		{
			velY += gravity;
			if(velY > MAX_SPEED) velY = MAX_SPEED;
			
		}  
		Collision();
	}
	
	
	private void Collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ObjectID.Block)
			{
				// Check if there is any intersection with a normal block
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					// If the player's head collides with a wall
					y = tempObject.getY() + 32;
					velY = 0;
				}
				if(getBounds().intersects(tempObject.getBounds()))
				{
					// Player is on top of a platform, no need to fall down
					y = tempObject.getY() - height;
					velY = 0;
					jumping = false;
					falling = false;
				}  else 
				{
					// NO INTERSECTION! The player is falling down!
					falling = true;
				}
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
				    x = tempObject.getX() - width;
				}
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					
					x = tempObject.getX() + 35;
				}
			
			
			} else if(tempObject.getID() == ObjectID.Dead)
			{
				// The player collided with 'death' block. Need to respawn
				if(getBounds().intersects(tempObject.getBounds()))
				{
					// Set the position to the spawn point
					x = rX;
					y = rY;
				}
			}
		}
			
	}
	
	// Render the player
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		Graphics2D g2D = (Graphics2D)g;
		g.setColor(Color.RED);
		
		// FOR DEBUGGING: can draw the collision boxes to model collisions
		/* Draws Collision boxes
		g2D.draw(getBounds());
		g2D.draw(getBoundsLeft());
		g2D.draw(getBoundsRight()); 
		g2D.draw(getBoundsTop());
		*/
	}

	
	// Get collision rectangles
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y + height/2), (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int)y , (int)width/2, (int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 5), (int)y+5, 5, (int)height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, 5, (int)height-10);
	}
	

}
