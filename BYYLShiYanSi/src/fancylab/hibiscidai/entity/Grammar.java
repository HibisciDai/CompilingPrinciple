package fancylab.hibiscidai.entity;

import fancylab.hibiscidai.entity.caculator.Analyzer;
import fancylab.hibiscidai.entity.caculator.Caculator;

public class Grammar {

	private Terminators ters = null;
	private NonTerminators nters = null;
	private boolean f[][] = null;
	private boolean l[][] = null;
	private String o[][] = null;

	public Grammar() {
		ters = new Terminators();
		nters = new NonTerminators();
	}

	public void print() {
		nters.print();
		ters.print();
	}

	public void sort() {
		nters.sort();
		ters.sort();
	}

	public void addTer(String s) {
		Terminator terminator = new Terminator(s);
		ters.add(terminator);
	}

	public void addNter(String s) {
		NonTerminator nonTerminator = new NonTerminator(s);
		nters.add(nonTerminator);
	}

	public void addProduction(String n, String p) {
		nters.getProduction(n, p);
	}

	public void caculate() {
		Caculator caculator = new Caculator(nters, ters);
		f = caculator.getFirstVT();
		l = caculator.getLastVT();
		o = caculator.getOperatorForm();
	}

	public void printF() {
		int size1 = nters.getSize();
		int size2 = ters.getSize();
		System.out.println("printF");
		System.out.print("    ");
		for (int i = 0; i < size2; i++) {
			System.out.print(ters.getTerById(i).getTermiannator() + "   ");
		}
		System.out.println();
		for (int i = 0; i < size1; i++) {
			System.out.print(nters.getNterById(i).getNonTerminator() + "  ");
			for (int j = 0; j < size2; j++) {
				if (f[i][j]) {
					System.out.print("1" + "   ");
				} else {
					System.out.print("0" + "   ");
				}
			}
			System.out.println();
		}
	}

	public void printL() {
		int size1 = nters.getSize();
		int size2 = ters.getSize();
		System.out.println("printL");
		System.out.print("    ");
		for (int i = 0; i < size2; i++) {
			System.out.print(ters.getTerById(i).getTermiannator() + "   ");
		}
		System.out.println();
		for (int i = 0; i < size1; i++) {
			System.out.print(nters.getNterById(i).getNonTerminator() + "  ");
			for (int j = 0; j < size2; j++) {
				if (l[i][j]) {
					System.out.print("1" + "   ");
				} else {
					System.out.print("0" + "   ");
				}
			}
			System.out.println();
		}

	}

	public void printO() {
		int size2 = ters.getSize();
		System.out.println("printO");
		System.out.print("    ");
		for (int i = 0; i < size2; i++) {
			System.out.print(ters.getTerById(i).getTermiannator() + "   ");
		}
		System.out.println();
		for (int i = 0; i < size2; i++) {
			System.out.print(ters.getTerById(i).getTermiannator() + "   ");
			for (int j = 0; j < size2; j++) {
				System.out.print(o[i][j] + "   ");
			}
			System.out.println();
		}
	}

	public void analy(String input) {
		Analyzer analyzer = new Analyzer(nters, ters, o, input);
		analyzer.analy();
	}
}
