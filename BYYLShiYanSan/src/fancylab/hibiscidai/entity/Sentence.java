package fancylab.hibiscidai.entity;

import java.util.ArrayList;

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
 * Namespace: fancylab.hibiscidai.entity
 * 
 * Introduce: ���ʵ��
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-26
 * 
 * Version: 1.0
 * 
 */

public class Sentence {// ���
	String left;
	String right;

	// left->right
	public Sentence(String a) {
		// Ԥ���������ո�
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

	// �ж��Ƿ�Ϊ�����
	public boolean isSimple() {
		if (right.indexOf("|") == -1)
			return true;
		else
			return false;
	}

	// ����Ϊ�����
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