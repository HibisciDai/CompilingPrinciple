package fancylab.hibiscidai.util;

import java.util.ArrayList;
import java.util.List;

import fancylab.hibiscidai.quote.LexicalAnalyzerToArithmeticalExpression;

/**
 * CopyRright (c)2018-hibiscidai;
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: ����ԭ��|ʵ���� С���﷨�����������
 * 
 * JDK version used: JDK1.8
 * 
 * Namespace: fancylab.hibiscidai.util
 * 
 * Introduce: ���ڱ�ʵ���һЩ���߷���
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-26
 * 
 * Version: 1.0
 * 
 */

public class SyntacticAnalyzerUtil {
	// ����ʵ����ʷ��������������������ʽ�����ص��ʷ���
	public static List<String> GetArithmeticalExpressionWordSymbol(String input) {
		LexicalAnalyzerToArithmeticalExpression.WordSymbol = new ArrayList<>();
		LexicalAnalyzerToArithmeticalExpression.lexicalAnalyzerToArithmeticalExpression(input);
		return LexicalAnalyzerToArithmeticalExpression.WordSymbol;
	}

	public static String GetArithmeticalExpressionWordSymbolString(String input) {
		input = formatString(input);
		return ListToString(GetArithmeticalExpressionWordSymbol(input));
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

	// ��list<String>תString
	public static String ListToString(List<String> l) {
		String temp = "";
		for (String att : l) {
			temp += att;
		}
		System.out.println(temp);
		return temp;
	}
}