package com.Gbits.Screens;

/*
 *Ambuj Mishra
 *1-1-2015 
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.Gbits.Screens.GbitsGame.Gravity;

public class InputScreen implements Screen,InputProcessor{

	GbitsGame game;
	private int tx,ty;
	public InputScreen(GbitsGame gg) {
		game=gg;


	}

	@Override
	public void show() {
		game.camera.setToOrtho(false,64*game.BC.getColumn(), 64*game.BC.getRow());
	    game.camera.update();
	    
		if (game.BC.getBitCount()==2)
			game.setScreen(game.GOS);
		
	Gdx.input.setInputProcessor(this);
    Gdx.graphics.setContinuousRendering(false);
    Gdx.graphics.requestRendering();
	}

	@Override
	public void render(float delta) {
		//System.out.println("input screen");
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
		
		game.batch.begin();
		for (int i=0; i < game.BC.getRow();i++)		//row i
		{	
			for (int j=0;j < game.BC.getColumn();j++)		//column j
			{
				if (game.BC.getBit(i, j)=='0')
				game.batch.draw(game.tbitI0,j*64,i*64);
				else if(game.BC.getBit(i, j)=='1')
				game.batch.draw(game.tbitI1,j*64,i*64);
			}
		}
		game.batch.end();
		
		if (game.getAnima())
		{
			game.setScreen(game.ANS);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.LEFT)
		{
			game.setGravity(Gravity.LEFT);
			game.setAnima(true);
		}
		if(keycode==Keys.RIGHT)
		{
			game.setGravity(Gravity.RIGHT);
			game.setAnima(true);
		}
		if(keycode==Keys.UP)
		{
			game.setGravity(Gravity.UP);
			game.setAnima(true);
		}
		if(keycode==Keys.DOWN)
		{
			game.setGravity(Gravity.DOWN);
			game.setAnima(true);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		tx=screenX;
		ty=screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (screenX-tx > 0 && screenY-ty < screenX-tx)
		{	//right
			game.setGravity(Gravity.RIGHT);
			game.setAnima(true);
		}
		if (tx-screenX >0 && tx-screenX> ty-screenY )
			{	//left
			game.setGravity(Gravity.LEFT);
			game.setAnima(true);
			}
		if(screenY-ty>0 && screenY-ty >screenX-tx)
		{	//down
			game.setGravity(Gravity.DOWN);
			game.setAnima(true);			
		}
		if(ty-screenY>0 && ty-screenY > tx-screenX)
		{	//up
			game.setGravity(Gravity.UP);
			game.setAnima(true);
		}
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
