package com.kingAm.games;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Anim implements ApplicationListener{

	private Texture tbitI0;
	private Texture tbitI1;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	float x=0, y=0, x1=0, y1=64;
	
	@Override
	public void create() {
		tbitI0=new Texture(Gdx.files.internal("bit0.png"));
		tbitI1=new Texture(Gdx.files.internal("bit1.png"));
		 camera = new OrthographicCamera();
	      camera.setToOrtho(false, 256, 256);
	      batch = new SpriteBatch();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// clear the screen with a dark blue color. The
	      // arguments to glClearColor are the red, green
	      // blue and alpha component in the range [0,1]
	      // of the color to be used to clear the screen.
	      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	      // tell the camera to update its matrices.
	      camera.update();

	      // tell the SpriteBatch to render in the
	      // coordinate system specified by the camera.
	      batch.setProjectionMatrix(camera.combined);

	      // begin a new batch and draw the bucket and
	      // all drops
	      batch.begin();
	      if (y!=y1)
	      batch.draw(tbitI1,x1,y1);
	      batch.draw(tbitI0,x,y);
	      batch.end();
	      
	      if(y<= 64)
	      y += 20 * Gdx.graphics.getDeltaTime();

	   
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
	public void dispose() {
		tbitI0.dispose();
		tbitI1.dispose();
		
	}

}
