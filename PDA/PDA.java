import java.util.*;

public class PDA
{
	private List<State> states;
	private List<Character> alphabet;
	private State initialState; // = q0
	private List<State> finalStates;
	private Stack<String> stack;
	private State currentState;

	public PDA()
	{
		states = new ArrayList<State>();
		alphabet = new ArrayList<Character>();
		finalStates = new ArrayList<State>();
		stack = new Stack<String>();
	}

	public boolean test(String input)
	{
		char[] array = input.toCharArray();
		for (char c : array)
		{

		}

		if (stack.size() > 0) return false;

		return true;
	}

	public void addToAlphabet(char c)
	{
		alphabet.add(c);
	}

	public void addToStates(State s)
	{
		states.add(s);
	}

	public void addFinalState(State s)
	{
		finalStates.add(s);
	}

	public void setInitialState(State state)
	{
		initialState = state;
	}

	public State findState(String symbol)
	{
		for (State state : states)
		{
			if (state.getSymbol().equals(symbol))
			{
				return state;
			}
		}

		return null;
	}

	public void print()
	{
		System.out.println("--------- PUSHDOWN AUTOMATA ---------");
		System.out.print("STATES: ");
		for (State state : states)
		{
			System.out.print(state.getSymbol() + ", ");
		}
		System.out.println();
		System.out.print("ALPHABET: ");
		for (Character c : alphabet)
		{
			System.out.print(c + ", ");
		}
		System.out.println();
		System.out.println("INITIAL STATE: " + initialState.getSymbol());
		System.out.println("PRODUCTION RULES:");
		for (State state : states)
		{
			for (Transition transition : state.getTransitions())
			{
				System.out.println(state.getSymbol() + " --> " + transition.getRule());
			}
		}
		System.out.println("------------------");
	}
}