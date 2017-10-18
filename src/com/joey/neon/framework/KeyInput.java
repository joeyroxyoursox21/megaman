package com.joey.neon.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.joey.neon.objects.Bullet;
import com.joey.neon.window.Handler;


public class KeyInput extends KeyAdapter {
 
        private Handler handler;
        private int xp, xm;//, yp, ym; //xm stands for x minus and xp stands for x plus etc.
        long start = 0;
        long end = 1500000;
        long chargeStart;
        long chargeRelease;
        long chargeSize, chargeTime;
        int count;
 
        public KeyInput(Handler handler) {
                this.handler = handler;
                count = 0;
        }
 
        public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
 
                for (int i = 0; i < handler.object.size(); i++) {
                        GameObject tempObject = handler.object.get(i);
 
                        if (tempObject.getId() == ObjectId.Player) {
                               
                        	if(key == KeyEvent.VK_SPACE)
                        	{
                        		count++;
                        		if(count == 1)
                        		{
                        		chargeStart = System.nanoTime();
                        		}
                        		tempObject.setCharging(true);
                        	}
                                //I collect the numbers before actually set the velocity.
                                //In addition, you can use arrow keys
                                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) xm = 3;
                                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) xp = 3;
                                if(key == KeyEvent.VK_W && !tempObject.isJumping())
                				{
                					tempObject.setJumping(true);
                					tempObject.setVelY(-10);
                				}
                                //And here I set the velocity
                                tempObject.setVelX(xp - xm);
                        }
                }
        }
 
        public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
 
                for (int i = 0; i < handler.object.size(); i++) {
                        GameObject tempObject = handler.object.get(i);
 
                        if (tempObject.getId() == ObjectId.Player) {
 
                                //Same story. I collect numbers before I change velocity
                                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) xm = 0;
                                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) xp = 0;
                               
                                tempObject.setVelX(xp - xm);
                        		if(key == KeyEvent.VK_SPACE)
                        		{
                        			chargeRelease = System.nanoTime();
                        			tempObject.setCharging(false);
                        			chargeTime = chargeRelease - chargeStart;
                        			chargeSize = (chargeTime / 10000000);
                        			chargeSize = clampLong(chargeSize, 4, 32);
                        			
                        		if(end - start > 1000)
                        		{
                        			start = System.nanoTime();
                        			
                        			if(tempObject.getFacing() == -1)
                        			{
                        				handler.addObject(new Bullet(tempObject.getX() - 24,tempObject.getY() + 24,ObjectId.Bullet, tempObject.getFacing()* 10, chargeSize));
                        			}
                        			else
                        			{
                        			handler.addObject(new Bullet(tempObject.getX() + 36,tempObject.getY() + 24,ObjectId.Bullet, tempObject.getFacing()* 10, chargeSize));
                        			}
                        		}
               				    end = System.nanoTime();            
               				    count = 0;
                        		}
                        }
                }
 
        }
    	public static long clampLong(long var, long min, long max){
    		if(var >= max)
    			return var = max;
    		else if(var <= min)
    			return var = min;
    		else
    			return var;
    	}
 
}


/*
public class KeyInput extends KeyAdapter
{
	Handler handler;
	
	private boolean[] keyDown = new boolean[2];
	public KeyInput(Handler handler){
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i =0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_D) tempObject.setVelX(5); keyDown[0] = true;
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5); keyDown[1] = true;
				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping())
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
			}
		}
		
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i =0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_D) 
					{
					//tempObject.setVelX(0);
					keyDown[0] = false;
					}
				if(key == KeyEvent.VK_A)
					{
					//tempObject.setVelX(0);
					keyDown[1] = false;
					}
			}
		}
		
	}

	
}
*/
