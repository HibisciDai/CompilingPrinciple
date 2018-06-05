package fancylab.hibiscidai.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CopyRright (c)2018-hibiscidai;
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: 编译原理|小型语法分析器的实现2-针对于算术表达式
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

public class LexicalAnalyzerToArithmeticalExpression {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 定义缓冲流用于从控制台读取数据，静态变量节省内存

	public static char[] letter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; // 字母
	public static char[] number = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' }; // 数字
	public static char[] operater = { '+', '-', '*', '/', '(', ')', '^', '%', '!' }; // 运算符

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

	// 消除表达式中的空格
	public static String formatString(String input) {
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			char temp = input.charAt(i);
			if (temp != ' ') {
				output += temp;
			}
		}
		return output;
	}

	public static void lexicalAnalyzerToArithmeticalExpression(String input) {
		String string = formatString(input);
		int state = 0; // 1为标识符，2为数字串，3为运算符
		int m = 0; // 游标

		for (int i = 0; i < string.length(); i++) {
			char temp = string.charAt(i);
			String out = "";
			if (isExistenceChar(operater, temp)) { // 是运算符
				if (state == 1) { // state=1表明其前面的为标识符
					out = string.substring(m, i);
					System.out.println(out);
					// System.out.println(out + " 标识符");
				} else if (state == 2) { // state=2表明其前面的为数字
					out = string.substring(m, i);
					System.out.println(out);
					// System.out.println(out + " 数字");
				}
				m = i + 1;
				state = 3;
				System.out.println(temp);
				// System.out.println(temp + " 运算符");
			} else if (isExistenceChar(number, temp)) { // 是数字
				if (i == m) { // 判断此时的数字是否为整数的第一个数字，若是则改变状态为无符号整数
					state = 2;
				}
			} else if (isExistenceChar(letter, temp)) { // 是字母
				if (state == 2) {
					if (temp != 'e') {
						System.out.println("检测到错误，数字串不能包含字母");
						return;
					}
				}
				if (i == m) {
					state = 1;
				}
			} else if (temp == 'e') {
				if (state == 2) { // 数字e
					state = 2;
					break;
				} else if (state == 1) { // 标识符e
					state = 1;
					break;
				}
			}
		}

		// 当字符串检查完后，若字符串最后部分为标识符，打印
		if (state == 1) {
			System.out.println(string.substring(m));
			// System.out.println(string.substring(m) + " 标识符");
		} else if (state == 2) { // 若字符串最后部分为无符号整数
			System.out.println(string.substring(m));
			// System.out.println(string.substring(m) + " 无符号整数");
		}
	}

	public static void main(String[] args) {
		String input = " ";
		try {
			input = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		lexicalAnalyzerToArithmeticalExpression(input);
		// 25.6 + 17*52.9e10 -6*2^ 3
		// 25.6+17*52.9e10-6*2^3
		// (t1+p)*(42+3)
	}
}