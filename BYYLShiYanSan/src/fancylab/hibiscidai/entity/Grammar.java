package fancylab.hibiscidai.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
 * Introduce: 文法实体
 * 
 * Author: hibiscidai
 * 
 * Create Date: 2018-4-26
 * 
 * Version: 1.0
 * 
 */
public class Grammar {
	private static final char Njump = 'ε';
	Set<String> VN;// 非终结符
	Set<String> VT;// 终结符集
	Set<Sentence> P;// 规则集
	String S;// 开始符
	Map<String, Integer> X;
	Map<String, Set> First;
	Map<String, Set> FirstR;
	Map<String, Set> Follow;
	Map<Sentence, Set> Select;
	String[][] Table;

	public Grammar(Set<String> VN, Set<String> VT, Set<Sentence> p, String s) {
		this.VN = VN;
		this.VT = VT;
		P = p;
		S = s;
	}

	public Grammar(String[] ip, String S) {
		this.S = S;
		VN = new HashSet();
		VT = new HashSet();
		P = new HashSet();
		for (int i = 0; i < ip.length; i++) {
			Sentence p = new Sentence(ip[i]);
			VN.add(p.getLeft());
			if (p.isSimple()) {
				P.add(p);
				String pr = ((Sentence) p).getRight();
				for (int j = 0; j < pr.length(); j++) {
					VT.add(String.valueOf(pr.charAt(j)));
				}
			} else {
				for (Object sp : p.toSimple()) {
					P.add((Sentence) sp);
					String spr = ((Sentence) sp).getRight();
					for (int j = 0; j < spr.length(); j++) {
						VT.add(String.valueOf(spr.charAt(j)));
					}
				}
			}
		}
		VT.removeAll(VN);
		VT.remove(String.valueOf(Njump));
	}

	public void calculationFirst() {
		if (X == null) {
			calculationX();
		}

		First = new HashMap<>();
		for (Object ivt : VT) {
			Set<String> ivtF = new HashSet<String>();
			ivtF.add((String) ivt);
			First.put((String) ivt, ivtF);
		}

		for (Object ivn : VN) {
			Set<String> ivnF = new HashSet<String>();
			First.put((String) ivn, ivnF);
		}
		Set<Sentence> p2 = new HashSet<>();// 右部第一个字符为非总结符的产生式
		for (Object ip : P) {
			String ipLeft = ((Sentence) ip).getLeft();
			String ipRight = ((Sentence) ip).getRight();
			String fR = ipRight.substring(0, 1);
			if (!VN.contains(fR)) {
				First.get(ipLeft).add(fR);
			} else {
				p2.add((Sentence) ip);
			}
		}

		boolean changed = true;
		while (changed) {
			changed = false;
			for (Object ip : p2) {
				String ipLeft = ((Sentence) ip).getLeft();
				String ipRight = ((Sentence) ip).getRight();
				Set<String> ipLFirst = First.get(ipLeft);
				if (upFirst(ipLFirst, ipRight)) {
					changed = true;
				}
			}
		}

		// Map
		FirstR = new HashMap<>();
		for (Object ip : P) {
			String ipRight = ((Sentence) ip).getRight();
			int pRL = ipRight.length();
			Set<String> ipRF = new HashSet<String>();
			upFirst(ipRF, ipRight);
			FirstR.put(ipRight, ipRF);
		}
		// First.putAll(FirstR);
	}

	private boolean upFirst(Set<String> ipRFirst, String ipR) {// 根据ipR更新ipRFirst
		boolean changed = false;
		int pRL = ipR.length();
		for (int i = 0; i < pRL; i++) {
			String fR = ipR.substring(i, i + 1);
			if (VN.contains(fR)) {
				for (Object ifRFirst : First.get(fR)) {
					if (!((String) ifRFirst).equals(String.valueOf(Njump))) {
						if (ipRFirst.add((String) ifRFirst))
							changed = true;
					}
				}
				if (X.get(fR) != 1) {// fR!->kong
					break;
				}
				if (i == pRL - 1) {
					if (ipRFirst.add(String.valueOf(Njump)))
						changed = true;
				}
			} else {
				if (ipRFirst.add(fR))
					changed = true;
				break;
			}
		}
		return changed;
	}

