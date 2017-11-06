public class Tree
{
		// System.out.println("Text: " + testStr);
	    // String[][] rulesArr = rulesToArray(rules);
	    // printRules(rulesArr);
	    // System.out.println("-------------------------------------------");

	    // System.out.println( test(testStr, rulesArr, "") );

	private boolean verifyTest(String origin, String gen)
	{
		return false;
	}

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
}