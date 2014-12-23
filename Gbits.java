package com.kingAm.games;


package com.kingAm.games;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.FPSLogger;


/*@author ambuj mishra*/
public class Gbits extends ApplicationAdapter implements InputProcessor {
	
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	public FPSLogger fpslog;
	private static int row=6;
	private static int col=6;
	bitStore bs;
	bitLogic bl=new bitLogic();
	//private renderswitch renders= renderswitch.ON;		//created render switch
	private boolean drawflag=true;
	//camera
	/*static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;
    private static float w;
    private static float h;*/
  
	@Override
	public void create () {

		 // creating or initializing bits
		 bs=new bitStore();
		  //w = Gdx.graphics.getWidth();
	     // h = Gdx.graphics.getHeight();  
		 camera = new OrthographicCamera();
	     //camera = new OrthographicCamera(100,100*(h/w));
	     camera.setToOrtho(false, 64*col,64*row);
	    //camera.setToOrtho(false, w,h);
	     //camera.setToOrtho(false);
	     //camera.position.set(64*col,64*row, 0);
	     //camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
	     camera.update();
	     batch = new SpriteBatch();
	     Gdx.input.setInputProcessor(this);
	      //initializing logger
	      fpslog=new FPSLogger();
	      //disabling the continuous rendering
	      Gdx.graphics.setContinuousRendering(false);
	      Gdx.graphics.requestRendering();
	      
	      //input event
	     // InputEvent e= new InputEvent();
	     // e.getListenerActor();
	      //e.handle();
	     
	}
	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.LEFT)
		{
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
			
		}
		if(keycode==Keys.RIGHT)
		{
			
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
			//Gdx.graphics.requestRendering();
		}
		if(keycode==Keys.DOWN)
		{
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
				 System.out.print(bs.getbitMap());
			}
			//Gdx.graphics.requestRendering();
		}
		if(keycode==Keys.UP)
		{
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
		}	
		drawflag=false;
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		drawflag=true;
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		drawflag=true;
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
} 
