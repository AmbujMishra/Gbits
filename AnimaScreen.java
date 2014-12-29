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
		
		String [] after=game.BC.getBitArray();
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
				render(0);
				before=after;
			}
			break;
		case RIGHT:
		case UP:
		case DOWN:
		default:
		}
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(camera.combined);
		camera.update();
		
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
