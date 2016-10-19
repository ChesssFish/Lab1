package calculator;
import java.util.*;

public class Item {
	ArrayList<String> variables;
	HashMap<String, Integer> powers;
	int coe;
	
	//计算系数等
	public Item(String str)
	{
		coe = 1;
		int num = 0;
		variables = new ArrayList<String>();
		powers = new HashMap<String, Integer>();
		String var = "";
		for(int i = 0; i < str.length(); ++i)
		{
			if(str.charAt(i) == '*')
			{
				modify(var, num);
				num = 0;
				var = "";
			}
			else if(str.charAt(i) >='0' && str.charAt(i) <= '9')
			{
				num = num * 10 + str.charAt(i) - '0';
			}
			else if(str.charAt(i) >='a' && str.charAt(i) <= 'z')
			{
				var += str.charAt(i);
			}
		}
		modify(var, num);
	}
	
	public void modify(String var, int num)
	{
		if(num != 0) coe *= num;
		if(!variables.contains(var) && var != "")
		{
			variables.add(var);
			powers.put(var, 1);
		}
		else if(powers.containsKey(var))
		{ 
			int x = powers.get(var);
			powers.remove(var);
			powers.put(var, x + 1);
		}
	}
}