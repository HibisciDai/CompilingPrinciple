package fancylab.hibiscidai.util;

import java.util.ArrayList;
import java.util.List;

import fancylab.hibiscidai.quote.LexicalAnalyzerToArithmeticalExpression;

/**
 * CopyRright (c)2018-hibiscidai;
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: 编译原理|实验三 小型语法分析器的设计
 * 
 * JDK version used: JDK1.8
 * 
 * Namespace: fancylab.hibiscidai.util
 * 
 * Introduce: 关于本实验的一些工具方法
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-26
 * 
 * Version: 1.0
 * 
 */

public class SyntacticAnalyzerUtil {
	// 调用实验二词法分析器来分析算术表达式并返回单词符号
	public static List<String> GetArithmeticalExpressionWordSymbol(String input) {
		LexicalAnalyzerToArithmeticalExpression.WordSymbol = new ArrayList<>();
		LexicalAnalyzerToArithmeticalExpression.lexicalAnalyzerToArithmeticalExpression(input);
		return LexicalAnalyzerToArithmeticalExpression.WordSymbol;
	}

	public static String GetArithmeticalExpressionWordSymbolString(String input) {
		input = formatString(input);
		return ListToString(GetArithmeticalExpressionWordSymbol(input));
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

	// 把list<String>转String
	public static String ListToString(List<String> l) {
		String temp = "";
		for (String att : l) {
			temp += att;
		}
		System.out.println(temp);
		return temp;
	}
}