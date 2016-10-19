package calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Calcu { // NOPMD by POP on 16-10-16 下午4:31
	/**
	 * Calcu.
	 *
	 * @author Siaoran
	 */
	private static ArrayList<Items> items;
	private final Scanner isinput;

	private final String sPy = "!simplify";
	private String dvt = "!d/d";// NOPMD by POP on 16-10-16 下午4:31

	public Calcu() { // NOPMD by POP on 16-10-16 下午4:31
		isinput = new Scanner(System.in);
	}
	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */

	public int input() {
		/** input. */ // NOPMD by POP on 16-10-16 下午4:31

		// System.err.print(">>");
		final String str = isinput.nextLine();

		String var;
		
		if (str == "q" || str == "Q") {
			return 0;
		}
		
		if (str.startsWith(sPy)) {
			var = str.substring(sPy.length());
			simplify(var);
			print();
			// return 1;
		} else if (str.startsWith(dvt)) {
			var = str.substring(dvt.length());
			derivative(var);
			print();
			// return 2;
		} else {
			items = new ArrayList<Items>();
			split(str);
			print();
			// return 0;
		}
		return 1;
	}
	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */

	public void split(String str) { // NOPMD by POP on 16-10-16 下午4:31
		final String[] strarr = str.split("\\+");
		for (int i = 0; i < strarr.length; ++i) {
			final Items itsword = new Items(strarr[i]);
			items.add(itsword);
		}
	}
	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */

	public void simplify(String str) { // NOPMD by POP on 16-10-16 下午4:31
		String var = "";
		int num = 0;

		str = str.trim();
		final String[] strarr = str.split("=");
		var = strarr[0];
		for (int i = 0; i < strarr[1].length(); ++i) {
			num = num * 10 + strarr[1].charAt(i) - '0';
		}
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).variables.contains(var)) {
				final int pow = items.get(i).powers.get(var);
				for (int j = 0; j < pow; ++j) {
					items.get(i).coe *= num;
				}
				items.get(i).powers.remove(var);
				items.get(i).variables.remove(var);
			}
		}
	}
	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */

	public void derivative(final String var) { // NOPMD by POP on 16-10-16 下午4:31
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).variables.contains(var)) {
				items.get(i).coe *= items.get(i).powers.get(var);
				final int xtrans = items.get(i).powers.get(var);
				items.get(i).powers.remove(var);
				items.get(i).powers.put(var, xtrans - 1);
				if (items.get(i).powers.get(var) == 0) {
					items.get(i).variables.remove(var);
				}
			} else {
				items.remove(i);
				--i;
			}
		}
	}
	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */

	public void print() { // NOPMD by POP on 16-10-16 下午4:31
		char clator;
		for (int i = 0; i < items.size(); ++i) {
			if (i == items.size() - 1 && items.get(i).variables.isEmpty()) {
				clator = '\n';
			} else {
				clator = items.get(i).variables.isEmpty() ? '+' : '*';
			}
			if (items.get(i).coe != 0) {
				System.out.printf("%d%c", items.get(i).coe, clator);
			}
			for (int j = 0; j < items.get(i).variables.size(); ++j) {
				final String var = items.get(i).variables.get(j);
				final int pow = items.get(i).powers.get(var);
				if (items.get(i).powers.get(var) != 0) {
					clator = j == items.get(i).variables.size() - 1 ? '+' : '*';
					if (i == items.size() - 1 
							&& j == items.get(i).variables.size() - 1) {
						clator = '\n';
					} else if (j == items.get(i).variables.size() - 1) {
						clator = '+';
					} else {
						clator = '*';
					}
					System.out.printf("%s^%d%c", var, pow, clator);
				}
			}
		}
	}
	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */

	public static void main(final String[] argc) {
     	final Calcu caculator = new Calcu();
		while (true) {
			caculator.input();
		}
		// final int command = caculator.input();

	}
}
