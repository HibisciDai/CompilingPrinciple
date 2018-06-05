package fancylab.hibiscidai.main;

import fancylab.hibiscidai.entity.Grammar;
import fancylab.hibiscidai.util.SyntacticAnalyzerUtil;

/**
 * CopyRright (c)2018-hibiscidai;
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: 编译原理|实验三 小型语法分析器的设计
 * 
 * JDK version used: JDK1.8
 * 
 * Namespace: fancylab.hibiscidai.main
 * 
 * Introduce: 程序入口
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-26
 * 
 * Version: 1.0
 * 
 */

public class Main {
	private static final char Njump = 'ε';

	public static void main(String arg[]) {
		String S = "E"; // 文法开始符号
		// E -> E + T | E – T | T
		// T -> T * F | T / F | F
		// F -> (E) | i

		// 最左递归

		// E -> TE’
		// E’ -> +TE’ | -TE’ |ε
		// T -> FT’
		// T’ -> *FT’ | /FT’|ε
		// F -> (E) | i

		// E’变A T’变为B
		String GS[] = { "E -> TA", "A -> +TA | -TA | " + Njump, "T -> FB", "B -> *FB | /FB | " + Njump,
				"F -> (E) | i" };

		for (int i = 0; i < GS.length; i++) {
			System.out.println(GS[i]);
		}

		Grammar G = new Grammar(GS, S);

		G.calculationX();
		System.out.println();
		G.calculationFirst();
		System.out.println();
		G.calculationFollow();
		System.out.println();
		G.calculationSelect();
		System.out.println();
		G.createLL1Table();
		System.out.println();
		G.out();

		System.out.println("25.6 * 14.5 + 2");// ( i * i + i)
		System.out.println(
				G.contains(SyntacticAnalyzerUtil.GetArithmeticalExpressionWordSymbolString("25.6 * 14.5 + 2")));
		System.out.println();
		System.out.println("2 / 5.2 + 78 - 6");// ( i / i + i - i)
		System.out.println(
				G.contains(SyntacticAnalyzerUtil.GetArithmeticalExpressionWordSymbolString(" 2 / 5.2 + 78 - 6")));
		System.out.println();
		System.out.println("2 / 5.2 + 78 –");// ( i / i + i - )
		System.out
				.println(G.contains(SyntacticAnalyzerUtil.GetArithmeticalExpressionWordSymbolString("2 / 5.2 + 78 –")));
		System.out.println();
		System.out.println("+56 * 7");// ( + i * i)
		System.out.println(G.contains(SyntacticAnalyzerUtil.GetArithmeticalExpressionWordSymbolString("+56 * 7")));
		System.out.println();
	}
}