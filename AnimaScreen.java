package com.gameScreens.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.gameScreens.games.GbitsGame.Gravity;
import com.gameScreens.games.GbitsGame.Gravity;

public class AnimaScreen implements Screen{

	GbitsGame game;
	private OrthographicCamera camera;
	//String before[];
	//String after[];
	//String[] change=new String[game.BC.getRow()];
	private float x;
	int max;
	private int loop=0;
	private boolean animationInProcess=false;
	//private boolean loopIncrement=false;
	public AnimaScreen(GbitsGame gg) {
		game=gg;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,64*game.BC.getRow(), 64*game.BC.getColumn());
	    camera.update();
	}
	/*private void setloopIncrement(boolean l)
	{
		loopIncrement=l;
	}*/
	/*private boolean getloopIncrement()
	{
		return loopIncrement;
	}*/
	/*private int getloop()
	{
		return loop;
	}*/
	private void setloop(int l)
	{
		loop=l;
		setX();
	}
	/*private void incrementloop()
	{
		loop=loop+1;
	}*/
	private void setX()
	{
		this.x=(loop+1)*64;
	}

	/*private float getX()
	{
		return x;
	}*/
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
		Gdx.input.setInputProcessor(null);
		Gdx.graphics.setContinuousRendering(true);
		//before=game.BC.getBitArray();
		//it will process single step
		//StepProcessing();
		//FastAnimaScreen FAS=new FastAnimaScreen();
		System.out.println("animationshow");
		max=game.BC.getColumn()-1;
		//int i=0;
		/*while(i<game.BC.getRow() && !animate)
		{
		if (game.BP.isAnimaRequired(game.BC.getBitRow(i)))
		animate=true;
		else		
		i=i+1;
		}*/
		//if(!animate)
		//	game.setScreen(game.INS);
		//else
		//{
		if(game.BP.isAnimaRequired(game.BC.getBitArray()))
		{
			setloop(0);
			setAnimationInProcess(true);
		}
		else
		{
			System.out.println("not required");
			game.setAnima(false);
			game.setGravity(Gravity.ZERO);
			game.setScreen(game.INS);
		}

		//Gdx.graphics.setContinuousRendering(true);
		//Gdx.graphics.requestRendering();
		//}
	}

	/*private void StepProcessing() {
		
		//split in columns and proceeed dear :P 
		//String [] after=game.BC.getBitArray();
		after=before;
		switch(game.getGravity())
		{
		case LEFT:
			
			for (int i=0;i<game.BC.getRow();i++)
			{
				//first check the normalized version of row then call this step.
				if (!game.BP.checkSeq(after[i], game.BP.countBlanks(after[i])))
				{
					after[i]=game.BP.singleStepProcess(after[i]);
					animate=true;
				}
				else 
					animate=false;
			}
			
			if (!animate)
			{
				//update bit container
				//return to Input screen
				game.BC.setBitArray(after);
				game.setScreen(game.INS);
			}
			else	//call render()  for animation and set before=after
			{
				//get animation bit location
				change=getAnimationLocation(before,after);
			//	render(0); dnt call render, let it be called itself;
			//	before=after; //call it into render after animation
			}
			break;
		case RIGHT:
		case UP:
		case DOWN:
		default:
		}
		
	}*/
	/*private String[] getAnimationLocation(String[] before, String[] after)
	{
		for(int i=0;i<change.length;i++)
		{
			for (int j=0;j<before.length();j++)
			if (before[i].charAt[j]!=after[i].charAt[j])		//its wrong, 2 bits will be changed
			change[i]=j;
		}
		return change;
	}*/
	@Override
	public void render(float delta) {
		//setAnimationInProcess(false);
		System.out.println("animation screen");
		//System.out.println("animationrender");
		//System.out.println(game.getAnima());
		//System.out.println(getAnimationInProcess());
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(camera.combined);
		camera.update();
/*		for(int i=0;i<game.BC.getRow();i++)
		{
			if (game.BC.getBitRow(i).substring(loop, loop+2)=="22")
				
		}*/

		if(getAnimationInProcess() /*&& loop<max-2 && !getloopIncrement()*/)
		{
			//Gdx.gl.glClearColor(1, 1, 1, 1);
			//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		setAnimationInProcess(false);
		//setloopIncrement(true);
		game.batch.begin();
		//drawing bits prior to loop bits including loop
		//drawing columns, not row
		for (int i=0;i<=loop;i++)
		{
			for (int j=0;j<game.BC.getRow();j++)
			{
				//if(game.BC.getBitRow(j).charAt(i)=='0')
				if(game.BC.getBit(j,i)=='0')
					game.batch.draw(game.tbitI0,i*64,j*64);
				//else if (game.BC.getBitRow(j).charAt(i)=='1')
				else if (game.BC.getBit(j,i)=='1')
					game.batch.draw(game.tbitI1,i*64,j*64);
			}
		}
		//drawing bits post loop bits excluding loop, drawing columns
		for (int i=loop+2;i<=max;i++)
		{
			for (int j=0;j<game.BC.getRow();j++)
			{
				if(game.BC.getBit(j,i)=='0')
					game.batch.draw(game.tbitI0,i*64,j*64);
				else if (game.BC.getBit(j,i)=='1')
					game.batch.draw(game.tbitI1,i*64,j*64);
			}
		}
			//setX(x+((loop+1)*64));
		for(int i=0;i<game.BC.getRow();i++)
		{
			switch(game.BC.getBitRow(i).substring(loop, loop+2))
			{
			case "00":
				if (x> loop*64)
				{
				game.batch.draw(game.tbitI0,x,i*64);
				//x=x-(Gdx.graphics.getDeltaTime()*20);
				setAnimationInProcess(true);
				//setloopIncrement(false);
				}
				else
				{
				game.batch.draw(game.tbitI0,loop*64,i*64);
				game.BC.setBit("2", i, loop+1);
				//setAnimationInProcess(false);
				//setloopIncrement(true);
				}
				break;
			case "01":
				game.batch.draw(game.tbitI1,(loop+1)*64,i*64);
				break;
			case "10":
				game.batch.draw(game.tbitI0,(loop+1)*64,i*64);
				break;
			case "02": case "12":
				//setAnimationInProcess(false);
				break;
			case "20": 
				if (x> loop*64)
				{
				game.batch.draw(game.tbitI0,x,i*64);
				//x=x-(Gdx.graphics.getDeltaTime()*20);
				setAnimationInProcess(true);
				//setloopIncrement(false);
				}
				else
				{
				game.batch.draw(game.tbitI0,loop*64,i*64);
				game.BC.setBit("0", i, loop);
				game.BC.setBit("2", i, loop+1);
				//setAnimationInProcess(false);
				//setloopIncrement(true);
				}
				break;
			case "21":
				if (x> loop*64)
				{
				game.batch.draw(game.tbitI1,x,i*64);
				//x=x-(Gdx.graphics.getDeltaTime()*20);
				setAnimationInProcess(true);
				//setloopIncrement(false);
				}
				else
				{
				game.batch.draw(game.tbitI1,loop*64,i*64);
				game.BC.setBit("1", i, loop);
				game.BC.setBit("2", i, loop+1);
				//setAnimationInProcess(false);
				//setloopIncrement(true);
				}
				break;
			case "11":
				if (x> loop*64)
				{
				game.batch.draw(game.tbitI0,(loop+1)*64,i*64);
				game.batch.draw(game.tbitI1,x,i*64);
				//x=x-(Gdx.graphics.getDeltaTime()*20);
				setAnimationInProcess(true);
				//setloopIncrement(false);
				}
				else
				{
				game.batch.draw(game.tbitI1,loop*64,i*64);
				game.batch.draw(game.tbitI0,(loop+1)*64,i*64);
				game.BC.setBit("0", i, loop+1);
				//setAnimationInProcess(false);
				//setloopIncrement(true);
				}
				//game.batch.draw(game.tbitI1,(loop+1)*64,i*64);
				break;
			case "22":
				//setAnimationInProcess(false);
				//it will never come now as handling in Fast animation screen.
				System.out.println("FTW");
				break; 
				//game.BC.findNextBitCluster();
				//find next bit cluster if any and fast animate the falling process
			}
		}
		//x=x-(Gdx.graphics.getDeltaTime()*200);
		x=x-delta*200;
		game.batch.end();
		}
		else
		{
			if (loop<max-1)
				{
				setloop(loop+1);
				setAnimationInProcess(true);
				//animate=false;
				}
			else if (game.BP.isAnimaRequired(game.BC.getBitArray()))
			{
				//System.out.println(max);
				//for(String ss:game.BC.getBitArray())
				//System.out.println(ss);
				setAnimationInProcess(true);
				setloop(0);
			}
			else
			{
				game.setAnima(false);
				game.setGravity(Gravity.ZERO);
				game.setScreen(game.INS);
			}
				
		}
		game.fpslog.log();
	}
		//game.setScreen(game.INS); 	
