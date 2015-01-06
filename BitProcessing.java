package com.Gbits.Screens;

/*
 *Ambuj Mishra
 *1-1-2015 
 */
public class BitProcessing {
	

	
	public boolean checkSeq(String shiftseq, int blankcount)
	{
		String thisseq=shiftseq.substring(0,shiftseq.length()-blankcount);
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
	
	public String shiftblanks(String seq) {

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

	// it will check only spaces in a seq only
public boolean isShiftRequired(String seq) {
		for (int i=0;i<seq.length()-countBlanks(seq);i++)
			{
			if (seq.charAt(i)=='2')
				return true;
			}
		return false;
	}

public boolean isAnimaRequired(String[] seqA, String g) {
	
	switch (g)
	{
	case "LEFT":
	for (int j=0;j<seqA.length;j++)
	{
		for (int i=0;i<seqA[j].length()-countBlanks(seqA[j]);i++)
		{
		if (seqA[j].charAt(i)=='2')
			return true;
		}
	for (int i=0;i<seqA[j].length()-1;i++)
	{
		switch(seqA[j].substring(i,i+2))
		{
		case "00": case "11": case "20": case "21":
			return true;
		}
	}
	}
	break;
	case "RIGHT":
		for (int j=0;j<seqA.length;j++)
		{
			for (int i=seqA[j].length()-1;i>=countBlanks(seqA[j]);i--)
			{
			if (seqA[j].charAt(i)=='2')
				return true;
			}
		for (int i=seqA[j].length();i>=2;i--)
		{
			switch(seqA[j].substring(i-2,i))
			{
			case "00": case "11": case "02": case "12":
				return true;
			}
		}
		}
		break;
	case "DOWN":
		for (int j=0;j<seqA[0].length();j++)
		{
			String colSeq="";
			for (int i=0;i<seqA.length;i++)
			{
				colSeq=colSeq+seqA[i].charAt(j);
			}
			
			for (int i=0;i<colSeq.length()-countBlanks(colSeq);i++)
			{
			if (colSeq.charAt(i)=='2')
				return true;
			}
			for (int i=0;i<colSeq.length()-1;i++)
			{
				switch(colSeq.substring(i,i+2))
				{
				case "00": case "11": case "20": case "21":
					return true;
				}
			}
		}
	break;
	case "UP":
		for (int j=0;j<seqA[0].length();j++)
		{
			String colSeq="";
			for (int i=seqA.length-1;i>=0;i--)
			{
				colSeq=colSeq+seqA[i].charAt(j);
			}
			
			for (int i=0;i<colSeq.length()-countBlanks(colSeq);i++)
			{
			if (colSeq.charAt(i)=='2')
				return true;
			}
			for (int i=0;i<colSeq.length()-1;i++)
			{
				switch(colSeq.substring(i,i+2))
				{
				case "00": case "11": case "20": case "21":
					return true;
				}
			}
		}
	break;
	case "ZERO":
		return false;
	}
	return false;
}

}
