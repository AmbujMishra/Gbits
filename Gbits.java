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
	bitLogic bl=new bitLogic();

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
			bitStore.bitType b1= bs.getbitType(0, i);
			bitStore.bitType b2= bs.getbitType(1, i);
			bitStore.bitType b3= bs.getbitType(2, i);
			bitStore.bitType b4= bs.getbitType(3, i);
			//for (int j=0;j<col;j++)		//column j
			//{
			if (b1== bitStore.bitType.bit0)
			{
				if(b2==bitStore.bitType.bit0)	// could be 1 or blank
					bs.setbitType(0, i, b1);
				else if(b2==bitStore.bitType.bit1)// could be blank
					{
						if (b3==bitStore.bitType.bit1)	// b2 must be 1
							{
								bs.setbitType(2, i, b1);	// set b3 as 0
								if (b4==bitStore.bitType.bit0)
								bs.setbitType(3, i, bitStore.bitType.nobit);
							}
						else if (b3==bitStore.bitType.bit0)	// b2 must be 1
							{
								if (b4==bitStore.bitType.bit0)
								bs.setbitType(3, i, b3);		//b4 blank now
							}
						else							//b3 must be blank and b2 is 1
							{
								if (b4==bitStore.bitType.bit0 ||b4==bitStore.bitType.bit1)
								bs.setbitType(2, i, b1);		// b3 is 0 now
								bs.setbitType(3, i, b3);		//b4 blank now
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
		for (int i=0; i < col;i++)		//column i
		{
			bitStore.bitType b[]= new bitStore.bitType[col];
			 b[0]= bs.getbitType(i, 0);
			 b[1]= bs.getbitType(i, 1);
			 b[2]= bs.getbitType(i, 2);
			 b[3]= bs.getbitType(i, 3);
			 
			 // creating string seq for column
			 String bitseq= bs.getbitTypeEquivalentString(b);
			 // processing string seq
			 String processedseq=bl.processbitSequence(bitseq);
			 // converting string seq back to bitTypes
			 b=bs.getEquivalentbitTypefromString(processedseq);
			 
			 // setting bit type
			 for (int j=0; j < row;j++)		//column j
				{
				 bs.setbitType(i, j, b[j]);
				}
		}
		
		
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
	
	// ****************8/122014: Trying to handle input with updateing camera.*******************************************
	// bit should be move along y axis, downwards
	/*
	private SpriteBatch batch;
	//private Texture bitImage;
	private OrthographicCamera cam;
	private Sprite bitsprite;
	public FPSLogger fpslog;
	private String message = "Do something already!";
	private BitmapFont font;
	private float rotationSpeed;
	
	@Override
	public void create () {
		rotationSpeed = 90f;
		//img = new Texture("bit.jpg");
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
		bitsprite = new Sprite(new Texture(Gdx.files.internal("bit.jpg")));
		bitsprite.setSize(64*(h/w),64*(h/w));
		//bitsprite.setPosition(w/2-bitsprite.getWidth()/2, 0);
		//bitImage = new Texture(Gdx.files.internal("bit.jpg"));
		
        //message+=h+w;
	      cam = new OrthographicCamera(100, 100 * (h / w)); 
	      cam.setToOrtho(false, 800, 800);
	      //cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2f, 0);
	      cam.update();
		  batch = new SpriteBatch();
		  font=new BitmapFont();
		  font.setColor(Color.BLUE);
		  //font.getBounds(message);
		  
	      //bit = new Rectangle();
	     // bit.x = 800 / 2 - 64 / 2; // center the bit horizontally
	    // bits. bit.y = 20; // bottom left corner of the btt is 20 pixels above the bottom screen edge
	     // bit.width = 64;
	     // bit.height = 64;
	      //initializing logger
	      fpslog=new FPSLogger();
	      //disabling the continuous rendering
	      Gdx.graphics.setContinuousRendering(false);
	     // Gdx.graphics.requestRendering();
	}
	
	@Override
	public void render () {
		int h = Gdx.graphics.getHeight();
		int w = Gdx.graphics.getWidth();
		handleInput();
		 cam.update();
		fpslog.log();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 // tell the camera to update its matrices.
	      //cam.update();
	      // tell the SpriteBatch to render in the
	      // coordinate system specified by the camera.
	      batch.setProjectionMatrix(cam.combined);
	      
		batch.begin();
		bitsprite.draw(batch);
		//font.draw(batch, message, w/2, h/2);
		//batch.draw(bitImage, bit.x, bit.y);
		batch.end();
}
	 private void handleInput() {
		// TODO Auto-generated method stub
		 if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			 //bitsprite.setX
	            cam.rotate(-rotationSpeed, 0, 0, 1);
	        }
		
	}
	@Override
	   public void dispose() {
	      // dispose of all the native resources
	      batch.dispose();
	      bitsprite.getTexture().dispose();
	   
	   }
	   @Override
	   public void resize(int width, int height) {
	   }
	   @Override
	   public void pause() {
	   }
	   @Override
	   public void resume() {
	   }*/
	
