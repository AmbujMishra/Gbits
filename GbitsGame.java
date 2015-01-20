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
import com.Gbits.Screens.BitContainer;

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
	private boolean animate;
	//Screens, Main Menu, Input Handler, Animation, Game over respectively
	MainMenuScreen MMS;
	InputScreen INS;
	AnimaScreen ANS;
	GameOverScreen GOS;
	StageScreen STS;

	BitContainer BC;
	BitProcessing BP;
	
	private Gravity g=Gravity.ZERO;
	private Gravity pg;		//previous gravity
	public SpriteBatch batch;
    public BitmapFont font;
	public Texture tbitI0;
	public Texture tbitI1;
	public Texture tstage;
	public FPSLogger fpslog;
	public OrthographicCamera camera;
	
	//bitsize in pixel,20/1/2015
	public int bitSize=32;
	//public int bitSize=64;
	
	public int getBitSize()
	{
		return bitSize;
	}
	public void setGravity(Gravity g)
	{
		this.pg=getGravity();
		this.g=g;
	}
	
	public Gravity getGravity()
	{
		return g;
	}
	//11 JAN 2015	getting garivity previously set
	public Gravity getPreviousGravity()
	{
		return pg;
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
    	STS=new StageScreen(this);
    	MMS =new MainMenuScreen(this);
    	INS=new InputScreen(this);
    	ANS =new AnimaScreen(this);
    	GOS= new GameOverScreen(this);
    	//initializing gravity with ZERO
    	setGravity(Gravity.ZERO);
    	setAnima(false);
    	//initializing bit processing class
    	BP=new BitProcessing();
    	BC=new BitContainer();
    	batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        //loading texture
      	//tbitI0=new Texture(Gdx.files.internal("bit0.jpg"));	
      	//tbitI1=new Texture(Gdx.files.internal("bit1.jpg"));
      	
      	tbitI0=new Texture(Gdx.files.internal("grey0.png"));	
      	tbitI1=new Texture(Gdx.files.internal("grey1.png"));
      	tstage= new Texture(Gdx.files.internal("stage1.jpg"));
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
        tstage.dispose();
        MMS.dispose();
        INS.dispose();
        ANS.dispose();
        GOS.dispose();
        STS.dispose();
		Gdx.app.exit();
    }
}
