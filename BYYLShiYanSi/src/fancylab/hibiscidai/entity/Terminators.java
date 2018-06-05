package fancylab.hibiscidai.entity;

import java.util.HashSet;
import java.util.Iterator;

public class Terminators {

	private int size = 0;
	private HashSet<Terminator> terminators = null;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Terminators() {
		terminators = new HashSet<Terminator>();
	}

	public void add(Terminator t) {
		terminators.add(t);
		size++;
	}

	public Terminator getTerByValue(String s) {
		Iterator<Terminator> iterator = terminators.iterator();
		Terminator terminator = null;
		Terminator temp;
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getTermiannator().equals(s)) {
				terminator = temp;
				return terminator;
			}
		}
		return terminator;
	}

	public Terminator getTerById(int id) {
		Iterator<Terminator> iterator = terminators.iterator();
		Terminator terminator = null;
		Terminator temp;
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getId() == id) {
				terminator = temp;
				return terminator;
			}
		}
		return terminator;
	}

	public boolean isTer(String s) {
		Iterator<Terminator> iterator = terminators.iterator();
		boolean b = false;
		while (iterator.hasNext()) {
			if (iterator.next().getTermiannator().equals(s)) {
				b = true;
				return b;
			}
		}
		return b;
	}

	public void print() {
		Iterator<Terminator> iterator = terminators.iterator();
		Terminator n;
		while (iterator.hasNext()) {
			n = iterator.next();
			System.out.println(n.getTermiannator() + ":" + n.getId());
		}
	}

	public void sort() {
		Iterator<Terminator> iterator = terminators.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			iterator.next().setId(i);
			i++;
		}
	}

}
