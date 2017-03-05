package com.wssys.utils;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateFormatUtils;

public class StringUtil {
	public static String stringIsNull(Object objstr) {
		if (null == objstr) {
			return "";
		} else {
			return objstr.toString();
		}
	}

	public static Integer IntegerIsNull(Object objstr) {
		if (null == objstr) {
			return null;
		} else {
			if (objstr.equals("")) {
				return null;
			}
			return Integer.valueOf((String) objstr);
		}
	}

	public static String stringIsNull(String objstr) {
		if (null == objstr) {
			return "";
		} else {
			return objstr;
		}
	}
	

	public static String stringIsNullReTurnZero(String objstr) {
		if (null == objstr) {
			return "0";
		} else {
			return objstr;
		}
	}

	public static String convertEmptyStr(String str) {
		if (str != null)
			return str;
		return "";
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals(""))
			return true;
		return false;
	}

	public static boolean notEmpty(String str) {
		if (str == null || str.trim().equals(""))
			return false;
		return true;
	}

	public static boolean isEqual(String str1, String str2) {
		if (str1 == null && str2 == null)
			return true;
		if (str1 == null && str2 != null)
			return false;
		return str1.equals(str2);
	}

	public static boolean notEquals(String str1, String str2) {
		if (isEmpty(str1) && isEmpty(str2))
			return false;
		if (!isEmpty(str1) && str1.equals(str2))
			return false;
		return true;
	}

	public static boolean contains(String str1, String str2) {
		if (isEmpty(str1))
			return false;
		if (str1.indexOf(str2) != -1)
			return true;
		return false;
	}

	/**
	 * 正则表达式匹配
	 */
	public static boolean matchPattern(String str, String regEx) {
		if (StringUtil.isEmpty(str))
			return false;
		if (StringUtil.isEmpty(regEx))
			return false;

		Pattern pattern = Pattern.compile(regEx);
		Matcher match = pattern.matcher(str);

		return match.matches();

	}

	/**
	 * 右对齐，不足补空格
	 * 
	 * @param value
	 * @param len
	 * @return
	 */
	public static String rightAlign(String value, int len) {
		StringBuffer space = new StringBuffer();
		if (value == null) {
			for (int i = 0; i < len; i++)
				space.append(" ");
			return space.toString();
		}
		if (value.getBytes().length > len) {
			return substrByte(value, 0, len);
		}

		for (int i = 0; i < len - value.getBytes().length; i++)
			space.append(" ");
		space.append(value);
		return space.toString();
	}

	/**
	 * 左对齐，不足补空格
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String leftAlign(String value, int length) {

		StringBuffer space = new StringBuffer();
		if (value == null) {
			for (int i = 0; i < length; i++)
				space.append(" ");
			return space.toString();
		}
		if (value.getBytes().length > length) {
			return substrByte(value, 0, length);
		}

		space.append(value);
		for (int i = 0; i < length - value.getBytes().length; i++)
			space.append(" ");
		return space.toString();
	}

	/**
	 * 按字节substring，一个汉字两个字节
	 * 
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String substrByte(String str, int start, int end) {
		if (str == null)
			return "";
		byte[] bt = str.getBytes();
		if (start > end || start < 0 || end > bt.length)
			return "";
		byte[] btNew = new byte[end - start];
		for (int i = start; i < end; i++) {
			btNew[i - start] = bt[i];
		}
		return new String(btNew);
	}

	/**
	 * 截取字符串，返回母串中子串之前的部分（不包括子串）
	 * 
	 * @param str
	 *            ：母串
	 * @param inStr
	 *            ：子串
	 * @return
	 */
	public static String substrBefore(String str, String inStr) {
		if (str == null || inStr == null)
			return null;
		int index = str.indexOf(inStr);
		if (str.indexOf(inStr) > 0) {
			return str.substring(0, index);
		}
		return null;
	}

	/**
	 * 截取字符串，返回母串中子串之后的部分（不包括子串）
	 * 
	 * @param str
	 *            ：母串
	 * @param inStr
	 *            ：子串
	 * @return
	 */
	public static String substrAfter(String str, String inStr) {
		if (str == null || inStr == null)
			return null;
		int index = str.indexOf(inStr);
		if (str.indexOf(inStr) > 0) {
			return str.substring(index + inStr.length());
		}
		return null;
	}

	/**
	 * 截取字符串，返回开始标记之后，第一个结束标记之前部分的字符串
	 * 
	 * @param str
	 * @param startTag
	 *            ：开始标记
	 * @param endTag
	 *            ：结束标记
	 * @return
	 */
	public static String substrBetween(String str, String startTag,
			String endTag) {
		if (str == null)
			return null;
		int start = str.indexOf(startTag);
		if (startTag == null || start == -1)
			return substrBefore(str, endTag);

		String afterStr = str.substring(start + startTag.length());
		int end = afterStr.indexOf(endTag);
		if (endTag == null || end == -1)
			return afterStr;

		return afterStr.substring(0, end);
	}

	/**
	 * 去掉前后多余空格，半角全角都去掉
	 * 
	 * @param str
	 * @return
	 */
	public static String cnTrim(String str) {
		if (str == null || str.equals("")) {
			return str;
		} else {
			return str.replaceAll("^[　 ]+|[　 ]+$", "").trim();
		}
	}

	public static byte[] str2bcd(String s) {
		if (s == null)
			return "".getBytes();
		if (s.length() % 2 != 0) {
			s = "0" + s;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i += 2) {
			int high = cs[i] - 48;
			int low = cs[i + 1] - 48;
			baos.write(high << 4 | low);
		}
		return baos.toByteArray();
	}

	public static String bcd2string(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			int h = ((b[i] & 0xff) >> 4) + 48;
			sb.append((char) h);
			int l = (b[i] & 0x0f) + 48;
			sb.append((char) l);
		}
		return sb.toString();
	}

	/**
	 * 身份证号转换
	 * 
	 * @param idCard
	 * @return
	 */
	public static final String getIDCard18(String idCard) {
		if (isEmpty(idCard))
			return "";
		if (idCard.length() == 18)
			return idCard;
		if (idCard.length() == 15) {
			try {
				String idCard17 = "";
				if (Integer.parseInt(idCard.substring(6, 8)) > 20) {
					idCard17 = idCard.substring(0, 6) + "19"
							+ idCard.substring(6, 15);
				} else {
					idCard17 = idCard.substring(0, 6) + "20"
							+ idCard.substring(6, 15);
				}
				int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
						2, 1 };
				String[] A = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
						"3", "2" };
				int sum = 0;
				for (int i = 0; i < idCard17.length(); i++) {
					int j = Integer.parseInt(idCard17.substring(i, i + 1))
							* W[i];
					sum = sum + j;
				}
				int s = sum % 11;
				String idCard18 = idCard17 + A[s];
				return idCard18;
			} catch (Exception ex) {

			}
		}

		return idCard;
	}

	/**
	 * 截取超长的车牌号码
	 * 
	 * @param cardNo
	 * @return
	 */
	public static final String getCardNo10(String cardNo) {
		if (cardNo == null || cardNo.equals(""))
			return "";
		byte[] b = cardNo.getBytes();
		if (b.length > 10) {
			return cardNo.substring(0, 5);
		} else
			return cardNo;
	}

	public static String bSubString(String s, int start, int length) {
		// if (s==null||s.equals("")) return s;
		// byte[] bytes = s.getBytes();
		// int n = 0;
		// int i=2;
		// for(;i<bytes.length&&i<length;i++){
		// if(i%2==1)n++;
		// else{
		// if(bytes[i]!=0)n++;
		// }
		// }
		// if(i%2==1){
		// if(bytes[i-1]!=0)i=i-1;
		// else
		// i=i+1;
		// }
		// return new String(bytes,start,i);
		if (s == null || s.equals(""))
			return s;
		byte[] bytes = s.getBytes();
		String r = null;
		if (bytes.length < start) {
			r = "";
		} else if (bytes.length < start + length) {
			r = new String(bytes, start, bytes.length - start);
		} else {
			r = new String(bytes, start, length);
		}
		return r;
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static boolean isMessyCode(String strName) {

		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");

		System.out.println("after=======" + after);
		System.out.println("=======================");
		String temp = after.replaceAll("\\p{P}", "");
		System.out.println("temp=======" + temp);

		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		System.out.println("chLength=" + chLength);
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) { // 确定指定字符是否为字母或数字

				if (!isChinese(c)) {
					count = count + 1;
					System.out.print(c);
				}
			}
		}
		System.out.println("count=" + count);
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符串
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
	 *         如果delim为null则使用逗号作为分隔字符串。
	 */
	public static String[] split(String source, String delim) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null) {
			delim = ",";
		}
		StringTokenizer st = new StringTokenizer(source, delim);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	synchronized static public String getGoodsnum() {
		String uuid = String.valueOf(UUID.randomUUID());
		String[] prefix = uuid.split("-", -1);
		String goodsnum = "HH" + prefix[0] + System.currentTimeMillis();

		return goodsnum;
	}

	/**
	 * 产生镇海公司的仓单号/货号 例：ZH20140225001
	 * 
	 * @return
	 */
	synchronized static public String getZHGoodsnum(String seqs) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式

		String goodsnum = "";
		String str = seqs; // 首先查询出那个counter值
		int s = Integer.parseInt(str);
		s = ++s;
		s = s == 1000 ? 1 : s;
		String reslut = s >= 10 ? (s >= 100 ? s + "" : "0" + s) : "00" + s; // 计算													// 转型
		goodsnum = "ZH" + df.format(new Date()) + reslut;
		return goodsnum;
	}

	/**
	 * 产生宁波公司仓单号/货号 例：NB20140225001
	 * 
	 * @return
	 */
	synchronized static public String getNBGoodsnum(String seqs) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式

		String goodsnum = "";
		String str = seqs; // 首先查询出那个counter值
		int s = Integer.parseInt(str);
		s = ++s;
		s = s == 1000 ? 1 : s;
		String reslut = s >= 10 ? (s >= 100 ? s + "" : "0" + s) : "00" + s; // 计算													// 转型
		goodsnum = "NB" + df.format(new Date()) + reslut;
		return goodsnum;
	}

