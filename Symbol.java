import java.util.*;

public class Symbol
{
	private String symbol;
	private List<String> rules;

	public Symbol(String symbol)
	{
		rules = new ArrayList<String>();
		this.symbol = symbol;
	}

	public void addRule(String rule)
	{
		rules.add(rule);
	}

	public String getSymbol()
	{
		return symbol;
	}

	public List<String> getRules()
	{
		return rules;
	}

	public void print()
	{
		System.out.println("Symbol: " + symbol);
		System.out.println("Rules:");
		for (String rule : rules)
		{
			System.out.println(symbol + "->" + rule);
		}
		System.out.println("-------------\n");
	}
}