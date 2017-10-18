package com.joey.neon.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.joey.neon.framework.KeyInput;
import com.joey.neon.framework.ObjectId;
import com.joey.neon.framework.Texture;
import com.joey.neon.objects.Block;
import com.joey.neon.objects.Enemy;
import com.joey.neon.objects.Player;

public class Game extends Canvas implements Runnable, KeyListener
{

		/**
	 * 
	 */
	private static final long serialVersionUID = -7077843141457865991L;
	
		private boolean running = false;
		private Thread thread;
		
		
		public static int WIDTH, HEIGHT;
		
		private BufferedImage level, background,playerIcon = null;
		//testing
		//
		HUD hud;
		Handler handler;
		Camera cam;
		static Texture tex;
		Menu menu;
		GameStateManager gsm;
		
		
		
		Random rand = new Random();
		
		public synchronized void start(){
			if(running)
				return;
			addKeyListener(this);
			running = true;
			thread = new Thread(this);
			thread.start();
			
		}

		public void run()
		{
				init();
				this.requestFocus();
				long lastTime = System.nanoTime();
				double ammountOfTicks = 60.0;
				double ns = 1000000000/ ammountOfTicks;
				double delta = 0;
				long timer = System.currentTimeMillis();
				@SuppressWarnings("unused")
				int updates = 0;
				@SuppressWarnings("unused")
				int frames = 0;
				while(running){
				long now = System.nanoTime();
				delta+= (now - lastTime)/ ns;
				lastTime = now;
				while(delta >= 1){
					tick();
					updates++;
					delta--;
				}
				render();
				frames++;
				if(System.currentTimeMillis() - timer > 1000){
					timer += 1000;
					frames = 0;
					updates = 0;
				}
			}
		}
		public void init()
		{
			WIDTH = getWidth();
			HEIGHT = getHeight();


			tex = new Texture();
			
			BufferedImageLoader loader = new BufferedImageLoader();
			//loading level
			level = loader.loadImage("/level.png");
			background = loader.loadImage("/grassyBackground.jpg");//loading background
			playerIcon = loader.loadImage("/healthPicture.png");
			
			handler = new Handler();
			hud = new HUD();
			
			cam = new Camera(0,0);
			
			LoadImageLevel(level);
			
			//handler.addObject(new Player(100, 100, handler, ObjectId.Player));
			
			//handler.createLevel();
			gsm = new GameStateManager(handler,cam,hud);
			menu = new Menu(gsm);
			gsm.setState(0);
			KeyInput keyInput = new KeyInput(handler);
			this.addKeyListener(keyInput);
		}
		
		
		private void LoadImageLevel(BufferedImage image)
		{
			int w = image.getWidth();
			int h = image.getHeight();
			
			//must decipher what pixel we're on
			// must loop through each pixel
			for(int xx = 0; xx < h; xx++){
				for(int yy = 0; yy < w; yy  ++){
					int pixel = image.getRGB(xx, yy);
					//will hold color of each pixel
					int red = (pixel >> 16) & 0xff;
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;
					
					if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(xx * 32, yy *32, 1, ObjectId.Block));
					if(red == 0 && green == 0 && blue == 255) handler.addObject(new Player(xx * 32, yy *32,handler, ObjectId.Player));
					if(red == 0 && green == 64 && blue == 0) handler.addObject(new Enemy(xx * 32, yy *32,handler, ObjectId.Enemy));
				}
			}
		}
		
		private void tick()
		{
			/*
			handler.tick();
			for(int i =0; i < handler.object.size(); i++){
				if(handler.object.get(i).getId() == ObjectId.Player){
					cam.tick(handler.object.get(i));
				
			}
			}
			*/
			gsm.update();
		}
		private void render()
		{
			// this is canvas class
			BufferStrategy bs = this.getBufferStrategy();
			if(bs == null)
			{
				this.createBufferStrategy(3);
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			Graphics2D g2d = (Graphics2D)g;
			//////////////////////////////////
			//draw here
			//hex color
			g.setColor(new Color(0,42,0));
			g.fillRect(0, 0, getWidth(), getHeight());
			/*
			g.drawImage(background, 0, 0, 850, 650, null);
			g.drawImage(playerIcon, 10, 675, 100, 100, null);
			//creates multiple images to along the path ex clouds
			 for(int xx =0; xx < background.getWidth() * 5; xx += background.getWidth())
			  g.drawImage(background,xx,50,this);
			  
			hud.render(g);
			
			
			g2d.translate(cam.getX(), cam.getY()); //begn of cam
			
			handler.render(g);
			
			g2d.translate(-cam.getX(), -cam.getY()); //cam end
			*/
			/////////////////////////////////
			gsm.render(g2d, background, playerIcon, handler, cam, hud);
			gsm.render((Graphics2D) g);
			g.dispose();
			bs.show();
		}
		
		public static Texture getInstance(){
			return tex;
		}
		public static int clamp(int var, int min, int max)
		{
			if(var >= max)
				return var = max;
			else if(var <= min)
				return var = min;
			else
				return var;
		}
		public static void main(String args[]){
			new Window(800, 800, "Neon Platformer Game Prototype", new Game());
			
		}

		public void keyTyped(KeyEvent key) {}
		public void keyPressed(KeyEvent key) {
			gsm.keyPressed(key.getKeyCode());
		}
		public void keyReleased(KeyEvent key) {
			gsm.keyReleased(key.getKeyCode());
		}
}
