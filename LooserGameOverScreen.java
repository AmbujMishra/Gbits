package com.Gbits.Screens;
/*
 *Ambuj Mishra
 *23-1-2015 
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;


public class LooserGameOverScreen implements Screen {

	GbitsGame game;
	 
	public LooserGameOverScreen(GbitsGame gg) {
		game = gg;
 
	}

	@Override
	public void show() {
		game.camera.setToOrtho(false,game.getBitSize()*game.BC.getColumn(), game.getBitSize()*game.BC.getRow());
		Gdx.graphics.setContinuousRendering(false);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(107/255f, 107/255f,107/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
 
		game.batch.begin();
		for (int i=0; i < game.BC.getRow();i++)		//row i
		{	
			for (int j=0;j < game.BC.getColumn();j++)		//column j
			{
				if (game.BC.getBit(i, j)=='0')
				//game.batch.draw(game.tbitI0,game.bitOffset*(j+1)+j*game.getBitSize(),game.bitOffset*(i+1)+i*game.getBitSize());
				game.batch.draw(game.tbitI0,j*game.getBitSize(),i*game.getBitSize());
				else if(game.BC.getBit(i, j)=='1')
				//game.batch.draw(game.tbitI1,game.bitOffset*(j+1)+j*game.getBitSize(),game.bitOffset*(i+1)+i*game.getBitSize());
				game.batch.draw(game.tbitI1,j*game.getBitSize(),i*game.getBitSize());
			}
		}
		game.font.setColor(1, 1, 0, 1);
		//game.font.draw(game.batch, "Game Over!!! ", 30, 60);
		game.font.draw(game.batch, "Game Over!!! ",10 , 60);
		game.batch.end();
 
		if (Gdx.input.justTouched()) {
			//if (Gdx.input.getX()>600)
				//Gdx.app.exit();
			game.setScreen(game.MMS);
		}
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			Gdx.app.exit();
			
		}
		//game.fpslog.log();
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
		Gdx.input.setInputProcessor(null);
		game.font.setColor(1, 1, 1, 1);
		
	}

	@Override
	public void dispose() {
		
	}

}
