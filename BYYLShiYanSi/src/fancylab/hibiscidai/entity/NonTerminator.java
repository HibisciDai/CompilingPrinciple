package fancylab.hibiscidai.entity;

import java.util.HashSet;

public class NonTerminator {

	private String nonTerminator = "";
	private HashSet<Terminator> firstVT = null;
	private HashSet<Terminator> lastVT = null;
	private boolean haveFirstVT = false;
	private int id = -1;
	private Production production = null;

	public HashSet<String> getProduction() {
		return production.getProduction();
	}

	public HashSet<Terminator> getFirstVT() {
		return firstVT;
	}

	public HashSet<Terminator> getLastVT() {
		return lastVT;
	}

	public int getProNum() {
		int i;
		i = production.getProduction().size();
		return i;
	}

	public void addF(Terminator t) {
		firstVT.add(t);
	}

	public void addL(Terminator t) {
		lastVT.add(t);
	}

	public void addProduction(String s) {
		production.add(s);
	}

	public boolean isHaveFirstVT() {
		return haveFirstVT;
	}

	public void setHaveFirstVT(boolean haveFirstVT) {
		this.haveFirstVT = haveFirstVT;
	}

	public String getNonTerminator() {
		return nonTerminator;
	}

	public void setNonTerminator(String nonTerminator) {
		this.nonTerminator = nonTerminator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NonTerminator(String nonTerminator) {
		production = new Production();
		firstVT = new HashSet<Terminator>();
		lastVT = new HashSet<Terminator>();
		this.nonTerminator = nonTerminator;
	}

}
