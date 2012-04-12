package timepiece;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateCode {
	public static void main(String[] args) {
		List<String>[][] strings = TimeNamesGerman.getTimeStrings();
		List<Pattern>[][] patterns = new List[strings.length][];

		for (int hour = 0; hour < strings.length; hour++) {
			patterns[hour] = new List[strings[hour].length];
			for (int minute = 0; minute < strings[hour].length; minute++) {
				patterns[hour][minute] = new LinkedList<Pattern>();

				for (String time : strings[hour][minute]) {
					String[] words = time.split(" ");
					
					StringBuffer regex = new StringBuffer();				
					for (int j = 0; j < words.length; j++) {
						if (regex.length() == 0) {
							regex.append("(.*?)");
						} else {
							regex.append("(.+?)");
						}
						
						StringBuffer subRegex = new StringBuffer();
						String[] subWords = words[j].split("\\+");
						for (String string : subWords) {
							if(subRegex.length() > 0) {
								subRegex.append("(.*)");
							}
							subRegex.append("(");
							subRegex.append(string);
							subRegex.append(")");
							
						}
						
						regex.append(subRegex.toString());
					}
					
					regex.append("(.*)");
					System.out.printf("%2d:%02d : %s\n", hour + 1,
							minute * 5, regex.toString());
					patterns[hour][minute].add(Pattern.compile(regex
							.toString()));
					
				}
			}
		}
		
		String solution = "FÜNFFZEHNU|DREIHNACHT|VORELFHALB|UHRVIERTEL|SIEBENACHT|DREISECHSZ|NEUNDZWÖLF|ZWEINSLUHR|ELFÜNFZEHN|UNDVIERZIG".toLowerCase();
		
		int totalCount = 0;
		
		for (int hour = 0; hour < strings.length; hour++) {
			for (int minute = 0; minute < strings[hour].length; minute++) {
				for (Pattern pattern : patterns[hour][minute]) {
					Matcher matcher = pattern.matcher(solution);
					if(matcher.matches()) {
						totalCount++;
						boolean[] bitsToSet = new boolean[solution.length()];						
						for (int i = 1; i <= matcher.groupCount(); i++) {				
							for(int j = matcher.start(i); j < matcher.end(i); j++) bitsToSet[j] = (i % 2 == 0);
						}
						
						StringBuffer bits = new StringBuffer();
						for (int i = 0; i < bitsToSet.length; i++) {							
							if(solution.charAt(i) != '|') {
								bits.append(bitsToSet[i]?"1":"0");
							} else {
								bits.append("-");
							}
						}
						String[] lines = bits.toString().split("-");
						
						
						StringBuffer timeBytes = new StringBuffer();
						timeBytes.append("");
						timeBytes.append(String.format("%3d", (hour + 1) * 12 + minute));
						timeBytes.append(String.format(" /* %2d:%02d */", hour + 1,
								minute * 5));
						
						for (String l : lines) {
							String firstByte  = "000000" + l.substring(0,2);
							String secondByte = l.substring(2,10);
							if(timeBytes.length() > 0) {
								timeBytes.append(", ");
							}
							timeBytes.append("B");
							timeBytes.append(firstByte);
							timeBytes.append(", B");
							timeBytes.append(secondByte);
						}
						timeBytes.append(",");		
						
						System.out.println(timeBytes.toString());
						
						
//						System.out.println(bits.toString());
//						String hundretBits = bits.toString();
//						System.out.println(hundretBits.length());
//						for (int line = 0; line < 10; line++) {
//							String firstByte  = hundretBits.substring(line * 10, line * 10 + 8);
//							String secondByte = hundretBits.substring(line * 10 + 8, line * 10 + 10) + "000000";
//							System.out.printf(", B%s, B%s", firstByte, secondByte);
//							
//						}
//						System.out.println();
						
					}
				}
			
			}
		}
		
	}
}
