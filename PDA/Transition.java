import java.util.*;

public class Transition
{
	private String rule;
	private String push;
	private String pop;
	private List derrived;

	public Transition(String rule)
	{
		this.rule = rule;
		derrived = new ArrayList<>();
	}

	public String getRule()
	{
		return rule;
	}
}