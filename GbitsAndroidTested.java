package com.Gbits.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.FPSLogger;

public class Gbits extends ApplicationAdapter implements InputProcessor{

	// 22/12/2014: office update for bit movement using tilt for working prototype
	private SpriteBatch batch;
	private OrthographicCamera camera;
	public FPSLogger fpslog;
	private static int row=6;
	private static int col=6;
	private boolean drawflag=true;
	private int previoust=0;
	
	bitStore bs;
	bitLogic bl=new bitLogic();
	
	private int tilt=0;	// initial tilting i.e. 0  1=right, 2=left, 3=up, 4=down
	
 private int tx;
private int ty;
	 
		@Override
		public void create () {
			
			// creating or initializing bits
			 bs=new bitStore();
			 //creating camera
			 camera = new OrthographicCamera();
		     camera.setToOrtho(false, 64*col,64*row);
		     // update camera
		     camera.update();
		     batch = new SpriteBatch();
		      //initializing logger
		      fpslog=new FPSLogger();
		      //disabling the continuous rendering
		      Gdx.input.setInputProcessor(this);
		      Gdx.graphics.setContinuousRendering(false);
		      Gdx.graphics.requestRendering();
		}

		@Override
		public void render () {
			
			
			/*************drawing bits****************/
			setbits(tilt);
			if (drawflag)
			{
				System.out.println("draw");
				 // tell the camera to update its matrices.
			      //camera.update();
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			      // tell the SpriteBatch to render in the
			      // coordinate system specified by the camera.
			   batch.setProjectionMatrix(camera.combined);
			     //camera.update();
				batch.begin();
				//batch.setProjectionMatrix(camera.combined);
				for (int i=0; i <row;i++)		//row i
				{	
					for (int j=0;j<col;j++)		//column j
					{
						//batch.setProjectionMatrix(camera.combined);
						batch.draw(bs.getbits(i,j),i*64,j*64);
						//camera.update();
						//batch.draw(bs.getbits(i,j),i*(w/col),j*(h/row));
						//bs.getbits(i, j,w/col,h/row).draw(batch);
					}
				}
				batch.end();
			}
			fpslog.log();
			//handleTilt();
			//setbits(tilt);
			//Gdx.graphics.requestRendering();
		      
		}
		
