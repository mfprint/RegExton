import java.util.*;

public class porsiacaso
{
	public static boolean hasCapital(String s){
		boolean res = false;
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++){
			if( Character.isUpperCase(arr[i]) ){
				res = true;
			}
		}
		return res;
	}

	//Convierte una lista de reglas a un arreglo bidimensional
	public static String[][] rulesToArray(List<String[]> rules){
		String[][] parts = new String[rules.size()][2];
		for(int i = 0; i < rules.size(); i++){
			parts[i] = rules.get(i);
		}
		return parts;
	}

		/*for (Symbol symbol : symbols)
		{
			sub = sub.replace(symbol.getSymbol(), "");

			for (String rule : symbol.getRules())
			{
				test(text, comulated, rule);
			}
		}*/

		// WUBA WUBA DUB DUB!!!

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

}