package com.joey.neon.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.joey.neon.framework.GameObject;
import com.joey.neon.framework.ObjectId;
import com.joey.neon.framework.Texture;
import com.joey.neon.window.Animation;
import com.joey.neon.window.Game;
import com.joey.neon.window.HUD;
import com.joey.neon.window.Handler;

public class Player extends GameObject{
	
	private float width = 32, height = 64;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private Handler handler;
	//facing 1 = right and -1 is left
	
	Texture tex = Game.getInstance();
	private Animation playerWalk;
	private Animation playerLeftWalk;
	private Animation playerJump;
	
	public Player(float x, float y,Handler handler, ObjectId id){
		super(x, y, id);
		this.handler= handler;
		
		this.facing = 1;
		
		playerWalk = new Animation(5, tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6]);
		playerLeftWalk = new Animation(5, tex.playerLeft[1],tex.playerLeft[2],tex.playerLeft[3],tex.playerLeft[4],tex.playerLeft[5],tex.playerLeft[6]);
		playerJump = new Animation(3, tex.playerJump[2],tex.playerJump[3],tex.playerJump[4],tex.playerJump[5],tex.playerJump[6],tex.playerJump[7],tex.playerJump[8]);
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y+= velY;
		
		if(velX <0) facing = -1;
		else if (velX > 0) facing = 1;
		//each tick increases velocity by grav
		if(falling || jumping)
		{
			velY += gravity;
			if(velY > MAX_SPEED)
			{
				velY = MAX_SPEED;
			}
		}
		Collision(object);
		
		playerWalk.runAnimation();
		playerLeftWalk.runAnimation();
		playerJump.runAnimation();
		
	}
	private void Collision(LinkedList<GameObject> object)
	{
		for(int i =0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			{
				//top collision first
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					y= tempObject.getY() + 32;
					velY = 0;
				}
				
				if(getBounds().intersects(tempObject.getBounds()))
				{
					y= tempObject.getY()-height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else
					falling = true;
				//side collision RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() - width;
				}
				// left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() + (34);
				}
				
			}
			if(tempObject.getId() == ObjectId.Enemy)
			{
				//top collision first
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH-=2;
				}
				
				if(getBounds().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH-=2;
				}
				else
					falling = true;
				//side collision RIGHT
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH-=2;
				}
				// left
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					HUD.HEALTH-=2;
				}
				
			}
		}
	}
	
	public void render(Graphics g) {
		if(jumping){
			playerJump.drawAnimation(g, (int)x, (int)y, 36, 64);
			
		}
		if(isCharging && jumping == false)
		{
			playerJump.drawAnimation(g, (int)x, (int)y, 36, 64);
		}
		else if(jumping == false){
			if(velX!= 0)
			{
				if(facing == 1)
				{
					playerWalk.drawAnimation(g, (int)x, (int)y, 36, 64);
				}
				else
					playerLeftWalk.drawAnimation(g, (int)x, (int)y, 36, 64);
					
			}
			else if(jumping == false)
			{
				if(facing == 1)
				g.drawImage(tex.player[0], (int)x, (int)y, 36,64, null);
				else if(facing == -1)
					g.drawImage(tex.playerLeft[0], (int)x, (int)y, 36,64, null);
			}
		}

	}

	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+ (width/2) - ((width/2))/2),(int) ((int)y + (height/2)), (int)width/2, (int) height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)),(int)y, (int)width/2, (int) height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width -5) ,(int)y + 5, (int)5, (int) height - 10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x,(int)y + 5, (int)5, (int) height- 10);
	}

}
