package com.Gbits.Solver;
/*
 *Ambuj Mishra
 *23-02-2015 
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.Gbits.Solver.BitContainer;


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
	LooserGameOverScreen LGOS;
	
	BitContainer BC;
	BitProcessing BP;
	
	private Gravity g=Gravity.ZERO;
	private Gravity pg=Gravity.ZERO;		//previous gravity
	public SpriteBatch batch;
    public BitmapFont font;
	public Texture tbitI0;
	public Texture tbitI1;
	public Texture tstage;
	public FPSLogger fpslog;
	public OrthographicCamera camera;
	//bitsize of each bit, it will be used in cameras (in pixel)
	//public int bitSize=256;
	//public int bitSize=64;
	public int bitSize=32;
	//offset space between bits (in pixel)
	public int bitOffset=5;
	private final int animaspeed=400;
	 public int getAnimaSpeed()
	 {
		 return animaspeed;
	 }
	
	public int getOffset()
	{
		return bitOffset;
	}
	public int getBitSize()
	{
		return bitSize;
	}
	public void setGravity(Gravity g)
	{
		if(g== Gravity.ZERO)	// This will ignore the zero gravity which is being set after completion of each animation.
			this.pg=getGravity();
			//this.pg=g;
			
		
		this.g=g;
	}
	
	public Gravity getGravity()
	{
		return g;
	}
	//11 JAN 2015	getting gravity previously set
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
    	LGOS=new LooserGameOverScreen(this);
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
      	//tbitI0=new Texture(Gdx.files.internal("bit0.png"));	
      	//tbitI1=new Texture(Gdx.files.internal("bit1.png"));
      	//tbitI0=new Texture(Gdx.files.internal("LED0.jpg"));	
      	//tbitI1=new Texture(Gdx.files.internal("LED1.jpg"));
      	tbitI0=new Texture(Gdx.files.internal("grey0.png"));	
      	tbitI1=new Texture(Gdx.files.internal("grey1.png"));
      	tstage= new Texture(Gdx.files.internal("stage1.png"));
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
