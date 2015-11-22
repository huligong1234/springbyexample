package org.jeedevframework.core.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
    public static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String C_DATE_DIVISION       = "-";
    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String C_yyyymmdd = "yyyyMMdd";
    
    public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
    public static final String C_TIME_PATTON_HH  = "HH";
    public static final String C_TIME_PATTON_HHMMSS  = "HH:mm:ss";
    
    public static final String C_TIME_PATTON_HHMM  = "HH:mm";
    
    public static final String C_TIME_PATTON_hhMM  = "hh:mm";
    
    public static final String C_DATE_HK_PATTON_DEFAULT = "dd/MM/yyyy";

    public static final int    C_ONE_SECOND          = 1000;

    public static final int    C_ONE_MINUTE          = 60 * C_ONE_SECOND;

    public static final int    C_ONE_HOUR            = 60 * C_ONE_MINUTE;

    public static final long   C_ONE_DAY             = 24 * C_ONE_HOUR;
    public static long differ(String startDay,String endDay){
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date date;
		try {
			date = myFormatter.parse(startDay);
			java.util.Date mydate= myFormatter.parse(endDay);
			long  day=(mydate.getTime()-date.getTime())/(24*60*60*1000);
			 return day;
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		 return 0;
	   
    }
    public static String getDateFormat(Date d){
        Calendar today = Calendar.getInstance();
        Calendar theDate = Calendar.getInstance();
        theDate.setTime(d);
        today.set(Calendar.HOUR,0);
        today.set(Calendar.MINUTE,0);
        today.set(Calendar.SECOND,0);
      if(theDate.before(today)){
          return format(d,C_DATE_PATTON_DEFAULT);
      }else{
          return format(d,C_TIME_PATTON_HHMM);
      }
   }

	public static String formatToday(String as_Pattern) {
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);
		return dateFromat.format(new Date());
	}

	public static String format(Date aTs_Datetime, String as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);
		return dateFromat.format(aTs_Datetime);
	}

	/**
	 * 解析format指定的格式的日期
	 */
	public static Date parseDate(String date, String format) {
		return parseDate(date, new SimpleDateFormat(format));
	}
        
    /**
     * 解析format指定的格式的日期
     */
    public static Date parseDate(String date, SimpleDateFormat format){
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Date parse error: " + date 
                    + " , expected format is " + format.toPattern(),ex);
        }
    }
   
}
