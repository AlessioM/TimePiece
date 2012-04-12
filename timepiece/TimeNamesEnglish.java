package timepiece;

import java.util.LinkedList;
import java.util.List;

public class TimeNamesEnglish {
	
	public static String[] getHours() {
		String[] hours = {
				"one",
				"two",
				"three",
				"four",
				"five",
				"six",
				"seven",
				"eight",
				"nine",
				"ten",
				"eleven",
				"twelve"
		};
		return hours;
	}
	
	public static String[] getMinutes() {
		String[] minutes = {
				"zero",
				"five",
		 		"ten",
		 		"fifteen",
		 		"twenty",
		 		"twenty+five",
		 		"thirty",
		 		"thirty+five",
		 		"forty",
		 		"forty+five",
		 		"fifty",
		 		"fifty+five"
		};
		return minutes;
	}
	
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
		String simpleFormat = "%s %s";
		for (int hour = 0; hour < hours.length; hour++) {
			for (int minute = 0; minute < minutes.length; minute++) {
				String h = hours[hour];
				String m =  minutes[minute];
//				if(hour == 0) h = "ein";
				if(minute == 0) m = "o clock";
				
				if(minute != 0) times[hour][minute].add(String.format("%s past %s", m, h).trim());
				
				if(minute == 1) m = "o five";
				times[hour][minute].add(String.format(simpleFormat, h, m).trim());
				
			}
		}
		
		for (int hour = 0; hour < hours.length; hour++) {
			times[hour][3].add(String.format("quarter past %s", hours[hour]).trim());
			
			times[hour][6].add(String.format("half past %s", hours[hour]).trim());
					
			String nextHour = hour == hours.length - 1?hours[0]:hours[hour+1];			
			times[hour][9].add(String.format("quarter to %s",nextHour).trim());
		}
		
		return times;
	}
	
	public static void main(String[] args) {
		List<String>[][] ts = getTimeStrings();
		for (List<String>[] lists : ts) {
			for (List<String> list : lists) {
				for (String string : list) {
					System.out.println(string);
				}
			}
		}
	}
}
