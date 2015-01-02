package com.Gbits.Screens;
/*
 *Ambuj Mishra
 *1-1-2015 
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameScreens.games.BitContainer;

public class GbitsGame extends Game{
	
	//Gravity Enum		AM: Think about ZERO, handle it appropriately
	public enum Gravity
	{
		ZERO,
		UP,
		DOWN,
		RIGHT,
		LEFT;
	}

	//Animation flag, Responsible for calling Animation screen
	private boolean animate=false;
	//Screens, Main Menu, Input Handler, Animation, Game over respectively
	MainMenuScreen MMS;
	InputScreen INS;
	AnimaScreen ANS;
	GameOverScreen GOS;

	BitContainer BC;
	BitProcessing BP;
	
	private Gravity g;
	public SpriteBatch batch;
    public BitmapFont font;
	public Texture tbitI0;
	public Texture tbitI1;
	public FPSLogger fpslog;
	public OrthographicCamera camera;
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
    public void create() {
    	camera = new OrthographicCamera();
    	MMS =new MainMenuScreen(this);
    	INS=new InputScreen(this);
    	ANS =new AnimaScreen(this);
    	GOS= new GameOverScreen(this);
    	//initializing gravity with ZERO
    	setGravity(Gravity.ZERO);
    	//initializing bit processing class
    	BP=new BitProcessing();
    	BC=new BitContainer();
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
        MMS.dispose();
        INS.dispose();
        ANS.dispose();
        GOS.dispose();
		Gdx.app.exit();
    }
}
