package fancylab.hibiscidai.entity;

import java.util.HashSet;

public class Production {

	private HashSet<String> production = null;

	public HashSet<String> getProduction() {
		return production;
	}

	public void add(String production) {
		this.production.add(production);
	}

	public Production() {
		production = new HashSet<String>();
	}
}
