package com.kingAm.games;

public class bitLogic {

	public String processbitSequence(String bitseq) {
	
		int blankcount=countBlanks(bitseq);
		int max=bitseq.length();
		if (blankcount==max)
		return bitseq;
		else
		{
			// shift blanks
		String shiftseq=shiftblanks(bitseq);
		
		//or recursive function on seq#not a good idea wasted 2 hr :(
		String finalSeq=processSeq(shiftseq,blankcount);
		return finalSeq;
		}		
		
	}
	
	private boolean checkSeq(String shiftseq, int blankcount)
	{
		String thisseq=shiftseq.substring(0,shiftseq.length()-blankcount);
		//String pairSeq=thisSeq.substring(0, 2);
		if (thisseq.equals(thiscodeSeq(thisseq)))
			return true;
		else
			return false;
	}
private String processSeq(String shiftseq, int blankcount) {
	
	String finalseq=shiftseq;
	int finalblankcount= blankcount;
	
	while (!checkSeq(finalseq,finalblankcount))
	{
	
		String tempseq=reprocesssSeq(finalseq,finalblankcount);
		finalseq=tempseq;
		finalblankcount=countBlanks(tempseq);
		//return finalseq;
	}
	return finalseq;
		
	}


private String reprocesssSeq(String seq, int blankcount) {
	
	// logic for bit movement, write logic for bit crashing, from top to bottom,
		StringBuilder sb= new StringBuilder(seq);
		
		for(int i=sb.length()-blankcount;i>1;i--)
		{
			switch(sb.substring(i-2,i))
			{
			case "00":
				
				//replace with 02
				sb.replace(i-2, i, "02");
				break;
			//case "01":case "10":
				//do nothing
				//break;
			case "11":
				// replace with 10
				sb.replace(i-2, i, "10");
				break;
			}
			seq=sb.toString();
			shiftblanks(seq);
		}
		return seq;
}
	private String thiscodeSeq(String thisSeq) {
		
		String codeSeq="";
		if (thisSeq.charAt(0)=='0')
		{
		codeSeq=codeSeq+0;
			for (int i=1;i<=thisSeq.length();i++)
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
			for (int i=1;i<=thisSeq.length();i++)
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

	private int countBlanks(String bitseq) {
		int count=0;
		for (int i=0; i<bitseq.length();i++)
		{
			if (bitseq.charAt(i)=='2')
				count++;
		}
		return count;
		
	}
}
