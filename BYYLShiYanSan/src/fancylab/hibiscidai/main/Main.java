package fancylab.hibiscidai.main;

import fancylab.hibiscidai.entity.Grammar;
import fancylab.hibiscidai.util.SyntacticAnalyzerUtil;

/**
 * CopyRright (c)2018-hibiscidai;
 * 
 * Project: BYYLShiYanEr
 * 
 * Comments: ����ԭ��|ʵ���� С���﷨�����������
 * 
 * JDK version used: JDK1.8
 * 
 * Namespace: fancylab.hibiscidai.main
 * 
 * Introduce: �������
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-26
 * 
 * Version: 1.0
 * 
 */

public class Main {
	private static final char Njump = '��';

	public static void main(String arg[]) {
		String S = "E"; // �ķ���ʼ����
		// E -> E + T | E �C T | T
		// T -> T * F | T / F | F
		// F -> (E) | i

		// ����ݹ�

		// E -> TE��
		// E�� -> +TE�� | -TE�� |��
		// T -> FT��
		// T�� -> *FT�� | /FT��|��
		// F -> (E) | i

		// E����A T����ΪB
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
		System.out.println("2 / 5.2 + 78 �C");// ( i / i + i - )
		System.out
				.println(G.contains(SyntacticAnalyzerUtil.GetArithmeticalExpressionWordSymbolString("2 / 5.2 + 78 �C")));
		System.out.println();
		System.out.println("+56 * 7");// ( + i * i)
		System.out.println(G.contains(SyntacticAnalyzerUtil.GetArithmeticalExpressionWordSymbolString("+56 * 7")));
		System.out.println();
	}
}