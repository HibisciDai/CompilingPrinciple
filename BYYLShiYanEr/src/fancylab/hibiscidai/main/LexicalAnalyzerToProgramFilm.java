package fancylab.hibiscidai.main;

/**
 * CopyRright (c)2018-hibiscidai
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: 编译原理|小型语法分析器的实现2-针对于程序片
 * 
 * JDK version used: JDK1.8
 * 
 * Namespace: fancylab.hibiscidai.main
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-5
 * 
 * Version: 1.0
 * 
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzerToProgramFilm {
	public static Scanner sc = new Scanner(System.in);

	public static char[] letter = { 'a', 'b', 'c', 'd', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; // 字母表剔除e
	public static char[] number = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // 数字表
	public static char[] operater = { '+', '-', '*', '/', '(', ')', '^', '%', '!', '<', '>', '|', '&', '~', '=' }; // 运算符表

	public static List<String> arry1;// 保存关键字
	public static String[] strings1 = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
			"class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally",
			"float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
			"new", "package", "private", "protected", "public", "return", "strictfp", "short", "static", "super",
			"switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while" };

	public static List<String> arry2; // 保存操作符
	public static String[] strings2 = { "+", "-", "*", "/", "%", "++", "--", "==", "!=", "<=", ">=", "=", "+=", "-=",
			"*=", "/=", "%=", "<<=", ">>=", "&=", "|=", "^=", ">", "<", "&", "|", "^", "~", "&&", "||", "!", "<<", ">>",
			">>>" };

	public static List<String> arry3; // 保存界符
	public static String[] strings3 = { "" + '{', "" + '}', "" + '[', "" + ']', "" + '(', "" + ')', "" + ';' };

	public static List<String> arry4; // 保存空格字符
	public static String[] strings4 = { " ", "\t", "\n" };

	static { // 初始化
		arry1 = Arrays.asList(strings1);
		arry2 = Arrays.asList(strings2);
		arry3 = Arrays.asList(strings3);
		arry4 = Arrays.asList(strings4);
	}

	public static int state = 0; // 状态码,1字母,2运算符,3常数
	public static StringBuffer stringBuffer = new StringBuffer(); // 字符串缓冲

	// 判断一个字符是否在字符数组中
	public static boolean isExistenceChar(char[] chars, char a) {
		boolean flag = false;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == a) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}

	public static void lexicalAnalyzerToArithmeticalExpression(String input) {
		int state = 0; // 1为字母，2为数字，3为运算符，4为标识符
		int m = 0; // 游标
		StringBuffer strTemp = new StringBuffer(); // 遍历临时字符串

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i); // 获取

			if (isExistenceChar(letter, ch)) { // 出现字母
				if (i == m) {
					state = 1;
				}
				strTemp.append(ch);
			} else if (arry3.indexOf(ch + "") >= 0) { // 界符
				if (state == 1) { // 纯字母
					if (Arrays.binarySearch(strings1, strTemp.toString().toLowerCase()) != -1) { // 匹配基本字
						System.out.println(strTemp + " 关键字");
						strTemp = new StringBuffer();
					} else {
						System.out.println(strTemp + " 普通字");
						strTemp = new StringBuffer();
					}
				} else if (state == 2) { // 数字
					System.out.println(strTemp + " 常数");
					strTemp = new StringBuffer();
				} else if (state == 3) { // 运算符
					System.out.println(strTemp + " 运算符");
					strTemp = new StringBuffer();
				} else if (state == 4) {
					System.out.println(strTemp + " 标识符");
					strTemp = new StringBuffer();
				}

				System.out.println(ch + " 界符");
				state = 0;
			} else if (arry4.indexOf(ch + "") >= 0) { // 空格换行字符
				if (state == 1) { // 纯字母
					if (Arrays.binarySearch(strings1, strTemp.toString().toLowerCase()) != -1) { // 匹配基本字
						System.out.println(strTemp + " 关键字");
						strTemp = new StringBuffer();
					} else {
						System.out.println(strTemp + " 普通字");
						strTemp = new StringBuffer();
					}
				} else if (state == 2) { // 数字
					System.out.println(strTemp + " 常数");
					strTemp = new StringBuffer();
				} else if (state == 3) { // 运算符
					System.out.println(strTemp + " 运算符");
					strTemp = new StringBuffer();
				} else if (state == 4) {
					System.out.println(strTemp + " 标识符");
					strTemp = new StringBuffer();
				}

				state = 0;
			} else if (isExistenceChar(number, ch)) { // 出现数字

				if (state == 1) { // 字母后出现数字，为标识符
					state = 4;
				} else {
					state = 2;
				}

				strTemp.append(ch);
			} else if (isExistenceChar(operater, ch)) { // 出现操作符
				if (i == m) {
					state = 3;
				}

				if (state == 1) { // 纯字母
					if (Arrays.binarySearch(strings1, strTemp.toString().toLowerCase()) != -1) { // 匹配基本字
						System.out.println(strTemp + " 关键字");
						strTemp = new StringBuffer();
						state = 0;
					} else {
						System.out.println(strTemp + " 普通字");
						strTemp = new StringBuffer();
						state = 0;
					}
				} else if (state == 2) { // 数字
					System.out.println(strTemp + " 常数");
					strTemp = new StringBuffer();
					state = 0;
				} else if (state == 4) {
					System.out.println(strTemp + " 标识符");
					strTemp = new StringBuffer();
					state = 0;
				}
				if (state == 3) { // 说明是非单目运算符
					strTemp.append(ch);
					m = i + 1;
					continue;
				}

			} else if (ch == '.') { // 出现.
				if (state == 2) { // 数字后接入
					state = 2;
				}
				if (state == 1) { // 字母后接入
					state = 1;
				}
				strTemp.append(ch);
			} else if (ch == 'e') { // 出现e
				if (state == 2) { // 数字后接入
					state = 2;
				}
				if (state == 1) { // 字母后接入
					state = 1;
				}
				strTemp.append(ch);
			} else { // 其他未识别
				System.out.println("输入有误，无法识别");
			}

			m = i + 1;
		}
	}

	public static void main(String[] args) {
		String inStr = "";
		String temp = "";

		while (!temp.equals("#")) {
			temp = sc.nextLine();
			inStr += temp;
		}

		lexicalAnalyzerToArithmeticalExpression(inStr);
	}
}