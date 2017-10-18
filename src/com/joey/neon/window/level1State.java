package com.joey.neon.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.joey.neon.framework.GameObject;
import com.joey.neon.framework.ObjectId;

public class level1State extends GameState {

    private Handler handler;
    private int xp, xm;//, yp, ym; //xm stands for x minus and xp stands for x plus etc.
    long start = 0;
    long end = 1500000;
    long chargeStart;
    long chargeRelease;
    long chargeSize, chargeTime;
    int count;
    HUD hud;
    static boolean gameOver;
    static boolean isPaused;
    GameStateManager gsm;
    Camera cam;
	public void tick() {
		handler.tick();
		for(int i =0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ObjectId.Player){
				cam.tick(handler.object.get(i));
			if(HUD.HEALTH==0)
			{
				gsm.setState(0);
			}
		}
		}
		
	}
	
	public level1State(Handler handler, Camera cam, GameStateManager gsm, HUD hud)
	{
		this.handler = handler;
		this.cam = cam;
		this.gsm = gsm;
		this.hud = hud;
	}
	
	@Override
	public void render(Graphics g, BufferedImage background, BufferedImage playerIcon, Handler handler, Camera cam, HUD hud) {
		g.drawImage(background, 0, 0, 850, 650, null);
		g.drawImage(playerIcon, 10, 675, 100, 100, null);
		Graphics2D g2d = (Graphics2D)g;
		//creates multiple images to along the path ex clouds
		/* for(int xx =0; xx < background.getWidth() * 5; xx += background.getWidth())
		  g.drawImage(background,xx,50,this);
		  */
		hud.render(g);
		g2d.translate(cam.getX(), cam.getY()); //begn of cam
		
		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY()); //cam end
		
	}


    public void keyPressed(int key) {

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



	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		handler = new Handler();
		hud = new HUD();
		
		cam = new Camera(0,0);
		
	}



}
