package com.joey.neon.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.joey.neon.framework.GameObject;
import com.joey.neon.framework.ObjectId;

public class Bullet extends GameObject
{
	long height;
	public Bullet(float x, float y, ObjectId id, int velX, long height) {
		super(x, y, id);
		this.velX = velX;
		this.height= height;
	}

	public void tick(LinkedList<GameObject> object) {
		x+= velX;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, (int)height);
		
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y, 16, 16);
	}

}