	public void calculationFollow() {
		if (First == null) {
			calculationFirst();
		}
		Follow = new HashMap<>();
		for (Object ivn : VN) {
			Set<String> ivnFo = new HashSet<String>();
			Follow.put((String) ivn, ivnFo);
		}

		Follow.get(S).add("#");

		boolean changed = true;// 标记Follow是否变化
		Set<String> adFo = new HashSet<String>();
		while (changed) {
			changed = false;
			for (Object ip : P) {
				String ipLeft = ((Sentence) ip).getLeft();
				String ipRight = ((Sentence) ip).getRight();
				int pRL = ipRight.length();
				for (int i = 0; i < pRL; i++) {
					String fR = ipRight.substring(i, i + 1);
					if (VN.contains(fR)) {
						if (i != pRL - 1) {
							adFo.clear();
							upFirst(adFo, ipRight.substring(i + 1, pRL));
							if (adFo.contains(String.valueOf(Njump))) {
								adFo.remove(String.valueOf(Njump));
								adFo.addAll(Follow.get(ipLeft));
							}
							if (Follow.get(fR).addAll(adFo)) {
								changed = true;
							}
						} else {
							if (Follow.get(fR).addAll(Follow.get(ipLeft))) {
								changed = true;
							}
						}
					}
				}
			}
		}
	}

	public void calculationSelect() {
		if (Follow == null) {
			calculationFollow();
		}
		Select = new HashMap<>();
		for (Object ip : P) {
			String ipLeft = ((Sentence) ip).getLeft();
			String ipRight = ((Sentence) ip).getRight();
			Set select = new HashSet();
			select.addAll(FirstR.get(ipRight));
			if (select.contains(String.valueOf(Njump))) {
				select.remove(String.valueOf(Njump));
				select.addAll(Follow.get(ipLeft));
			}
			Select.put((Sentence) ip, select);
		}
	}

	// 计算非终结符能否推出空串的数组(Map)-1 未确定 0不能 1能到'ε'
	public void calculationX() {
		X = new HashMap();
		for (Object key : VN) {
			X.put((String) key, -1);
		}

		Set<Sentence> p2 = new HashSet<>();
		for (Object ip : P) {
			Sentence i = (Sentence) ip;
			boolean haveVT = false;
			for (Object ivt : VT) {
				int idx = i.getRight().indexOf((String) ivt);
				if (idx != -1) {
					haveVT = true;
					break;
				}
			}
			if (!haveVT) {
				p2.add(i);
			}
		}
		Set<String> left = new HashSet<>();
		for (Object ip : p2) {
			left.add(((Sentence) (ip)).getLeft());
		}
		for (Object key : VN) {
			if (!left.contains((String) key)) {
				X.put((String) key, 0);
			}
		}

		Set<String> left2 = new HashSet<>();
		for (Object ip : p2) {
			if (((Sentence) (ip)).getRight().equals(String.valueOf(Njump))) {
				String key = ((Sentence) (ip)).getLeft();
				left2.add(key);
				X.put(key, 1);
			}
		}
		Set<Sentence> p3 = new HashSet<>();
		for (Object ip : p2) {
			if (left2.contains(((Sentence) (ip)).getLeft())) {
				p3.add((Sentence) ip);
			}
		}
		p2.removeAll(p3);

		boolean changed = true;
		while (changed) {
			changed = false;
			p3.clear();
			for (Object ip : p2) {
				String ipRight = ((Sentence) (ip)).getRight();
				for (int i = 0; i < ipRight.length(); i++) {
					int ix = X.get(String.valueOf(ipRight.charAt(i)));
					if (ix == 1) {
						if (ix < ipRight.length() - 1) {
							continue;
						} else {
							String ipLeft = ((Sentence) (ip)).getLeft();
							System.out.println(ipLeft);
							X.put(ipLeft, 1);
							for (Object ipp : p2) {
								if (ipLeft.equals(((Sentence) (ipp)).getLeft())) {
									p3.add((Sentence) ipp);
								}
							}
							changed = true;
						}
					} else if (ix == 0) {
						p3.add((Sentence) ip);
						String ipLeft = ((Sentence) (ip)).getLeft();
						int haveipLeftN = 0;
						for (Object ipp : p2) {
							if (ipLeft.equals(((Sentence) (ipp)).getLeft())) {
								haveipLeftN++;
							}
						}
						if (haveipLeftN == 1) {
							X.put(ipLeft, 0);
							changed = true;
						}
						break;
					}
				}

			}
			p2.removeAll(p3);
		}
	}

