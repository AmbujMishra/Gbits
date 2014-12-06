package com.Gbits.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Gbits extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture bitImage;
	private OrthographicCamera camera;
	private Rectangle bit;
	@Override
	public void create () {
		//img = new Texture("bit.jpg");
		bitImage = new Texture(Gdx.files.internal("bit.jpg"));
		camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 400);
	      batch = new SpriteBatch();
	      bit = new Rectangle();
	      bit.x = 800 / 2 - 64 / 2; // center the bit horizontally
	      bit.y = 20; // bottom left corner of the btt is 20 pixels above the bottom screen edge
	      bit.width = 64;
	      bit.height = 64;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 // tell the camera to update its matrices.
	      camera.update();
	      // tell the SpriteBatch to render in the
	      // coordinate system specified by the camera.
	      batch.setProjectionMatrix(camera.combined);
	      
		batch.begin();
		batch.draw(bitImage, bit.x, bit.y);
		batch.end();
		
		 // process user input
	      if(Gdx.input.isTouched()) {
	         Vector3 touchPos = new Vector3();
	         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	         camera.unproject(touchPos);
	         bit.x = touchPos.x - 64 / 2;
	         bit.y=touchPos.y;
	      }
	      if(Gdx.input.isKeyPressed(Keys.LEFT)) bit.x -= 400 * Gdx.graphics.getDeltaTime();
	      if(Gdx.input.isKeyPressed(Keys.RIGHT)) bit.x += 400 * Gdx.graphics.getDeltaTime();
	      if(Gdx.input.isKeyPressed(Keys.UP)) bit.y += 400 * Gdx.graphics.getDeltaTime();
	      if(Gdx.input.isKeyPressed(Keys.DOWN)) bit.y -= 400 * Gdx.graphics.getDeltaTime();

	      // make sure the bucket stays within the screen bounds
	      if(bit.x < 0) bit.x = 0;
	      if(bit.x > 800 - 64) bit.x = 800 - 64;
	      if(bit.y< 0) bit.y=0;
	      if(bit.y > 400 - 64) bit.y = 400 - 64;
	}
	 @Override
	   public void dispose() {
	      // dispose of all the native resources
	      bitImage.dispose();
	      batch.dispose();
	   }

	   @Override
	   public void resize(int width, int height) {
	   }

	   @Override
	   public void pause() {
	   }

	   @Override
	   public void resume() {
	   }
}
