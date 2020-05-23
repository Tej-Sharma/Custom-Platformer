package com.Tejas.platformer.main;

import com.Tejas.platformer.framework.GameObject;

/**
 * Controls what the player sees in the form of a camera of the game
 * Basically, the position of the camera controls what the player sees
 * Thus, it is a simple, self-explanatory class
 * @author tejas
 *
 */
public class Camera {
	private float x, y;
	
	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	public void tick(GameObject player) {
		x = -player.getX() + Main.WIDTH/2;
		y = -player.getY() + Main.HEIGHT/2;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	
}
