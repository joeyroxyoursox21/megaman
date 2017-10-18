package com.joey.neon.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	Handler handler;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	
	public GameStateManager(Handler handler, Camera cam, HUD hud) {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		
		gameStates.add(new Menu(this));
		gameStates.add(new level1State(handler,cam, this, hud));
		this.handler = handler;
		
	}
	
	public void setState(int state) {
		currentState = state;
	}
	
	public void update() {
		gameStates.get(currentState).tick();
	}
	
	public void render(java.awt.Graphics2D g) {
		gameStates.get(currentState).render(g);
	}
	public void render(Graphics g, BufferedImage background, BufferedImage playerIcon, Handler handler, Camera cam, HUD hud)
	{
		gameStates.get(currentState).render(g, background, playerIcon, handler, cam, hud);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
}