		private void handleTilt()
		{
			/*
	Azimuth, rotation around the Z axis (0<=azimuth<360). 0 = North, 90 = East, 180 = South, 270 = West 
	Pitch, rotation around X axis (-180<=pitch<=180), with positive values when the z-axis moves toward the y-axis. 
	Roll, rotation around Y axis (-90<=roll<=90), with positive values when the z-axis moves toward the x-axis.
			*/
	       float xdeg= Gdx.input.getPitch();
	       float ydeg=Gdx.input.getRoll();

		if (ydeg>10)
		{
		if(previoust!=3)
		tilt=3;		//up
		if (xdeg< -10 && previoust!=1 )
		tilt=1;  //up right
		if (xdeg>10&& previoust!=2 )
		tilt=2;	  //up left
		}
		if (ydeg< -10)
		{
		if(previoust!=4)
		tilt=4;		//down
		if (xdeg<-10 && previoust!=1)
		tilt=1;		//down right
		if (xdeg>10 && previoust!=2 )
		tilt=2;		//down left
		}
		if(xdeg>10)
		{
		if(previoust!=2)
		tilt=2;		//left
		if (ydeg>10 && previoust!=3)
		tilt=3;		//left up
		if(ydeg<-10 && previoust!=4)
		tilt=4;		// left down
		}
		if (xdeg< -10)
		{
		if(previoust!=1)
		tilt=1;		//right
		if (ydeg>10 && previoust!=3)
		tilt=3;		//right  up
		if(ydeg<-10 && previoust!=4)
		tilt=4;		//right down
		}
		if (xdeg > -5 && xdeg< 5 && ydeg > -5 && ydeg <5)
		{
		 if(previoust!=0)
			tilt=0;
		}
		// no tilt change =no drawing
		if (previoust==tilt)
		drawflag=false;	
		else
		{
		previoust=tilt;		
		setbits(tilt);
		}
		Gdx.graphics.requestRendering();
		}
		private void setbits(int tilt)
		{
			switch(tilt)
			{
			case 1:
			//set bit position to extreme right
				//drawflag=true;
				System.out.println("right");
				for (int i=0; i<row;i++)		//row i
				{
					bitStore.bitType b[]= new bitStore.bitType[col];
					// b[0]= bs.getbitType(3, i);
					// b[1]= bs.getbitType(2, i);
					// b[2]= bs.getbitType(1, i);
					// b[3]= bs.getbitType(0, i);
					 for (int bc=0;bc<b.length;bc++)
					 {
						 b[bc]=bs.getbitType(b.length-bc-1,i);
					 }
						
					 // creating string seq for column
					 String bitseq= bs.getbitTypeEquivalentString(b);
					 // processing string seq
					 String processedseq=bl.processbitSequence(bitseq);
					 // converting string seq back to bitTypes
					 b=bs.getEquivalentbitTypefromString(processedseq);
					 
					 // setting bit type
					 for (int j=0; j < col;j++)		//row j
						{
						 bs.setbitType(b.length-j-1, i, b[j]);
						}
				}
			break;
			
			case 2:
			//extreme left
				//drawflag=true;
				System.out.println("left");
				for (int i=0; i<row;i++)		//row i
				{
					bitStore.bitType b[]= new bitStore.bitType[col];
					// b[0]= bs.getbitType(0, i);
					// b[1]= bs.getbitType(1, i);
					// b[2]= bs.getbitType(2, i);
					// b[3]= bs.getbitType(3, i);
					 for (int bc=0;bc<b.length;bc++)
					 {
						 b[bc]=bs.getbitType(bc,i);
					 }
						
					 // creating string seq for column
					 String bitseq= bs.getbitTypeEquivalentString(b);
					 // processing string seq
					 String processedseq=bl.processbitSequence(bitseq);
					 // converting string seq back to bitTypes
					 b=bs.getEquivalentbitTypefromString(processedseq);
					 
					 // setting bit type
					 for (int j=0; j < col;j++)		//row j
						{
						 bs.setbitType(j, i, b[j]);
						}
				}
			//Gdx.graphics.requestRendering();
			break;
			
			case 3:
			//up
				//drawflag=true;
				System.out.println("UP");
				 for (int i=0; i < col;i++)		//column i
			  		{
			  			bitStore.bitType b[]= new bitStore.bitType[row];
			  			 //b[0]= bs.getbitType(i, 3);
			  			// b[1]= bs.getbitType(i, 2);
			  			// b[2]= bs.getbitType(i, 1);
			  			// b[3]= bs.getbitType(i, 0);
			  			 for (int bc=0;bc<b.length;bc++)
			  			 {
			  				 b[bc]=bs.getbitType(i,b.length-bc-1);
			  			 }
			  				
			  			 // creating string seq for column
			  			 String bitseq= bs.getbitTypeEquivalentString(b);
			  			 // processing string seq
			  			 String processedseq=bl.processbitSequence(bitseq);
			  			 // converting string seq back to bitTypes
			  			 b=bs.getEquivalentbitTypefromString(processedseq);
			  			 
			  			 // setting bit type
			  			 for (int j=0; j < row;j++)		//column j
			  				{
			  				 bs.setbitType(i, b.length-j-1, b[j]);
			  				}
			  		}
			//Gdx.graphics.requestRendering();
			break;
			
			case 4:
			//down
				//drawflag=true;
				System.out.println("DOWN");
				for (int i=0; i < col;i++)		//column i
				{
					bitStore.bitType b[]= new bitStore.bitType[row];
					 //b[0]= bs.getbitType(i, 0);
					// b[1]= bs.getbitType(i, 1);
					// b[2]= bs.getbitType(i, 2);
					// b[3]= bs.getbitType(i, 3);
					 for (int bc=0;bc<b.length;bc++)
					 {
						 b[bc]=bs.getbitType(i,bc);
					 }
						
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
					 //System.out.print(bs.getbitMap());
				}
			//Gdx.graphics.requestRendering();
			break;
			
			case 0:
				//middle
				//drawflag=true;
				break;
			}
			drawflag=true;
			//Gdx.graphics.requestRendering();
		}
		 @Override
		   public void dispose() {
		      // dispose of all the native resources
		      batch.dispose();
		   }

