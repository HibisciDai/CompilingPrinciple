package fancylab.hibiscidai.entity.caculator;

import java.util.HashSet;
import java.util.Iterator;

import fancylab.hibiscidai.entity.*;

public class Caculator {

	private NonTerminators nonTerminators = null;
	private Terminators terminators = null;
	private boolean[][] f = null;
	private boolean[][] l = null;
	private String[][] o = null;
	int size1 = 0;
	int size2 = 0;

	public void init() {

	}

	public Caculator(NonTerminators nonTerminators, Terminators terminators) {
		this.nonTerminators = nonTerminators;
		this.terminators = terminators;
		size1 = nonTerminators.getSize();
		size2 = terminators.getSize();
		init();

	}

	public boolean[][] getFirstVT() {
		f = new boolean[size1][size2];
		for (int i = 0; i < size1; i++) {
			for (int j = 0; j < size2; j++) {
				f[i][j] = false;
			}
		}
		for (int i = 0; i < nonTerminators.getProNum(); i++) {
			Iterator<NonTerminator> iterator = nonTerminators.getNonTerminators().iterator();
			while (iterator.hasNext()) {
				caculateBooleaFormF(iterator.next());
			}
		}
		Iterator<NonTerminator> iterator = nonTerminators.getNonTerminators().iterator();
		while (iterator.hasNext()) {
			NonTerminator n = iterator.next();
			int id = n.getId();
			for (int i = 0; i < size2; i++) {
				if (f[id][i]) {
					n.addF(terminators.getTerById(i));
				}
			}
		}
		return f;

	}

	private void caculateBooleaFormF(NonTerminator nonTerminator) {

		if (!nonTerminator.isHaveFirstVT()) {
			HashSet<String> pro = nonTerminator.getProduction();
			String right;
			if (pro.size() > 0) {
				Iterator<String> iterator = pro.iterator();
				String c;
				int nonId = nonTerminator.getId();
				while (iterator.hasNext()) {
					right = iterator.next();
					if (right.length() == 1) {
						c = String.valueOf(right.charAt(0));
						if (terminators.isTer(c)) { // 第一位为终结符
							f[nonId][terminators.getTerByValue(c).getId()] = true;
						} else {
							NonTerminator nonT = nonTerminators.getNterByValue(c);
							// if (nonT.isHaveFirstVT()){
							int id = nonT.getId();
							for (int i = 0; i < size2; i++) {
								if (f[id][i]) {
									f[nonId][i] = f[id][i];
								}
							}
							// }else {
							// caculateBooleaForm(nonT);
							// }
						}
					} else {
						c = String.valueOf(right.charAt(0));
						if (terminators.isTer(c)) { // 第一位为终结符
							f[nonId][terminators.getTerByValue(c).getId()] = true;
						} else {
							c = String.valueOf(right.charAt(1));
							f[nonId][terminators.getTerByValue(c).getId()] = true;
						}
					}
				}
			}
			// nonTerminator.setHaveFirstVT(true);
		}
	}

	public boolean[][] getLastVT() {
		l = new boolean[size1][size2];
		for (int i = 0; i < size1; i++) {
			for (int j = 0; j < size2; j++) {
				l[i][j] = false;
			}
		}
		for (int i = 0; i < nonTerminators.getProNum(); i++) {
			Iterator<NonTerminator> iterator = nonTerminators.getNonTerminators().iterator();
			while (iterator.hasNext()) {
				caculateBooleaFormL(iterator.next());
			}
		}
		Iterator<NonTerminator> iterator = nonTerminators.getNonTerminators().iterator();
		while (iterator.hasNext()) {
			NonTerminator n = iterator.next();
			int id = n.getId();
			for (int i = 0; i < size2; i++) {
				if (l[id][i]) {
					n.addL(terminators.getTerById(i));
				}
			}
		}
		return l;
	}

	private void caculateBooleaFormL(NonTerminator nonTerminator) {
		// if (!nonTerminator.isHaveFirstVT()){
		HashSet<String> pro = nonTerminator.getProduction();
		String right;
		if (pro.size() > 0) {
			Iterator<String> iterator = pro.iterator();
			String c;
			int nonId = nonTerminator.getId();
			while (iterator.hasNext()) {
				right = iterator.next();
				if (right.length() == 1) {
					c = String.valueOf(right.charAt(0));
					if (terminators.isTer(c)) { // 第一位为终结符
						l[nonId][terminators.getTerByValue(c).getId()] = true;
					} else {
						NonTerminator nonT = nonTerminators.getNterByValue(c);
						// if (nonT.isHaveFirstVT()){
						int id = nonT.getId();
						for (int i = 0; i < size2; i++) {
							if (l[id][i]) {
								l[nonId][i] = l[id][i];
							}
						}
					}
				} else {
					c = String.valueOf(right.charAt(right.length() - 2));
					if (terminators.isTer(c)) { // 第一位为终结符
						l[nonId][terminators.getTerByValue(c).getId()] = true;
					} else {
						c = String.valueOf(right.charAt(right.length() - 1));
						l[nonId][terminators.getTerByValue(c).getId()] = true;
					}
				}
			}
		}
		// nonTerminator.setHaveFirstVT(true);
		// }
	}

	public String[][] getOperatorForm() {

		o = new String[size2][size2];
		for (int i = 0; i < size2; i++) {
			for (int j = 0; j < size2; j++) {
				o[i][j] = " ";
			}
		}
		Iterator<NonTerminator> iterator = nonTerminators.getNonTerminators().iterator();
		while (iterator.hasNext()) {
			caculateOperatorForm(iterator.next());
		}
		return o;
	}

	private void caculateOperatorForm(NonTerminator nonTerminator) {
		HashSet<String> pro = nonTerminator.getProduction();
		String right;
		if (pro.size() > 0) {
			Iterator<String> iterator = pro.iterator();
			String c1;
			String c2;
			while (iterator.hasNext()) {
				right = iterator.next();
				if (right.length() > 2) { // 计算=
					int[] d = new int[size2];
					for (int i = 0; i < d.length; i++) {
						d[i] = -1;
					}
					int k = 0;
					for (int i = 0; i < right.length(); i++) {
						c1 = String.valueOf(right.charAt(i));
						if (terminators.isTer(c1)) {
							d[k] = terminators.getTerByValue(c1).getId();
							k++;
						}
					}
					if (k >= 2) {
						for (int i = 0; i < k - 1; i++) {
							o[d[i]][d[i + 1]] = "=";
							// o[d[i+1]][d[i]]="=";
						}
					}
				}
				if (right.length() >= 2) { // 产生式长度大于等于2的情况 计算>和<
					for (int i = 0; i < right.length() - 1; i++) {
						c1 = String.valueOf(right.charAt(i));
						c2 = String.valueOf(right.charAt(i + 1));
						if (nonTerminators.isNter(c1)) { // 当倒数第二个为非终结符时 计算>
							Iterator<Terminator> iterator1 = nonTerminators.getNterByValue(c1).getLastVT().iterator();
							while (iterator1.hasNext()) {
								Terminator terminator = iterator1.next();
								o[terminator.getId()][terminators.getTerByValue(c2).getId()] = ">";
							}
						} else { // 当倒数第二个为终结符时 计算<
							Iterator<Terminator> iterator1 = nonTerminators.getNterByValue(c2).getFirstVT().iterator();
							while (iterator1.hasNext()) {
								Terminator terminator = iterator1.next();
								o[terminators.getTerByValue(c1).getId()][terminator.getId()] = "<";
							}
						}
					}
				}
			}
		}
	}
}