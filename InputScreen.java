package com.Gbits.Solver;

/*
 *Ambuj Mishra
 *23-02-2015 
 */
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.Gbits.Solver.GbitsGame.Gravity;
import com.badlogic.gdx.math.MathUtils;

public class InputScreen implements Screen/*,InputProcessor*/{

	GbitsGame game;
	//private int tx,ty;
	public InputScreen(GbitsGame gg) {
		game=gg;


	}

	public boolean gameOver()
	{
		//23 JAN 2015
		//Made it independent of gravity, because gravity is getting set as zero after each animation cycle.
		
		//check for up,if no then check for left and right from up
		//check for left, if no then check for up and down
		//check for right, if no then check for up and down
		//check for down, if no then check for left and right
		/*go for above logic*/
		
		//First write a common method to transpose existing bit container based on gravity.No
		//form a temp bit array
		int r=game.BC.getRow();
		int c=game.BC.getColumn();
		String seq;
		boolean go=true;
		
		//Apply LEFT and RIGHT both by removing blanks in rows
		for (int i=0;i<r;i++)
		{
			if(game.BP.getNonBlanks(game.BC.getBitRow(i))!=0)
			{
			seq=game.BP.removeBlanks(game.BC.getBitRow(i));
			if (!game.BP.checkSeq(seq, 0))
				go=false;
			}
		}
		//Apply UP and DOWN both by removing blanks in columns
		for (int i=0;i<c;i++)
		{
			if(game.BP.getNonBlanks(game.BC.getBitCol(i, "DOWN"))!=0)
			{
			seq=game.BP.removeBlanks(game.BC.getBitCol(i, "DOWN"));
			if (!game.BP.checkSeq(seq, 0))
				go=false;
			}
		}
		//Check UP and DOWN after applying LEFT gravity, It can be done simultaneously by removing blanks and checking columns
		//LEFT
		if(go)
		{
		//Forming temp bit array after removing blanks
			String temp[]=new String[r];
			for (int i=0;i<r;i++)
				temp[i]=game.BP.shiftblanks(game.BC.getBitRow(i));   // by default it will shift blanks towards right  24FEB2015
				//temp[i]=game.BP.removeBlanks(game.BC.getBitRow(i));
			for (int i=0;i<c;i++)
			{
				String tempC="";
				for (int j=0;j<r;j++)
				{
					tempC=tempC+temp[j].charAt(i);
				}
				
				if(game.BP.getNonBlanks(tempC)!=0)
				{
				seq=game.BP.removeBlanks(tempC);
				if (!game.BP.checkSeq(seq, 0))
					go=false;
				}
			}
		}
		
		//RIGHT
		if(go)
		{
			//Forming temp bit array after removing blanks
			String temp[]=new String[r];
			for (int i=0;i<r;i++)
				temp[i]=game.BP.shiftblanksLeft(game.BC.getBitRow(i));
			
			for (int i=0;i<c;i++)
			{
				String tempC="";
				for (int j=0;j<r;j++)
				{
					tempC=tempC+temp[j].charAt(i);
				}
				
				if(game.BP.getNonBlanks(tempC)!=0)
				{
				seq=game.BP.removeBlanks(tempC);
				if (!game.BP.checkSeq(seq, 0))
					go=false;
				}
			}
			
		}
		
		//DOWN
		if(go)
		{
			String temp[]=new String[c];
			for (int i=0;i<c;i++)
			{
					temp[i]=game.BP.shiftblanks(game.BC.getBitCol(i, "DOWN"));
			}
			
			String tempR[]=new String[r];
			for (int i=0;i<r;i++)
			{
				tempR[i]="";
				for(int j=0;j<c;j++)
				tempR[i]=tempR[i]+temp[j].charAt(i);
			}
			//Two for loop above is kind of transposing your bit container from column to row and vice versa. Look out for this
			for (int i=0;i<r;i++)
			{
				if(game.BP.getNonBlanks(tempR[i])!=0)
				{
					seq=game.BP.removeBlanks(tempR[i]);
					if (!game.BP.checkSeq(seq, 0))
						go=false;
				}
			}
			
		}
		
		//UP
		if(go)
		{
			String temp[]=new String[c];
			for (int i=0;i<c;i++)
			{
					temp[i]=game.BP.shiftblanksLeft(game.BC.getBitCol(i, "UP"));
			}
			
			String tempR[]=new String[r];
			for (int i=0;i<r;i++)
			{
				tempR[i]="";
				for(int j=0;j<c;j++)
				tempR[i]=tempR[i]+temp[j].charAt(i);
			}
			//Two for loop above is kind of transposing your bit container from column to row and vice versa. Look out for this
			for (int i=0;i<r;i++)
			{
				if(game.BP.getNonBlanks(tempR[i])!=0)
				{
					seq=game.BP.removeBlanks(tempR[i]);
					if (!game.BP.checkSeq(seq, 0))
						go=false;
				}
			}
		}
		
		return go;
	}
	