	public void createLL1Table() {
		if (Select == null) {
			calculationSelect();
		}
		ArrayList<String> vn = new ArrayList();
		for (Object vN : VN) {
			vn.add((String) vN);
		}
		ArrayList<String> vt = new ArrayList();
		for (Object vT : VT) {
			vt.add((String) vT);
		}
		vt.add("#");
		Table = new String[vn.size() + 1][vt.size() + 1];

		for (int i = 0; i < vt.size(); i++) {
			Table[0][i + 1] = vt.get(i);
		}
		for (int i = 0; i < vn.size(); i++) {
			Table[i + 1][0] = vn.get(i);
		}
		int n, t;
		for (Object ip : P) {
			n = vn.indexOf(((Sentence) ip).getLeft());
			for (Object s : Select.get((Sentence) ip)) {
				t = vt.indexOf((String) s);
				Table[n + 1][t + 1] = ((Sentence) ip).getRight();
			}
		}

		// System.out.println();
		//
		// for (int i = 0; i < VN.size() + 1; i++) {
		// for (int j = 0; j < VT.size() + 2; j++) {
		// String jt = "";
		// if (Table[i][j] == null) {
		// Table[i][j] = "";
		// }
		// System.out.printf("%10s", Table[i][j]);
		// }
		// System.out.println();
		// }
	}

	// 是否存在对应的分析树
	public boolean contains(String st) {
		if (Table == null) {
			createLL1Table();
		}
		boolean contained = false;
		ArrayList<String> vn = new ArrayList();
		ArrayList<String> vt = new ArrayList();
		for (int i = 0; i < VT.size() + 1; i++) {
			vt.add(Table[0][i + 1]);
		}
		for (int i = 0; i < VN.size(); i++) {
			vn.add(Table[i + 1][0]);
		}

		int n, t;
		Stack<String> fx = new Stack<>(); // 符号栈
		fx.push("#");
		fx.push(S);
		st = st + "#";

		String ist;
		String ifx;
		int i = 0;
		while (i < st.length()) {
			ist = st.substring(i, i + 1);// 输入串顶
			if (fx.empty()) {
				contained = false;
				break;
			}
			ifx = fx.pop(); // 符号栈栈顶

			n = vn.indexOf(ifx);
			t = vt.indexOf(ist);
			if (!vt.contains(ifx)) {
				System.out.println(ifx + " -> " + Table[n + 1][t + 1]);
			}
			if (ist.equals(ifx)) {
				i++;
			} else {
				if (n != -1 && t != -1) {
					String nfx = Table[n + 1][t + 1];

					if (nfx.equals(String.valueOf(Njump))) {
						continue;
					}
					if (nfx == null || nfx.equals("")) {
						break;
					}
					for (int j = nfx.length() - 1; j >= 0; j--) {
						fx.push(nfx.substring(j, j + 1));
					}
				} else {
					System.out.println("error");
					break;
				}
			}
		}
		if (fx.empty() && i == st.length()) {
			contained = true;
		} else {
			contained = false;
		}
		return contained;
	}

	public void out() {
		System.out.println("VN:");
		for (Object iVN : VN) {
			System.out.println(iVN);
		}
		System.out.println();
		System.out.println("VT:");
		for (Object iVT : VT) {
			System.out.println(iVT);
		}
		System.out.println();
		System.out.println("SimpleSentence:");
		for (Object ip : P) {
			System.out.println(((Sentence) ip).toString());
		}
		System.out.println();
		System.out.println("StartSymbol:");
		System.out.println(S);
		System.out.println();
		System.out.println("X:");
		for (Object x : X.entrySet()) {
			System.out.println(x.toString());
		}
		System.out.println();
		System.out.println("First:");
		for (Object x : First.entrySet()) {
			System.out.println(x.toString());
		}
		System.out.println();
		System.out.println("FirstR:");
		for (Object x : FirstR.entrySet()) {
			System.out.println(x.toString());
		}
		System.out.println();
		System.out.println("Follow:");
		for (Object x : Follow.entrySet()) {
			System.out.println(x.toString());
		}
		System.out.println();
		System.out.println("Select:");
		for (Object x : Select.entrySet()) {
			System.out.println(x.toString());
		}
		System.out.println();
		System.out.println("Table:");
		for (int i = 0; i < VN.size() + 1; i++) {
			for (int j = 0; j < VT.size() + 2; j++) {
				String jt = "";
				if (Table[i][j] == null) {
					Table[i][j] = "";
				}
				System.out.printf("%10s", Table[i][j]);
			}
			System.out.println();
		}
	}
}