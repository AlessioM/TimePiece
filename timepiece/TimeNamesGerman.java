package timepiece;

import java.util.LinkedList;
import java.util.List;

public class TimeNamesGerman {
	
	public static String[] getHours() {
		String[] hours = {
				"eins",
				"zwei",
				"drei",
				"vier",
				"fünf",
				"sechs",
				"sieben",
				"acht",
				"neun",
				"zehn",
				"elf",
				"zwölf"
		};
		return hours;
	}
	
	public static String[] getMinutes() {
		String[] minutes = {
				"null",
				"fünf",
		 		"zehn",
		 		"fünf+zehn",
		 		"zwanzig",
		 		"fünf+und+zwanzig",
		 		"dreissig",
		 		"fünf+und+dreissig",
		 		"vierzig",
		 		"fünf+und+vierzig",
		 		"fünfzig",
		 		"fünf+und+fünfzig"
		};
		return minutes;
	}
	

	@SuppressWarnings("unchecked")
	public static List<String>[][] getTimeStrings() {
		List<String>[][] times = new List[12][];
		for (int i = 0; i < times.length; i++) {
			times[i] = new List[12];
			for (int j = 0; j < times[i].length; j++) {
				times[i][j] = new LinkedList<String>();
			}
		}
		
		String[] hours = getHours();
		String[] minutes = getMinutes();
		
		//simple format    X uhr Y, Y = n * 5
		String simpleFormat = "%s uhr %s";
		for (int hour = 0; hour < hours.length; hour++) {
			for (int minute = 0; minute < minutes.length; minute++) {
				String h = hours[hour];
				String m =  minutes[minute];
				if(hour == 0) h = "ein";
				if(minute == 0) m = "";
				times[hour][minute].add(String.format(simpleFormat, h, m).trim());
			}
		}
		
		/*
		 * punkt X
		 * Y nach X, Y = fünf, zehn, zwanzig
		 * viertel nach X
		 * Y vor halb X+1 , Y = zehn, fünf
		 * halb X+1
		 * Y nach halb X+1, Y = zehn, fünf
		 * dreiviertel X+1
		 * Y vor X+1, Y = zwanzig, zehn, fünf
		 */
		
		String[] fzz = {
				minutes[1],
				minutes[2],
				null,
				minutes[4]
		};
		
		String[] fz = {
				minutes[1],
				minutes[2]
		};
		
		for (int hour = 0; hour < hours.length; hour++) {
			String nextHour = hour == hours.length - 1?hours[0]:hours[hour+1];
			
			times[hour][0].add(String.format("punkt %s", hours[hour]).trim());
			times[hour][3].add(String.format("viertel nach %s", hours[hour]).trim());
			times[hour][6].add(String.format("halb %s", nextHour).trim());
			times[hour][9].add(String.format("drei+viertel %s", nextHour).trim());
			
			for (int i = 0; i < fzz.length; i++) {
				if(fzz[i] != null ) {
					times[hour][i + 1].add(String.format("%s nach %s", fzz[i], hours[hour]).trim());
					times[hour][11 -i ].add(String.format("%s vor %s", fzz[i], nextHour).trim());
				}
			}
			
			for (int i = 0; i < fz.length; i++) {
				times[hour][5 - i].add(String.format("%s vor halb %s", fz[i], nextHour).trim());
				times[hour][7 +i ].add(String.format("%s nach halb %s", fz[i], nextHour).trim());
			}
		}
		
		
		return times;
	}
}
