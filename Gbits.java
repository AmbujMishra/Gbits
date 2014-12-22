package com.kingAm.games;

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
			
			
			/*************drawing bits****************/
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
				drawflag=true;
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
				drawflag=true;
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
				drawflag=true;
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
				drawflag=true;
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
					 System.out.print(bs.getbitMap());
				}
			//Gdx.graphics.requestRendering();
			break;
			
			case 0:
				//middle
				drawflag=true;
				break;
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

}

