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

		if (result == 0 || result == 3) // Text is not viable
		{
			// no expansion for this unit
		}
		else if (result == 1) // Text equals TestString
		{
			found = true;
		}
		else if (result == 2) // Text contains Symbols
		{
			for (int i = 0; i < unit.getText().length(); i++)
			{
				Symbol temp = GrammarProcessor.findSymbol(unit.getText().charAt(i) + "");
				if (temp != null)
				{
					for (String rule : temp.getRules())
					{
						if (!found) // !!!!!!!
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
							
							Unit u = new Unit(text);
							unit.getUnits().add(u);
							expand(u); // !!!!!!
						}
					}
				}
			}

			// for (Unit child : unit.getUnits())
			// {
			// 	if (!found)
			// 		expand(child);
			// }
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