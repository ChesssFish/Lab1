package calculator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 实现多项式计算.
 *
 * @author George Bush
 */
public class Items {
	// public ArrayList<String> variables;
	// public HashMap<String, Integer> powers;
	// sub-optimal approach
	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */
	public ArrayList<String> variables;
	/**Javadoc.
	 * 
	 *
	 * @author George Bush
	 */
	public HashMap<String, Integer> powers;
	/**Javadoc.
	 * 
	 *
	 * @author George Bush
	 */
	private static final char MAX1 = '*';
	/**Javadoc.
	 * 
	 *
	 * @author George Bush
	 */
	public int coe;

	/**
	 * 计算系数等.
	 * 
	 * @author George Bush
	 */
	public Items(final String str) {
		coe = 1;
		int num = 0;
		variables = new ArrayList<String>();
		powers = new HashMap<String, Integer>();
		String var = "";
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) == MAX1) {
				modify(var, num);
				num = 0;
				var = "";
			} else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				num = num * 10 + str.charAt(i) - '0';
			} else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				var += str.charAt(i);

			}
		}
		modify(var, num);
	}

	/**
	 * Javadoc.
	 *
	 * @author George Bush
	 */
	public void modify(final String var, final int num) {
		if (num != 0) {
			coe *= num;
		}
		if ((variables.contains(var) || "".equals(var)) && powers.containsKey(var)) {
			final int xtrans = powers.get(var);
			powers.remove(var);
			powers.put(var, xtrans + 1);
		} else if (!variables.contains(var) && var != ("")) {
			variables.add(var);
			powers.put(var, 1);
		}
	}
}