//	/**
//	 * 采购合同编号
//	 * 
//	 * @return
//	 */
//	synchronized public static String PmNum() {
//		return "PM" + System.currentTimeMillis() + generateNumber();
//	}

	/**
	 * 提货单编号
	 * 
	 * @return
	 */
	synchronized public static String BolNum() {
		return "BOL" + System.currentTimeMillis() + generateNumber();
	}

//	/**
//	 * 出库编号
//	 * 
//	 * @return
//	 */
//	synchronized public static String DeliverNum() {
//		return "CK" + System.currentTimeMillis() + generateNumber();
//	}
	
	/**
	 * 出库编号
	 * 
	 * @return
	 */
	synchronized public static String CkNum(String goodsnum,String ckSeqNum) {
		return goodsnum+"CKD" + ckSeqNum;  
	}
	
	synchronized static public String getCkSeq(String seqs) {

		String str = seqs; // 首先查询出那个counter值
		int s = Integer.parseInt(str);
		s = ++s;
		s = s == 1000 ? 1 : s;
		String reslut = s >= 10 ? (s >= 100 ? s + "" : "0" + s) : "00" + s; // 计算													// 转型
		
		return reslut;
	}
	
	
	/**
	 * 采购合同编号
	 * 
	 * @return
	 */
	synchronized public static String CgNum(String goodsnum,String cgSeqNum) {
		return goodsnum+"CGHT" + cgSeqNum;  
	}
	
	synchronized static public String getCgSeq(String seqs) {

		String str = seqs; // 首先查询出那个counter值
		int s = Integer.parseInt(str);
		s = ++s;
		s = s == 1000 ? 1 : s;
		String reslut = s >= 10 ? (s >= 100 ? s + "" : "0" + s) : "00" + s; // 计算													// 转型
		
		return reslut;
	}

	private static final int LENGTH = 8;

	/**
	 * 这是典型的随机洗牌算法。 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域） 算法时间复杂度O(n)
	 * 
	 * @return 随机8为不重复数组
	 */
	public static String generateNumber() {
		String no = "";
		// 初始化备选数组
		int[] defaultNums = new int[10];
		for (int i = 0; i < defaultNums.length; i++) {
			defaultNums[i] = i;
		}

		Random random = new Random();
		int[] nums = new int[LENGTH];
		// 默认数组中可以选择的部分长度
		int canBeUsed = 10;
		// 填充目标数组
		for (int i = 0; i < nums.length; i++) {
			// 将随机选取的数字存入目标数组
			int index = random.nextInt(canBeUsed);
			nums[i] = defaultNums[index];
			// 将已用过的数字扔到备选数组最后，并减小可选区域
			swap(index, canBeUsed - 1, defaultNums);
			canBeUsed--;
		}
		if (nums.length > 0) {
			for (int i = 0; i < nums.length; i++) {
				no += nums[i];
			}
		}

		return no;
	}

	private static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static boolean isNumeric(String str) {
		// for (int i = str.length(); --i >= 0;) {
		// if (!Character.isDigit(str.charAt(i))) {
		// return false;
		// }
		// }
		// return true;
		return str.matches("[\\+-]?[0-9]+((.)[0-9])*[0-9]*");
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(DateFormatUtils.format(new Date(),
		// "yyyy-MM-dd HH:mm:ss").substring(0, 4));
		// String a = "I am a, I am Hello  ok,  new line ffdsa!";
		// String str = "　 a 　b 　c 　";
		// String aa = "11a";
		// System.out.println(isNumeric(str));
		// System.out.println(isNumeric(aa));
		// HH:mm:ss

		// 货号: 镇海公司 ZH20140225001
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
		// System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

		String str = "999"; // 首先查询出那个counter值
		int s = Integer.parseInt(str);
		s = ++s;
		s = s == 1000 ? 1 : s;
		String reslut = s >= 10 ? (s >= 100 ? s + "" : "0" + s) : "00" + s; // 计算
																			// 转型
		System.out.println("ZH" + df.format(new Date()) + reslut); // 然后存到 数据库！
																	// 已测试哈！试试看！
	}
}