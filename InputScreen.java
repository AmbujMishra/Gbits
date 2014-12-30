package com.gameScreens.games;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.gameScreens.games.GbitsGame.Gravity;

public class InputScreen implements Screen,InputProcessor{

	GbitsGame game;
	private OrthographicCamera camera;
	//private FPSLogger fpslog;
	//BitContainer BC;
	//private boolean animFlag;
	//private String[] bitA;
	//private int bitCount=0;
	public InputScreen(GbitsGame gg) {
		game=gg;
		//fpslog=new FPSLogger();
		//BC=new BitContainer(44);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,64*game.BC.getRow(), 64*game.BC.getColumn());
	    camera.update();
	    //Gdx.graphics.setContinuousRendering(false);
	    //Gdx.graphics.requestRendering();
	}

	@Override
	public void show() {
	Gdx.input.setInputProcessor(this);
    Gdx.graphics.setContinuousRendering(false);
    Gdx.graphics.requestRendering();
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(camera.combined);
		camera.update();
		
		//bitA=game.BC.getBitArray();
		game.batch.begin();
		for (int i=0; i < game.BC.getRow();i++)		//row i
		{	
			for (int j=0;j < game.BC.getColumn();j++)		//column j
			//for (char c: bitA[i].)
			{
			//if (bitA[i].charAt(j)=='0')
				if (game.BC.getBit(i, j)=='0')
				game.batch.draw(game.tbitI0,i*64,j*64);
			//else if(bitA[i].charAt(j)=='1')
				else if(game.BC.getBit(i, j)=='1')
				game.batch.draw(game.tbitI1,i*64,j*64);
				
			}
		}
		game.batch.end();
		
		if (game.BC.getBitCount()==2)
			game.setScreen(game.GOS);
		
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
			//process current bit array
			// store the arrays in temp arrays
			// show animation by calling AnimaScreen
			// Return back to this screen
		//game.BP.processbitSequence("1111");
			game.setGravity(Gravity.LEFT);
			game.setScreen(game.ANS);
			//game.setScreen(game.FAS);
		}
		if(keycode==Keys.RIGHT)
		{
			game.setGravity(Gravity.RIGHT);
			game.setScreen(game.ANS);
			//game.setScreen(game.FAS);
		}
		if(keycode==Keys.UP)
		{
			game.setGravity(Gravity.UP);
			game.setScreen(game.ANS);
			//game.setScreen(game.FAS);
		}
		if(keycode==Keys.DOWN)
		{
			game.setGravity(Gravity.DOWN);
			//game.setScreen(game.ANS);
			//game.setScreen(game.FAS);
			game.setScreen(game.GOS);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
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
