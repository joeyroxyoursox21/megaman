package com.joey.neon.framework;

import java.awt.image.BufferedImage;

import com.joey.neon.window.BufferedImageLoader;

public class Texture {
	
	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	//private BufferedImage player_sheet = null;
	//private BufferedImage block1 = null;
	//private BufferedImage block2 = null;
	private BufferedImage playerStill, playerMove1,playerMove2,playerMove3,playerMove4,playerMove5,playerMove6 = null;
	private BufferedImage playerLStill,playerLMove1,playerLMove2,playerLMove3,playerLMove4,playerLMove5,playerLMove6 = null;
	private BufferedImage playerJump1, playerJump2, playerJump3, playerJump4, playerJump5, playerJump6,playerJump7,playerJump8,playerJump9 = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[7];
	public BufferedImage[] playerLeft = new BufferedImage[7];
	public BufferedImage[] playerJump = new BufferedImage[9];
	
	public Texture(){
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			block_sheet = loader.loadImage("/blockSheet.png");
			//block1 = loader.loadImage("/block1.png");
			//block2 = loader.loadImage("/block3.png");
			///// Player Images
			//player_sheet = loader.loadImage("/altPlayerSheet.png");
			playerStill = loader.loadImage("/megamanStand.png");
			//player move right
			playerMove1 = loader.loadImage("/walk1.png");
			playerMove2 = loader.loadImage("/walk3.png");
			playerMove3 = loader.loadImage("/walk6.png");
			playerMove4 = loader.loadImage("/walk9.png");
			playerMove5 = loader.loadImage("/walk12.png");
			playerMove6 = loader.loadImage("/walk15.png");
			//player move left
			playerLStill = loader.loadImage("/walkl11.png");
			playerLMove1 = loader.loadImage("/walkl1.png");
			playerLMove2 = loader.loadImage("/walkl3.png");
			playerLMove3 = loader.loadImage("/walkl6.png");
			playerLMove4 = loader.loadImage("/walkl9.png");
			playerLMove5 = loader.loadImage("/walkl12.png");
			playerLMove6 = loader.loadImage("/walkl15.png");
			//player jump
			playerJump1 = loader.loadImage("/jump1.png");
			playerJump2 = loader.loadImage("/jump2.png");
			playerJump3 = loader.loadImage("/test3.png");
			playerJump4 = loader.loadImage("/test4.png");
			playerJump5 = loader.loadImage("/test5.png");
			playerJump6 = loader.loadImage("/test6.png");
			playerJump7 = loader.loadImage("/test7.png");
			playerJump8 = loader.loadImage("/test8.png");
			playerJump9 = loader.loadImage("/test9.png");
			
		}
		catch(Exception e){
		
		}
		
		 bs = new SpriteSheet(block_sheet);
		//ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	private void getTextures()
	{
		/*
		block[0] = block1; //dirt
		block[1] = block2; //grass
		*/
		block[0] = bs.grabImage(1, 1, 34, 32); // block1
		block[1] = bs.grabImage(2, 1, 34, 32);
		
		//player[0] = ps.grabImage(1, 1, 32, 32);//idle player
		player[0] = playerStill; //megaman
		//walking
		player[1] = playerMove1;
		player[2] = playerMove2;
		player[3] = playerMove3;
		player[4] = playerMove4;
		player[5] = playerMove5;
		player[6] = playerMove6;
		//walking left
		//stand still first
		playerLeft[0] = playerLStill;
		playerLeft[1] = playerLMove1;
		playerLeft[2] = playerLMove2;
		playerLeft[3] = playerLMove3;
		playerLeft[4] = playerLMove4;
		playerLeft[5] = playerLMove5;
		playerLeft[6] = playerLMove6;
		//jumping
		playerJump[0] = playerJump1;
		playerJump[1] = playerJump2;
		playerJump[2] = playerJump3;
		playerJump[3] = playerJump4;
		playerJump[4] = playerJump5;
		playerJump[5] = playerJump6;
		playerJump[6] = playerJump7;
		playerJump[7] = playerJump8;
		playerJump[8] = playerJump9;
		
		
	}
}