	@Override
	public void show() {
		game.camera.setToOrtho(false,game.bitSize*game.BC.getColumn(), game.bitSize*game.BC.getRow());
	    game.camera.update();
	    //Game over mechanism , watch gameOver method
		if (game.BC.getBitCount()<=2)
		{
			System.out.println("winner");
			// show winner screen here
			game.setScreen(game.GOS);
		}
		else if(gameOver())
		{
			System.out.println("looser");
			// show game over screen here
			game.setScreen(game.LGOS);
		}
	//Gdx.input.setInputProcessor(this);
   //Gdx.graphics.setContinuousRendering(false);
		autoInput();
    Gdx.graphics.requestRendering();
	}

	@Override
	public void render(float delta) {
		//System.out.println("input screen");
		Gdx.gl.glClearColor(107/255f, 107/255f,107/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
		
		game.batch.begin();
		for (int i=0; i < game.BC.getRow();i++)		//row i
		{	
			for (int j=0;j < game.BC.getColumn();j++)		//column j
			{
				if (game.BC.getBit(i, j)=='0')
				game.batch.draw(game.tbitI0,j*game.bitSize,i*game.bitSize);
				else if(game.BC.getBit(i, j)=='1')
				game.batch.draw(game.tbitI1,j*game.bitSize,i*game.bitSize);
			}
		}
		game.batch.end();
		
		if (game.getAnima())
		{
			//System.out.println("Input screen flag:"+game.getAnima());
			game.setScreen(game.ANS);
		}
		//if (Gdx.input.justTouched())
		//autoInput();		//thread is still running while going into anima class, write it out of render class
		/*if (Gdx.input.justTouched())
		{
			Gdx.input.setInputProcessor(this);
			tx=Gdx.input.getX();
			ty=Gdx.input.getY();
		}*/
		//Gdx.input.setInputProcessor(this);
		//game.fpslog.log();
	}

	public void autoInput()
	{
		//apply proper logic here.......applying...
		
		// 0=DOWN, 1=UP,2=LEFT, 3=RIGHT 		virtual assignment		23FEB2015
		//0=DOWN 1=LEFT 2=UP 3=RIGHT		24FEB2015	changing assignment for better result. To eliminate down up down or left right left sequence, Update: It didn't work :|
		int random=MathUtils.random(3);
		
		if (game.getPreviousGravity()== Gravity.ZERO)	// This is for first movement only
		{
			System.out.print("fk|");
			switch(random)
			{
			case 0:
				if(game.BP.isAnimaRequired(game.BC.getBitArray(), "DOWN"))
				{
					game.setGravity(Gravity.DOWN);
					game.setGravity(Gravity.ZERO); 		//to avoid calling this loop 2 times
					game.setGravity(Gravity.DOWN);
					game.setAnima(true);
					//System.out.print("fk|");
					break;
				}
			case 1:
				if(game.BP.isAnimaRequired(game.BC.getBitArray(), "LEFT"))
				{
					game.setGravity(Gravity.LEFT);
					game.setGravity(Gravity.ZERO);
					game.setGravity(Gravity.LEFT);
					game.setAnima(true);
					//System.out.print("fk|");
					break;
				}
			case 2:
				if(game.BP.isAnimaRequired(game.BC.getBitArray(), "UP"))
				{
					game.setGravity(Gravity.UP);
					game.setGravity(Gravity.ZERO);
					game.setGravity(Gravity.UP);
					game.setAnima(true);
					//System.out.print("fk|");
					break;
				}
			case 3:
				if(game.BP.isAnimaRequired(game.BC.getBitArray(), "RIGHT"))
				{
					game.setGravity(Gravity.RIGHT);
					game.setGravity(Gravity.ZERO);
					game.setGravity(Gravity.RIGHT);
					game.setAnima(true);
					//System.out.print("fk|");
					break;
				}	
			}
		}
		else
		{
			switch(random)
			{
			case 0:
				if (game.getPreviousGravity()!=Gravity.DOWN && game.BP.isAnimaRequired(game.BC.getBitArray(), "DOWN") )  // To avoid up down up down sequences for better result 24FEB2015
				//if (game.getPreviousGravity()!=Gravity.DOWN && game.getPreviousGravity()!=Gravity.UP && game.BP.isAnimaRequired(game.BC.getBitArray(), "DOWN") )	//update: its also not working :|  giving same sequence again and again  24FEB2015
				{game.setGravity(Gravity.DOWN);
				game.setAnima(true);
				break;
				}
			case 1:
				if (game.getPreviousGravity()!=Gravity.LEFT && game.BP.isAnimaRequired(game.BC.getBitArray(), "LEFT"))
				{game.setGravity(Gravity.LEFT);
				game.setAnima(true);
				break;
				}
			case 2:
				if (game.getPreviousGravity()!=Gravity.UP && game.BP.isAnimaRequired(game.BC.getBitArray(), "UP"))
				{game.setGravity(Gravity.UP);
				game.setAnima(true);
				break;
				}
			case 3:
				if (game.getPreviousGravity()!=Gravity.RIGHT && game.BP.isAnimaRequired(game.BC.getBitArray(), "RIGHT"))
				{game.setGravity(Gravity.RIGHT);
				game.setAnima(true);
				break;
				}
		     default:
		    	 autoInput();
		    	 Gdx.graphics.requestRendering();
		    	 break;
					
			}
		}
		/*switch(game.getPreviousGravity())
		{
		case ZERO:
			game.setGravity(Gravity.DOWN);
			game.setAnima(true);
			break;
		case UP:
			game.setGravity(Gravity.LEFT);
			game.setAnima(true);
			break;
		case LEFT:
			game.setGravity(Gravity.UP);
			game.setAnima(true);
			break;
		case RIGHT:
			game.setGravity(Gravity.DOWN);
			game.setAnima(true);
			break;
		case DOWN:
			game.setGravity(Gravity.RIGHT);
			game.setAnima(true);
		}*/
		
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
		 //System.out.println("hide input screen");
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

/*	@Override
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
		//tx=screenX;
		//ty=screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (screenX-tx > 20 && screenY-ty < screenX-tx)
		{	//right
			game.setGravity(Gravity.RIGHT);
			game.setAnima(true);
			//tx=screenX;
			//ty=screenY;
		}
		if (tx-screenX >20 && tx-screenX> ty-screenY )
			{	//left
			game.setGravity(Gravity.LEFT);
			game.setAnima(true);
			//tx=screenX;
			//ty=screenY;
			}
		if(screenY-ty>20 && screenY-ty >screenX-tx)
		{	//down
			game.setGravity(Gravity.DOWN);
			game.setAnima(true);	
			//tx=screenX;
			//ty=screenY;
		}
		if(ty-screenY>20 && ty-screenY > tx-screenX)
		{	//up
			game.setGravity(Gravity.UP);
			game.setAnima(true);
			//tx=screenX;
			//ty=screenY;
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
	}*/

}
