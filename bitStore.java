package com.kingAm.games;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.kingAm.games.bitStore.bitType;
//import com.badlogic.gdx.math.Vector2;

public class bitStore {
	
	// Enumeration for bits
	public enum bitType{
		bit0,bit1,nobit
	}
	
	// Texture instances
	private Texture bitI0;
	private Texture bitI1;
	private Texture blank;
	
	// Map for bit position as key and bit type as value
	private static Map<String, bitType> bitM;
	
	
	// constructor to initialize bits in game
	bitStore()
	{	
		//loading texture
		bitI0=new Texture(Gdx.files.internal("bit0.png"));
		bitI1=new Texture(Gdx.files.internal("bit1.png"));
		blank=new Texture(Gdx.files.internal("blank.png"));
		//creating new hash map
		bitM=new HashMap<String, bitType>();
		//creating map, set bits at start of game
		createbitMap();
	}
	
	// getter method to get bit type at any position in map
	public bitType getbitType(int r, int c)
	{
		//forming key
		String key=Integer.toString(r)+Integer.toString(c);
		return bitM.get(key);
	}
	
	//getter method to get Map
	public Map<String, bitType> getbitMap()
	{
		return bitM;
	}
	// setter method to set bit type at any position
	public void setbitType(int r, int c, bitType t)
	{
		String key=Integer.toString(r)+Integer.toString(c);
		bitM.put(key, t);
	}
	
	//update method to update map for bits
	/*public void updatebitMap(int r, int c)
	{
		// forming key
		String key=Integer.toString(r)+Integer.toString(c);
		bitStore.bitM.put( key,getbitType(r,c));
		//printbitMap();
	}*/
	
	public Texture getbits(int r, int c)
	{
		// generating key
		String key=Integer.toString(r)+Integer.toString(c);
		//returning bit0, bit1 or blank based on type stored in bitM (Map) corresponding to key
		if (bitM.get(key)==bitType.bit0)
		return bitI0;
		else if (bitM.get(key)==bitType.bit1)
		return bitI1;
		else
		return blank;
		
		
		
	}
	
	public void printbitMap()
	{
		System.out.println(bitM);
	}
	
	
	public void createbitMap()
	{
		bitM.put("00", bitType.bit0);
		bitM.put("01", bitType.bit1);
		bitM.put("02", bitType.bit1);
		bitM.put("03", bitType.bit0);
		bitM.put("10", bitType.bit1);
		bitM.put("11", bitType.bit1);
		bitM.put("12", bitType.bit0);
		bitM.put("13", bitType.bit0);
		bitM.put("20", bitType.bit0);
		bitM.put("21", bitType.bit1);
		bitM.put("22", bitType.bit1);
		bitM.put("23", bitType.bit0);
		bitM.put("30", bitType.bit1);
		bitM.put("31", bitType.bit1);
		bitM.put("32", bitType.bit0);
		bitM.put("33", bitType.bit1);	
	}
	
	/*public Texture randombits(int x, int y)
	{
		//bitI0 = new Texture(Gdx.files.external("Desktop/bit0.png"));
		//bitI1=new Texture(Gdx.files.external("Desktop/bit1.png"));
		bitI0 = new Texture(Gdx.files.internal("bit0.png"));
		bitI1=new Texture(Gdx.files.internal("bit1.png"));
		blank=new Texture(Gdx.files.internal("blank.png"));
		
		if ((x+y)==0 ||(x+y)==1||(x+y)==5||(x+y)==6)
		return bitI0;
		else if ((x+y)==2 ||(x+y)==3||(x+y)==4)
		return bitI1;
		else
		return blank;
	}*/
	
	public void bitcalculate()
	{
		
		
	}
	

}
