package com.gameScreens.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GbitsGame extends Game{
	
	public enum Gravity
	{
		ZERO,
		UP,
		DOWN,
		RIGHT,
		LEFT;
	}
	private Gravity g;
	public boolean animate=false;
	//https://code.google.com/p/libgdx-users/wiki/ScreenAndGameClasses
	MainMenuScreen MMS;
	InputScreen INS;
	AnimaScreen ANS;
	GameOverScreen GOS;
	FastAnimaScreen FAS;
	BitContainer BC;
	BitProcessing BP;
	public SpriteBatch batch;
    public BitmapFont font;
	public Texture tbitI0;
	public Texture tbitI1;
	public FPSLogger fpslog;
    public void create() {
    	MMS =new MainMenuScreen(this);
    	INS=new InputScreen(this);
    	ANS =new AnimaScreen(this);
    	GOS= new GameOverScreen(this);
    	FAS=new FastAnimaScreen(this);
    	//initializing gravity with ZERO
    	setGravity(Gravity.ZERO);
    	BP=new BitProcessing();
    	batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        //loading texture
      	tbitI0=new Texture(Gdx.files.internal("bit0.png"));	
      	tbitI1=new Texture(Gdx.files.internal("bit1.png"));
      	fpslog=new FPSLogger();
        setScreen(MMS);
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        tbitI0.dispose();
        tbitI1.dispose();
		Gdx.app.exit();
    }
	
	public void setGravity(Gravity g)
	{
		this.g=g;
	}
	
	public Gravity getGravity()
	{
		return g;
	}
	public void setAnima(boolean a)
	{
		animate=a;
	}
	public boolean getAnima()
	{
		return animate;
	}
}
