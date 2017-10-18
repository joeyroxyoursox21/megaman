package com.joey.neon.window;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 100;
	
	private int greenValue =255;
	
	private int score = 0;
	private int level = 1;
	int x = 155;
	int y = 675;
	
	public void tick(){
			
			HEALTH = Game.clamp(HEALTH, 0, 100);
			greenValue = Game.clamp(greenValue, 0, 255);
			greenValue = HEALTH * 2;
	}
	public void render(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(x, y, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(x, y, 200, 32);
		
		g.drawString("Score " + score, 150 , 724);
		g.drawString("Level " + level, 150, 740);
		g.drawString("MEGAMAN", 250, 724);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}