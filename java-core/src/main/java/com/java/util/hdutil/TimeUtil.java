package com.java.util.hdutil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 时间的辅助工具
 * 
 * @author lexond & sunfeng
 * @version 1.0
 */
public class TimeUtil {

	/**
	 * 默认时间格式，yyyy-MM-dd HH:mm:ss
	 */
	public final static String DATE_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 年月日时间格式，yyyy-MM-dd
	 */
	public final static String DATE_DAY_FORMAT = "yyyy-MM-dd";

	/**
	 * 年月时间格式，yyyy-MM
	 */
	public final static String DATE_MONTH_FORMAT = "yyyy-MM";

	/**
	 * 年时间格式，yyyy
	 */
	public final static String DATE_YEAR_FORMAT = "yyyy";

	/**
	 * 第一季度--1
	 */
	public final static String FIRST_SEASON = "1";

	/**
	 * 第二季度--2
	 */
	public final static String SECOND_SEASON = "2";

	/**
	 * 第三季度--3
	 */
	public final static String THIRD_SEASON = "3";

	/**
	 * 第四季度--4
	 */
	public final static String FOURTH_SEASON = "4";

	/**
	 * 日-DAY
	 */
	public final static String TIME_DAY = "DAY";

	/**
	 * 月-MONTH
	 */
	public final static String TIME_MONTH = "MONTH";

	/**
	 * 年-YEAR
	 */
	public final static String TIME_YEAR = "YEAR";

	/**
	 * 季-SEASON
	 */
	public final static String TIME_SEASON = "SEASON";

	/**
	 * 半年-HALF_YEAR
	 */
	public final static String TIME_HALF_YEAR = "HALF_YEAR";

