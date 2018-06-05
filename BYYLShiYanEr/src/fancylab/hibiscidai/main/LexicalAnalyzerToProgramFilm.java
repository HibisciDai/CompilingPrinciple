package fancylab.hibiscidai.main;

/**
 * CopyRright (c)2018-hibiscidai
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: ����ԭ��|С���﷨��������ʵ��2-����ڳ���Ƭ
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
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; // ��ĸ���޳�e
	public static char[] number = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // ���ֱ�
	public static char[] operater = { '+', '-', '*', '/', '(', ')', '^', '%', '!', '<', '>', '|', '&', '~', '=' }; // �������

	public static List<String> arry1;// ����ؼ���
	public static String[] strings1 = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
			"class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally",
			"float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
			"new", "package", "private", "protected", "public", "return", "strictfp", "short", "static", "super",
			"switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while" };

	public static List<String> arry2; // ���������
	public static String[] strings2 = { "+", "-", "*", "/", "%", "++", "--", "==", "!=", "<=", ">=", "=", "+=", "-=",
			"*=", "/=", "%=", "<<=", ">>=", "&=", "|=", "^=", ">", "<", "&", "|", "^", "~", "&&", "||", "!", "<<", ">>",
			">>>" };

	public static List<String> arry3; // ������
	public static String[] strings3 = { "" + '{', "" + '}', "" + '[', "" + ']', "" + '(', "" + ')', "" + ';' };

	public static List<String> arry4; // ����ո��ַ�
	public static String[] strings4 = { " ", "\t", "\n" };

	static { // ��ʼ��
		arry1 = Arrays.asList(strings1);
		arry2 = Arrays.asList(strings2);
		arry3 = Arrays.asList(strings3);
		arry4 = Arrays.asList(strings4);
	}

	public static int state = 0; // ״̬��,1��ĸ,2�����,3����
	public static StringBuffer stringBuffer = new StringBuffer(); // �ַ�������

	// �ж�һ���ַ��Ƿ����ַ�������
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
		int state = 0; // 1Ϊ��ĸ��2Ϊ���֣�3Ϊ�������4Ϊ��ʶ��
		int m = 0; // �α�
		StringBuffer strTemp = new StringBuffer(); // ������ʱ�ַ���

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i); // ��ȡ

			if (isExistenceChar(letter, ch)) { // ������ĸ
				if (i == m) {
					state = 1;
				}
				strTemp.append(ch);
			} else if (arry3.indexOf(ch + "") >= 0) { // ���
				if (state == 1) { // ����ĸ
					if (Arrays.binarySearch(strings1, strTemp.toString().toLowerCase()) != -1) { // ƥ�������
						System.out.println(strTemp + " �ؼ���");
						strTemp = new StringBuffer();
					} else {
						System.out.println(strTemp + " ��ͨ��");
						strTemp = new StringBuffer();
					}
				} else if (state == 2) { // ����
					System.out.println(strTemp + " ����");
					strTemp = new StringBuffer();
				} else if (state == 3) { // �����
					System.out.println(strTemp + " �����");
					strTemp = new StringBuffer();
				} else if (state == 4) {
					System.out.println(strTemp + " ��ʶ��");
					strTemp = new StringBuffer();
				}

				System.out.println(ch + " ���");
				state = 0;
			} else if (arry4.indexOf(ch + "") >= 0) { // �ո����ַ�
				if (state == 1) { // ����ĸ
					if (Arrays.binarySearch(strings1, strTemp.toString().toLowerCase()) != -1) { // ƥ�������
						System.out.println(strTemp + " �ؼ���");
						strTemp = new StringBuffer();
					} else {
						System.out.println(strTemp + " ��ͨ��");
						strTemp = new StringBuffer();
					}
				} else if (state == 2) { // ����
					System.out.println(strTemp + " ����");
					strTemp = new StringBuffer();
				} else if (state == 3) { // �����
					System.out.println(strTemp + " �����");
					strTemp = new StringBuffer();
				} else if (state == 4) {
					System.out.println(strTemp + " ��ʶ��");
					strTemp = new StringBuffer();
				}

				state = 0;
			} else if (isExistenceChar(number, ch)) { // ��������

				if (state == 1) { // ��ĸ��������֣�Ϊ��ʶ��
					state = 4;
				} else {
					state = 2;
				}

				strTemp.append(ch);
			} else if (isExistenceChar(operater, ch)) { // ���ֲ�����
				if (i == m) {
					state = 3;
				}

				if (state == 1) { // ����ĸ
					if (Arrays.binarySearch(strings1, strTemp.toString().toLowerCase()) != -1) { // ƥ�������
						System.out.println(strTemp + " �ؼ���");
						strTemp = new StringBuffer();
						state = 0;
					} else {
						System.out.println(strTemp + " ��ͨ��");
						strTemp = new StringBuffer();
						state = 0;
					}
				} else if (state == 2) { // ����
					System.out.println(strTemp + " ����");
					strTemp = new StringBuffer();
					state = 0;
				} else if (state == 4) {
					System.out.println(strTemp + " ��ʶ��");
					strTemp = new StringBuffer();
					state = 0;
				}
				if (state == 3) { // ˵���Ƿǵ�Ŀ�����
					strTemp.append(ch);
					m = i + 1;
					continue;
				}

			} else if (ch == '.') { // ����.
				if (state == 2) { // ���ֺ����
					state = 2;
				}
				if (state == 1) { // ��ĸ�����
					state = 1;
				}
				strTemp.append(ch);
			} else if (ch == 'e') { // ����e
				if (state == 2) { // ���ֺ����
					state = 2;
				}
				if (state == 1) { // ��ĸ�����
					state = 1;
				}
				strTemp.append(ch);
			} else { // ����δʶ��
				System.out.println("���������޷�ʶ��");
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