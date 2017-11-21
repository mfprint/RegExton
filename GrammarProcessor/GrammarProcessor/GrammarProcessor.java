package GrammarProcessor;

import java.util.*;
import java.io.*;

public class GrammarProcessor
{
	public static String lambda = "[lmb]";

	public static Processor processor;
	public static Symbol initialSymbol;
	public static List<Symbol> symbols;
	public static List<Character> alphabet;
	public static String testString;
	public static String file;

	public static int build(String file)
	{
		GrammarProcessor.file = file.replace(".txt", "");
		processor = new Processor();
		alphabet = new ArrayList<Character>();
		symbols = new ArrayList<Symbol>();

		BufferedReader buffer;
		try
		{
			buffer = new BufferedReader(new FileReader("grammars/" + file));
			String line = buffer.readLine();
			int i = 1;
			while (line != null)
			{
				if (i == 1) // States separated by commas
				{
					String[] symbolsA = line.split(",");
					for (String symbol : symbolsA)
					{
						Symbol s = new Symbol(symbol);
						symbols.add(s);
					}
				}
				else if (i == 2) // alphabet symbols separated by commas
				{
					String[] chars = line.split(",");
					for (String s : chars)
					{
						alphabet.add(s.charAt(0));
					}
				}
				else if (i == 3) // non-terminal starting symbol
				{
					Symbol initial = findSymbol(line);
					initialSymbol = initial;
				}
				else if (i == 4) // string to process
				{
					testString = line;
				}
				else // grammar production rules
				{
					String[] result = line.split("->");
					Symbol temp = findSymbol(result[0]);
					temp.getRules().add(result[1]);
				}

				line = buffer.readLine();
				i++;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return verifyGrammer();
	}

	private static int verifyGrammer()
	{
		if (!testString.equals(GrammarProcessor.lambda))
		{
			for (int i = 0; i < testString.length(); i++)
			{
				if (!alphabet.contains(testString.charAt(i)))
				{
					return 1;
				}
			}
		}

		return 0;
	}

	public static boolean runTest()
	{
		boolean b = processor.start();
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DATE);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int mins = now.get(Calendar.MINUTE);
		int secs = now.get(Calendar.SECOND);

		String file = GrammarProcessor.file + " " + year + "-" + (month < 10 ? "0" : "") + month + "-"
			+ (day < 10 ? "0" : "") + day + " " + (hour < 10 ? "0" : "") + hour + "-"
			+ (mins < 10 ? "0" : "") + mins + "-" + (secs < 10 ? "0" : "") + secs + ".txt";

		try
		{
			PrintWriter writer = new PrintWriter("results/" + file, "UTF-8");
			writer.println("+ RESULT OF THE TEST: " + (b ? "THE STRING IS ACCEPTED" : "THE STRING IS NOT ACCEPTED"));
			writer.println("\n+ Definition of the Grammar:");
			writer.print(buildString());
			writer.println("-------- branches show strings that are NOT the tested string.");
			writer.println("******** branches show strings that are EQUAL to the tested string.\n");
			writer.println("+ Generated Tree on Run:");
			writer.println(processor);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return b;
	}

	public static Symbol findSymbol(String symbol)
	{
		for (Symbol s : symbols)
		{
			if (s.getSymbol().equals(symbol))
			{
				return s;
			}
		}

		return null;
	}

	public static List<Symbol> symbolsInText(String text)
	{
		List<Symbol> symbols = new ArrayList<Symbol>();
		for (int i = 0; i < text.length(); i++)
		{
			Symbol x = findSymbol(text.charAt(i) + "");
			if (x != null)
			{
				symbols.add(x);
			}
		}

		return symbols;
	}

	public static String clearFromSymbols(String s)
	{
		String r = s;
		for (Symbol symbol : symbols)
		{
			r = r.replace(symbol.getSymbol(), "");
		}
		r = r.replace(lambda, "");
		return r;
	}

	public static String buildString()
	{
		String s = "Symbols: ";
		for (Symbol symbol : symbols)
		{
			s += symbol.getSymbol() + "   ";
		}
		s += "\n";
		s += "Initial Symbol: " + initialSymbol.getSymbol() + "\n";
		s += "Alphabet: ";
		for (Character c : alphabet)
		{
			s += c + "   ";
		}
		s += "\n";
		s += "Testing string: " + testString + "\n";
		s += "Production rules:" + "\n";
		for (Symbol symbol : symbols)
		{
			for (String rule : symbol.getRules())
			{
				s += symbol.getSymbol() + " --> " + rule + "\n";
			}
		}
		s += "\n";

		return s;
	}
}