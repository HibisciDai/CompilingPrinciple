package fancylab.hibiscidai.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CopyRright (c)2018-hibiscidai;
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: ����ԭ��|С���﷨��������ʵ��2-������������ʽ
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
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // ���建�������ڴӿ���̨��ȡ���ݣ���̬������ʡ�ڴ�

	public static char[] letter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; // ��ĸ
	public static char[] number = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' }; // ����
	public static char[] operater = { '+', '-', '*', '/', '(', ')', '^', '%', '!' }; // �����

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

	// �������ʽ�еĿո�
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
		int state = 0; // 1Ϊ��ʶ����2Ϊ���ִ���3Ϊ�����
		int m = 0; // �α�

		for (int i = 0; i < string.length(); i++) {
			char temp = string.charAt(i);
			String out = "";
			if (isExistenceChar(operater, temp)) { // �������
				if (state == 1) { // state=1������ǰ���Ϊ��ʶ��
					out = string.substring(m, i);
					System.out.println(out);
					// System.out.println(out + " ��ʶ��");
				} else if (state == 2) { // state=2������ǰ���Ϊ����
					out = string.substring(m, i);
					System.out.println(out);
					// System.out.println(out + " ����");
				}
				m = i + 1;
				state = 3;
				System.out.println(temp);
				// System.out.println(temp + " �����");
			} else if (isExistenceChar(number, temp)) { // ������
				if (i == m) { // �жϴ�ʱ�������Ƿ�Ϊ�����ĵ�һ�����֣�������ı�״̬Ϊ�޷�������
					state = 2;
				}
			} else if (isExistenceChar(letter, temp)) { // ����ĸ
				if (state == 2) {
					if (temp != 'e') {
						System.out.println("��⵽�������ִ����ܰ�����ĸ");
						return;
					}
				}
				if (i == m) {
					state = 1;
				}
			} else if (temp == 'e') {
				if (state == 2) { // ����e
					state = 2;
					break;
				} else if (state == 1) { // ��ʶ��e
					state = 1;
					break;
				}
			}
		}

		// ���ַ������������ַ�����󲿷�Ϊ��ʶ������ӡ
		if (state == 1) {
			System.out.println(string.substring(m));
			// System.out.println(string.substring(m) + " ��ʶ��");
		} else if (state == 2) { // ���ַ�����󲿷�Ϊ�޷�������
			System.out.println(string.substring(m));
			// System.out.println(string.substring(m) + " �޷�������");
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