		   @Override
		   public void resize(int width, int height) {
			   camera.update();
		   }

		   @Override
		   public void pause() {
		   }

		   @Override
		   public void resume() {
		   }

		@Override
		public boolean keyDown(int keycode) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			tx=screenX;
			ty=screenY;
			drawflag=false;
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			
			if (screenX-tx > 0 && screenY-ty < screenX-tx)
				tilt=1;		//right
			if (tx-screenX >0 && tx-screenX> ty-screenY )
				tilt=2;
			if(screenY-ty>0 && screenY-ty >screenX-tx)
				tilt=4;	//down
			if(ty-screenY>0 && ty-screenY > tx-screenX)
				tilt=3;	//up
			drawflag=true;
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			drawflag=false;
			return false;
		}

}
/*
// working set as of 22 dec on mobile
public class Gbits extends ApplicationAdapter implements InputProcessor{

	// 22/12/2014: office update for bit movement using tilt for working prototype
	private SpriteBatch batch;
	private OrthographicCamera camera;
	public FPSLogger fpslog;
	private static int row=8;
	private static int col=8;
	private boolean drawflag=true;
	private int previoust=0;
	
	bitStore bs;
	bitLogic bl=new bitLogic();
	
	private int tilt=0;	// initial tilting i.e. 0  1=right, 2=left, 3=up, 4=down
	

	 
		@Override
		public void create () {
			
			// creating or initializing bits
			 bs=new bitStore();
			 //creating camera
			 camera = new OrthographicCamera();
		     camera.setToOrtho(false, 64*col,64*row);
		     // update camera
		     camera.update();
		     batch = new SpriteBatch();
		      //initializing logger
		      fpslog=new FPSLogger();
		      //disabling the continuous rendering
		      Gdx.graphics.setContinuousRendering(false);
		      Gdx.graphics.requestRendering();
		}

		@Override
		public void render () {
			
			
			*//*************drawing bits****************//*
			if (drawflag)
			{
				System.out.println("draw");
				 // tell the camera to update its matrices.
			      //camera.update();
				Gdx.gl.glClearColor(1, 1, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			      // tell the SpriteBatch to render in the
			      // coordinate system specified by the camera.
			   batch.setProjectionMatrix(camera.combined);
			     //camera.update();
				batch.begin();
				//batch.setProjectionMatrix(camera.combined);
				for (int i=0; i <row;i++)		//row i
				{	
					for (int j=0;j<col;j++)		//column j
					{
						//batch.setProjectionMatrix(camera.combined);
						batch.draw(bs.getbits(i,j),i*64,j*64);
						//camera.update();
						//batch.draw(bs.getbits(i,j),i*(w/col),j*(h/row));
						//bs.getbits(i, j,w/col,h/row).draw(batch);
					}
				}
				batch.end();
			}
			fpslog.log();
			handleTilt();
			setbits(tilt);
			//Gdx.graphics.requestRendering();
		      
		}
		
		private void handleTilt()
		{
			
	Azimuth, rotation around the Z axis (0<=azimuth<360). 0 = North, 90 = East, 180 = South, 270 = West 
	Pitch, rotation around X axis (-180<=pitch<=180), with positive values when the z-axis moves toward the y-axis. 
	Roll, rotation around Y axis (-90<=roll<=90), with positive values when the z-axis moves toward the x-axis.
			
	       float xdeg= Gdx.input.getPitch();
	       float ydeg=Gdx.input.getRoll();

		if (ydeg>10)
		{
		tilt=3;		//up
		if (xdeg< -10) 
		tilt=1;  //up right
		if (xdeg>10)
		tilt=2;	  //up left
		}
		if (ydeg< -10)
		{
		tilt=4;		//down
		if (xdeg<-10)
		tilt=1;		//down right
		if (xdeg>10)
		tilt=2;		//down left
		}
		if(xdeg>10)
		{
		tilt=2;		//left
		if (ydeg>10)
		tilt=3;		//left up
		if(ydeg<-10)
		tilt=4;		// left down
		}
		if (xdeg< -10)
		{
		tilt=1;		//right
		if (ydeg>10)
		tilt=3;		//right  up
		if(ydeg<-10)
		tilt=4;		//right down
		}
		if (xdeg > -5 && xdeg< 5 && ydeg > -5 && ydeg <5)
			tilt=0;
		}
		private void setbits(int tilt)
		{
			if(tilt==previoust)
			{
			drawflag=false;
			Gdx.graphics.requestRendering();
			}
			else
			{
			switch(tilt)
			{
			case 1:
			//set bit position to extreme right
				drawflag=true;
				previoust=1;
				System.out.println("right");
				for (int i=0; i<row;i++)		//row i
				{
					bitStore.bitType b[]= new bitStore.bitType[col];
					// b[0]= bs.getbitType(3, i);
					// b[1]= bs.getbitType(2, i);
					// b[2]= bs.getbitType(1, i);
					// b[3]= bs.getbitType(0, i);
					 for (int bc=0;bc<b.length;bc++)
					 {
						 b[bc]=bs.getbitType(b.length-bc-1,i);
					 }
						
					 // creating string seq for column
					 String bitseq= bs.getbitTypeEquivalentString(b);
					 // processing string seq
					 String processedseq=bl.processbitSequence(bitseq);
					 // converting string seq back to bitTypes
					 b=bs.getEquivalentbitTypefromString(processedseq);
					 
					 // setting bit type
					 for (int j=0; j < col;j++)		//row j
						{
						 bs.setbitType(b.length-j-1, i, b[j]);
						}
				}
				Gdx.graphics.requestRendering();
			break;
			
			case 2:
			//extreme left
				drawflag=true;
				previoust=2;
				System.out.println("left");
				for (int i=0; i<row;i++)		//row i
				{
					bitStore.bitType b[]= new bitStore.bitType[col];
					// b[0]= bs.getbitType(0, i);
					// b[1]= bs.getbitType(1, i);
					// b[2]= bs.getbitType(2, i);
					// b[3]= bs.getbitType(3, i);
					 for (int bc=0;bc<b.length;bc++)
					 {
						 b[bc]=bs.getbitType(bc,i);
					 }
						
					 // creating string seq for column
					 String bitseq= bs.getbitTypeEquivalentString(b);
					 // processing string seq
					 String processedseq=bl.processbitSequence(bitseq);
					 // converting string seq back to bitTypes
					 b=bs.getEquivalentbitTypefromString(processedseq);
					 
					 // setting bit type
					 for (int j=0; j < col;j++)		//row j
						{
						 bs.setbitType(j, i, b[j]);
						}
				}
			Gdx.graphics.requestRendering();
			break;
			
			case 3:
			//up
				drawflag=true;
				previoust=3;
				System.out.println("UP");
				 for (int i=0; i < col;i++)		//column i
			  		{
			  			bitStore.bitType b[]= new bitStore.bitType[row];
			  			 //b[0]= bs.getbitType(i, 3);
			  			// b[1]= bs.getbitType(i, 2);
			  			// b[2]= bs.getbitType(i, 1);
			  			// b[3]= bs.getbitType(i, 0);
			  			 for (int bc=0;bc<b.length;bc++)
			  			 {
			  				 b[bc]=bs.getbitType(i,b.length-bc-1);
			  			 }
			  				
			  			 // creating string seq for column
			  			 String bitseq= bs.getbitTypeEquivalentString(b);
			  			 // processing string seq
			  			 String processedseq=bl.processbitSequence(bitseq);
			  			 // converting string seq back to bitTypes
			  			 b=bs.getEquivalentbitTypefromString(processedseq);
			  			 
			  			 // setting bit type
			  			 for (int j=0; j < row;j++)		//column j
			  				{
			  				 bs.setbitType(i, b.length-j-1, b[j]);
			  				}
			  		}
			Gdx.graphics.requestRendering();
			break;
			
			case 4:
			//down
				drawflag=true;
				previoust=4;
				System.out.println("DOWN");
				for (int i=0; i < col;i++)		//column i
				{
					bitStore.bitType b[]= new bitStore.bitType[row];
					 //b[0]= bs.getbitType(i, 0);
					// b[1]= bs.getbitType(i, 1);
					// b[2]= bs.getbitType(i, 2);
					// b[3]= bs.getbitType(i, 3);
					 for (int bc=0;bc<b.length;bc++)
					 {
						 b[bc]=bs.getbitType(i,bc);
					 }
						
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
					// System.out.print(bs.getbitMap());
				}
			Gdx.graphics.requestRendering();
			break;
			
			case 0:
				//middle
				drawflag=false;
				previoust=0;
				Gdx.graphics.requestRendering();
				break;
			}
			}
		}
		 @Override
		   public void dispose() {
		      // dispose of all the native resources
		      batch.dispose();
		   }

		   @Override
		   public void resize(int width, int height) {
			   camera.update();
		   }

		   @Override
		   public void pause() {
		   }

		   @Override
		   public void resume() {
		   }

		@Override
		public boolean keyDown(int keycode) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			drawflag=false;
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			drawflag=false;
			return false;
		}

}*/
//working prototype for tilt only
/*
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.FPSLogger;

public class Gbits extends ApplicationAdapter {
	
	//Working code for simple bit movement on screen
	
	private SpriteBatch batch;
	private Texture bitImage;
	private OrthographicCamera camera;
	private Rectangle bit;
	public FPSLogger fpslog;
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
	      //initializing logger
	      fpslog=new FPSLogger();
	      //disabling the continuous rendering
	      Gdx.graphics.setContinuousRendering(false);
	     // Gdx.graphics.requestRendering();
	}

	@Override
	public void render () {
		fpslog.log();
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
	      //fpslog.log();
	     // System.out.println ("frame changed");
	     // Gdx.graphics.requestRendering();
	      //System.out.println ("calling rendering");
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
	   
	
	// ****************8/122014: Trying to handle input with updateing camera.*******************************************
	// bit should be move along y axis, downwards
	
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
	   }
	// 15/12/2014: office update for bit movement using tilt
	    private SpriteBatch batch;
		private Texture bitImage;
		private OrthographicCamera camera;
		private Sprite bit;
		public FPSLogger fpslog;
		private int tilt=0;	// initial tilting i.e. 0  1=right, 2=left, 3=up, 4=down
		@Override
		public void create () {
			
			//img = new Texture("bit.jpg");
			//creating texture
			bitImage = new Texture(Gdx.files.internal("bit.jpg"));
			//creating sprite from above texture
			bit = new Sprite(bitImage);
			// setting initial position of sprite at centre of screen
			bit.setPosition(800/2 - 64/2, 400/2-64/2);
			
			camera = new OrthographicCamera();
		      camera.setToOrtho(false, 800, 400);
		      batch = new SpriteBatch();
		      //bit = new Rectangle();
		     // bit.x = 800 / 2 - 64 / 2; // center the bit horizontally
		     // bit.y = 20; // bottom left corner of the btt is 20 pixels above the bottom screen edge
		      //bit.width = 64;
		      //bit.height = 64;
		      //initializing logger
		      fpslog=new FPSLogger();
		      //disabling the continuous rendering
		     // Gdx.graphics.setContinuousRendering(false);
		      //Gdx.graphics.requestRendering();
		}

		@Override
		public void render () {
			fpslog.log();
			// edit your android manifest and set
			//android:screenOrientation="landscape"
			// it will stop screen to rotate automatically
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			 // tell the camera to update its matrices.
		      camera.update();
		      // tell the SpriteBatch to render in the
		      // coordinate system specified by the camera.
		      batch.setProjectionMatrix(camera.combined);
			batch.begin();
			bit.draw(batch);
			batch.end();
			// Method to calculate tilting of device
			//if(Gdx.input.isPeripheralAvailable(Peripheral.Compass)){
				handleTilt();
				setbits(tilt);
				
			//}
	               // else{
	                     // message += "No compass available\n";
	                //     }
			//handleTilt();
			// method to move bits according to rotation or tilt
			//setbits(tilt);
			//calculate inclination from z axis and decide
			
		
			
			 // process user input
		      //if(Gdx.input.isTouched()) {
		      	
		         Vector3 touchPos = new Vector3();
		         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		         camera.unproject(touchPos);
		         bit.x = touchPos.x - 64 / 2;
		         bit.y=touchPos.y;
		         
		     // }
		      //if(Gdx.input.isKeyPressed(Keys.LEFT)) bit.x -= 400 * Gdx.graphics.getDeltaTime();
		      //if(Gdx.input.isKeyPressed(Keys.RIGHT)) bit.x += 400 * Gdx.graphics.getDeltaTime();
		      //if(Gdx.input.isKeyPressed(Keys.UP)) bit.y += 400 * Gdx.graphics.getDeltaTime();
		      //if(Gdx.input.isKeyPressed(Keys.DOWN)) bit.y -= 400 * Gdx.graphics.getDeltaTime();

		      // make sure bit stays within the screen bounds
		     if(bit.getX()< 0) bit.setX(0);
		     if(bit.getX() > 800 - 64) bit.setX(800 - 64);
		    if(bit.getY()< 0) bit.setY(0);
		    if(bit.getY() > 400 - 64) bit.setY(400 - 64);
		      
		}
		private void handleTilt()
		{
			
	Azimuth, rotation around the Z axis (0<=azimuth<360). 0 = North, 90 = East, 180 = South, 270 = West 
	Pitch, rotation around X axis (-180<=pitch<=180), with positive values when the z-axis moves toward the y-axis. 
	Roll, rotation around Y axis (-90<=roll<=90), with positive values when the z-axis moves toward the x-axis.
			
	       //float zdeg= Gdx.input.getAzimuth();
	       float xdeg= Gdx.input.getPitch();
	       float ydeg=Gdx.input.getRoll();
		if (Tilt right)	//write logic
		tilt=1
		if (Tilt left)
		tilt=2
		if (Tilt up)
		tilt=3
		if(Tilt down)
		tilt=4
			
	       if (ydeg>10)
			{
			tilt=3;		//up
			if (xdeg< -10) 
			tilt=1;  //up right
			if (xdeg>10)
			tilt=2;	  //up left
			}
			if (ydeg< -10)
			{
			tilt=4;		//down
			if (xdeg<-10)
			tilt=1;		//down right
			if (xdeg>10)
			tilt=2;		//down left
			}
			if(xdeg>10)
			{
			tilt=2;		//left
			if (ydeg>10)
			tilt=3;		//left up
			if(ydeg<-10)
			tilt=4;		// left down
			}
			if (xdeg< -10)
			{
			tilt=1;		//right
			if (ydeg>10)
			tilt=3;		//right  up
			if(ydeg<-10)
			tilt=4;		//right down
			}
			if (xdeg > -5 && xdeg< 5 && ydeg > -5 && ydeg <5)
				tilt=0;
			}
		private void setbits(int tilt)
		{
			switch(tilt)
			{
			case 1:
			//set bit position to extreme right
			bit.setPosition(800 - 64, 400/2-64/2);
			//Gdx.graphics.requestRendering();
			break;
			
			case 2:
			//extreme left
			bit.setPosition(0, 400/2-64/2);
			//Gdx.graphics.requestRendering();
			break;
			
			case 3:
			//up
			bit.setPosition(800/2 - 64/2, 400-64);
			//Gdx.graphics.requestRendering();
			break;
			
			case 4:
			//down
			bit.setPosition(800/2 - 64/2, 0);
			//Gdx.graphics.requestRendering();
			break;
			
			case 0:
				//middle
				bit.setPosition(800/2-64/2, 400/2-64/2);
				//Gdx.graphics.requestRendering();
				break;
			}
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
*/
