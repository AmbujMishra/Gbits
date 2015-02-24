package com.Gbits.Solver;
/*
 *Ambuj Mishra
 *23-02-2015 
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
		//game.camera.setToOrtho(false, 800, 480);
		game.camera.setToOrtho(false, 300, 200);
		Gdx.graphics.setContinuousRendering(false);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
 
		game.batch.begin();
		//game.font.draw(game.batch, "Game Over!!! ", 100, 400);
		//game.font.draw(game.batch, "Tap anywhere to begin again!", 100, 350);
		//game.font.draw(game.batch, "Exit Game!", 600, 200);
		game.font.draw(game.batch, "Stage Cleared!", 100, 150);
		game.font.draw(game.batch, "Tap Anywhere To Select Next Stage...", 30, 100);
		game.batch.end();
 
		if (Gdx.input.justTouched()) {
			//if (Gdx.input.getX()>600)
				//Gdx.app.exit();
			//game.setScreen(game.MMS);
			game.setScreen(game.STS);
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
		
	}

	@Override
	public void dispose() {
		
	}

}
