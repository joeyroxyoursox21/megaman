package com.joey.neon.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameState {
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void render(Graphics g, BufferedImage background, BufferedImage playerIcon, Handler handler, Camera cam,HUD hud);

}
