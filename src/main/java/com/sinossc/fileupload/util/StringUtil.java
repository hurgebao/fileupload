package com.sinossc.fileupload.util;


import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;


public class StringUtil {
	private static int n = 1;
	final private static byte[] so = new byte[0];


	 /** 
	   * 方法说明：这是典型的随机洗牌算法。 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域） 算法时间复杂度O(n)
	   * 创建者：范兴乾
	   * 返回类型：String
	   * 创建时间：2015-1-21 上午9:53:14 
	   * 参数列表： 随机8为不重复数组
	   */ 
	public static String getSeqNoPk() {
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		String date = sdf.format(new Date());
		no = date+no;
		return no;
	}

	private static final int LENGTH = 8;

	private static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

    
    
    /**
     * 实现leftPad功能, 对字符串实现左填充
     * @param str 被填充字符串: 5
     * @param ch 填充字符: #
     * @param length 填充以后的长度: 8
     * @return "#######5"
     */
    public static String leftPad(String str, char ch, int length){
      if(str.length() == length){
        return str;
      }
      char[] chs = new char[length];
      Arrays.fill(chs, ch);
      System.arraycopy(str.toCharArray(), 0, chs, 
          length - str.length(), str.length());
      return new String(chs);
    }
    /**
     * 
     * @param oldStr 原字符串
     * @param str1 要替换的字符
     * @param str2 替换后的字符
     * @return
     */
    public static String replace(String oldStr,String str1,String str2){
    	if(oldStr==null ||"".equals(oldStr)){
    		return "";
    	}
    	String newStr="";
    	newStr=oldStr.replace(str1, str2);
    	return newStr;
    }
    
    public static void main(String[] args) {
    	for(int i=0;i<500;i++) {
    		System.out.println(getSeqNoPk());
    	}
    }

    
    public static boolean isEmpty(String str){
    	return str==null || str.length()==0 || "".equals(str);
    }


	/** Get 请求把中文转码 */
	public static void getHttpCharToUtf8(String fieldName, final Map<String, Object> params) throws Exception{
		String fieldVal = (String)params.get(fieldName);
		fieldVal = URLDecoder.decode(fieldVal, "UTF-8");
		params.put(fieldName, fieldVal);
	}


	/**  字符串 位移  */
	public static String generator(String str,int step) {
		char[] c = str.toCharArray();
		for(int i=0;i < c.length;i++) {
			c[i] = (char) (c[i] ^ step);
		}
		return new String(c);
	}

	/**  字符串 左 位移  */
	public static String generatorZuo(String str,int step) {
		char[] c = str.toCharArray();
		for(int i=0;i < c.length;i++) {
			c[i] = (char) (c[i] << step);
		}
		return new String(c);
	}


	/** 字符串 加密 */
	public static String createEncrypt(String txtStr){
		Random random = new Random();
		int num = random.nextInt(8)+1;
		//后面添加一个数字
		String encryptVal = ""+ num + txtStr;
		return encryptVal;
	}

	/**  字符串 解密  */
	public static String getEncryptDecode(String str) {
		String encryptVal = str.substring(1);
		char[] d = new char[encryptVal.length()/2];
		char[] c = encryptVal.toCharArray();
		for(int i=0;i < c.length;i=i+2) {
			d[i/2] = c[i];
		}
		return new String(d);
	}
	
}
