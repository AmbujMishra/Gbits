package com.Gbits.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.Gbits.Screens.GbitsGame.Gravity;

public class InputScreenForTilt implements Screen{

	GbitsGame game;
	private int previoust=0;
	private int tilt=0;	// initial tilting i.e. 0  1=right, 2=left, 3=up, 4=down

	public InputScreenForTilt(GbitsGame gg) {
		game=gg;
	}

	@Override
	public void show() {
		game.camera.setToOrtho(false,64*game.BC.getColumn(), 64*game.BC.getRow());
	    game.camera.update();
		
	    if (game.BC.getBitCount()==2)
			game.setScreen(game.GOS);
		

    Gdx.graphics.setContinuousRendering(false);
    Gdx.graphics.requestRendering();
	}

	@Override
	public void render(float delta) {
		System.out.println("input screen");
		Gdx.gl.glClearColor(1,1,1, 1);
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
			game.setScreen(game.ANS);
		}
		handleTilt();
		game.fpslog.log();
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
	// no tilt change =no animation screen
	if (previoust==tilt)
		handleTilt();
	else
	{
		previoust=tilt;	
		//set Gravity and animation flag
		switch (tilt)
		{
		case 0:
			game.setGravity(Gravity.ZERO);
			game.setAnima(true);
			break;
		case 1:
			game.setGravity(Gravity.RIGHT);
			game.setAnima(true);
			break;
		case 2:
			game.setGravity(Gravity.LEFT);
			game.setAnima(true);
			break;
		case 3:
			game.setGravity(Gravity.UP);
			game.setAnima(true);
			break;
		case 4:
			game.setGravity(Gravity.DOWN);
			game.setAnima(true);
			break;
		}
		Gdx.graphics.requestRendering();
	}
	}
}
