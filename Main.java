import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main{

	static List<Symbol> symbols = new ArrayList<Symbol>();
	static String testStr = "";

	public static void main(String[] args){

		readFile();
	    for (Symbol cl : symbols)
	    {
	    	cl.print();
	    }

	    test(testStr, "");
	    
	}

	public static void readFile()
	{
		try {
	        FileReader reader = new FileReader("input.txt");
	        BufferedReader bufferedReader = new BufferedReader(reader);

	        //primera linea es el string a probar
	        testStr = bufferedReader.readLine();

	        String line;

	 		// Guarda las reglas en la variable rules
	        while ((line = bufferedReader.readLine()) != null) {
	        	String[] parts = line.split("->");
	        	boolean found = false;
	        	for (Symbol s : symbols)
	        	{
	        		if (s.getSymbol().equals(parts[0]))
	        		{
	        			found = true;
	        			s.addRule(parts[1]);
	        			break;
	        		}
	        	}

	        	if (!found)
	        	{
	        		Symbol s = new Symbol(parts[0]);
	        		s.addRule(parts[1]);
	        		symbols.add(s);
	        	}
	        }
	        reader.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static boolean test(String text, String comulated)
	{
		for (Symbol symbol : symbols)
		{
			for (String rule : symbol.getRules())
			{
				test(text, comulated, rule);
			}
		}

		return true;
	}

	// wabaabb

	// S->lambda
	// S->Sa
	// S->Sb

	public static boolean test(String text, String comulated, String rule)
	{
		if(rule.equals("lambda")){
			if(comulated == text){
				return true;
			}else{
				return false;
			}
		}
		//Caso recursivo
		else
		{
			// comulated = rule + comulated;
			// char[] symbols = capitalLetters(comulated);
			// if (symbols.length > 0)
			// {
			// 	for (int i = 0; i < symbols.length; i++)
			// 	{
			// 		for (Symbol s : symbols)
			// 		{
			// 			if (s.getSymbol().equals(symbols[i]))
			// 			{
			// 				for (String r : symbol.getRules())
			// 				{
			// 					test(text, comulated, rule);
			// 				}
			// 			}
			// 		}
			// 	}
			// }
		}

		return true;
	}

	public static char[] capitalLetters(String s){
		String res = "";
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++){
			if(Character.isUpperCase(arr[i]) ){
				res += arr[i] + "";
			}
		}
		return res.toCharArray();
	}
}