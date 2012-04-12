package timepiece;

public class Candidate {
	public int fittnes = Integer.MIN_VALUE;


//	public String candidate = //10x10
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX|" +
//		"XXXXXXXXXX";

	
	public String candidate = //11x11
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX|" +
		"XXXXXXXXXXX";
	
	
	
	//circle
//	public String candidate = //circle
//			      "ONER|" +
//			    "SETHIRTY|" +
//			  "FIFTYPTYHALF|" +
//			  "FTENETWENTYR|" +
//			 "FIVENTPASTNINE|" +
//			 "TIELEVENTHREEH|" +
//			"XSIXTFIFTEENTWOEN|" +
//			"TWELVEEIGHTSEVENR|" +
//			"YWEQUARTERFOURTYE|" +
//			"FOFIVENTCLOCKTENY|" +
//			 "NINEEPASTENTOU|" +
//			 "FIFTEENFOURTHY|" +
//			  "TWOHFIFTYEWR|" +
//			  "THIRTYTWENTY|" +
//			   "FIVENONE|" +
//			     "YTEN";
	
//		public String candidate = //triangle		
//			"PSEVENSIXTWELVEN|" +
//			"ONELEVENINEIGHT|" +
//			"TWENTYHYYTENNY|" +
//			"FOURTHYTHIRTY|" +
//			"EFIFTEENSTWO|" +
//			"FOURTYFIFTY|" +
//			"THREEFIVEY|" +
//			"PASTHFOUR|" +
//			"FNINEVEY|" +
//			"FIVEONE|" +
//			"CLOCKT|" +
//			"THREE|" +
//			"XTEN|" +
//			"TWO|" +
//			"TO|" +
//			"W";
	
//		public String candidate = //diamond
//			"H|" +
//			"TO|" +
//			"SIX|" +
//			"NINE|" +
//			"TWONE|" +
//			"FIFTYY|" +
//			"SSEVENO|" +
//			"LEIGHTEN|" +
//			"PASTWELVE|" +
//			"THREELEVEN|" +
//			"FIVEFOURTHY|" +
//			"TWENTYTENO|" +
//			"CLOCKPAST|" +
//			"FIFTEENN|" +
//			"FFOURTY|" +
//			"THIRTY|" +
//			"FIFTY|" +
//			"FIVE|" +
//			"TWO|" +
//			"TO|" +
//			"O";
		
	public int checkedOK = 0;
	public int checkedNOK = 0;
	public int checkedTimesOK = 0;
	public int checkedTimesNOK = 0;
	public int splitPos = 0;
	public double variance = 0;
	public double avgLen = 0;
	
	@Override
	public String toString() {
		return String.format("%10d: splits: %d, check ok: %d, nok: %d, times ok: %d, nok %d, %s", fittnes, splitPos,
				checkedOK,checkedNOK,checkedTimesOK,checkedTimesNOK,
				candidate);
	}
}
