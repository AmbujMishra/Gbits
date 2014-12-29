package com.gameScreens.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
 
public class MainMenuScreen implements Screen{
 
	GbitsGame game;
	OrthographicCamera camera;

	public MainMenuScreen(GbitsGame gg) {
		game = gg;
 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		//write a logic here to load the stage and initialize BitContainer class
		game.BC=new BitContainer(44);
 
	}
 
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
 
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Gbits!!! ", 100, 400);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 350);
		game.batch.end();
 
		if (Gdx.input.isTouched()) {
			//game.setScreen(new GameScreen(game));
			game.setScreen(game.INS);
			//dispose();
		}
		game.fpslog.log();
	}
 
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
	}
 
	@Override
	public void hide() {
	}
 
	@Override
	public void pause() {
	}
 
	@Override
	public void resume() {
	}
 
	@Override
	public void dispose() {
	}
}
