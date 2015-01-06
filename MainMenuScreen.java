package com.Gbits.Screens;
/*
 *Ambuj Mishra
 *1-1-2015 
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
 
public class MainMenuScreen implements Screen{
 
	GbitsGame game;

	public MainMenuScreen(GbitsGame gg) {
		game = gg;
	}
 
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
 
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Gbits!!! ", 100, 400);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 350);
		//game.font.draw(game.batch, "Press 2,3,4 or 5 to set stage", 100, 200);
		game.batch.end();
 
		if (Gdx.input.justTouched()) {
			//System.out.println("touched");
			/*game.BC.Stage(22);
		    for (int i=0;i<game.BC.getRow();i++)
			    System.out.println(game.BC.getBitRow(i));*/
			game.setScreen(game.STS);
		}
		/*if(Gdx.input.isKeyPressed(Keys.NUM_2))
		{
			game.BC.Stage(22);
		    for (int i=0;i<game.BC.getRow();i++)
			    System.out.println(game.BC.getBitRow(i));
			game.setScreen(game.INS);
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_3))
		{
			game.BC.Stage(33);
		    for (int i=0;i<game.BC.getRow();i++)
			    System.out.println(game.BC.getBitRow(i));
			game.setScreen(game.INS);
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_4))
		{
			game.BC.Stage(44);
		    for (int i=0;i<game.BC.getRow();i++)
			    System.out.println(game.BC.getBitRow(i));
			game.setScreen(game.INS);
		}
		if(Gdx.input.isKeyPressed(Keys.NUM_5))
		{
			game.BC.Stage(55);
		    for (int i=0;i<game.BC.getRow();i++)
			    System.out.println(game.BC.getBitRow(i));
			game.setScreen(game.INS);
		}*/
		//game.fpslog.log();
	}
 
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
		game.camera.setToOrtho(false, 800, 480);
		game.camera.update();
		Gdx.graphics.setContinuousRendering(false);
	}
 
	@Override
	public void hide() {
		//System.out.println("main menu hide");
		Gdx.input.setInputProcessor(null);
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
