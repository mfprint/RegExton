import GrammarProcessor.*;
import java.util.Scanner;
import java.io.*;

public class Program
{
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args)
	{
		String grammar = toggleGrammarSelection();
		int buildResult = GrammarProcessor.build(grammar);
		if (buildResult == 0)
		{
			if (toggleDisplayGrammarSelection())
			{
				System.out.print(GrammarProcessor.buildString());
			}

			boolean valid = GrammarProcessor.runTest();
			System.out.println(valid ? "THE STRING IS ACCEPTED" : "THE STRING IS NOT ACCEPTED");
			System.out.print("Press [enter] to continue...");
			scanner.nextLine();

			if (toggleDisplayTreeSelection())
			{
				System.out.println(GrammarProcessor.processor);
			}
		}
		else if (buildResult == 1)
		{
			System.out.println("The string to be tested contains characters not included in the alphabet.");
		}
		else if (buildResult == 2)
		{
			System.out.println("At least one production rule contains a symbol or character not included in the definition.");
		}

		System.out.println();
	}

	public static String toggleGrammarSelection()
	{
		File folder = new File("grammars/");
		File[] grammars = folder.listFiles();

		
		System.out.println("Choose a grammar:");
		for (int i = 0; i < grammars.length; i++)
		{
			System.out.println((i + 1) + ". " + grammars[i].getName());
		}
		System.out.print("Answer > ");

		int selection = Integer.parseInt(scanner.nextLine());
		System.out.println();
		
		return grammars[selection - 1].getName();
	}

	public static boolean toggleDisplayGrammarSelection()
	{
		System.out.println("Would you like to display the read grammar?");
		System.out.println("1. Yes       2. No");
		System.out.print("Answer > ");
		int selection = Integer.parseInt(scanner.nextLine());
		System.out.println();

		return (selection == 1 ? true : false);
	}

	public static boolean toggleDisplayTreeSelection()
	{
		System.out.println("\nA tree was built for this test saved at the Results folder.\nWould you like to display it now?");
		System.out.println("1. Yes       2. No");
		System.out.print("Answer > ");
		int selection = Integer.parseInt(scanner.nextLine());
		System.out.println();

		return (selection == 1 ? true : false);
	}
}