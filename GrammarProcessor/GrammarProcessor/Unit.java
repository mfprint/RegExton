package GrammarProcessor;

import java.util.*;

public class Unit
{
	private String text;
	private List<Unit> units;
	private boolean containsValid = false;

	public Unit(String text)
	{
		this.text = text;
		units = new ArrayList<Unit>();
	}

	public int explore()
	{
		String tempText = text.replace(GrammarProcessor.lambda, "");
		String tempTestString = GrammarProcessor.testString.replace(GrammarProcessor.lambda, "");

		if (tempText.equals(tempTestString))
		{
			containsValid = true;
			return 1;
		}

		String cleanText = GrammarProcessor.clearFromSymbols(tempText);
		if (cleanText.length() <= tempTestString.length())
		{
			if (tempText.length() > 0)
			{
				// 2 symbols, text = 5
				// 5 - 3 = 2 - 1 - 1 = 0
				int delta = tempTestString.length() - cleanText.length();
				List<Symbol> symbols = GrammarProcessor.symbolsInText(tempText);
				for (Symbol symbol : symbols)
				{
					if (!symbol.producesLambda())
					{
						delta -= 1;
					}
				}

				if (delta < 0)
				{
					return 3;
				}
				else if (delta == 0)
				{
					return 2;
				}
				else
				{
					for (int i = 0; i < tempText.length(); i++)
					{
							if (tempText.charAt(i) != tempTestString.charAt(i))
							{
								if (GrammarProcessor.findSymbol(tempText.charAt(i) + "") != null)
								{
									return 2;
								}
								else
								{
									return 3;
								}
							}
					}

					return 2;
				}
			}
		}

		return 0;
	}

	public String getText()
	{
		return text;
	}

	public List<Unit> getUnits()
	{
		return units;
	}

	public boolean containsValid()
	{
		return containsValid;
	}

	public String toString()
	{
		return text;
	}
}