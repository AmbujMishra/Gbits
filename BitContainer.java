package com.Gbits.Screens;

public class BitContainer {

	private static String [] BitA;
	private static int row;			//rows
	private static int col;			//columns
	
	BitContainer(int stage)
	{
		//call stage
		Stage(stage);
	}
	
	public void Stage(int stageNo)
	{
		switch (stageNo)
		{
		case 44:
			row=4;
			col=4;
			BitA=new String[]{"1101","1000","1011","1111"};
			break;
		case 55:
			row=5;
			col=5;
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
	
	public String getBitRow(int row)
	{
		return BitA[row];
	}
	
	public String getBitCol(int col, String g)
	{
		String bitC="";
		for (int i=0;i<row;i++)
		{
			bitC=bitC+BitA[i].charAt(col);
		}
		return bitC;
	}
	
	public char getBit(int row, int col)
	{
		return BitA[row].charAt(col);
	}
	public void setBit(String b, int row, int col)
	{
		StringBuilder sb=new StringBuilder(BitA[row]);
		sb.replace(col, col+1,b);
		BitA[row]=sb.toString();
	}
}
