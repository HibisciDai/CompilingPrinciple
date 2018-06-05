package fancylab.hibiscidai.entity;

import java.util.HashSet;
import java.util.Iterator;

public class NonTerminators {

	private int size = 0;
	private HashSet<NonTerminator> nonTerminators = null;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public HashSet<NonTerminator> getNonTerminators() {
		return nonTerminators;
	}

	public NonTerminators() {
		nonTerminators = new HashSet<NonTerminator>();
	}

	public void add(NonTerminator n) {
		nonTerminators.add(n);
		size++;
	}

	public void print() {
		Iterator<NonTerminator> iterator = nonTerminators.iterator();
		NonTerminator n;
		while (iterator.hasNext()) {
			n = iterator.next();
			System.out.println(n.getNonTerminator() + ":" + n.getId());
		}
	}

	public int getProNum() {
		Iterator<NonTerminator> iterator = nonTerminators.iterator();
		NonTerminator n;
		int t = 0;
		while (iterator.hasNext()) {
			n = iterator.next();
			t += n.getProNum();
		}
		return t;
	}

	public void sort() {
		Iterator<NonTerminator> iterator = nonTerminators.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			iterator.next().setId(i);
			i++;
		}
	}

	public boolean isNter(String s) {
		Iterator<NonTerminator> iterator = nonTerminators.iterator();
		boolean b = false;
		while (iterator.hasNext()) {
			if (iterator.next().getNonTerminator().equals(s)) {
				b = true;
				return b;
			}
		}
		return b;
	}

	public NonTerminator getNterByValue(String s) {
		Iterator<NonTerminator> iterator = nonTerminators.iterator();
		while (iterator.hasNext()) {
			NonTerminator n = iterator.next();
			if (n.getNonTerminator().equals(s)) {
				return n;
			}
		}
		return null;
	}

	public NonTerminator getNterById(int id) {
		Iterator<NonTerminator> iterator = nonTerminators.iterator();
		while (iterator.hasNext()) {
			NonTerminator n = iterator.next();
			if (id == n.getId()) {
				return n;
			}
		}
		return null;
	}

	public void getProduction(String nonT, String pro) {
		if (isNter(nonT)) {
			NonTerminator nonTerminator = getNterByValue(nonT);
			nonTerminator.addProduction(pro);
		}
	}

}
