package com.Gbits.Screens;
/*
 *Ambuj Mishra
 *1-1-2015 
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.Gbits.Screens.GbitsGame.Gravity;

public class AnimaScreen implements Screen{

	GbitsGame game;
	private float x,y;
	int max;
	private int loop=1;
	private boolean animationInProcess=false;
	
	public AnimaScreen(GbitsGame gg) {
		game=gg;
	}
	private void setloop(int l)
	{
		loop=l;
	}
	private void setX(int x)
	{
		this.x=x*game.getBitSize();
	}
	private void setY(int y)
	{
		this.y=y*game.getBitSize();
	}
private void setAnimationInProcess(boolean f)
{
	animationInProcess=f;
}
private boolean getAnimationInProcess()
{
	return animationInProcess;
}

	@Override
	public void show() {
		game.camera.setToOrtho(false,game.getBitSize()*game.BC.getColumn(), game.getBitSize()*game.BC.getRow());
	    game.camera.update();
	    
		Gdx.input.setInputProcessor(null);
		Gdx.graphics.setContinuousRendering(true);
		//System.out.println("animation screen show method");
		System.out.print(game.getGravity()+"|");
		if(game.BP.isAnimaRequired(game.BC.getBitArray(),game.getGravity().toString()))
		{
			switch(game.getGravity())
			{
			case LEFT:
				max=game.BC.getColumn()-1;
				setloop(1);
				setX(loop);
				break;
			case RIGHT:
				max=game.BC.getColumn()-1;
				setloop(max);
				setX(loop-1);
				break;
			case DOWN:					// like LEFT swap x and y coordinates
				max=game.BC.getRow()-1;
				setloop(1);
				setY(loop);			// setting the y location of moving row
				break;
			case UP:				// like RIGHT swap x and y coordinates
				max=game.BC.getRow()-1;
				setloop(max);
				setY(loop-1);
				break;
			case ZERO:
				game.setAnima(false);
				game.setScreen(game.INS);
				break;
			}
			setAnimationInProcess(true);
		}
		else
		{
			//System.out.println("Animation is not required");
			game.setAnima(false);
			game.setGravity(Gravity.ZERO);
			game.setScreen(game.INS);
		}
	}

	@Override
	public void render(float delta) {
		//System.out.println("animation screen render");
		Gdx.gl.glClearColor(107/255f, 107/255f,107/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.camera.update();
		if(game.getGravity()== Gravity.LEFT)
		{
			//System.out.println("left animation");
		if(getAnimationInProcess())
		{
		setAnimationInProcess(false);
		game.batch.begin();
		//drawing bits prior to loop bits including loop
		//drawing columns, not row
		for (int i=0;i<loop;i++)
		{
			for (int j=0;j<game.BC.getRow();j++)
			{
				if(game.BC.getBit(j,i)=='0')
					game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
				else if (game.BC.getBit(j,i)=='1')
					game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
			}
		}
		//drawing bits post loop bits excluding loop, drawing columns
		for (int i=loop+1;i<=max;i++)
		{
			for (int j=0;j<game.BC.getRow();j++)
			{
				if(game.BC.getBit(j,i)=='0')
					game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
				else if (game.BC.getBit(j,i)=='1')
					game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
			}
		}

		for(int i=0;i<game.BC.getRow();i++)
		{
			switch(game.BC.getBitRow(i).substring(loop-1, loop+1))
			{
			case "00":
				if (x> (loop-1)*game.getBitSize())
				{
				game.batch.draw(game.tbitI0,x,i*game.getBitSize());
				setAnimationInProcess(true);
				}
				else
				{
				game.batch.draw(game.tbitI0,(loop-1)*game.getBitSize(),i*game.getBitSize());
				game.BC.setBit("2", i, loop);
				}
				break;
			case "01":
				game.batch.draw(game.tbitI1,(loop)*game.getBitSize(),i*game.getBitSize());
				break;
			case "10":
				game.batch.draw(game.tbitI0,(loop)*game.getBitSize(),i*game.getBitSize());
				break;
			case "02": case "12": case "22":
				break;
			case "20": 
				if (x> (loop-1)*game.getBitSize())
				{
				game.batch.draw(game.tbitI0,x,i*game.getBitSize());
				setAnimationInProcess(true);
				}
				else
				{
				game.batch.draw(game.tbitI0,(loop-1)*game.getBitSize(),i*game.getBitSize());
				game.BC.setBit("0", i, loop-1);
				game.BC.setBit("2", i, loop);
				}
				break;
			case "21":
				if (x> (loop-1)*game.getBitSize())
				{
				game.batch.draw(game.tbitI1,x,i*game.getBitSize());
				setAnimationInProcess(true);
				}
				else
				{
				game.batch.draw(game.tbitI1,(loop-1)*game.getBitSize(),i*game.getBitSize());
				game.BC.setBit("1", i, loop-1);
				game.BC.setBit("2", i, loop);
				}
				break;
			case "11":
				if (x> (loop-1)*game.getBitSize())
				{
				game.batch.draw(game.tbitI0,(loop)*game.getBitSize(),i*game.getBitSize());
				game.batch.draw(game.tbitI1,x,i*game.getBitSize());
				setAnimationInProcess(true);
				}
				else
				{
				game.batch.draw(game.tbitI1,(loop-1)*game.getBitSize(),i*game.getBitSize());
				game.batch.draw(game.tbitI0,(loop)*game.getBitSize(),i*game.getBitSize());
				game.BC.setBit("0", i, loop);
				}
				break;
			}
		}
		x=x-delta*game.getAnimaSpeed();
		game.batch.end();
		}
		else
		{
			//drawing all bits once again to fix flicker of screen
			// As nothing was being drawn in this loop
			game.batch.begin();
			for (int i=0;i<game.BC.getColumn();i++)
			{
				for (int j=0;j<game.BC.getRow();j++)
				{
					//if(game.BC.getBitRow(j).charAt(i)=='0')
					if(game.BC.getBit(j,i)=='0')
						game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
					//else if (game.BC.getBitRow(j).charAt(i)=='1')
					else if (game.BC.getBit(j,i)=='1')
						game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
				}
			}
			game.batch.end();
			if (loop<max)
				{
				setloop(loop+1);
				setX(loop);
				setAnimationInProcess(true);
				}
			else if (game.BP.isAnimaRequired(game.BC.getBitArray(),game.getGravity().toString()))
			{
				setAnimationInProcess(true);
				setloop(1);
				setX(loop);
			}
			else
			{
				game.setAnima(false);
				game.setGravity(Gravity.ZERO);
				game.setScreen(game.INS);
			}
				
		}
	}
		//write code for RIGHT case game.getGravity()= Gravity.RIGHT
		if(game.getGravity()== Gravity.RIGHT)
		{
			//System.out.println("right animation");
			if(getAnimationInProcess())
			{
			setAnimationInProcess(false);
			game.batch.begin();
			//drawing bits prior to loop bits including loop
			//drawing columns, not row
			for (int i=loop;i<=max;i++)
			{
				for (int j=0;j<game.BC.getRow();j++)
				{
					if(game.BC.getBit(j,i)=='0')
						game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
					else if (game.BC.getBit(j,i)=='1')
						game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
				}
			}
			//drawing bits post loop bits excluding loop, drawing columns
			for (int i=0;i<loop-1;i++)
			{
				for (int j=0;j<game.BC.getRow();j++)
				{
					if(game.BC.getBit(j,i)=='0')
						game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
					else if (game.BC.getBit(j,i)=='1')
						game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
				}
			}
			for(int i=0;i<game.BC.getRow();i++)
			{
				switch(game.BC.getBitRow(i).substring(loop-1, loop+1))
				{
				case "00":
					if (x < (loop)*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,x,i*game.getBitSize());
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI0,loop*game.getBitSize(),i*game.getBitSize());
					game.BC.setBit("2", i, loop-1);
					}
					break;
				case "01":
					game.batch.draw(game.tbitI0,(loop-1)*game.getBitSize(),i*game.getBitSize());
					break;
				case "10":
					game.batch.draw(game.tbitI1,(loop-1)*game.getBitSize(),i*game.getBitSize());
					break;
				case "20": case "21": case "22":
					break;
				case "02": 
					if (x< loop*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,x,i*game.getBitSize());
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI0,loop*game.getBitSize(),i*game.getBitSize());
					game.BC.setBit("2", i, loop-1);
					game.BC.setBit("0", i, loop);
					}
					break;
				case "12":
					if (x< loop*game.getBitSize())
					{
					game.batch.draw(game.tbitI1,x,i*game.getBitSize());
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI1,loop*game.getBitSize(),i*game.getBitSize());
					game.BC.setBit("2", i, loop-1);
					game.BC.setBit("1", i, loop);
					}
					break;
				case "11":
					if (x< loop*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,(loop-1)*game.getBitSize(),i*game.getBitSize());
					game.batch.draw(game.tbitI1,x,i*game.getBitSize());
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI1,loop*game.getBitSize(),i*game.getBitSize());
					game.batch.draw(game.tbitI0,(loop-1)*game.getBitSize(),i*game.getBitSize());
					game.BC.setBit("0", i, loop-1);
					}
					break;
				}
			}
			x=x+delta*game.getAnimaSpeed();
			game.batch.end();
			}
			else
			{
				//drawing all bits once again to fix flicker of screen
				// As nothing was being drawn in this loop
				game.batch.begin();
				for (int i=0;i<game.BC.getColumn();i++)
				{
					for (int j=0;j<game.BC.getRow();j++)
					{
						//if(game.BC.getBitRow(j).charAt(i)=='0')
						if(game.BC.getBit(j,i)=='0')
							game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
						//else if (game.BC.getBitRow(j).charAt(i)=='1')
						else if (game.BC.getBit(j,i)=='1')
							game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
					}
				}
				game.batch.end();
				
				if (loop>1)
					{
					setloop(loop-1);
					setX(loop-1);
					setAnimationInProcess(true);
					}
				else if (game.BP.isAnimaRequired(game.BC.getBitArray(),game.getGravity().toString()))  //correct here it is getting true always
				{
					setAnimationInProcess(true);
					setloop(max);
					setX(loop-1);
				}
				else
				{
					game.setAnima(false);
					game.setGravity(Gravity.ZERO);
					game.setScreen(game.INS);
				}
					
			}
		}
		
		//write code for DOWN case game.getGravity()= Gravity.DOWN
		// like LEFT, swap x and y coordinates
		if(game.getGravity()== Gravity.DOWN)
		{
			//System.out.println("down animation");
			if(getAnimationInProcess())
			{
			setAnimationInProcess(false);
			game.batch.begin();
			//drawing bits prior to loop bits including loop
			//drawing rows, not column
			for (int i=0;i<loop;i++)
			{
				for (int j=0;j<game.BC.getColumn();j++)
				{
					if(game.BC.getBit(i,j)=='0')
						game.batch.draw(game.tbitI0,j*game.getBitSize(),i*game.getBitSize());
					else if (game.BC.getBit(i,j)=='1')
						game.batch.draw(game.tbitI1,j*game.getBitSize(),i*game.getBitSize());
				}
			}
			//drawing bits post loop bits excluding loop, drawing rows
			for (int i=loop+1;i<=max;i++)
			{
				for (int j=0;j<game.BC.getColumn();j++)
				{
					if(game.BC.getBit(i,j)=='0')
						game.batch.draw(game.tbitI0,j*game.getBitSize(),i*game.getBitSize());
					else if (game.BC.getBit(i,j)=='1')
						game.batch.draw(game.tbitI1,j*game.getBitSize(),i*game.getBitSize());
				}
			}
			for(int i=0;i<game.BC.getColumn();i++)
			{
				switch(game.BC.getBitCol(i,game.getGravity().toString()).substring(loop-1, loop+1))
				{
				case "00":
					if (y> (loop-1)*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop-1)*game.getBitSize());	// i think this line is causing flicker
					game.BC.setBit("2",loop,i);
					}
					break;
				case "01":
					game.batch.draw(game.tbitI1,i*game.getBitSize(),(loop)*game.getBitSize());
					break;
				case "10":
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop)*game.getBitSize());
					break;
				case "02": case "12": case "22":
					break;
				case "20": 
					if (y> (loop-1)*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop-1)*game.getBitSize());
					game.BC.setBit("0", loop-1,i);
					game.BC.setBit("2",loop,i);
					}
					break;
				case "21":
					if (y> (loop-1)*game.getBitSize())
					{
					game.batch.draw(game.tbitI1,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI1,i*game.getBitSize(),(loop-1)*game.getBitSize());
					game.BC.setBit("1",loop-1,i);
					game.BC.setBit("2",loop,i);
					}
					break;
				case "11":
					if (y> (loop-1)*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop)*game.getBitSize());
					game.batch.draw(game.tbitI1,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI1,i*game.getBitSize(),(loop-1)*game.getBitSize());
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop)*game.getBitSize());
					game.BC.setBit("0", loop,i);
					}
					break;
				}
			}
			y=y-delta*game.getAnimaSpeed();
			game.batch.end();
			}
			else
			{
				//drawing all bits once again to fix flicker of screen
				// As nothing was being drawn in this loop
				game.batch.begin();
				for (int i=0;i<game.BC.getColumn();i++)
				{
					for (int j=0;j<game.BC.getRow();j++)
					{
						//if(game.BC.getBitRow(j).charAt(i)=='0')
						if(game.BC.getBit(j,i)=='0')
							game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
						//else if (game.BC.getBitRow(j).charAt(i)=='1')
						else if (game.BC.getBit(j,i)=='1')
							game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
					}
				}
				game.batch.end();
				
				if (loop<max)
					{
					setloop(loop+1);
					setY(loop);
					setAnimationInProcess(true);
					}
				else if (game.BP.isAnimaRequired(game.BC.getBitArray(),game.getGravity().toString()))
				{
					setAnimationInProcess(true);
					setloop(1);
					setY(loop);
				}
				else
				{
					game.setAnima(false);
					game.setGravity(Gravity.ZERO);
					game.setScreen(game.INS);
				}		
			}
		}
		//write code for UP case game.getGravity()= Gravity.UP
		//just like RIGHT swap x and y coordinates
		if(game.getGravity()== Gravity.UP)
		{
			//System.out.println("up animation");
			if(getAnimationInProcess())
			{
			setAnimationInProcess(false);
			game.batch.begin();
			//drawing bits prior to loop bits including loop
			//drawing rows, not column
			for (int i=loop;i<=max;i++)
			{
				for (int j=0;j<game.BC.getColumn();j++)
				{
					if(game.BC.getBit(i,j)=='0')
						game.batch.draw(game.tbitI0,j*game.getBitSize(),i*game.getBitSize());
					else if (game.BC.getBit(i,j)=='1')
						game.batch.draw(game.tbitI1,j*game.getBitSize(),i*game.getBitSize());
				}
			}
			//drawing bits post loop bits excluding loop, drawing rows
			for (int i=0;i<loop-1;i++)
			{
				for (int j=0;j<game.BC.getColumn();j++)
				{
					if(game.BC.getBit(i,j)=='0')
						game.batch.draw(game.tbitI0,j*game.getBitSize(),i*game.getBitSize());
					else if (game.BC.getBit(i,j)=='1')
						game.batch.draw(game.tbitI1,j*game.getBitSize(),i*game.getBitSize());
				}
			}
			for(int i=0;i<game.BC.getColumn();i++)
			{
				switch(game.BC.getBitCol(i,game.getGravity().toString()).substring(loop-1, loop+1))
				{
				case "00":
					if (y < (loop)*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),loop*game.getBitSize());
					game.BC.setBit("2", loop-1, i);
					}
					break;
				case "01":
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop-1)*game.getBitSize());
					break;
				case "10":
					game.batch.draw(game.tbitI1,i*game.getBitSize(),(loop-1)*game.getBitSize());
					break;
				case "20": case "21": case"22":
					break;
				case "02": 
					if (y< loop*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),loop*game.getBitSize());
					game.BC.setBit("2", loop-1, i);
					game.BC.setBit("0", loop, i);
					}
					break;
				case "12":
					if (y< loop*game.getBitSize())
					{
					game.batch.draw(game.tbitI1,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI1,i*game.getBitSize(),loop*game.getBitSize());
					game.BC.setBit("2", loop-1, i);
					game.BC.setBit("1", loop, i);
					}
					break;
				case "11":
					if (y< loop*game.getBitSize())
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop-1)*game.getBitSize());
					game.batch.draw(game.tbitI1,i*game.getBitSize(),y);
					setAnimationInProcess(true);
					}
					else
					{
					game.batch.draw(game.tbitI0,i*game.getBitSize(),(loop-1)*game.getBitSize());
					game.batch.draw(game.tbitI1,i*game.getBitSize(),loop*game.getBitSize());
					game.BC.setBit("0", loop-1, i);
					}
					break;
				}
			}
			y=y+delta*game.getAnimaSpeed();
			game.batch.end();
			}
			else
			{
				//drawing all bits once again to fix flicker of screen
				// As nothing was being drawn in this loop
				game.batch.begin();
				for (int i=0;i<game.BC.getColumn();i++)
				{
					for (int j=0;j<game.BC.getRow();j++)
					{
						//if(game.BC.getBitRow(j).charAt(i)=='0')
						if(game.BC.getBit(j,i)=='0')
							game.batch.draw(game.tbitI0,i*game.getBitSize(),j*game.getBitSize());
						//else if (game.BC.getBitRow(j).charAt(i)=='1')
						else if (game.BC.getBit(j,i)=='1')
							game.batch.draw(game.tbitI1,i*game.getBitSize(),j*game.getBitSize());
					}
				}
				game.batch.end();
				
				if (loop>1)
					{
					setloop(loop-1);
					setY(loop-1);
					setAnimationInProcess(true);
					}
				else if (game.BP.isAnimaRequired(game.BC.getBitArray(),game.getGravity().toString()))  //correct here it is getting true always
				{
					setAnimationInProcess(true);
					setloop(max);
					setY(loop-1);
				}
				else
				{
					game.setAnima(false);
					game.setGravity(Gravity.ZERO);
					game.setScreen(game.INS);
				}		
			}
		}
		
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
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
