package com.Gbits.Screens;
/*
 *Ambuj Mishra
 *1-1-2015 
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;


public class GameOverScreen implements Screen {

	GbitsGame game;
	 
	public GameOverScreen(GbitsGame gg) {
		game = gg;
 
	}

	@Override
	public void show() {
		game.camera.setToOrtho(false, 800, 480);
		Gdx.graphics.setContinuousRendering(false);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
 
		game.batch.begin();
		game.font.draw(game.batch, "Game Over!!! ", 100, 400);
		game.font.draw(game.batch, "Tap anywhere to begin again!", 100, 350);
		game.font.draw(game.batch, "Hit Enter to exit Game!", 100, 300);
		game.batch.end();
 
		if (Gdx.input.isTouched()) {
			game.setScreen(game.MMS);
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
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		
	}

}
