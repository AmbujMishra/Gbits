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

	public boolean gameOver()
	{
		/*10Jan2015 Update*/
		char cornerbits[]=new char[]{game.BC.getBit(0,0),game.BC.getBit(0,game.BC.getColumn()-1),game.BC.getBit(game.BC.getRow()-1,game.BC.getColumn()-1),game.BC.getBit(game.BC.getRow()-1,0)};
		String cb=new String(cornerbits);
		//botleft, botright, rightup, leftup
    	/*
    	 * 3	2
    	 * 0	1
    	 */
		if(game.BP.countBlanks(cb)==3)
		{
			int i=0;
    		while(cb.charAt(i)=='2')
    		{
    			i=i+1;		//non blank position
    		}
    		switch(i)
    		{
    		case 0:
    		
    		case 1:
    		case 2:
    		case 3:
    		}
		}
		if (game.BC.getBitCount()<=(game.BC.getRow()+game.BC.getColumn()-1) && game.BC.getBitCount()>4)
		{
			// remove it from here, its a win case
			//if (game.BC.getBitCount()<=2)
				//return true;
			// count no of blank corners
			//botleft, botright, rightup, leftup
	    	/*
	    	 * 3	2
	    	 * 0	1
	    	 */
			for (int i=1;i<game.BC.getRow()-1;i++)		//rows
			{
				for(int j=1;j<game.BC.getColumn()-1;j++)	//columns
				{
					if (game.BC.getBit(i, j)!='2')
						return false;
				}
			}
			//find active corner
			//corner 0
			if (game.BC.getBit(0,0)!='2' && game.BC.getBit(0,1)!='2' && game.BC.getBit(1,0)!='2')
				{
				int rowblank=game.BP.countBlanks(game.BC.getBitRow(0));
				int colblank=game.BP.countBlanks(game.BC.getBitCol(0, "DOWN"));		// not using second parameter i.e. down
				int rowl=game.BC.getColumn();
				int coll=game.BC.getRow();
				
				if (game.BC.getBit(0, 0)==game.BC.getBit(0, rowl-rowblank-1) && game.BC.getBit(0, 0)==game.BC.getBit(coll-colblank-1, 0) && game.BP.checkSeq(game.BC.getBitRow(0), rowblank) && game.BP.checkSeq(game.BC.getBitCol(0, "DOWN"), colblank))
				return true;
				}
			//similarly do for other corners
			
			//corner 1
			if (game.BC.getBit(0,game.BC.getColumn()-1)!='2' && game.BC.getBit(0,game.BC.getColumn()-2)!='2' && game.BC.getBit(1,game.BC.getColumn()-1)!='2')
			{
				//int rowl=game.BC.getColumn();
				int coll=game.BC.getRow();
			int rowblank=game.BP.countBlanks(game.BC.getBitRow(0));
			int colblank=game.BP.countBlanks(game.BC.getBitCol(game.BC.getColumn()-1, "DOWN"));		// not using second parameter i.e. down
			if (game.BC.getBit(0,game.BC.getColumn()-1)==game.BC.getBit(0,rowblank) && game.BC.getBit(0,game.BC.getColumn()-1)==game.BC.getBit(coll-colblank-1, game.BC.getColumn()-1) && game.BP.checkSeq(game.BP.shiftblanks(game.BC.getBitRow(0)), rowblank) && game.BP.checkSeq(game.BC.getBitCol(game.BC.getColumn()-1, "DOWN"), colblank))
			return true;
			}
			//corner 2
			if (game.BC.getBit(game.BC.getRow()-1,game.BC.getColumn()-1)!='2' && game.BC.getBit(game.BC.getRow()-1,game.BC.getColumn()-2)!='2' && game.BC.getBit(game.BC.getRow()-2,game.BC.getColumn()-1)!='2')
			{
				//int rowl=game.BC.getColumn();
				//int coll=game.BC.getRow();
			int rowblank=game.BP.countBlanks(game.BC.getBitRow(game.BC.getRow()-1));
			int colblank=game.BP.countBlanks(game.BC.getBitCol(game.BC.getColumn()-1, "DOWN"));		// not using second parameter i.e. down
			if (game.BC.getBit(game.BC.getRow()-1,game.BC.getColumn()-1)==game.BC.getBit(game.BC.getRow()-1,rowblank) && game.BC.getBit(game.BC.getRow()-1,game.BC.getColumn()-1)==game.BC.getBit(colblank, game.BC.getColumn()-1) && game.BP.checkSeq(game.BP.shiftblanks(game.BC.getBitRow(game.BC.getRow()-1)), rowblank) && game.BP.checkSeq(game.BP.shiftblanks(game.BC.getBitCol(game.BC.getColumn()-1, "DOWN")), colblank))
			return true;
			}
			//corner 3
			if (game.BC.getBit(game.BC.getRow()-1,0)!='2' && game.BC.getBit(game.BC.getRow()-2,0)!='2' && game.BC.getBit(game.BC.getRow()-1,1)!='2')
			{
				int rowl=game.BC.getColumn();
				//int coll=game.BC.getRow();
			int rowblank=game.BP.countBlanks(game.BC.getBitRow(game.BC.getRow()-1));
			int colblank=game.BP.countBlanks(game.BC.getBitCol(0, "DOWN"));		// not using second parameter i.e. down
			if (game.BC.getBit(game.BC.getRow()-1,0)==game.BC.getBit(game.BC.getRow()-1,rowl-rowblank-1) && game.BC.getBit(game.BC.getRow()-1,0)==game.BC.getBit(colblank, 0) && game.BP.checkSeq((game.BC.getBitRow(game.BC.getRow()-1)), rowblank) && game.BP.checkSeq(game.BP.shiftblanks(game.BC.getBitCol(0, "DOWN")), colblank))
			return true;
			}
			
			
			/*char cornerbits[]=new char[]{game.BC.getBit(0,0),game.BC.getBit(0,game.BC.getColumn()-1),game.BC.getBit(game.BC.getRow()-1,game.BC.getColumn()-1),game.BC.getBit(game.BC.getRow()-1,0)};
			String cb=new String(cornerbits);
			
			switch(game.BP.countBlanks(cb))
			{
			case 1:				// no of blanks=1
				int i=0;
	    		while(cb.charAt(i)!='2')
	    		{
	    			i=i+1;		//blank location
	    		}
	    		switch(i)
	    		{
	    		case 0:
	    			if(cb.charAt(2)==cb.charAt(1)&&cb.charAt(2)==cb.charAt(3) && game.BP.checkSeq(game.BC.getBitRow(game.BC.getRow()-1),0) && game.BP.checkSeq(game.BC.getBitCol(game.BC.getColumn()-1,"DOWN"),0))
	    			{
	    				return true;
	    			}
	    			else 
	    				break;
	    		case 1:
	    			if(cb.charAt(3)==cb.charAt(2)&&cb.charAt(3)==cb.charAt(0) && game.BP.checkSeq(game.BC.getBitRow(game.BC.getRow()-1),0) && game.BP.checkSeq(game.BC.getBitCol(0,"UP"),0))
	    			{
	    				return true;
	    			}
	    			else 
	    				break;
	    		case 2:
	    			if(cb.charAt(0)==cb.charAt(3)&&cb.charAt(0)==cb.charAt(1) && game.BP.checkSeq(game.BC.getBitRow(0),0) && game.BP.checkSeq(game.BC.getBitCol(0,"LEFT"),0))
	    			{
	    				return true;
	    			}
	    			else 
	    				break;
	    		case 3:
	    			if(cb.charAt(1)==cb.charAt(2)&&cb.charAt(1)==cb.charAt(0) && game.BP.checkSeq(game.BC.getBitRow(0),0) && game.BP.checkSeq(game.BC.getBitCol(game.BC.getColumn()-1,"RIGHT"),0))
	    			{
	    				return true;
	    			}
	    			else 
	    				break;
	    		}
			case 2:					//no of blanks=2
				if(game.BC.getBitCount()<=4)
					return false;
				
				//get active row and column
				int c[]=new int[2], j=0,k=0;
	    		while(j<4)
	    		{
	    			if(cb.charAt(j)=='2')
	    			{
	    				c[k]=j;
	    				k++;
	    			}
	    		}
	    		
	    		//missed few case below
	    		if(c[0]==0 && c[1]==1)		//row 3 full and column 0 not
	    		{
	    			int cell=game.BP.countBlanks(game.BC.getBitCol(0, "UP"));
	    			if (cb.charAt(3)==cb.charAt(2) && cb.charAt(3)==game.BC.getBit(cell,0) && game.BP.checkSeq(game.BC.getBitRow(game.BC.getRow()-1),0) && game.BP.checkSeq(game.BP.shiftblanks(game.BC.getBitCol(0,"UP")),cell))
	    			{
	    				return true;
	    			}
	    		}
	    		if(c[0]==1 && c[1]==2)		//column 0 full and row 3 not
	    		{
	    			int cell=game.BC.getColumn()-(game.BP.countBlanks(game.BC.getBitRow(game.BC.getRow()-1)))-1;
	    			if (cb.charAt(0)==cb.charAt(3) && cb.charAt(0)==game.BC.getBit(game.BC.getRow()-1,cell) && game.BP.checkSeq(game.BC.getBitCol(0, "UP"),0) && game.BP.checkSeq(game.BC.getBitRow(game.BC. getRow()-1),game.BP.countBlanks(game.BC.getBitRow(game.BC.getRow()-1))))
	    			{
	    				return true;
	    			}
	    		}
	    		if(c[0]==2 && c[1]==3)
	    		{
	    			int cell=game.BC.getRow()-game.BP.countBlanks(game.BC.getBitCol(0, "UP"))-1;
	    			if (cb.charAt(1)==cb.charAt(0) && cb.charAt(1)==game.BC.getBit(cell,0) && game.BP.checkSeq(game.BC.getBitRow(0),0) && game.BP.checkSeq(game.BP.shiftblanks(game.BC.getBitCol(0,"UP")),game.BP.countBlanks(game.BC.getBitCol(0, "UP"))))
	    			{
	    				return true;
	    			}
	    		}
	    		if(c[0]==0 && c[1]==3)
	    		{
	    			int cell=game.BP.countBlanks(game.BC.getBitRow(game.BC.getRow()-1));
	    			if (cb.charAt(2)==cb.charAt(1) && cb.charAt(2)==game.BC.getBit(game.BC.getRow()-1,cell) && game.BP.checkSeq(game.BC.getBitCol(game.BC.getColumn()-1, "UP"),0) && game.BP.checkSeq(game.BP.shiftblanks(game.BC.getBitRow(game.BC. getRow()-1)),cell))
	    			{
	    				return true;
	    			}
	    		}
				break;
			case 3:
			case 4:
			}*/
		}
		return false;
	}
	
	@Override
	public void show() {
		game.camera.setToOrtho(false,64*game.BC.getColumn(), 64*game.BC.getRow());
	    game.camera.update();
	    //Game over mechanism , watch gameOver method
		if (game.BC.getBitCount()<=2)
		{
			System.out.println("winner");
			// show winner screen here
			game.setScreen(game.GOS);
		}
		if(gameOver())
		{
			System.out.println("looser");
			// show game over screen here
			game.setScreen(game.GOS);
		}
	//Gdx.input.setInputProcessor(this);
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
			//System.out.println("Input screen flag:"+game.getAnima());
			game.setScreen(game.ANS);
		}
		if (Gdx.input.justTouched())
		{
			Gdx.input.setInputProcessor(this);
			tx=Gdx.input.getX();
			ty=Gdx.input.getY();
		}
		//Gdx.input.setInputProcessor(this);
		//game.fpslog.log();
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
		if (screenX-tx > 10 && screenY-ty < screenX-tx)
		{	//right
			game.setGravity(Gravity.RIGHT);
			game.setAnima(true);
			//tx=screenX;
			//ty=screenY;
		}
		if (tx-screenX >10 && tx-screenX> ty-screenY )
			{	//left
			game.setGravity(Gravity.LEFT);
			game.setAnima(true);
			//tx=screenX;
			//ty=screenY;
			}
		if(screenY-ty>10 && screenY-ty >screenX-tx)
		{	//down
			game.setGravity(Gravity.DOWN);
			game.setAnima(true);	
			//tx=screenX;
			//ty=screenY;
		}
		if(ty-screenY>10 && ty-screenY > tx-screenX)
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
	}

}