	/**
	 * 格式化date时间，格式为默认格式,yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            时间
	 * @return 格式好的时间
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATE_DEFAULT_FORMAT);
	}

	/**
	 * 格式化时间,将Date时间格式化成指定格式的时间字符串
	 * 
	 * @param date
	 *            需要格式化的时间
	 * @param format
	 *            格式化文本
	 * @return 格式好的时间，如果出现异常，则按照默认格式yyyy-MM-dd HH:mm:ss格式化
	 */
	public static String formatDate(Date date, String format) {

		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			sdf = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
			return sdf.format(date);
		}
	}

	/**
	 * 解析字符串的时间，根据指定的格式生成 Date（将字符串格式转换成Date）
	 * 
	 * @param date
	 *            时间字符串
	 * @param format
	 *            格式化文本
	 * @return 格式好的时间Date，如果出现异常，则按照默认格式yyyy-MM-dd
	 *         HH:mm:ss格式化，如果按照默认格式格式化也出现异常则返回null
	 */
	public static Date parseDate(String date, String format) {

		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (Exception e) {
			sdf = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
			try {
				return sdf.parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * 格式化今天的时间，格式化成默认格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 格式好的字符串yyyy-MM-dd HH:mm:ss
	 */
	public static String formatToday() {
		return formatDate(new Date(), DATE_DEFAULT_FORMAT);
	}

	/**
	 * 将指定Date时间格式化成yyyy-MM-dd格式的字符串
	 * 
	 * @param date
	 *            日期值
	 * @return 格式化后的字符串
	 */
	public static String formatDay(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 将指定Date时间格式化成yyyy-MM格式的字符串
	 * 
	 * @param date
	 *            日期值
	 * @return 格式化好的月值
	 */
	public static String formatMonth(Date date) {
		return formatDate(date, "yyyy-MM");
	}

	/**
	 * 得到今天时间的timestamp格式
	 * 
	 * @return 得到今天时间的timestamp格式
	 */
	public static Timestamp getTodayTimeStamp() {
		return Timestamp.valueOf(formatToday());
	}

	/**
	 * 得到指定时间Date的Calendar实例
	 * 
	 * @param date
	 *            指定时间
	 * @return 返回得到的Calendar对象
	 */
	public static Calendar getCalendar(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		return cale;
	}

	/**
	 * 通过月份数据得到日历对象
	 * 
	 * @param monthTime
	 *            月份数据，yyyy-MM格式的时间，如2012-12
	 * @return 日历对象
	 */
	public static Calendar getCalendarByMonth(String monthTime) {

		Timestamp t = Timestamp.valueOf(monthTime + "-01 11:11:11");
		Calendar cale = Calendar.getInstance();
		cale.setTime(t);
		return cale;
	}

	/**
	 * 得到指定Date时间前一天的日期
	 * 
	 * @param date
	 *            日期
	 * @return 得到前一天的日期
	 */
	public static Date getYesterdayDate(Date date) {
		Calendar c = getCalendar(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	/**
	 * 得到指定Date时间后一天的日期
	 * 
	 * @param date
	 *            日期
	 * @return 得到后一天的日期
	 */
	public static Date getNextDate(Date date) {
		Calendar c = getCalendar(date);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * 得到指定Date时间的下一个月日期
	 * 
	 * @param date
	 *            日期
	 * @return 得到下一个月的日期
	 */
	public static Date getNextMonth(Date date) {
		Calendar c = getCalendar(date);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}

	/**
	 * 得到指定Date时间的上一个月日期
	 * 
	 * @param date
	 *            日期
	 * @return 得到对应日期的上月日期
	 */
	public static Date getLastMonthDate(Date date) {
		Calendar c = getCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}

	/**
	 * 得到指定时间的上一年的年份
	 * 
	 * @param date
	 *            指定时间
	 * @return 上一年年份，如2012 的得到是2011
	 */
	public static String getLastYear(Date date) {
		Calendar c = getCalendar(date);
		c.add(Calendar.YEAR, -1);
		return String.valueOf(c.get(Calendar.YEAR));
	}

	/**
	 * 得到指定时间的年份
	 * 
	 * @param date
	 *            指定时间
	 * @return 指定时间的年份，如2012
	 */
	public static String getYear(Date date) {
		return String.valueOf(getCalendar(date).get(Calendar.YEAR));
	}

	/**
	 * 获取指定时间的上个月最后一天日期
	 * 
	 * @param date
	 *            指定时间
	 * @return 得到指定时间的上个月最后一天
	 */
	public static Date getLastMonthLastDay(Date date) {
		Calendar c = getCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	/**
	 * 获取指定时间当前月最后一天日期
	 * 
	 * @param date
	 *            指定时间
	 * @return 得到指定时间的最后一天
	 */
	public static Date getCurrentMonthLastDay(Date date) {
		Calendar c = getCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	/**
	 * 得到指定时间的在月中的日期
	 * 
	 * @param date
	 *            时间
	 * @return 日期，得到日期值如21
	 */
	public static String getDay(Date date) {
		return String.valueOf(getCalendar(date).get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 得到指定时间的月份
	 * 
	 * @param date
	 *            时间
	 * @return 指定时间的月份
	 */
	public static String getMonth(Date date) {
		return String.valueOf(getCalendar(date).get(Calendar.MONTH) + 1);
	}

	/**
	 * 得到今天的年份
	 * 
	 * @return 得到今天的年份
	 */
	public static String getTodayYear() {
		return getYear(new Date());
	}

	/**
	 * 得到今天在当月的天数
	 * 
	 * @return 得到今天在当月的天数
	 */
	public static String getTodayDay() {
		return getDay(new Date());
	}

	/**
	 * 得到今天的月份
	 * 
	 * @return 得到今天的月份
	 */
	public static String getTodayMonth() {
		return getMonth(new Date());
	}

	/**
	 * 得到字符串日期的年
	 * 
	 * @param time
	 *            时间
	 * @return 年份
	 */
	public static int getYear(String time) {

		String yearTime = time.substring(0, 4);
		return Integer.parseInt(yearTime);

	}

	/**
	 * 获取指定时间的最后一天
	 * 
	 * @param date
	 *            日期
	 * @return 最后一天
	 */
	public static int getMonthLastDay(Date date) {
		Calendar c = getCalendar(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定月所属的季度
	 * 
	 * @param month
	 *            月份
	 * @return String 月份所在季度
	 */
	public static String getMonthSeason(Integer month) {
		if (month <= 3) {
			return FIRST_SEASON;
		}
		if (month > 3 && month <= 6) {
			return SECOND_SEASON;
		}
		if (month > 6 && month <= 9) {
			return THIRD_SEASON;
		}
		if (month > 9 && month <= 12) {
			return FOURTH_SEASON;
		}
		return null;
	}

	/**
	 * 根据日期得到季度值
	 * 
	 * @param time
	 *            时间
	 * @return 季度值
	 */
	public static int getSeason(String time) {

		String yearTime = time.substring(5, 7);
		int month = Integer.parseInt(yearTime);

		if (month <= 3) {
			return 3;
		}
		if (month > 3 && month <= 6) {
			return 6;
		}
		if (month > 6 && month <= 9) {
			return 9;
		}
		if (month > 9 && month <= 12) {
			return 12;
		}
		return -1;
	}

	/**
	 * 得到Calendar对象
	 * 
	 * @param date
	 *            日期格式字符串
	 * @return Calendar对象
	 */
	public static Calendar getCalendar(String date) {

		String[] dates = date.split("-");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(dates[0]));
		c.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dates[2]));

		return c;
	}

	/**
	 * 获取指定时间的月初(参数是Date格式)
	 * 
	 * @param date
	 *            日期格式的时间
	 * @return String 得到的月初时间，格式为yyyy-MM-dd
	 */
	public static String getFirstDayOfMonth(Date date) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return TimeUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取当前时间的月末(参数是Date格式)
	 * 
	 * @param date
	 *            日期格式的时间
	 * @return String 得到的月末时间，格式为yyyy-MM-dd
	 */
	public static String getLastDayOfMonth(Date date) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return TimeUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 根据指定时间获取时间跨度
	 * 
	 * @param time
	 *            当前时间，年度
	 * @return 如果是当前年或者之后的年则获取至当前月的月度时间值，如果是当前年之前的年份则获取到的是12个月度的值。例如当前年是2012，
	 *         参数time是2012或者2012之后的年份
	 *         ，则获取的月度就是2012-01至当前月；如果当前年是2012，参数time是2011，则获取的月度就是2012
	 *         -01至2012-12
	 */
	public static List<String> getMonthSpan(String time) {
		List<String> timeList = new ArrayList<String>();
		// 当前年
		String year = getYear(new Date());
		// 当前年或者之后的年
		if (Integer.parseInt(time) >= Integer.parseInt(year)) {
			String nowMonth = getMonth(new Date());
			// 获取当前时间的月份，先处理月份
			for (int i = 1; i <= Integer.parseInt(nowMonth); i++) {
				String month = "";
				if (i < 10) {
					month = "0" + i;
				} else {
					month = String.valueOf(i);
				}
				String newTime = time + "-" + month;
				timeList.add(newTime);
			}
			Collections.sort(timeList);
		} else {
			for (int i = 1; i <= 12; i++) {
				String month = "";
				if (i < 10) {
					month = "0" + i;
				} else {
					month = String.valueOf(i);
				}
				String newTime = time + "-" + month;
				timeList.add(newTime);
			}
		}
		return timeList;
	}

	/**
	 * 根据当前时间获取时间的值，带有中文的月度值
	 * 
	 * @param time
	 *            当前时间
	 * @param format
	 *            时间格式
	 * @return 月度集合，如1月、2月、3月。。。
	 */
	public static List<String> getTimeValue(String time, String format) {
		List<String> list = new ArrayList<String>();
		List<String> timeList = getMonthSpan(time);
		for (String str : timeList) {
			Date date = TimeUtil.parseDate(str, format);
			String month = getMonth(date) + "月";
			list.add(month);
		}
		return list;
	}

	/**
	 * 日利润用俞兴进20120419 得到下个月的值
	 * 
	 * @param date
	 *            日期
	 * @return 得到昨日的日期
	 */
	public static Date getNextMonthDate(Date date) {
		Calendar c = getCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}

	/**
	 * 日利润用俞兴进20120419 得到指定月的最大天数
	 * 
	 * @param date
	 *            日期
	 * @return 返回指定月的最大天数
	 */
	public static int getMaxOfMonth(Date date) {

		Calendar calendar = TimeUtil.getCalendar(date);

		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		return calendar.get(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 根据时间周期与默认日期得到具体的时间
	 * 
	 * @param timeCycle
	 *            时间周期
	 * @param defaultTime
	 *            默认时间类型
	 * @return 格式化好的日期值
	 */
	public static String getTimeByTimeCycleAndDefaultAndNowTime(
			String timeCycle, String defaultTime, String now) {

		Date nowDate = null;
		if (timeCycle.equals(TIME_DAY)) {
			nowDate = TimeUtil.parseDate(now, "yyyy-MM-dd");
		} else if (timeCycle.equals(TIME_YEAR)) {
			nowDate = TimeUtil.parseDate(now, "yyyy");
		} else {
			nowDate = TimeUtil.parseDate(now, "yyyy-MM");
		}
		Calendar calendar = getCalendar(nowDate);

		// 如果统计周期为日
		if (timeCycle.equals(TIME_DAY)) {

			if (defaultTime.equals("lastDay")) {
				Date yesterdayDate = getYesterdayDate(nowDate);
				return formatDay(yesterdayDate);
			}

			if (defaultTime.equals("thisDay")) {
				return formatDay(nowDate);
			}

			if (defaultTime.equals("nextDay")) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				Date nextDayDate = calendar.getTime();
				return formatDay(nextDayDate);
			}

		}

		// 如果统计周期为月
		if (timeCycle.equals(TIME_MONTH)) {

			if (defaultTime.equals("lastMonth")) {
				Date lastMonthDate = getLastMonthDate(nowDate);
				return formatMonth(lastMonthDate);
			}

			if (defaultTime.equals("thisMonth")) {
				return formatMonth(nowDate);
			}

			if (defaultTime.equals("nextMonth")) {
				calendar.add(Calendar.MONTH, 1);
				Date nextDayDate = calendar.getTime();
				return formatMonth(nextDayDate);
			}

		}

		// 如果统计周期为年
		if (timeCycle.equals(TIME_YEAR)) {

			if (defaultTime.equals("thisYear")) {
				return getYear(nowDate);
			}

			if (defaultTime.equals("lastYear")) {
				calendar.add(Calendar.YEAR, -1);
				return calendar.get(Calendar.YEAR) + "";
			}

			if (defaultTime.equals("nextYear")) {
				calendar.add(Calendar.YEAR, 1);
				return calendar.get(Calendar.YEAR) + "";
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] times = format.format(nowDate).split("-");
		int month = Integer.parseInt(times[1]);
		int year = Integer.parseInt(times[0]);
		// 如果统计周期为半年
		if (timeCycle.equals(TIME_HALF_YEAR)) {
			if (defaultTime.equals("thisHalfYear")) {
				if (month <= 6) {
					return year + "-06";
				} else {
					return year + "-12";
				}
			}

			if (defaultTime.equals("lastHalfYear")) {
				if (month <= 6) {
					return (year - 1) + "-12";
				} else {
					return year + "-06";
				}
			}

			if (defaultTime.equals("nextHalfYear")) {
				if (month <= 6) {
					return year + "-12";
				} else {
					return (year + 1) + "-06";
				}
			}

		}

		// 如果统计周期为季度
		if (timeCycle.equals(TIME_SEASON)) {
			if (defaultTime.equals("thisSeason")) {
				if (month <= 3) {
					return year + "-03";
				} else if (month > 3 && month <= 6) {
					return year + "-06";
				} else if (month > 6 && month <= 9) {
					return year + "-09";
				} else {
					return year + "-12";
				}
			}

			if (defaultTime.equals("lastSeason")) {
				if (month <= 3) {
					return (year - 1) + "-12";
				} else if (month > 3 && month <= 6) {
					return year + "-03";
				} else if (month > 6 && month <= 9) {
					return year + "-06";
				} else {
					return year + "-09";
				}
			}

			if (defaultTime.equals("nextSeason")) {
				if (month <= 3) {
					return year + "-06";
				} else if (month > 3 && month <= 6) {
					return year + "-09";
				} else if (month > 6 && month <= 9) {
					return year + "-12";
				} else {
					return (year + 1) + "-03";
				}
			}
		}

		return null;

	}

	/**
	 * 根据时间周期与默认日期得到具体的时间
	 * 
	 * @param timeCycle
	 *            时间周期
	 * @param defaultTime
	 *            默认时间类型
	 * @return 格式化好的日期值
	 */
	public static String getTimeByTimeCycleAndDefault(String timeCycle,
			String defaultTime) {

		Date nowDate = new Date();
		Calendar calendar = getCalendar(nowDate);

		// 如果统计周期为日
		if (timeCycle.equals(TIME_DAY)) {

			if (defaultTime.equals("lastDay")) {
				Date yesterdayDate = getYesterdayDate(nowDate);
				return formatDay(yesterdayDate);
			}

			if (defaultTime.equals("thisDay")) {
				return formatDay(nowDate);
			}

			if (defaultTime.equals("nextDay")) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				Date nextDayDate = calendar.getTime();
				return formatDay(nextDayDate);
			}

		}

		// 如果统计周期为月
		if (timeCycle.equals(TIME_MONTH)) {

			if (defaultTime.equals("lastMonth")) {
				Date lastMonthDate = getLastMonthDate(nowDate);
				return formatMonth(lastMonthDate);
			}

			if (defaultTime.equals("thisMonth")) {
				return formatMonth(nowDate);
			}

			if (defaultTime.equals("nextMonth")) {
				calendar.add(Calendar.MONTH, 1);
				Date nextDayDate = calendar.getTime();
				return formatMonth(nextDayDate);
			}

		}

		// 如果统计周期为年
		if (timeCycle.equals(TIME_YEAR)) {

			if (defaultTime.equals("thisYear")) {
				return getYear(nowDate);
			}

			if (defaultTime.equals("lastYear")) {
				calendar.add(Calendar.YEAR, -1);
				return calendar.get(Calendar.YEAR) + "";
			}

			if (defaultTime.equals("nextYear")) {
				calendar.add(Calendar.YEAR, 1);
				return calendar.get(Calendar.YEAR) + "";
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] times = format.format(nowDate).split("-");
		int month = Integer.parseInt(times[1]);
		int year = Integer.parseInt(times[0]);
		// 如果统计周期为半年
		if (timeCycle.equals(TIME_HALF_YEAR)) {
			if (defaultTime.equals("thisHalfYear")) {
				if (month <= 6) {
					return year + "-06";
				} else {
					return year + "-12";
				}
			}

			if (defaultTime.equals("lastHalfYear")) {
				if (month <= 6) {
					return (year - 1) + "-12";
				} else {
					return year + "-06";
				}
			}

			if (defaultTime.equals("nextHalfYear")) {
				if (month <= 6) {
					return year + "-12";
				} else {
					return (year + 1) + "-06";
				}
			}

		}

		// 如果统计周期为季度
		if (timeCycle.equals(TIME_SEASON)) {
			if (defaultTime.equals("thisSeason")) {
				if (month <= 3) {
					return year + "-03";
				} else if (month > 3 && month <= 6) {
					return year + "-06";
				} else if (month > 6 && month <= 9) {
					return year + "-09";
				} else {
					return year + "-12";
				}
			}

			if (defaultTime.equals("lastSeason")) {
				if (month <= 3) {
					return (year - 1) + "-12";
				} else if (month > 3 && month <= 6) {
					return year + "-03";
				} else if (month > 6 && month <= 9) {
					return year + "-06";
				} else {
					return year + "-09";
				}
			}

			if (defaultTime.equals("nextSeason")) {
				if (month <= 3) {
					return year + "-06";
				} else if (month > 3 && month <= 6) {
					return year + "-09";
				} else if (month > 6 && month <= 9) {
					return year + "-12";
				} else {
					return (year + 1) + "-03";
				}
			}
		}

		return null;

	}

	/**
	 * 根据时间周期与默认日期得到具体的时间
	 * 
	 * @param timeCycle
	 *            时间周期
	 * @param defaultTime
	 *            默认时间类型
	 * @return 格式化好的日期值
	 */
	public static String[] getTimeStringsForSeasonAndHalfYear(String timeCycle,
			String defaultTime) {

		Date nowDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] times = format.format(nowDate).split("-");
		int month = Integer.parseInt(times[1]);
		int year = Integer.parseInt(times[0]);
		// 如果统计周期为半年
		if (timeCycle.equals(TIME_HALF_YEAR)) {
			if (defaultTime.equals("thisHalfYear")) {
				if (month <= 6) {
					String[] result = new String[] { year + "-01",
							year + "-02", year + "-03", year + "-04",
							year + "-05", year + "-06" };
					return result;
				} else {
					String[] result = new String[] { year + "-07",
							year + "-08", year + "-09", year + "-10",
							year + "-11", year + "-12" };
					return result;
				}
			}

			if (defaultTime.equals("lastHalfYear")) {
				if (month <= 6) {
					String[] result = new String[] { (year - 1) + "-07",
							(year - 1) + "-08", (year - 1) + "-09",
							(year - 1) + "-10", (year - 1) + "-11",
							(year - 1) + "-12" };
					return result;
				} else {
					String[] result = new String[] { year + "-01",
							year + "-02", year + "-03", year + "-04",
							year + "-05", year + "-06" };
					return result;
				}
			}

			if (defaultTime.equals("nextHalfYear")) {
				if (month <= 6) {
					String[] result = new String[] { year + "-07",
							year + "-08", year + "-09", year + "-10",
							year + "-11", year + "-12" };
					return result;
				} else {
					String[] result = new String[] { (year + 1) + "-01",
							(year + 1) + "-02", (year + 1) + "-03",
							(year + 1) + "-04", (year + 1) + "-05",
							(year + 1) + "-06" };
					return result;
				}
			}

		}

		// 如果统计周期为季度
		if (timeCycle.equals(TIME_SEASON)) {
			if (defaultTime.equals("thisSeason")) {
				if (month <= 3) {
					String[] result = new String[] { year + "-01",
							year + "-02", year + "-03" };
					return result;
				} else if (month > 3 && month <= 6) {
					String[] result = new String[] { year + "-04",
							year + "-05", year + "-06" };
					return result;
				} else if (month > 6 && month <= 9) {
					String[] result = new String[] { year + "-07",
							year + "-08", year + "-09" };
					return result;
				} else {
					String[] result = new String[] { year + "-10",
							year + "-11", year + "-12" };
					return result;
				}
			}

			if (defaultTime.equals("lastSeason")) {
				if (month <= 3) {
					String[] result = new String[] { (year - 1) + "-10",
							(year - 1) + "-11", (year - 1) + "-12" };
					return result;
				} else if (month > 3 && month <= 6) {
					String[] result = new String[] { year + "-01",
							year + "-02", year + "-03" };
					return result;
				} else if (month > 6 && month <= 9) {
					String[] result = new String[] { year + "-04",
							year + "-05", year + "-06" };
					return result;
				} else {
					String[] result = new String[] { year + "-07",
							year + "-08", year + "-09" };
					return result;
				}
			}

			if (defaultTime.equals("nextSeason")) {
				if (month <= 3) {
					String[] result = new String[] { year + "-04",
							year + "-05", year + "-06" };
					return result;
				} else if (month > 3 && month <= 6) {
					String[] result = new String[] { year + "-07",
							year + "-08", year + "-09" };
					return result;
				} else if (month > 6 && month <= 9) {
					String[] result = new String[] { year + "-10",
							year + "-11", year + "-12" };
					return result;
				} else {
					String[] result = new String[] { (year + 1) + "-01",
							(year + 1) + "-02", (year + 1) + "-03" };
					return result;
				}
			}
		}

		return null;

	}

	/**
	 * 根据时间周期与默认日期得到具体的时间
	 * 
	 * @param timeCycle
	 *            时间周期
	 * @param defaultTime
	 *            默认时间类型
	 * @param nowDate
	 *            当前日期
	 * @return 格式化好的日期值
	 */
	public static String getTimeByTimeCycleAndDefaultAndDate(String timeCycle,
			String defaultTime, Date nowDate) {

		Calendar calendar = getCalendar(nowDate);

		// 如果统计周期为日
		if (timeCycle.equals(TIME_DAY)) {

			if (defaultTime.equals("lastDay")) {
				Date yesterdayDate = getYesterdayDate(nowDate);
				return formatDay(yesterdayDate);
			}

			if (defaultTime.equals("thisDay")) {
				return formatDay(nowDate);
			}

			if (defaultTime.equals("nextDay")) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				Date nextDayDate = calendar.getTime();
				return formatDay(nextDayDate);
			}

		}

		// 如果统计周期为月
		if (timeCycle.equals(TIME_MONTH)) {

			if (defaultTime.equals("lastMonth")) {
				Date lastMonthDate = getLastMonthDate(nowDate);
				return formatMonth(lastMonthDate);
			}

			if (defaultTime.equals("thisMonth")) {
				return formatMonth(nowDate);
			}

			if (defaultTime.equals("nextMonth")) {
				calendar.add(Calendar.MONTH, 1);
				Date nextDayDate = calendar.getTime();
				return formatMonth(nextDayDate);
			}

		}

		// 如果统计周期为年
		if (timeCycle.equals(TIME_YEAR)) {

			if (defaultTime.equals("thisYear")) {
				return getYear(nowDate);
			}

			if (defaultTime.equals("lastYear")) {
				calendar.add(Calendar.YEAR, -1);
				return calendar.get(Calendar.YEAR) + "";
			}

			if (defaultTime.equals("nextYear")) {
				calendar.add(Calendar.YEAR, 1);
				return calendar.get(Calendar.YEAR) + "";
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String timeTemp = format.format(nowDate.getTime());
		String[] times = timeTemp.split("-");
		int year = Integer.parseInt(times[0]);
		int month = Integer.parseInt(times[1]);
		// 如果统计周期为半年
		if (timeCycle.equals(TIME_HALF_YEAR)) {
			if (defaultTime.equals("thisHalfYear")) {
				if (month <= 6) {
					return year + "-06" + ";" + year + "年上半年";
				} else {
					return year + "-12" + ";" + year + "年下半年";
				}
			}

			if (defaultTime.equals("lastHalfYear")) {
				if (month <= 6) {
					return (year - 1) + "-12" + ";" + year + "年下半年";
				} else {
					return year + "-06" + ";" + year + "年上半年";
				}
			}

			if (defaultTime.equals("nextHalfYear")) {
				if (month <= 6) {
					return year + "-12" + ";" + year + "年下半年";
				} else {
					return (year + 1) + "-06" + ";" + year + "年上半年";
				}
			}

		}

		// 如果统计周期为季度
		if (timeCycle.equals(TIME_SEASON)) {
			if (defaultTime.equals("thisSeason")) {
				if (month <= 3) {
					return year + "-03" + ";" + year + "年第一季度";
				} else if (month > 3 && month <= 6) {
					return year + "-06" + ";" + year + "年第二季度";
				} else if (month > 6 && month <= 9) {
					return year + "-09" + ";" + year + "年第三季度";
				} else {
					return year + "-12" + ";" + year + "年第四季度";
				}
			}

			if (defaultTime.equals("lastSeason")) {
				if (month <= 3) {
					return (year - 1) + "-12" + ";" + year + "年第四季度";
				} else if (month > 3 && month <= 6) {
					return year + "-03" + ";" + year + "年第一季度";
				} else if (month > 6 && month <= 9) {
					return year + "-06" + ";" + year + "年第二季度";
				} else {
					return year + "-09" + ";" + year + "年第三季度";
				}
			}

			if (defaultTime.equals("nextSeason")) {
				if (month <= 3) {
					return year + "-06" + ";" + year + "年第二季度";
				} else if (month > 3 && month <= 6) {
					return year + "-09" + ";" + year + "年第三季度";
				} else if (month > 6 && month <= 9) {
					return year + "-12" + ";" + year + "年第四季度";
				} else {
					return (year + 1) + "-03" + ";" + year + "年第一季度";
				}
			}
		}

		return null;

	}

	/**
	 * 获取两段日期内的所有日期列表
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param dateFormat
	 *            格式
	 * @return 两段时间内的所有字符类型日期
	 */
	public static List<String> getBetweenDate(String startDate, String endDate,
			String dateFormat) {
		List<String> list = new ArrayList<String>();
		Calendar startCal = getCalendar(startDate);
		Calendar endCal = getCalendar(endDate);
		while (startCal.before(endCal)) {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			list.add(formatDate(startCal.getTime(), dateFormat));
		}
		return list;
	}

	/**
	 * 获取两段时间内的总小时
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 总小时数
	 */
	public static Long getTimeSpanHour(Date startDate, Date endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		Long hour = diff / (60 * 60 * 1000);
		return hour;
	}

	/**
	 * 获取两段日期内的所有月份(包含结束时间，不包含开始时间)
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param dateFormat
	 *            格式
	 * @return List<String>
	 */
	public static List<String> getBetweenMonth(String startDate,
			String endDate, String dateFormat) {
		List<String> list = new ArrayList<String>();
		Calendar startCal = getCalendar(startDate);
		Calendar endCal = getCalendar(endDate);
		while (startCal.before(endCal)) {
			startCal.add(Calendar.MONTH, 1);
			list.add(formatDate(startCal.getTime(), dateFormat));
		}
		return list;
	}

	/**
	 * 判断日期是否为周六周日
	 * 
	 * @param dt
	 *            String型的日期参数
	 * 
	 * @return boolean 是否周末（是周末返回：true，不是周末返回：false）
	 */
	public static boolean weekJudge(String dt) {
		boolean bl = false;
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdfInput.parse(dt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (dayOfWeek) {
		case 0:
			bl = true;
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			bl = true;
			break;
		}
		return bl;
	}

	/**
	 * 取得当前日期所在周的第一天（以星期一为一周的开始）
	 * 
	 * @param date
	 *            当前日期
	 * @return 当前日期所在周的第一天
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime();
	}

	/**
	 * 返回上一期的日期值
	 * 
	 * @param code
	 *            时间周期编码
	 * @param value
	 *            需要转化的时间
	 * @return 上一期时间值
	 */
	public static String last(String code, String value) {

		if (code.equals(TIME_DAY)) {
			Calendar c = TimeUtil.getCalendar(value);
			c.add(Calendar.DAY_OF_MONTH, -1);

			return TimeUtil.formatDate(c.getTime(), DATE_DAY_FORMAT);
		}

		if (code.equals(TIME_MONTH)) {

			String tempValue = value + "-01";

			Calendar c = TimeUtil.getCalendar(tempValue);
			c.add(Calendar.MONTH, -1);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String monthStr = String.valueOf(month);
			if (month < 10) {
				monthStr = "0" + month;
			}

			return year + "-" + monthStr;
		}

		if (code.equals(TIME_YEAR)) {
			return (Integer.parseInt(value) - 1) + "";
		}

		if (code.equals(TIME_SEASON)) {

			String[] seasons = value.split("-");
			String yearStr = seasons[0];
			String seasonStr = seasons[1];

			int year = Integer.parseInt(yearStr);
			int season = Integer.parseInt(seasonStr);

			if (season == 3) {
				year = year - 1;
				season = 12;
			} else {
				season = season - 3;
			}

			String month = "";
			if (season < 10) {
				month = "0" + season;
			} else {
				month = String.valueOf(season);
			}
			return year + "-" + month;

		}

		if (code.equals(TIME_HALF_YEAR)) {
			String[] halfYears = value.split("-");
			String yearStr = halfYears[0];
			String halfYearStr = halfYears[0];

			int year = Integer.parseInt(yearStr);
			int halfYear = Integer.parseInt(halfYearStr);

			if (halfYear == 6) {
				year = year - 1;
				halfYear = 12;
			} else {
				halfYear = 6;
			}
			String month = String.valueOf(halfYear);
			if (halfYear < 10) {
				month = "0" + halfYear;
			}
			return year + "-" + month;
		}

		return null;
	}

}