/*		game.batch.begin();
		x=x+((loop+1)*64);
		
		if (loop<max)
		{
			
			//drawing bits prior to loop bits excluding loop
			for (int i=0;i<=loop;i++)
			{
				for (int j=0;j<game.BC.getRow();j++)
				{
					if(after[j].charAt(i)=='0')
						game.batch.draw(game.tbitI0,i*64,j*64);
					else if (after[j].charAt(i)=='1')
						game.batch.draw(game.tbitI1,i*64,j*64);
				}
			}
			//drawing bits post loop bits excluding loop
			for (int i=loop+2;i<max;i++)
			{
				for (int j=0;j<game.BC.getRow();j++)
				{
					if(after[j].charAt(i)=='0')
						game.batch.draw(game.tbitI0,i*64,j*64);
					else if (after[j].charAt(i)=='1')
						game.batch.draw(game.tbitI1,i*64,j*64);
				}
			}
			for(int i=0;i<game.BC.getRow();i++)
			{
				switch(after[i].substring(loop, loop+2))
				{
				case "00":
					if (x>= loop*64)
					{
					game.batch.draw(game.tbitI0,x,i*64);
					x=x-(Gdx.graphics.getDeltaTime()*20);
					}
					else
					game.BC.setBit("2", i, loop+1);	
					break;
				case "20": 
					if (x>= loop*64)
					{
					game.batch.draw(game.tbitI0,x,i*64);
					x=x-(Gdx.graphics.getDeltaTime()*20);
					}
					else
					{
					game.BC.setBit("2", i, loop+1);	
					game.BC.setBit("0", i, loop);
					}
					break;
				case"21": 
				if(after[i].charAt(loop)=='0')
					game.batch.draw(game.tbitI0,loop*64,i*64);
				if(after[i].charAt(loop+1)=='0')
					game.batch.draw(game.tbitI0,((loop+1)*64)-(Gdx.graphics.getDeltaTime()*20),i*64);
				else if(after[i].charAt(loop+1)=='1')
					game.batch.draw(game.tbitI1,((loop+1)*64)-(Gdx.graphics.getDeltaTime()*20),i*64);
				break;
				case "11":
					x=((loop+1)*64)-(Gdx.graphics.getDeltaTime()*20);
					game.batch.draw(game.tbitI1,loop*64,i*64);
					if(x>loop*64)
					{
					game.batch.draw(game.tbitI1,x,i*64);
					x=x-(Gdx.graphics.getDeltaTime()*20);
					}
					else
					game.batch.draw(game.tbitI0,(loop+1)*64,i*64);
					break;
				default:
					if(after[i].charAt(loop)=='0')
						game.batch.draw(game.tbitI0,loop*64,i*64);
					else if(after[i].charAt(loop)=='1')
						game.batch.draw(game.tbitI1,loop*64,i*64);
					if(after[i].charAt(loop+1)=='0')
						game.batch.draw(game.tbitI0,(loop+1)*64,i*64);
					else if(after[i].charAt(loop+1)=='1')
						game.batch.draw(game.tbitI1,(loop+1)*64,i*64);
					break;
				}
			}
			
		}
		else
		game.setScreen(game.INS); */ //maybe
		
/*		for (int i=0; i < game.BC.getRow();i++)		//row i
		{	
			for (int j=0;j < game.BC.getColumn();j++)		//column j
			//for (char c: bitA[i].)
			{
			if (before[i].charAt(j)=='0')
				game.batch.draw(game.tbitI0,i*64,j*64);
			else if(before[i].charAt(j)=='1')
				game.batch.draw(game.tbitI1,i*64,j*64);
				
			}
		}
		// Now write animation here, record animation somewhere it will be easy
		for (int c=0;c<change.length();c++)
		{
			if (after[c].charAt(change[c])==2 && )
			game.batch.draw(game.tbitI0,(change[c]*64)-(20 * Gdx.graphics.getDeltaTime()),c*64);
		}*/
		//game.batch.end();
		//write complex logic for animation :(	


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
		//Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
