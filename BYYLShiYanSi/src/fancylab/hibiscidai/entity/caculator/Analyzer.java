package fancylab.hibiscidai.entity.caculator;

import fancylab.hibiscidai.entity.*;

public class Analyzer {

	private NonTerminators nonTerminators = null;
	private Terminators terminators = null;
	private String[][] o = null;
	private String input;
	int num = 0;
	int action = -1;
	String[] s;
	String current = "";

	public Analyzer(NonTerminators n, Terminators t, String[][] o, String input) {
		this.nonTerminators = n;
		this.terminators = t;
		this.o = o;
		this.input = input;
	}

	public void analy() {
		s = new String[input.length()];
		for (int i = 0; i < s.length; i++) {
			s[i] = "";
		}
		int j = 1;
		int k = 0;
		System.out.println("步骤" + space(3) + "符号栈" + space(3) + "当前符号" + space(3) + "剩余输入串" + space(3) + "移进或归约");
		action = 0;
		s[k] = "#";
		num++;
		current = String.valueOf(input.charAt(0));
		input = input.substring(1, input.length());
		while (true) {
			if (terminators.isTer(s[k])) {
				j = k;
			} else {
				j = k - 1;
			}
			if (o[terminators.getTerByValue(s[j]).getId()][terminators.getTerByValue(current).getId()].equals(">")) {
				boolean b = false;
				while (!b) {
					String q = s[j];
					if (terminators.isTer(s[j - 1])) {
						j = j - 1;
					} else {
						j = j - 2;
					}
					if (o[terminators.getTerByValue(s[j]).getId()][terminators.getTerByValue(q).getId()].equals("<")) {
						b = true;
						action = 1;
						print();
						k = guiYue(j + 1, k);
					}
				}
			} else if (o[terminators.getTerByValue(s[j]).getId()][terminators.getTerByValue(current).getId()]
					.equals("<")) {
				action = 0;
				print();
				k = k + 1;
				s[k] = current;
				current = yiJin();
			} else if (o[terminators.getTerByValue(s[j]).getId()][terminators.getTerByValue(current).getId()]
					.equals("=")) {
				if (o[terminators.getTerByValue(s[j]).getId()][terminators.getTerByValue(s[0]).getId()].equals("=")) {
					action = 2;
					print();
					break;
				} else {
					action = 0;
					print();
					k = k + 1;
					s[k] = current;
					current = yiJin();

				}
			} else {
				action = -1;
				print();
				break;
			}

		}

	}

	public void print() {
		String ss = "";
		for (int i = 0; i < s.length; i++) {
			ss += s[i];
		}
		System.out.println(" " + num + space(6 - String.valueOf(num).length()) + ss + space(11 - ss.length()) + current
				+ space(15 - input.length()) + input + space(6) + action(action));

	}

	private String space(int n) {
		String space = "";
		for (int i = 0; i < n; i++) {
			space += " ";
		}
		return space;
	}

	private String action(int a) {
		if (a == 0) {
			return "移进";
		} else if (a == 1) {
			return "归约";
		} else if (a == 2) {
			return "接受";
		} else if (a == -1) {
			return "出错";
		}
		return "";
	}

	private String yiJin() {
		String current;
		current = String.valueOf(input.charAt(0));
		if (input.length() != 1) {
			input = input.substring(1, input.length());
		} else {
			input = "";
		}
		num++;
		return current;
	}

	private int guiYue(int j1, int k) {
		for (int i = j1; i <= k; i++) {
			s[i] = "";
		}
		k = j1;
		s[k] = "N";
		num++;
		return k;
	}
}
