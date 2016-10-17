package calculator;
import java.util.*;
/*ÐÞ¸Ä*/
<<<<<<< HEAD
/*aaa*/
=======
aaa;
>>>>>>> C4
public class Calc {
	ArrayList<Item> item;
	Scanner is;
	public Calc()
	{
		is = new Scanner(System.in);
	}
	
	public int input()
	{
		System.out.print(">>");
		String str = is.nextLine();
		String spy = "!simplify";
		String dvt = "!d/d";
		String var;
		if (str.startsWith(spy)){
			var = str.substring(spy.length());
			simplify(var);
			print();
			return 1;
		}
		else if(str.startsWith(dvt)){
			var = str.substring(dvt.length());
			derivative(var);
			print();
			return 2;
		}
		else
		{
			item = new ArrayList<Item>();
			Split(str);
			print();
			return 0;
		}
	}
	
	public void Split(String str)
	{
		String[] strarr = str.split("\\+");
		for(int i = 0; i < strarr.length; ++i)
		{
			Item it = new Item (strarr[i]);
			item.add(it);
		}
	}
	
	public void simplify(String str)
	{
		String var = "";
		int	num = 0;
		
		str = str.trim();
		String[] strarr = str.split("=");
		var = strarr[0];
		for(int i = 0; i < strarr[1].length(); ++i)
		{
			num = num * 10 + strarr[1].charAt(i) - '0';
		}
		for(int i = 0; i < item.size(); ++i)
		{
			if (item.get(i).variables.contains(var))
			{
				int pow = item.get(i).powers.get(var);
				for(int j = 0; j < pow; ++j)
				{
					item.get(i).coe *= num;
				}
				item.get(i).powers.remove(var);
				item.get(i).variables.remove(var);
			}
		}
	}
	
	public void derivative(String var)
	{
		for(int i=0;i<item.size();++i)
		{
			if(item.get(i).variables.contains(var))
			{
				item.get(i).coe *= item.get(i).powers.get(var);
				int x = item.get(i).powers.get(var);
				item.get(i).powers.remove(var);
				item.get(i).powers.put(var, x-1);
				if(item.get(i).powers.get(var) == 0)
				{
					item.get(i).variables.remove(var);
				}
			}
			else
			{
				item.remove(i);
				--i;
			}
		}
	}
	
	public void print()
	{
		char c;
		for(int i = 0; i < item.size(); ++i)
		{
			if(i == item.size() - 1 && item.get(i).variables.isEmpty())
			{
				c = '\n';
			}
			else
			{
				c = item.get(i).variables.isEmpty()?'+':'*';
			}
			if(item.get(i).coe != 0)
			{
				System.out.printf("%d%c",item.get(i).coe,c);
			}
			for(int j = 0; j < item.get(i).variables.size(); ++j)
			{
				String var = item.get(i).variables.get(j);
				int pow = item.get(i).powers.get(var);
				if(item.get(i).powers.get(var) != 0)
				{
					c = j == item.get(i).variables.size()-1 ? '+':'*';
					if(i == item.size() - 1 && j == item.get(i).variables.size()-1){
						c = '\n';
					}
					else if(j == item.get(i).variables.size()-1){
						c = '+';
					}
					else{
						c = '*';
					}
					System.out.printf("%s^%d%c",var,pow,c);
				}
			}
		}
	}
	public static void main(String argc[])
	{
		Calc caculator = new Calc();
		while(true)
		{
			int command = caculator.input();
		}
	}
}
