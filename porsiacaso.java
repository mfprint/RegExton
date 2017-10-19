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
}