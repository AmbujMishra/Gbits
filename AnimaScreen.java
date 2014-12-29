package com.gameScreens.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.gameScreens.games.GbitsGame.Gravity;

public class AnimaScreen implements Screen{

	GbitsGame game;
	private OrthographicCamera camera;
	String before[];
	String after[];
	int[] change=new int[game.BC.getRow];
	private boolean animate=false;
	public AnimaScreen(GbitsGame gg) {
		game=gg;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,64*game.BC.getRow(), 64*game.BC.getColumn());
	    camera.update();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(null);
		before=game.BC.getBitArray();
		//it will process single step
		StepProcessing();
	}

	private void StepProcessing() {
		
		//String [] after=game.BC.getBitArray();
		after=before;
		switch(game.getGravity())
		{
		case LEFT:
			
			for (int i=0;i<game.BC.getRow();i++)
			{
				//first check the normalized version of row then call this step.
				if (!game.BP.checkSeq(after[i], game.BP.countBlanks(after[i])))
				{
					after[i]=game.BP.singleStepProcess(after[i]);
					animate=true;
				}
				else 
					animate=false;
			}
			
			if (!animate)
			{
				//update bit container
				//return to Input screen
				game.BC.setBitArray(after);
				game.setScreen(game.INS);
			}
			else	//call render()  for animation and set before=after
			{
				//get animation bit location
				change=getAnimationLocation(before,after);
			//	render(0); dnt call render, let it be called itself;
			//	before=after; //call it into render after animation
			}
			break;
		case RIGHT:
		case UP:
		case DOWN:
		default:
		}
		
	}
	private int[] getAnimationLocation(String[] before, String[] after)
	{
		for(int i=0;i<change.length();i++)
		{
			for (int j=0;j<before.length();j++)
			if (before[i].charAt[j]!=after[i].charAt[j])		//its wrong, 2 bits will be changed
			change[i]=j;
		}
		return change;
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(camera.combined);
		camera.update();
		game.batch.begin();
		for (int i=0; i < game.BC.getRow();i++)		//row i
		{	
			for (int j=0;j < game.BC.getColumn();j++)		//column j
			//for (char c: bitA[i].)
			{
			if (before[i].charAt(j)=='0')
				game.batch.draw(game.tbitI0,i*64,j*64);
			else if(before[i].charAt(j)=='1')
				game.batch.draw(game.tbitI1,i*64,j*64);
				
			}
		}
		// Now write animation here, record animation somewhere it will be easy
		for (int c=0;c<change.length();c++)
		{
			if (after[c].charAt(change[c])==2 && )
			game.batch.draw(game.tbitI0,(change[c]*64)-(20 * Gdx.graphics.getDeltaTime()),c*64);
		}
		game.batch.end();
		//write complex logic for animation :(
		
		game.fpslog.log();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
