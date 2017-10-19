import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main{

	public static void main(String[] args){
		
		String testStr = "";
		List<String[]> rules = new ArrayList<String[]>();

		try {
	        FileReader reader = new FileReader("input.txt");
	        BufferedReader bufferedReader = new BufferedReader(reader);

	        //primera linea es el string a probar
	        testStr = bufferedReader.readLine();	

	        String line;

	 		// Guarda las reglas en la variable rules
	        while ((line = bufferedReader.readLine()) != null) {
	        	String[] parts = line.split("->");
	        	rules.add(parts);
	        }
	        reader.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    System.out.println("Text: " + testStr);
	    String[][] rulesArr = rulesToArray(rules);
	    printRules(rulesArr);
	    System.out.println("-------------------------------------------");

	    //System.out.println( test(testStr, rulesArr, "") );
	}

	//Pendiente
	/*
	Metodo recursivo:
		text: texto al que tenemos que llegar
		rulesArr: arreglo de reglas
		comulated: texto acomulado
	*/
	public static String test(String text, String[][] rulesArr, String comulated){
		//Posiblemete hay que cambiar esto!
		//Llama al metodo test por cada regla
		for(int i = 0; i < rulesArr.length; i++){
			comulated += rulesArr[i][1];
			test(text, rulesArr, rulesArr[i][1], comulated);
		}
		return text;
	}
	
	//Pendiente
	//Metodo recursivo. presentRule = La regla que esta analizando
	public static boolean test(String text, String[][] rulesArr, String comulated, String presentRule){
		// Caso base
		if(presentRule == "lambda"){
			if(comulated == text){
				return true;
			}else{
				return false;
			}
		}
		//Caso recursivo
		else
		{
			//Pendiente
			if(false){
				System.out.println("¡Continuar aquí!");
			}
		}
		return false;
	}

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

	//Imprime las reglas de un arreglo bidimensional
	public static void printRules(String[][] rulesA){
		System.out.println("Rules: ");
		for(int i = 0; i < rulesA.length; i++){
			System.out.print(rulesA[i][0] + " -> " + rulesA[i][1] + "\n");
		}
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