package com.gameScreens.games;

public class BitContainer {

	private static String [] BitA;
	private static int row;			//rows
	private static int col;			//columns
	//private Texture tbitI0;
	//private Texture tbitI1;
	//private Texture tblank;
	
	BitContainer(int stage)
	{
		//loading texture : Loading in main game class now as it is reused by 2 screens
		//tbitI0=new Texture(Gdx.files.internal("bit0.png"));	
		//tbitI1=new Texture(Gdx.files.internal("bit1.png"));
		//tblank=new Texture(Gdx.files.internal("blank.png"));
		
		//call stage
		Stage(stage);
	}
	
	private void Stage(int stageNo)
	{
		switch (stageNo)
		{
		case 44:
			row=4;
			col=4;
			//BitA=new String[row];
			BitA=new String[]{"1011","1100","1011","1111"};
			break;
		case 55:
			row=5;
			col=5;
			//BitA=new String[row];
			BitA=new String[]{"10110","10100","10101","10111","11000"};
			break;
		}
			
	}
	
	public String[] getBitArray()
	{
		return BitA;
	}
	public void setBitArray(String[] b)
	{
		BitA=b;
	}
	public int getRow()
	{
		return row;
	}
	public int getColumn()
	{
		return col;
	}
	
	public int getBitCount()
	{
		int count=0;
		for (int i=0; i <row;i++)		//row i
		{	
			for (int j=0;j < col;j++)		//column j
			{
			if (BitA[i].charAt(j)=='0' || BitA[i].charAt(j)=='1')
				count+=1;
			}
		}
		return count;
	}

	public void setBitRow(int index, String seq)
	{
		BitA[index]=seq;
	}
	/*public Texture getBitTexture(char c)
	{
		if (c=='0')
		return tbitI0;
		else if (c=='1')
		return tbitI1;
		else
		return null;
	}*/
}
