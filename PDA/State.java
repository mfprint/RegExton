import java.util.*;

public class State
{
	private List<Transition> transitions;
	private String symbol;

	public State(String symbol)
	{
		transitions = new ArrayList<Transition>();
		this.symbol = symbol;
	}

	public List<Transition> getTransitions()
	{
		return transitions;
	}

	public String getSymbol()
	{
		return symbol;
	}
}