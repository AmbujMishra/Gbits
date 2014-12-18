package com.kingAm.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.FPSLogger;


public class Gbits extends ApplicationAdapter {
	
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	public FPSLogger fpslog;
	private static int row=4;
	private static int col=4;
	bitStore bs;

	@Override
	public void create () {

		 // creating or initializing bits
		 bs=new bitStore();
		 camera = new OrthographicCamera();
	     camera.setToOrtho(false, 256, 256);
	     batch = new SpriteBatch();
	      //initializing logger
	      fpslog=new FPSLogger();
	      //disabling the continuous rendering
	      Gdx.graphics.setContinuousRendering(false);
	     // Gdx.graphics.requestRendering();
	}
	@Override
	public void render () {
		
		/*************drawing bits****************/
		fpslog.log();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 // tell the camera to update its matrices.
	      camera.update();
	      // tell the SpriteBatch to render in the
	      // coordinate system specified by the camera.
	      batch.setProjectionMatrix(camera.combined);
	      
		batch.begin();
		for (int i=0; i <row;i++)		//row i
		{	
			for (int j=0;j<col;j++)		//column j
			{
				batch.draw(bs.getbits(i,j),j*64,i*64);
			}
		}
		batch.end();
		
		/***************handling bit logic**************/
		
		//if pressed left key
		for (int i=row-1; i >=0;i--)		//row i
		{	
			bitStore.bitType b1= bs.getbitType(i, 0);
			bitStore.bitType b2= bs.getbitType(i, 1);
			bitStore.bitType b3= bs.getbitType(i, 2);
			bitStore.bitType b4= bs.getbitType(i, 3);
			//for (int j=0;j<col;j++)		//column j
			//{
			if (b1== bitStore.bitType.bit0)
			{
				if(b2==bitStore.bitType.bit0)	// could be 1 or blank
					bs.setbitType(i, 0, b1);
				else if(b2==bitStore.bitType.bit1)// could be blank
					{
						if (b3==bitStore.bitType.bit1)	// b2 must be 1
							{
								bs.setbitType(i, 2, b1);	// set b3 as 0
								if (b4==bitStore.bitType.bit0)
								bs.setbitType(i, 3, bitStore.bitType.nobit);
							}
						else if (b3==bitStore.bitType.bit0)	// b2 must be 1
							{
								if (b4==bitStore.bitType.bit0)
								bs.setbitType(i, 3, b3);		//b4 blank now
							}
						else							//b3 must be blank and b2 is 1
							{
								if (b4==bitStore.bitType.bit0 ||b4==bitStore.bitType.bit1)
								bs.setbitType(i, 2, b1);		// b3 is 0 now
								bs.setbitType(i, 3, b3);		//b4 blank now
							}
					}
				else	// b2 must be blank
					{
					
					
					}
				
			}
			//}
		}
		//if pressed right key
		//if pressed up key
		//if pressed down key
		
		
		//bs.bitcalculate();
		 // process user input
	     /* if(Gdx.input.isTouched()) {
	    	  
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
	      //fpslog.log();
	     // System.out.println ("frame changed");
	     // Gdx.graphics.requestRendering();
	      //System.out.println ("calling rendering");
	       * */
	       
	}
	

	 @Override
	   public void dispose() {
	      // dispose of all the native resources
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
