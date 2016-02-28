package com.finance.framework.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {
    /**
     * ********************AM上午*********************
     */
    public final static int AM = 0;
    /**
     * ********************PM下午*********************
     */
    public final static int PM = 1;
    /**
     * ********************TIMEZONE_CN时区*********************
     */

    public static final String TIMEZONE_CN = "Asia/Shanghai";
    /**
     * ********************SIMPLEFORMATTYPE对应的字串*********************
     */
    /**
     * SIMPLEFORMATTYPESTRING1 对应类型：yyyyMMddHHmmss
     */
    public final static String SIMPLEFORMATTYPESTRING1 = "yyyyMMddHHmmss";

    /**
     * SIMPLEFORMATTYPESTRING2 对应的类型：yyyy-MM-dd HH:mm:ss
     */
    public final static String SIMPLEFORMATTYPESTRING2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * SIMPLEFORMATTYPESTRING3 对应的类型：yyyy-M-d HH:mm:ss
     */
    public final static String SIMPLEFORMATTYPESTRING3 = "yyyy-M-d HH:mm:ss";

    /**
     * SIMPLEFORMATTYPESTRING4对应的类型：yyyy-MM-dd HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING4 = "yyyy-MM-dd HH:mm";

    /**
     * SIMPLEFORMATTYPESTRING5 对应的类型：yyyy-M-d HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING5 = "yyyy-M-d HH:mm";

    /**
     * SIMPLEFORMATTYPESTRING6对应的类型：yyyyMMdd
     */
    public final static String SIMPLEFORMATTYPESTRING6 = "yyyyMMdd";

    /**
     * SIMPLEFORMATTYPESTRING7对应的类型：yyyy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING7 = "yyyy-MM-dd";


    /**
     * SIMPLEFORMATTYPESTRING8对应的类型： yyyy-M-d
     */
    public final static String SIMPLEFORMATTYPESTRING8 = "yyyy-M-d";

    /**
     * SIMPLEFORMATTYPESTRING9对应的类型：yyyy年MM月dd日
     */
    public final static String SIMPLEFORMATTYPESTRING9 = "yyyy年MM月dd日";

    /**
     * SIMPLEFORMATTYPESTRING10对应的类型：yyyy年M月d日
     */
    public final static String SIMPLEFORMATTYPESTRING10 = "yyyy年M月d日";

    /**
     * SIMPLEFORMATTYPESTRING11对应的类型：M月d日
     */
    public final static String SIMPLEFORMATTYPESTRING11 = "M月d日";

    /**
     * SIMPLEFORMATTYPESTRING12对应的类型：HH:mm:ss
     */
    public final static String SIMPLEFORMATTYPESTRING12 = "HH:mm:ss";

    /**
     * SIMPLEFORMATTYPESTRING13对应的类型：HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING13 = "HH:mm";
    /**
     * SIMPLEFORMATTYPESTRING14对应的类型：yyyy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING14 = "yyyy/MM/dd";
    /**
     * SIMPLEFORMATTYPESTRING15对应的类型：MM/dd
     */
    public final static String SIMPLEFORMATTYPESTRING15 = "MM/dd";
    /**
     * SIMPLEFORMATTYPESTRING16对应的类型：MM月dd日
     */
    public final static String SIMPLEFORMATTYPESTRING16 = "MM月dd日";
    /**
     * SIMPLEFORMATTYPESTRING17对应的类型：MM月dd日 HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING17 = "MM月dd日 HH:mm";
    /**
     * SIMPLEFORMATTYPESTRING18对应的类型：yyyy年MM月dd日 HH:mm
     */
    public final static String SIMPLEFORMATTYPESTRING18 = "yyyy年MM月dd日 HH:mm";

    /**
     * SIMPLEFORMATTYPESTRING7对应的类型： yy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING19 = "yy-MM-dd";

    /**
     * SIMPLEFORMATTYPESTRING20对应的类型： yy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING20 = "yy-MM-dd  HH:mm";

    /**
     * SIMPLEFORMATTYPESTRING20对应的类型： yy-MM-dd
     */
    public final static String SIMPLEFORMATTYPESTRING21 = "MM月dd日  HH:mm ";

    /**
     * 将时间转换为simpleFormatString对应的格式输出
     *
     * @param calendar
     * @param simpleFormatString
     * @return
     */
    public static String getCalendarStrBySimpleDateFormat(Calendar calendar, String simpleFormatString) {
        String str = "";
        if (!StringUtil.isEmptyOrNull(simpleFormatString) && calendar != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(simpleFormatString);
            dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
            str = (dateFormat).format(calendar.getTime());
        }
        return str;
    }

    /**
     * 将时间转换为simpleFormatString对应的格式输出
     *
     * @param time
     * @param simpleFormatString
     * @return
     */
    public static String getCalendarStrBySimpleDateFormat(Long time, String simpleFormatString) {
        if (time == null) return "";
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat(simpleFormatString);
        return dateFormat.format(date);
    }

    /**
     * String 转Date
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
        }
        return date;
    }



    /**
     * String 转Date
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING7);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
        }
        return date;
    }


    /**
     * 将时间字串#yyyyMMddHHmmss转为 Calendar
     *
     * @param dateStr 小于8位时返回null，不足14位补0
     * @return
     */
    @SuppressWarnings("ResourceType")
    public static Calendar getCalendarByDateTimeStr(String dateStr) {
        if (StringUtil.isEmptyOrNull(dateStr) || dateStr.length() < 8)
            return null;
        while (dateStr.length() < 14) {
            dateStr += "0";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
        int year = StringUtil.toInt(dateStr.substring(0, 4));
        int month = StringUtil.toInt(dateStr.substring(4, 6));
        int day = StringUtil.toInt(dateStr.substring(6, 8));
        int hour = StringUtil.toInt(dateStr.substring(8, 10));
        int min = StringUtil.toInt(dateStr.substring(10, 12));
        int second = 0;
        if (dateStr.length() >= 14)
            second = StringUtil.toInt(dateStr.substring(12, 14));
        calendar.set(year, month - 1, day, hour, min, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 获取手机系统日期
     *
     * @return
     */
    public static Calendar getCalendarTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
        return calendar;
    }

    /**
     * SIMPLEFORMATTYPESTRING2 对应的类型：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String currentTimeMillis() {
        SimpleDateFormat formatter = new SimpleDateFormat(SIMPLEFORMATTYPESTRING2);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    /**
     * SIMPLEFORMATTYPESTRING2 对应的类型：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(SIMPLEFORMATTYPESTRING2);
        String strDate = format.format(date);
        return strDate;
    }

    /**
     * 获取服务器系统时间
     *
     * @return
     */
    public static Calendar getCurrentTime() {
        return TimeUtil.getCurrentTime();
    }

    /**
     * 发布时间格式： 1小时内:XX分钟前 24小时内：XX小时前 7天内：X天前 超过7天：显示日期
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String fattrDate(Date date) {
        String fattrStr = "";
        if (date != null) {
            long time = getCurrentTime().getTime().getTime() - date.getTime();// 得到 当前时间的毫秒数
            long minute = time / toMinute; // 分钟
            long hours = time / toHour;// 小时
            long day = caculateDay(getCurrentTime().getTime(), date); // 天
            if (day >= 7) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
                fattrStr = sdf.format(date);
            } else if (day >= 1 && day < 7) {
                fattrStr = day + "天前";
            } else if (1 <= hours && hours < 24) {
                fattrStr = hours + "小时前";
            } else if (hours < 1) {
                fattrStr = (minute <= 1 ? 1 : minute) + "分钟前";
            }
        }
        return fattrStr;
    }

    /**
     * 如果小于1min，展示1分钟前；如果小于60min，展现XX分钟前；如果小于24h，展现XX小时前；如果小于1个月，展示XX天前；如果大于1个月，展示XX月前
     *
     * @param date
     * @return
     */

    public static String fattrDate2(Date date) {
        String fattrStr = "";
        if (date != null) {
            Date now = new Date();
            Calendar c = Calendar.getInstance();
            long time = now.getTime() - date.getTime();// 得到 当前时间的毫秒数
            long minute = time / toMinute; // 分钟
            long hours = time / toHour;// 小时

            long day = caculateDay(c.getTime(), date); // 天
            if (day >= 30) {
                fattrStr = day / 30 + "月前";
            } else if (day >= 1 && day < 30) {
                fattrStr = day + "天前";
            } else if (1 <= hours && hours < 24) {
                fattrStr = hours + "小时前";
            } else if (minute <= 1) {
                fattrStr = "1分钟内";
                return fattrStr;
            } else if (hours < 1) {
                fattrStr = (minute <= 1 ? 1 : minute) + "分钟前";
            }
        }

        return fattrStr;

    }


    private final static long toDay = 1000 * 60 * 60 * 24; // 将毫秒数转换为天
    private final static long toHour = 1000 * 60 * 60; // 将毫秒数转换为小时
    private final static long toMinute = 1000 * 60; // 将毫秒数转换为分钟

    /**
     * 发布时间格式： 1小时内:XX分钟前 24小时内：XX小时前 7天内：X天前
     * <p/>
     * 超过一天显示“**天前”；当天显示“刚刚”
     *
     * @param date
     * @return
     */
    public static String fattrDay(Date date) {
        String fattrStr = "";
        if (date != null) {
            long currentDay = getCurrentTime().getTimeInMillis() / toDay;
            long desDay = date.getTime() / toDay;
            if (currentDay == desDay) {
                fattrStr = "刚刚";
            } else {
                long day = currentDay - desDay;// 得到 当前相隔的天数
                fattrStr = day + "天前";
            }
        }
        return fattrStr;
    }

    /**
     * 返回两个日期的小时差，异常返回-1；
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long caculateHour(Date date1, Date date2) {

        long result = -1;
        if (date1 != null && date2 != null) {
            long maxDay = date1.getTime() / toHour;
            long minDay = date2.getTime() / toHour;
            result = maxDay > minDay ? maxDay - minDay : minDay - maxDay;
        }
        return result;

    }

    /**
     * 返回两个日期的天数差，同一天返回0
     *
     * @param fromDate
     * @param desDate
     * @return fromDate - desDate
     */
    public static long caculateDay(Date fromDate, Date desDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(SIMPLEFORMATTYPESTRING7);
            Date from = format.parse(format.format(fromDate));
            Date dest = format.parse(format.format(desDate));
            return (from.getTime() - dest.getTime()) / toDay;
        } catch (Exception e) {
            return Long.MAX_VALUE;
        }
    }

    /**
     * 返回目标日期同今天的天数差;
     *
     * @param desDate
     * @return [今天]-desDate
     */
    public static long caculateDayFromToday(Date desDate) {
        return caculateDay(getCurrentTime().getTime(), desDate);
    }

    /**
     * 返回目标日期同今天的天数差，同一天返回true，异常返回false；
     *
     * @param dateA , dateB
     * @return
     */

    public static boolean isSameDay(Date dateA, Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * /**
     * 返回目标日期同今天的天数差，同一天返回true，异常返回false；
     *
     * @param desDate
     * @return
     */
    public static boolean isToday(Date desDate) {
        return isSameDay(getCurrentTime().getTime(), desDate);
    }

    /**
     * ryan
     * 日期 + 时间
     * D日 0点0分0秒 到 23点59分59秒 ： 今天 HH24:mm
     * D - 1+日 0点0分0秒 到 23点59分59秒 ： 昨天 HH24:mm
     * D - 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd HH24:mm  “3月19日 10:30”
     * D + 1+日 0点0分0秒 到 23点59分59秒 ： 明天 HH24:mm
     * D + 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd HH24:mm
     */
    public static String displayDateAndTime(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            int day = (int) caculateDayFromToday(date); // 天
            switch (day) {
                case -1:
                    dateFormart.append("明天");
                    break;
                case 0:
                    dateFormart.append("今天");
                    break;
                case 1:
                    dateFormart.append("昨天");
                    break;
                default:
                    SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING16);
                    dateFormart.append(sdf.format(date) + " ");
                    break;
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat(SIMPLEFORMATTYPESTRING13);
            dateFormart.append(" ");
            dateFormart.append(timeFormat.format(date));
        }
        return dateFormart.toString();
    }


    /**
     * ryan
     * 日期 + 时间
     * D日 0点0分0秒 到 23点59分59秒 ： 今天 HH24:mm
     * D - 1+日 0点0分0秒 到 23点59分59秒 ： 昨天 HH24:mm
     * D - 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd HH24:mm  “3月19日 10:30”
     * D + 1+日 0点0分0秒 到 23点59分59秒 ： 明天 HH24:mm
     * D + 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd HH24:mm
     */
    public static String displayDateAndTime(Date date, Date currentDate) {
        if (date == null) return "";

        StringBuilder dateFormart = new StringBuilder("");

        if (currentDate != null) {
            long disDay = caculateDay(currentDate, date);
            if (disDay == -1L) dateFormart.append("明天");
            else if (disDay == 0L) dateFormart.append("今天");
            else if (disDay == 1L) dateFormart.append("昨天");
        }

        if (TextUtils.isEmpty(dateFormart.toString())) {
            dateFormart.append(new SimpleDateFormat(SIMPLEFORMATTYPESTRING16).format(date));
        }

        dateFormart.append(" ");

        SimpleDateFormat timeFormat = new SimpleDateFormat(SIMPLEFORMATTYPESTRING13);
        dateFormart.append(timeFormat.format(date));
        return dateFormart.toString();
    }


    public static String displayDateAndTime(String time) {
        Date mDate = new Date(Long.parseLong(time));
        return displayMarkContactDateAndTime(mDate);
    }

    /**
     * 日期 + 时间
     * D日 0点0分0秒 到 23点59分59秒 ： 今天 HH24:mm
     * D - 1+日 0点0分0秒 到 23点59分59秒 ： 昨天 HH24:mm
     * D - 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd HH24:mm  “3月19日 10:30”
     * D + 1+日 0点0分0秒 到 23点59分59秒 ： 明天 HH24:mm
     * D + 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd HH24:mm
     */
    public static String displayMarkContactDateAndTime(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            int day = (int) caculateDayFromToday(date); // 天
            switch (day) {
                case -1:
                    dateFormart.append("明天");
                    break;
                case 0:
                    dateFormart.append("今天");
                    break;
                case 1:
                    dateFormart.append("昨天");
                    break;
                default:
                    SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING16);
                    dateFormart.append(sdf.format(date) + "");
                    break;
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat(SIMPLEFORMATTYPESTRING13);
            dateFormart.append(" ");
            dateFormart.append(timeFormat.format(date));
        }
        return dateFormart.toString();
    }


    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat(SIMPLEFORMATTYPESTRING2);
        try {

            Date date1 = new Date(1426521620000L);
//            Date date2 = format.parse("2015-3-16 23:23:23");
            Date date2 = getCurrentTime().getTime();
            System.out.println(caculateDay(date2, date1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ryan
     * 只有时间
     * HH24:mm
     */
    public static String displayTime(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING13);
            dateFormart.append(sdf.format(date));
        }
        return dateFormart.toString();
    }

    /**
     * 获取日期
     * yyyy-MM-dd
     */
    public static String getDate(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING7);
            dateFormart.append(sdf.format(date));
        }
        return dateFormart.toString();
    }

    /**
     * 获取日期
     * SIMPLEFORMATTYPESTRING6对应的类型：yyyyMMdd
     * String 转Date
     *
     * @param strDate
     * @return
     */
    public static String strToDate6(String strDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(SIMPLEFORMATTYPESTRING7);
        SimpleDateFormat sdf2 = new SimpleDateFormat(SIMPLEFORMATTYPESTRING6);
        Date date = null;
        String str = null;
        try {
            date = sdf1.parse(strDate);
            str = sdf2.format(date);
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * ryan
     * 只有日期
     * D日 0点0分0秒 到 23点59分59秒 ： 今天
     * D - 1+日 0点0分0秒 到 23点59分59秒 ： 昨天
     * D - 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd
     * D + 1+日 0点0分0秒 到 23点59分59秒 ： 明天
     * D + 2+日 0点0分0秒 到 23点59分59秒 ： yy-MM-dd
     */
    public static String displayDate(Date date) {
        StringBuilder dateFormart = new StringBuilder();
        if (date != null) {
            int day = (int) caculateDayFromToday(date); // 天
            switch (day) {
                case -1:
                    dateFormart.append("明天");
                    break;
                case 0:
                    dateFormart.append("今天");
                    break;
                case 1:
                    dateFormart.append("昨天");
                    break;
                default:
                    SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING16);
                    dateFormart.append(sdf.format(date));
                    break;
            }
        }
        return dateFormart.toString();
    }

    /**
     * 把毫秒转化成日期
     *
     * @param dateFormat(日期格式，例如：MM/ dd/yyyy HH:mm:ss)
     * @param millSec(毫秒数)
     * @return
     */
    public static Date transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return date;
    }

    /**
     * 把毫秒转化成日期
     *
     * @param dateFormat(日期格式，例如：MM/ dd/yyyy HH:mm:ss)
     * @param millSec(毫秒数)
     * @return
     */
    public static String transitionLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING9);
        long between_days = 0;
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (Exception e) {
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 比较两个日期先后
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean compareDate(String beginDate, String endDate) {
        DateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING7);
        Date begin = null;
        Date end = null;
        try {
            begin = sdf.parse(beginDate);
            end = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (begin.before(end)) {
            //表示bt小于et
            return false;
        } else {
            //反之
            return true;
        }
    }

    /**
     * 获取明天 日期
     *
     * @return
     */
    public static String getTomarrowDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(SIMPLEFORMATTYPESTRING7);
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param startDate 当前日期 yyyy-MM-dd HH:mm:ss
     * @param endDate   结束日期 yyyy-MM-dd HH:mm:ss
     * @param strTime   结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(Date startDate, Date endDate, String strTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLEFORMATTYPESTRING2);
        String strDate = sdf.format(startDate);
        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strTime.substring(0, 2));
        int strDateEndM = Integer.parseInt(strTime.substring(3, 5));
        int strDateEndS = Integer.parseInt(strTime.substring(6, 8));

//        daysBetween(startDate,endDate);

        // 当前时间小时数在开始时间和结束时间小时数之间
        if (strDateH < strDateEndH) {
            return true;
            // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
        } else if (strDateM <= strDateEndM) {
            return true;
            // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
        } else if (strDateS <= strDateEndS) {
            return true;
        }
        // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
        else if (strDateH == strDateEndH
                && strDateM <= strDateEndM) {
            return true;
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
        } else if (strDateH == strDateEndH
                && strDateM == strDateEndM && strDateS <= strDateEndS) {
            return true;
        } else {
            return false;
        }
    }

//    public static java.lang.String displayDateAndTime(java.lang.String time) { /* compiled code */ }

}
