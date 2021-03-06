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
	
	// getter method for bit equivalent bit0=0, bit1=1, blank=2
	public String getbitTypeEquivalentString(bitType [] t)
	{
		
		String seq="";
		for (int i=0;i<t.length;i++)
		{
		if(t[i]==bitStore.bitType.bit0)
		seq=seq+0;
		else if (t[i]==bitStore.bitType.bit1)
		seq=seq+1;
		else 
		seq=seq+2;
		}
		return seq;
	}
	
	// reverse method for getbitTypeEquivalentString
	public bitType [] getEquivalentbitTypefromString(String seq)
	{
		bitType b[]=new bitType[seq.length()];
		for (int i=0;i<seq.length();i++)
		{
			if (seq.charAt(i)=='0')
			b[i]=bitStore.bitType.bit0;
			else if (seq.charAt(i)=='1')
			b[i]=bitStore.bitType.bit1;
			else
			b[i]=bitStore.bitType.nobit;
		}
		return b;
		
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
		bitM.put("04", bitType.bit0);
		bitM.put("05", bitType.bit1);
		bitM.put("06", bitType.bit0);
		bitM.put("07", bitType.bit0);
		bitM.put("10", bitType.bit1);
		bitM.put("11", bitType.bit1);
		bitM.put("12", bitType.bit0);
		bitM.put("13", bitType.bit0);
		bitM.put("14", bitType.bit0);
		bitM.put("15", bitType.bit1);
		bitM.put("16", bitType.bit1);
		bitM.put("17", bitType.bit0);
		bitM.put("20", bitType.bit0);
		bitM.put("21", bitType.bit1);
		bitM.put("22", bitType.bit1);
		bitM.put("23", bitType.bit0);
		bitM.put("24", bitType.bit0);
		bitM.put("25", bitType.bit0);
		bitM.put("26", bitType.bit1);
		bitM.put("27", bitType.bit0);
		bitM.put("30", bitType.bit1);
		bitM.put("31", bitType.bit1);
		bitM.put("32", bitType.bit0);
		bitM.put("33", bitType.bit1);
		bitM.put("34", bitType.bit0);
		bitM.put("35", bitType.bit1);
		bitM.put("36", bitType.bit1);
		bitM.put("37", bitType.bit1);
		bitM.put("40", bitType.bit1);
		bitM.put("41", bitType.bit1);
		bitM.put("42", bitType.bit0);
		bitM.put("43", bitType.bit1);
		bitM.put("44", bitType.bit0);
		bitM.put("45", bitType.bit1);
		bitM.put("46", bitType.bit0);
		bitM.put("47", bitType.bit0);
		bitM.put("50", bitType.bit1);
		bitM.put("51", bitType.bit1);
		bitM.put("52", bitType.bit1);
		bitM.put("53", bitType.bit1);
		bitM.put("54", bitType.bit0);
		bitM.put("55", bitType.bit1);
		bitM.put("56", bitType.bit0);
		bitM.put("57", bitType.bit0);
		bitM.put("60", bitType.bit1);
		bitM.put("61", bitType.bit1);
		bitM.put("62", bitType.bit0);
		bitM.put("63", bitType.bit1);
		bitM.put("64", bitType.bit0);
		bitM.put("65", bitType.bit1);
		bitM.put("66", bitType.bit0);
		bitM.put("67", bitType.bit0);
		bitM.put("70", bitType.bit1);
		bitM.put("71", bitType.bit0);
		bitM.put("72", bitType.bit1);
		bitM.put("73", bitType.bit1);
		bitM.put("74", bitType.bit0);
		bitM.put("75", bitType.bit1);
		bitM.put("76", bitType.bit0);
		bitM.put("77", bitType.bit1);	
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
