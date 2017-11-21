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

		String cleanText = GrammarProcessor.clearFromSymbols(tempText); // bbaabbbbba
		if (cleanText.length() <= tempTestString.length())
		{
			if (tempText.length() > 0)
			{
				// aabB
				// aabb - aab = 1 - 1 = 0
				int delta = tempTestString.length() - cleanText.length();
				List<Symbol> symbols = GrammarProcessor.symbolsInText(tempText);
				for (Symbol symbol : symbols)
				{
					if (!symbol.producesLambda())
					{
						delta -= 1;
					}
				}
				  // AbbAaabbbBBbbBBBAaA
				// aabB  aabb

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
					//System.out.println("!!!!! " + text + " TESTLEN: " + GrammarProcessor.testString.length());
					for (int i = 0; i < tempText.length(); i++)
					{
						// if (i < tempTestString.length())
						// {
							if (tempText.charAt(i) != tempTestString.charAt(i))
							{
								if (GrammarProcessor.findSymbol(tempText.charAt(i) + "") != null)
								{
									//System.out.println("Viable: " + cleanText + ", " + text + ", " + tempTestString);
									return 2;
								}
								else
								{
									return 3;
								}
							}
						// }
						// else
						// {
						// 	Symbol givesLambda = GrammarProcessor.findSymbol(tempText.charAt(i) + "");
						// 	if (givesLambda != null)
						// 	{
						// 		if (!givesLambda.producesLambda())
						// 		{
						// 			return 3;
						// 		}
						// 	}
						// 	else
						// 	{
						// 		return 3;
						// 	}
						// }
					}

					return 2;
				}
				// else
				// {
				// 	return 3;
				// }

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