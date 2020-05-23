package com.Tejas.platformer.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.Tejas.platformer.framework.GameObject;
import com.Tejas.platformer.framework.ObjectID;
import com.Tejas.platformer.object.Block;

/**
 * Manages the level of the game including the list of objects
 */
public class LevelHandler {
	
	// Stores all the objects in the game
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	// To retrieve an object temporarily
	private GameObject tempObject;
	
	// Update the object
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		
			
		}
	}
	
	// Render the object
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	// Add an object
	public void addObject(GameObject object)
	{
		this.object.add(object);
		
	}
	
	// Remove an object
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	// Create the extraneous features of the level (the bottom platform and the side
	// walls that stay the same) - these features stay the same, despite the level
	public void createLevel()
	{
		//Bottom
		for(int xx = 0; xx < Main.WIDTH+32; xx += 32)
		{
			addObject(new Block(xx, Main.HEIGHT-32, ObjectID.Block));
		}
		//Right Wall
		for(int yy = 0; yy < Main.HEIGHT+32; yy += 32)
		{
			addObject(new Block(Main.WIDTH  - 32, yy, ObjectID.Block));
		}
		//Left Wall
		for(int yy = 0; yy < Main.HEIGHT+32; yy += 32)
		{
			addObject(new Block(0, yy, ObjectID.Block));
		}
		//Middle Wall
		for(int xx = 160; xx < Main.WIDTH - 160; xx += 32)
		{
			addObject(new Block(xx, 450, ObjectID.Block));
			
		}
	}
}
