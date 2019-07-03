package com.popcivilar.youth.youthbase.utils;

import com.xiaoleilu.hutool.util.ObjectUtil;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 工具类
 * @author Administrator
 * @time 2017年11月3日
 */
public class StringUtil {

	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private static Pattern humpPattern = Pattern.compile("[A-Z]");
	public static final String PATTERN_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_DATE_YYYYMMDD="yyyyMMdd";

	/**
	 * 下划线转驼峰 （大驼峰）
	 * 
	 * @param str
	 * @return
	 */
	public static String lineToHump(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);

		str = sb.toString();
		str = str.substring(0, 1).toUpperCase() + str.substring(1);

		return str;
	}
	/**
	 * 下划线转驼峰（小驼峰）
	 *
	 * @param str
	 * @return
	 */
	public static String lineToHumpFirstLower(String str) {
		String str2 = lineToHump(str);
		return str2.substring(0,1).toLowerCase()+str2.substring(1);
	}
	/**
	 * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
	 * 
	 * @param str
	 * @return
	 */
	public static String humpToLine(String str) {
		return str.replaceAll("[A-Z]", "_$0").toLowerCase();
	}

	/**
	 * 驼峰转下划线,效率比上面高
	 *
	 * @param str
	 * @return
	 */
	public static String humpToLine2(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 驼峰转下划线,效率比上面高
	 *
	 * @param str
	 * @return
	 */
	public static String lineToHumpFirstUp(String str) {
		String str2 = lineToHump(str);
		return str2.substring(0,1).toLowerCase()+str2.substring(1);
	}

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (s == null || "".equals(s.trim())) {
			return s;
		}
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (s == null || "".equals(s.trim())) {
			return s;
		}
		if (Character.isUpperCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * object转String
	 * 
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {
		return getString(object, "");
	}

	public static String getString(Object object, String defaultValue) {
		if (null == object) {
			return defaultValue;
		}
		try {
			return object.toString();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * object转Integer
	 * 
	 * @param object
	 * @return
	 */
	public static int getInt(Object object) {
		return getInt(object, -1);
	}

	public static int getInt(Object object, Integer defaultValue) {
		if (null == object) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(object.toString());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * object转Integer
	 * 
	 * @param object
	 * @return
	 */
	public static Long getLong(Object object) {
		return getLong(object, -1l);
	}

	public static Long getLong(Object object, Long defaultValue) {
		if (null == object) {
			return defaultValue;
		}
		try {
			return Long.parseLong(object.toString());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * object转Boolean
	 * 
	 * @param object
	 * @return
	 */
	public static boolean getBoolean(Object object) {
		return getBoolean(object, false);
	}

	public static boolean getBoolean(Object object, Boolean defaultValue) {
		if (null == object) {
			return defaultValue;
		}
		try {
			return Boolean.parseBoolean(object.toString());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 将aObj 转化伟String,如果aObj为null则放回默认值aDefault
	 *
	 * @param aObj
	 *            Object
	 * @param aDefault
	 *            String 默认值
	 * @return String
	 */
	public static String objToString(Object aObj, String aDefault) {
		String strRtn = aObj == null ? aDefault : (aObj + "");
		return strRtn;
	}

	public static String nullToString(Object aObj, String aDefault) {
		String strRtn = (aObj == null || "".equals(aObj)) ? aDefault : (aObj + "");
		return strRtn;
	}

	public static String objToString(Object aObj) {
		return objToString(aObj, "");
	}
	
	/**
	 * 方法：转换字符串
	 * @author
	 * @version 1.0
	 * @param aObj
	 * @return
	 */
	public static Long objToLong(Object aObj) {
		Long lValue = null;
		try {
			lValue = Long.parseLong(objToString(aObj, ""));
		} catch (Exception e) {
			lValue = 0l;
		}
		return lValue;
	}
	
	/**
	 * 方法：转换字符串
	 * @version 1.0
	 * @param aObj
	 * @return
	 */
	public static Integer objToIngeger(Object aObj) {
		Integer iValue = null;
		try {
			iValue = Integer.parseInt(objToString(aObj, ""));
		} catch (Exception e) {
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * 方法：转换字符串
	 * @version 1.0
	 * @param aObj
	 * @return
	 */
	public static Short objToShort(Object aObj) {
		Short sValue = null;
		try {
			sValue = Short.parseShort(objToString(aObj, ""));
		} catch (Exception e) {
			sValue = 0;
		}
		return sValue;
	}
	
	/**
	 * 方法：转换字符串
	 * @version 1.0
	 * @param aObj
	 * @return
	 */
	public static Double objToDouble(Object aObj) {
		Double dValue = null;
		try {
			dValue = Double.parseDouble(objToString(aObj, ""));
		} catch (Exception e) {
			dValue = 0d;
		}
		return dValue;
	}

	/**
	 * 方法：转换成金额型
	 * @author wu_jw
	 * @version 1.0
	 * @param aObj
	 * @return
	 */
	public static BigDecimal objToDec(Object aObj) {
		BigDecimal dValue = null;
		try {
			dValue = new BigDecimal(objToString(aObj, "").toString());
		} catch (Exception e) {
			dValue = BigDecimal.ZERO;
		}
		return dValue;
	}
	
	/**
	 * 将格式为123,456,789的字符串转换为long型数组
	 * add by wu_jw
	 */
	public static long[] convertToArray(String rowIdStr) {
		long[] rowIds = null;
		if (rowIdStr != null && !"".equals(rowIdStr)) {
			String[] rowIdStrs = rowIdStr.split(",");
			if (rowIdStrs != null && rowIdStrs.length > 0) {
				rowIds = new long[rowIdStrs.length];
				for (int i = 0; i < rowIdStrs.length; i++) {
					rowIds[i] = new Long(rowIdStrs[i]);
				}
			}
		}
		return rowIds;
	}

	/**
	 * 将格式为123,456,789的字符串转换为String型数组
	 * add by wu_jw
	 */
	public static String[] convertStringToArray(String rowIdStr) {
		String[] rowIds = null;
		if (rowIdStr != null && !"".equals(rowIdStr)) {
			String[] rowIdStrs = rowIdStr.split(",");
			if (rowIdStrs != null && rowIdStrs.length > 0) {
				rowIds = new String[rowIdStrs.length];
				for (int i = 0; i < rowIdStrs.length; i++) {
					rowIds[i] = new String(rowIdStrs[i]);
				}
			}
		}
		return rowIds;
	}
	
	/**
	 * 将格式为list的数值转化为数组对象
	 * add by wu_jw
	 */
	@SuppressWarnings("unchecked")
	public static<T> T[] convertToArray(List<T> source) {
		T[] target = null;
		if (null != source && !source.isEmpty()) {
			Class tType = source.get(0).getClass();
			target=(T[]) Array.newInstance(tType, source.size());
			target = source.toArray(target);
		}
		return target;
	}

	/**
	 * 将aDate按照aFormat格式化为String
	 * 
	 * @param aDate
	 *            Date
	 * @param aFormat
	 *            String example:"yyyy'/'MM'/'dd HH:mm ss"
	 * @return String
	 */
	public static String dateToString(Date aDate, String aFormat) {
		String strRtn = "";
		SimpleDateFormat objFormat = new SimpleDateFormat(aFormat);
		strRtn = objFormat.format(aDate);
		return strRtn;
	}

	/**
	 * 将aDate按照aFormat格式化为Date
	 * 
	 * @param aData
	 *            String
	 * @param aFormat
	 *            String example:"yyyy'/'MM'/'dd HH:mm ss"
	 * @return Date
	 */
	public static Date stringToDate(String aData, String aFormat) {
		Date objDate = null;
		SimpleDateFormat objFormat = new SimpleDateFormat(aFormat);
		try {
			objDate = objFormat.parse(aData);
		} catch (Exception ex) {
			objDate = null;
		}
		return objDate;
	}


	// 加入金额格式化代码
	public static String CashAddComma(double Cash) throws NumberFormatException {
		String tempstr = NumberFormat.getInstance().format(Cash);
		if (tempstr.indexOf(".") == -1) {
			tempstr += ".00";
		}
		int dotpos = tempstr.indexOf(".");
		int len = tempstr.length();
		if ((len - dotpos) == 2) {
			tempstr += "0";
		}
		return tempstr;
	}

	/**
	 * @todo 加逗号
	 * @param Cash
	 *            String
	 * @throws NumberFormatException
	 * @return String
	 */
	public static String CashAddComma(String Cash) throws NumberFormatException {
		if (Cash != null && !"".equals(Cash)) {
			double temp = Double.parseDouble(CashFormat(Cash));
			return CashAddComma(temp);
		} else {
			return "";
		}
	}

	/**
	 * @todo 小数位格式化
	 * @param Cash
	 *            String
	 * @throws NumberFormatException
	 * @return String
	 */
	public static String CashFormat(String Cash) throws NumberFormatException {
		double temp = Double.parseDouble(Cash.trim());
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("###.##");
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		return df.format(temp);
	}
	
	/**
	 * @desc 判断对象是null  还是 "" "    "
	 * @author wennn
	 * @time 2018年2月8日
	 */
	public static boolean isNullOrEmpty(Object o){
		return o == null || "".equals(o.toString().trim());
	}
	
	/**
	 * @desc 判断对象是null  还是 "" "    "
	 * @author wennn
	 * @time 2018年2月8日
	 */
	public static boolean isNullOrEmpty(Object ...args){
		for(Object o:args){
			if(isNullOrEmpty(o)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotNullOrEmpty(String str){
		return null != str && !"".equals(str);
	}
	
	/**
	* 指定日期加上天数后的日期
	* @param num 为增加的天数
	* @param newDate 创建时间
	* @return
	* @throws ParseException 
	*/
	public static Date plusDay(int num,String newDate) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date  currdate = format.parse(newDate);
		
		Calendar ca = Calendar.getInstance();
		ca.setTime(currdate);
		ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
		currdate = ca.getTime();
		return currdate;
	}
	
	/**
	* 当前日期加上天数后的日期
	* @param num 为增加的天数
	* @return
	*/
	public static Date plusDay2(int num){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
		 return ca.getTime();
	}


	/**
	 * 校验IDS里面是否全部是long类型，不是则throw
	 * @param ids id串，逗号分隔
	 * @return
	 */
	public static void checkIdsLongType(String ids){
		String[] idList = ids.split(",");
		for(int i=0;i<idList.length;i++){
			Long.parseLong(idList[i]);
		}
	}
	  /**
		  * 首字母转大写
		  * @param str
		  * @return
		  */
	public static String toUpperCaseFirst(String str){
		   String strResult = str;
	    if(!Character.isUpperCase(str.charAt(0))){
	 	   StringBuffer strBud = new StringBuffer();
	 	   strBud.append(Character.toUpperCase(str.charAt(0)));
	 	   strBud.append(str.substring(1)).toString();
	 	   strResult = strBud.toString();
	    }
	    return strResult;
	}

	/**
	 * 转义特殊字符
	 * @param str
	 * @return
	 */
	public static String transferSpecialChar(String str){
//		str = str.replaceAll("&", "&amp;");
//		str = str.replaceAll("<", "&lt;");
//		str = str.replaceAll(">", "&gt;");
//		str = str.replaceAll(" ", "&nbsp;");
//		str = str.replaceAll("'", "&#39;");
//		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * 反转特殊字符
	 * @param str
	 * @return
	 */
	public static String unTransferSpecialChar(String str){
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&nbsp;", " ");
		str = str.replaceAll("&#39;", "'");
		str = str.replaceAll("&quot;", "\"");
		return str;
	}

	/**
      * 获取利用反射获取类里面的值和名称
      *
      * @param obj
      * @return
      * @throws IllegalAccessException
      */
     public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
         Map<String, Object> map = new HashMap<String, Object>();
         Class<?> clazz = obj.getClass();
         System.out.println(clazz);
         for (Field field : clazz.getDeclaredFields()) {
             field.setAccessible(true);
             String fieldName = field.getName();
             Object value = field.get(obj);
             map.put(fieldName, value);
         }
         return map;
     }

     /**
      * map转成object对象
      * @param map
      * @param beanClass
      * @return
      * @throws Exception
      */
     public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
         if (map == null)  
             return null;    
    
         Object obj = beanClass.newInstance();  
    
         Field[] fields = obj.getClass().getDeclaredFields();   
         for (Field field : fields) {    
             int mod = field.getModifiers();    
             if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                 continue;    
             }    
             field.setAccessible(true);    
             field.set(obj, map.get(field.getName()));   
         }   
    
         return obj;    
     }

	/**
	 * 根据长度截取字段，如果字段空，或者长度《设定长度，则返回原字段
	 *
	 * @param s,len
	 * @return
	 */
	public static String subString( String s,int len){
		return (s==null||s.length()<len)? s:s.substring(0,len);
	}
}
