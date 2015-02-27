package com.Gbits.Solver.Advance;

import com.Gbits.Solver.Advance.GbitsGame.Gravity;
/*
 *Ambuj Mishra
 *26-02-2015 
 */

// it will hold the methods of bit container and processor simultaneously ...No/Yes
public class AdvanceBitProcessor {
	
	GbitsGame game;
	
	public AdvanceBitProcessor(GbitsGame gg)
	{
		game=gg;
	}
	
	private String getBitCol(int col, String[] bitA)
	{
		String bitC="";
		int row=bitA.length;
		for (int i=0;i<row;i++)
		{
			bitC=bitC+bitA[i].charAt(col);
		}
		return bitC;
	}
	//26FEB2015 this would apply based on input string array..independent of bit container..good..
	private boolean gameOver(String [] bitA)
	{
		//23 JAN 2015
		//Made it independent of gravity, because gravity is getting set as zero after each animation cycle.
		
		//check for up,if no then check for left and right from up
		//check for left, if no then check for up and down
		//check for right, if no then check for up and down
		//check for down, if no then check for left and right
		/*go for above logic*/
		
		//First write a common method to transpose existing bit container based on gravity.No
		//form a temp bit array
		
		int r=bitA.length;
		int c=bitA[0].length();
		String seq="";
		boolean go=true;
		
		//Apply LEFT and RIGHT both by removing blanks in rows
		for (int i=0;i<r;i++)
		{
			if(game.BP.getNonBlanks(bitA[i])!=0)
			{
			seq=game.BP.removeBlanks(bitA[i]);
			if (!game.BP.checkSeq(seq, 0))
				go=false;
			}
		}
		//Apply UP and DOWN both by removing blanks in columns
		for (int i=0;i<c;i++)
		{
			if(game.BP.getNonBlanks(getBitCol(i, bitA))!=0)
			{
			seq=game.BP.removeBlanks(getBitCol(i, bitA));
			if (!game.BP.checkSeq(seq, 0))
				go=false;
			}
		}
		//Check UP and DOWN after applying LEFT gravity, It can be done simultaneously by removing blanks and checking columns
		//LEFT
		if(go)
		{
		//Forming temp bit array after removing blanks
			String temp[]=new String[r];
			for (int i=0;i<r;i++)
				temp[i]=game.BP.shiftblanks(bitA[i]);   // by default it will shift blanks towards right  24FEB2015
				//temp[i]=game.BP.removeBlanks(game.BC.getBitRow(i));
			for (int i=0;i<c;i++)
			{
				String tempC="";
				for (int j=0;j<r;j++)
				{
					tempC=tempC+temp[j].charAt(i);
				}
				
				if(game.BP.getNonBlanks(tempC)!=0)
				{
				seq=game.BP.removeBlanks(tempC);
				if (!game.BP.checkSeq(seq, 0))
					go=false;
				}
			}
		}
		
		//RIGHT
		if(go)
		{
			//Forming temp bit array after removing blanks
			String temp[]=new String[r];
			for (int i=0;i<r;i++)
				temp[i]=game.BP.shiftblanksLeft(bitA[i]);
			
			for (int i=0;i<c;i++)
			{
				String tempC="";
				for (int j=0;j<r;j++)
				{
					tempC=tempC+temp[j].charAt(i);
				}
				
				if(game.BP.getNonBlanks(tempC)!=0)
				{
				seq=game.BP.removeBlanks(tempC);
				if (!game.BP.checkSeq(seq, 0))
					go=false;
				}
			}
			
		}
		
		//DOWN
		if(go)
		{
			String temp[]=new String[c];
			for (int i=0;i<c;i++)
			{
					temp[i]=game.BP.shiftblanks(getBitCol(i, bitA));
			}
			
			String tempR[]=new String[r];
			for (int i=0;i<r;i++)
			{
				tempR[i]="";
				for(int j=0;j<c;j++)
				tempR[i]=tempR[i]+temp[j].charAt(i);
			}
			//Two for loop above is kind of transposing your bit container from column to row and vice versa. Look out for this
			for (int i=0;i<r;i++)
			{
				if(game.BP.getNonBlanks(tempR[i])!=0)
				{
					seq=game.BP.removeBlanks(tempR[i]);
					if (!game.BP.checkSeq(seq, 0))
						go=false;
				}
			}
			
		}
		
		//UP
		if(go)
		{
			String temp[]=new String[c];
			for (int i=0;i<c;i++)
			{
					temp[i]=game.BP.shiftblanksLeft(getBitCol(i, bitA));
			}
			
			String tempR[]=new String[r];
			for (int i=0;i<r;i++)
			{
				tempR[i]="";
				for(int j=0;j<c;j++)
				tempR[i]=tempR[i]+temp[j].charAt(i);
			}
			//Two for loop above is kind of transposing your bit container from column to row and vice versa. Look out for this
			for (int i=0;i<r;i++)
			{
				if(game.BP.getNonBlanks(tempR[i])!=0)
				{
					seq=game.BP.removeBlanks(tempR[i]);
					if (!game.BP.checkSeq(seq, 0))
						go=false;
				}
			}
		}
		
		return go;
	}
	
