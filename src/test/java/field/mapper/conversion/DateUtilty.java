package field.mapper.conversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtilty {
	
	public static Date mmddyyToDate(String string) {
		DateFormat format = new SimpleDateFormat("MM-DD-YY", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String dateToMmddyy(Date date) {
		if(date!=null) {
			DateFormat dateFormat = new SimpleDateFormat("MM-DD-YY");  
			return dateFormat.format(date);  
		}
		return null;
	}

}
