package com.joey.neon.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Menu extends GameState {
	private int currentChoice = 0;
	private String[] options = {
		"Start",
		"Help",
		"Quit"
	};
	GameStateManager gsm;
	public Menu(GameStateManager gsm)
	{
		this.gsm = gsm;
	}
	public void render(Graphics g)
	{
		Font fn0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fn0);
		g.setColor(Color.WHITE);
		g.drawString("MEGAMAN", 180, 180);
		
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 300, 400 + i * 50);
		}
	}
	
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}

	public void select()
	{
		if(currentChoice == 0)
		{
			gsm.setState(1);
		}
		if(currentChoice == 1)
		{
			
		}
		if(currentChoice == 2)
		{
			System.exit(0);
		}
	}

	public void tick() {
		
		
	}
	@Override
	public void render(Graphics g, BufferedImage background, BufferedImage playerIcon, Handler handler, Camera cam,
			HUD hud) {
		// TODO Auto-generated method stub
		
	}
	

}
