package com.Tejas.platformer.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * Use OOB to abstract a game object class
 * that represents the basic features of an object in the game
 * @author tejas
 *
 */
public abstract class GameObject 
{
	
	protected float x, y;
	protected ObjectID id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true, jumping = false;

	public GameObject(float x, float y, ObjectID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	// Update and render methods must be updated
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	// Child class must define its bounds (to handle collisions)
	public abstract Rectangle getBounds();
	
	public  float getX(){
		return x;
	};
	public  float getY(){
		return y;
	}
	public  void setX(float x)
	{
		this.x = x;
	}
	public  void setY(float y)
	{
		this.y = y;
	}
	
	public  float getVelX() {
		return velX;
	}
	public  float getVelY() {
		return velY;
	}
	public  void setVelX(float velX) {
		this.velX = velX;
	}
	public  void setVelY(float velY) {
		this.velY = velY;
	}

	public ObjectID getID()
	{
		return id;
		
	}
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

}
