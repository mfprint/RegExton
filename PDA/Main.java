import java.util.*;
import java.io.*;

public class Main
{
	public static String testString = "";

	public static void main(String[] args)
	{
		PDA pushdown = buildPDA();
		pushdown.print();

		boolean valid = pushdown.test(testString);
		System.out.println(testString + " is " + (valid ? "valid" : "not valid"));
	}

	public static PDA buildPDA()
	{
		PDA pda = new PDA();

		BufferedReader buffer;
		try
		{
			buffer = new BufferedReader(new FileReader("test.txt"));
			String line = buffer.readLine();
			int i = 1;
			while (line != null)
			{
				if (i == 1) // States separated by commas
				{
					String[] statesA = line.split(",");
					for (String stateSymbol : statesA)
					{
						State state = new State(stateSymbol);
						pda.addToStates(state);
					}
				}
				else if (i == 2) // alphabet symbols separated by commas
				{
					String[] chars = line.split(",");
					for (String s : chars)
					{
						pda.addToAlphabet(s.charAt(0));
					}
				}
				else if (i == 3) // non-terminal starting symbol
				{
					State initial = pda.findState(line);
					pda.setInitialState(initial);
				}
				else if (i == 4) // string to process
				{
					testString = line;
				}
				else // grammar production rules
				{
					String[] result = line.split("->");
					State temp = pda.findState(result[0]);
					temp.getTransitions().add(new Transition(result[1]));
				}

				line = buffer.readLine();
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return pda;
	}

	public List<State> containsState(PDA p, String rule)
	{
		List<State> containedStates = new ArrayList<State>();
		for (int i = 0; i < rule.length(); i++)
		{
			State contained = p.findState(rule.charAt(i) + "");
			if (contained != null)
			{
				containedStates.add(contained);
			}
		}

		return containedStates;
	}
}