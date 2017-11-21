package GrammarProcessor;

import java.util.*;

public class Symbol
{
	private String symbol;
	private List<String> rules;

	public Symbol(String symbol)
	{
		this.symbol = symbol;
		rules = new ArrayList<String>();
	}

	public boolean producesLambda()
	{
		return rules.contains(GrammarProcessor.lambda);
	}

	public String getSymbol()
	{
		return symbol;
	}

	public List<String> getRules()
	{
		return rules;
	}

	public String toString()
	{
		String s = "\nSymbol --" + symbol + "-- RULES:\n";
		for (String rule : rules)
		{
			s += symbol + " --> " + rule + "\n";
		}

		return s;
	}
}