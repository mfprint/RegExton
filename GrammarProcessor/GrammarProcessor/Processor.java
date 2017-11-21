package GrammarProcessor;

import java.util.*;

public class Processor
{
	private Unit root;
	private boolean found = false;

	public Processor()
	{

	}

	public boolean start()
	{
		root = new Unit(GrammarProcessor.initialSymbol.getSymbol());
		expand(root);
		return found;
	}

	private void expand(Unit unit)
	{
		int result = unit.explore();
		// System.out.println(unit + " ====== " + result);
		if (result == 0) // Text is not viable
		{
			//System.out.println("0");
		}
		else if (result == 1) // Text equals TestString
		{
			//System.out.println("1");
			// System.out.println("THE STRING IS ACCEPTED");
			found = true;
		}
		else if (result == 2) // Text contains Symbols
		{
			//System.out.println("2");
			for (int i = 0; i < unit.getText().length(); i++)
			{
				Symbol temp = GrammarProcessor.findSymbol(unit.getText().charAt(i) + "");
				if (temp != null)
				{
					for (String rule : temp.getRules())
					{
						String text = "";
						if (unit.getText().length() > 1)
						{
							if (i > 0)
							{
								text += unit.getText().substring(0, i);
							}

							text += rule + unit.getText().substring(i + 1, unit.getText().length());
						}
						else
						{
							text = rule;
						}
						
						// System.out.println("UNIT RESULT: " + text);
						Unit u = new Unit(text);
						unit.getUnits().add(u);
					}
				}
			}

			for (Unit child : unit.getUnits())
			{
				if (!found)
					expand(child);
			}
		}
		else if (result == 3) // Text is not viable because string doesn't start with TestingString
		{
			//System.out.println("3");
			// no expansion for this unit
		}
	}

	public void setRoot(Unit root)
	{
		this.root = root;
	}

	public Unit getRoot()
	{
		return root;
	}

	public String toString()
	{
		return printUnits(root, 0);
	}

	public String printUnits(Unit unit, int level)
	{
		String s = "|";
		for (int i = 0; i < level; i++)
		{
			if (unit.containsValid())
			{
				s += "****";
			}
			else
			{
				s += "----";
			}
		}
		s += "| " + unit.getText() + "\n";
		for (Unit u : unit.getUnits())
		{
			s += printUnits(u, level + 1);
		}

		return s;
	}
}