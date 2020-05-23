package com.Tejas.platformer.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.Tejas.platformer.framework.GameObject;
import com.Tejas.platformer.framework.ObjectID;

/**
 * The block object, very simple
 * @author tejas
 *
 */
public class Block extends GameObject {

	public Block(float x, float y, ObjectID id) {
		super(x, y, id);
	}

	
	public void tick(LinkedList<GameObject> object) {
		// No update needed (for now, in the future, can add animations)
	}
	
	public void render(Graphics g) {
		if(this.id == ObjectID.Block) g.setColor(Color.WHITE);
		else g.setColor(Color.RED);
		g.drawRect((int)x,  (int)y, 32, 32);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}	

}
