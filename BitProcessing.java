package com.gameScreens.games;

/*@author ambuj mishra*/


public class BitProcessing {
	

	
	public boolean checkSeq(String shiftseq, int blankcount)
	{
		String thisseq=shiftseq.substring(0,shiftseq.length()-blankcount);
		//String pairSeq=thisSeq.substring(0, 2);
		if (thisseq.equals(thiscodeSeq(thisseq)))
			return true;
		else
			return false;
	}
	
	private String thiscodeSeq(String thisSeq) {
		
		String codeSeq="";
		if (thisSeq.charAt(0)=='0')
		{
		codeSeq=codeSeq+0;
			for (int i=1;i<thisSeq.length();i++)
			{
				if(i%2!=0)
					codeSeq=codeSeq+1;
				else
					codeSeq=codeSeq+0;
			}
		}
		else
		{
			codeSeq=codeSeq+1;
			for (int i=1;i<thisSeq.length();i++)
			{
				if(i%2!=0)
					codeSeq=codeSeq+0;
				else
					codeSeq=codeSeq+1;
			}
		}
			
	//0101010101
		//or
	//10101010
		return codeSeq;

}
	
	private String shiftblanks(String seq) {

		String bitseq="";
		String blankseq="";
		for (int i=0;i<seq.length();i++)
		{
			if (seq.charAt(i)=='2')
				blankseq=blankseq+2;
			else
				bitseq=bitseq+seq.charAt(i);
		}
				
		return bitseq+blankseq;		//example: 10110022222
	}

	public int countBlanks(String bitseq) {
		int count=0;
		for (int i=0; i<bitseq.length();i++)
		{
			if (bitseq.charAt(i)=='2')
				count++;
		}
		return count;
		
	}

	public String singleStepProcess(String seq) 
	{
	
			if (isShiftRequired(seq)) //now check for shift, isRequired?
				return shiftblanks(seq);
			else
				return unitBitPairProcess(seq);
	}
	
	private String unitBitPairProcess(String seq) {
		
		// logic for bit movement, write logic for bit crashing, from bottom to top,
			StringBuilder sb= new StringBuilder(seq);
			boolean changed=false;
			int i=0;
			while (!changed)
			{
				switch(sb.substring(i,i+2))
				{
				case "00":		
					//replace with 02
					sb.replace(i,i+2, "02");
					changed=true;
					break;
				//case "01":case "10":
					//do nothing
					//break;
				case "11":
					// replace with 10
					sb.replace(i, i+2, "10");
					changed=true;
					break;
				default:
					i=i+1;
					break;	
				}
			}

			return sb.toString();
	}
	
private boolean isShiftRequired(String seq) {
		for (int i=0;i<seq.length()-countBlanks(seq);i++)
			{
			if (seq.charAt(i)=='2')
				return true;
			}
		return false;
	}

}
