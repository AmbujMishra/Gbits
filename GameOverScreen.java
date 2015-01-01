package com.Gbits.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class GameOverScreen implements Screen {

	GbitsGame game;
	 
	OrthographicCamera camera;
 
	public GameOverScreen(GbitsGame gg) {
		game = gg;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
 
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
 
		game.batch.begin();
		game.font.draw(game.batch, "Game Over!!! ", 100, 400);
		game.font.draw(game.batch, "Tap anywhere to begin again!", 100, 350);
		game.font.draw(game.batch, "Hit Enter to exit Game!", 100, 300);
		game.batch.end();
 
		if (Gdx.input.isTouched()) {
			//correct your camera to get rid of extra screen
			game.BC.Stage(44);
			game.setScreen(game.MMS);
			//dispose();
		}
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			Gdx.app.exit();
			
		}
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
		
	}

}
