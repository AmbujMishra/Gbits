package com.Gbits.Screens;
/*
 *Ambuj Mishra
 *1-1-2015 
 */
public class BitContainer {

	private static String [] BitA;
	private static int row;			//rows
	private static int col;			//columns
	
	public void Stage(int stageNo)
	{
		
		row=stageNo/10;
		col=stageNo%10;
		
		BitA=new String[row];
		
		String rowS="1";
		for (int i=1;i<col;i++)
			rowS=rowS+"1";
		for (int i=0;i<row;i++)
		{
			BitA[i]=rowS;
		}
		
		/*switch (stageNo)
		{
		case 11:
			row=1;
			col=1;
			BitA=new String[]{"1"};
			break;
		case 12:
			row=1;
			col=2;
			BitA=new String[]{"11"};
			break;
		case 13:
			row=1;
			col=3;
			BitA=new String[]{"111"};
			break;
		case 14:
			row=1;
			col=4;
			BitA=new String[]{"1111"};
			break;
		case 15:
			row=1;
			col=4;
			BitA=new String[]{"11111"};
			break;
		case 21:
			row=2;
			col=1;
			BitA=new String[]{"1","1"};
			break;
		case 22:
			row=2;
			col=2;
			BitA=new String[]{"11","11"};
			break;
		case 23:
			row=2;
			col=3;
			BitA=new String[]{"111","111"};
			break;
		case 24:
			row=2;
			col=4;
			BitA=new String[]{"1111","1111"};
			break;
		case 25:
			row=2;
			col=5;
			BitA=new String[]{"11111","11111"};
			break;
		case 31:
			row=3;
			col=1;
			BitA=new String[]{"1","1","1"};
			break;
		case 32:
			row=3;
			col=2;
			BitA=new String[]{"11","11","11"};
			break;
		case 33:
			row=3;
			col=3;
			BitA=new String[]{"111","111","111"};
			break;
		case 44:
			row=4;
			col=4;
			BitA=new String[]{"1101","1000","1011","1111"};
			break;
		case 43:
			row=4;
			col=3;
			BitA=new String[]{"110","100","101","111"};
			break;
		case 55:
			row=5;
			col=5;
			BitA=new String[]{"10110","10100","10101","10111","11000"};
			break;
		}*/
			
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
