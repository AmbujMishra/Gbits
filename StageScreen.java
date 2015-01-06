package com.Gbits.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class StageScreen implements Screen{
	GbitsGame game;

	public StageScreen(GbitsGame gg) {
		game = gg;
	}

	@Override
	public void show() {
		game.camera.setToOrtho(false, 427, 208);
		game.camera.update();
		Gdx.graphics.setContinuousRendering(false);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
		
		game.batch.begin();
		game.batch.draw(game.tstage,0,0);
		game.batch.end();
		
		if (Gdx.input.isTouched()) {
			int row=Gdx.input.getY()/(Gdx.graphics.getHeight()/5)+1;
			int col=(Gdx.input.getX()/(Gdx.graphics.getWidth()/5))+1;
			game.BC.Stage(row*10+col);
			//System.out.println(Gdx.input.getX()+","+Gdx.input.getY());
			//System.out.println(row);
			//System.out.println(col);
			//System.out.println(row*10+col);
			//System.out.println(Gdx.graphics.getHeight()+","+Gdx.graphics.getWidth());
			game.setScreen(game.INS);
		}
		
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
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
