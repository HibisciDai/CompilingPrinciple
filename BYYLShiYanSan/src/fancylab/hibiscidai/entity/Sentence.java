package fancylab.hibiscidai.entity;

import java.util.ArrayList;

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
 * Namespace: fancylab.hibiscidai.entity
 * 
 * Introduce: 语句实体
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-26
 * 
 * Version: 1.0
 * 
 */

public class Sentence {// 语句
	String left;
	String right;

	// left->right
	public Sentence(String a) {
		// 预处理消除空格
		a = SyntacticAnalyzerUtil.formatString(a);
		String b[] = a.split("->");
		left = b[0];
		right = b[1];
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	// 判断是否为简单语句
	public boolean isSimple() {
		if (right.indexOf("|") == -1)
			return true;
		else
			return false;
	}

	// 更改为简单语句
	public ArrayList<Sentence> toSimple() {
		ArrayList<Sentence> sentenceList = new ArrayList<Sentence>();
		String b[] = right.split("\\|");
		for (int i = 0; i < b.length; i++) {
			// System.out.println(b[i]);
			Sentence ib = new Sentence(left + "->" + b[i]);
			sentenceList.add(ib);
		}
		return sentenceList;
	}

	public String toString() {
		return left + "->" + right;
	}
}