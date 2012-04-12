package timepiece;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateHTML {


	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		String cand = "elevenntsix|sevenfourty|twelvehotwo|eightoneten|threertnine|fivefifteen|twentypastl|fourthytenv|fiftythirty|clockfourty|gtwofiveten";

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
							regex.append("(.*)");
						} else {
							regex.append("(.+)");
						}

						StringBuffer subRegex = new StringBuffer();
						String[] subWords = words[j].split("\\+");
						for (String string : subWords) {
							if (subRegex.length() > 0) {
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

		FileWriter htmlOut = new FileWriter("visu.html");

		int solution = 0;
		HashSet<Integer> splitPos = new HashSet<Integer>();
		for (int hour = 0; hour < strings.length; hour++) {
			for (int minute = 0; minute < strings[hour].length; minute++) {
				for (Pattern pattern : patterns[hour][minute]) {
					Matcher matcher = pattern.matcher(cand);
					if (matcher.matches()) {
						solution++;

						htmlOut
								.write(String
										.format(
												"<div class='solution english_solution' id='english_time%d'>",
												solution));
						htmlOut.write(String.format(
								"<span class='time'>%2d:%02d</span>", hour + 1,
								minute * 5));

						htmlOut.write(String.format("<div class='clock'>"));
						for (int i = 1; i <= matcher.groupCount(); i++) {
							splitPos.add(matcher.start(i));
							if (i % 2 == 1) {
								htmlOut.write(String.format(
										"<span class='background'>%s</span>",
										matcher.group(i).toUpperCase()
											.replaceAll("\\|</span>", "</span><br />")
											.replaceAll("\\|", "<br />")));
							} else {
								
								htmlOut.write(String.format(
										"<span class='highlight'>%s</span>",
										matcher.group(i).toUpperCase()
												.replaceAll("\\|</span>", "</span><br />")
												.replaceAll("\\|", "<br />")));
							}
						}
						htmlOut.write(String.format("</div>"));

						htmlOut.write(String.format("</div>\n"));
					}
				}
			}
		}

		htmlOut.close();
	}
}
