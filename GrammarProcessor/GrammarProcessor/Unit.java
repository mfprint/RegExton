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
		if (text.length() > 0 && text.length() <= GrammarProcessor.testString.length())
		{
			if (text.equals(GrammarProcessor.testString))
			{
				containsValid = true;
				return 1;
			}
			else
			{
				for (int i = 0; i < text.length(); i++)
				{
					if (text.charAt(i) != GrammarProcessor.testString.charAt(i))
					{
						if (GrammarProcessor.findSymbol(text.charAt(i) + "") != null)
						{
							return 2;
						}
						else
						{
							return 3;
						}
					}
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