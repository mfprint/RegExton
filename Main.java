import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main{

	static List<Symbol> symbols = new ArrayList<Symbol>();
	static String testStr = "";
	static int count = 0;
	static boolean end = false;

	public static void main(String[] args){

		readFile();
	    for (Symbol cl : symbols)
	    {
	    	cl.print();
	    }

	    start(testStr);
	    
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

	public static void start(String text){
		for (Symbol symbol : symbols)
		{
			if( symbol.getSymbol().equals("S") ){
				for (String rule : symbol.getRules())
				{
					System.out.println("Start count: " + count);
					if(!end)
						test(text, rule);
				}
				break;
			}
		}
	}

	public static boolean test(String text, String comulated)
	{
		count++;
		System.out.println('\n' + count + "_____________________\n" + comulated);
		String sub = comulated.replace("S","");
		if( comulated.contains("lambda_")){
			if( text.equals(sub) ){
				System.out.println("true");
				end = true;
			}else{
				System.out.println("false");
				end = false;
			}
		}
		if( text.equals(sub) ){
			System.out.println("true");
			end = true;
		}else if( text.contains(sub) ){
			for (Symbol symbol : symbols)
			{
				if( symbol.getSymbol().equals("S") ){
					for (String rule : symbol.getRules())
					{
						System.out.println("Original Comulated: "+ comulated + '\n');
						System.out.print("Text: "+text+'\n'+"Comulated: "+ comulated.replace("S",rule) );
						if(!end)
							test( text, comulated.replace("S",rule) );
					}
					break;
				}
			}
		}
		System.out.println("MARTIN");
		return end;
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