	public String nextMove(String [] bitA)
	{
		
		// First find out goD, goU, goL, goR then apply below condition...
		int r=bitA.length;
		int c=bitA[0].length();
		String tempbitAL[]=new String[r];
		String tempbitAR[]=new String[r];
		String tempbitAD[]=new String[c];
		String tempbitAU[]=new String[c];
		
		//Apply LEFT
		tempbitAL=bitA;
		tempbitAL=applyLeftGravity(tempbitAL);
		boolean goL=gameOver(tempbitAL);
		
		//Apply RIGHT
		//revert the strings in array then apply LEFT that would be same as RIGHT
		for (int i=0;i<r;i++)
		{
			String s="";
			for (int j=c-1;j>=0;j--)
			{
				s=s+bitA[i].charAt(j);
			}
			tempbitAR[i]=s;
		}
			tempbitAR=applyLeftGravity(tempbitAR);
			boolean goR=gameOver(tempbitAR);
			
			//Apply DOWN
			for (int i=0;i<c;i++)
			{
				String s="";
				for (int j=0;j<r;j++)
				{
					s=s+bitA[j].charAt(i);
				}
				tempbitAD[i]=s;
			}
			tempbitAD=applyLeftGravity(tempbitAD);
			boolean goD=gameOver(tempbitAD);
			
			//Apply UP
			for (int i=0;i<c;i++)
			{
				String s="";
				for (int j=r-1;j>=0;j--)
				{
					s=s+bitA[j].charAt(i);
				}
				tempbitAU[i]=s;
			}
				
			tempbitAU=applyLeftGravity(tempbitAU);
			boolean goU=gameOver(tempbitAU);
			
			
		if (game.getl2g()==game.getl1g() && game.getl2g()==Gravity.ZERO)		//that means first move
		{
			return "DLUR";						// You can apply any movement
		}
		else if(game.getl2g()==Gravity.ZERO && game.getl1g()!=Gravity.ZERO)    // that means second move
		{
			switch (game.getl1g())
			{
			case DOWN:
				return "LUR";
			case UP:
				return "DLR";
			case LEFT:
				return "DUR";
			case RIGHT:
				return "DLU";
			}
		}
		else
		{
			if ((game.getl1g()==Gravity.DOWN && game.getl2g()==Gravity.UP)||(game.getl1g()==Gravity.UP && game.getl2g()==Gravity.DOWN)) 
			{
				/*int r=bitA.length;
				String tempbitAL[]=new String[r];
				String tempbitAR[]=new String[r];*/
				//now check if left or right is ending game? prefer non go movement
			//Apply LEFT
				//tempbitAL=bitA;
				//tempbitAL=applyLeftGravity(tempbitAL);
					/*for (int i=0;i<r;i++)
					{
                     String seq=game.BP.shiftblanks(tempbitAL[i]);
                     do
                     {
                    	 for (int j=0;j<game.BP.getNonBlanks(seq)-1;j++)
                    	 {
                    		 StringBuilder sb=new StringBuilder(seq);
                    		 switch (seq.substring(j, j+2))
                    		 {
                    		 case "00":
                    			 sb.replace(j, j+2,"02");
                    			seq=sb.toString();
                    			seq=game.BP.shiftblanks(seq);
                    			break;
                    		 case "11":
                 				sb.replace(j, j+2,"10");
                 				seq=sb.toString();
                 				break;
                    		 case "20":
                    			sb.replace(j, j+2,"02");
                  				seq=sb.toString();
                  				break;
                    		 case "21":
                    			sb.replace(j, j+2,"12");
                  				seq=sb.toString();
                  				break;
                    		 }
                    	 }
                     }
                     while (!game.BP.checkSeq(seq, game.BP.countBlanks(seq)));
                    	
                     tempbitAL[i]=seq;
                     
					}*/
					//boolean goL=gameOver(tempbitAL);
					
					//Apply RIGHT
					//revert the strings in array then apply LEFT that would be same as RIGHT
					/*int c= tempbitAR[0].length();
					for (int i=0;i<r;i++)
					{
						String s="";
						for (int j=c-1;j>=0;j--)
						{
							s=s+bitA[i].charAt(j);
						}
						tempbitAR[i]=s;
					}
						tempbitAR=applyLeftGravity(tempbitAR);*/
						
					/*for (int i=0;i<r;i++)
					{
                     String seq=game.BP.shiftblanks(tempbitAR[i]);
                     do
                     {
                    	 for (int j=0;j<game.BP.getNonBlanks(seq)-1;j++)
                    	 {
                    		 StringBuilder sb=new StringBuilder(seq);
                    		 switch (seq.substring(j, j+2))
                    		 {
                    		 case "00":
                    			 sb.replace(j, j+2,"02");
                    			seq=sb.toString();
                    			seq=game.BP.shiftblanks(seq);
                    			break;
                    		 case "11":
                 				sb.replace(j, j+2,"10");
                 				seq=sb.toString();
                 				break;
                    		 case "20":
                    			sb.replace(j, j+2,"02");
                  				seq=sb.toString();
                  				break;
                    		 case "21":
                    			sb.replace(j, j+2,"12");
                  				seq=sb.toString();
                  				break;
                    		 }
                    	 }
                     }
                     while (!game.BP.checkSeq(seq, game.BP.countBlanks(seq)));
                    	
                     tempbitAR[i]=seq;
                     
					}*/
					//boolean goR=gameOver(tempbitAR);
					
					if ((goL && goR) || (!goL && !goR))
						return "LR";
					else if (goL)
						return "R";
					else
						return "L";
				
			}
			else if ((game.getl1g()==Gravity.LEFT && game.getl2g()==Gravity.RIGHT)||(game.getl1g()==Gravity.RIGHT && game.getl2g()==Gravity.LEFT))
			{
				/*int r=bitA.length;
				int c=bitA[0].length();
				String tempbitAD[]=new String[c];
				String tempbitAU[]=new String[c];*/
				//now check if down or up is ending game? prefer non go movement
			    //Apply DOWN
				//int c= tempbitAR[0].length();
				/*for (int i=0;i<c;i++)
				{
					String s="";
					for (int j=0;j<r;j++)
					{
						s=s+bitA[j].charAt(i);
					}
					tempbitAD[i]=s;
				}
				tempbitAD=applyLeftGravity(tempbitAD);*/
					/*for (int i=0;i<c;i++)
					{
                     String seq=game.BP.shiftblanks(tempbitAD[i]);
                     do
                     {
                    	 for (int j=0;j<game.BP.getNonBlanks(seq)-1;j++)
                    	 {
                    		 StringBuilder sb=new StringBuilder(seq);
                    		 switch (seq.substring(j, j+2))
                    		 {
                    		 case "00":
                    			 sb.replace(j, j+2,"02");
                    			seq=sb.toString();
                    			seq=game.BP.shiftblanks(seq);
                    			break;
                    		 case "11":
                 				sb.replace(j, j+2,"10");
                 				seq=sb.toString();
                 				break;
                    		 case "20":
                    			sb.replace(j, j+2,"02");
                  				seq=sb.toString();
                  				break;
                    		 case "21":
                    			sb.replace(j, j+2,"12");
                  				seq=sb.toString();
                  				break;
                    		 }
                    	 }
                     }
                     while (!game.BP.checkSeq(seq, game.BP.countBlanks(seq)));
                    	
                     tempbitAD[i]=seq;
                     
					}*/
					//boolean goD=gameOver(tempbitAD);
					
					//Apply UP
					//revert the strings in array then apply LEFT that would be same as RIGHT
					/*for (int i=0;i<c;i++)
					{
						String s="";
						for (int j=r-1;j>=0;j--)
						{
							s=s+bitA[j].charAt(i);
						}
						tempbitAU[i]=s;
					}
						
					tempbitAU=applyLeftGravity(tempbitAU);*/
					
					/*for (int i=0;i<c;i++)
					{
                     String seq=game.BP.shiftblanks(tempbitAU[i]);
                     do
                     {
                    	 for (int j=0;j<game.BP.getNonBlanks(seq)-1;j++)
                    	 {
                    		 StringBuilder sb=new StringBuilder(seq);
                    		 switch (seq.substring(j, j+2))
                    		 {
                    		 case "00":
                    			 sb.replace(j, j+2,"02");
                    			seq=sb.toString();
                    			seq=game.BP.shiftblanks(seq);
                    			break;
                    		 case "11":
                 				sb.replace(j, j+2,"10");
                 				seq=sb.toString();
                 				break;
                    		 case "20":
                    			sb.replace(j, j+2,"02");
                  				seq=sb.toString();
                  				break;
                    		 case "21":
                    			sb.replace(j, j+2,"12");
                  				seq=sb.toString();
                  				break;
                    		 }
                    	 }
                     }
                     while (!game.BP.checkSeq(seq, game.BP.countBlanks(seq)));
                    	
                     tempbitAU[i]=seq;
                     
					}*/
					
					//boolean goU=gameOver(tempbitAU);
					
					if ((goD && goU) || (!goD && !goD))
						return "DU";
					else if (goD)
						return "U";
					else
						return "D";
				
			}
			else
			{
				String ret="";
				switch (game.getl1g())
				{
				case DOWN:
					
					/*if(!goD)
						ret=ret+"D";*/
					if (!goL)
						ret=ret+"L";
					if(!goU)
						ret=ret+"U";
					if(!goR)
						ret=ret+"R";
					if (ret.equals(""))
							return "LUR";
						else
							return ret;
					//return "LUR";		
				case UP:
					if(!goD)
						ret=ret+"D";
					if (!goL)
						ret=ret+"L";
					/*if(!goU)
						ret=ret+"U";*/
					if(!goR)
						ret=ret+"R";
					
					if (ret.equals(""))
						return "DLR";
					else
						return ret;
					//return "DLR";
				case LEFT:
					if(!goD)
						ret=ret+"D";
					/*if (!goL)
						ret=ret+"L";*/
					if(!goU)
						ret=ret+"U";
					if(!goR)
						ret=ret+"R";
					
					if (ret.equals(""))
						return "DUR";
					else
						return ret;
					//return "DUR";
				case RIGHT:
					if(!goD)
						ret=ret+"D";
					if (!goL)
						ret=ret+"L";
					if(!goU)
						ret=ret+"U";
					/*if(!goR)
						ret=ret+"R";*/
					
					if (ret.equals(""))
						return "DLU";
					else
						return ret;
					//return "DLU";
				}
			}
		}
			
		return "DLUR";		//check this....?????
	}

	private String[] applyLeftGravity(String[] bitA)
	{
		int r=bitA.length;
		for (int i=0;i<r;i++)
		{
         String seq=game.BP.shiftblanks(bitA[i]);
         do
         {
        	 for (int j=0;j<game.BP.getNonBlanks(seq)-1;j++)
        	 {
        		 StringBuilder sb=new StringBuilder(seq);
        		 switch (seq.substring(j, j+2))
        		 {
        		 case "00":
        			 sb.replace(j, j+2,"02");
        			seq=sb.toString();
        			seq=game.BP.shiftblanks(seq);
        			break;
        		 case "11":
     				sb.replace(j, j+2,"10");
     				seq=sb.toString();
     				break;
        		 /*case "20":
        			sb.replace(j, j+2,"02");
      				seq=sb.toString();
      				break;
        		 case "21":
        			sb.replace(j, j+2,"12");
      				seq=sb.toString();
      				break;*/
        		 }
        	 }
         }
         while (!game.BP.checkSeq(seq, game.BP.countBlanks(seq)));
        	
         bitA[i]=seq;
         
		}
		return bitA;
	}
}
