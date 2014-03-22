import java.util.*;

class Clause
{
	private int ID; //ID of this clause. Would be the order of the clause in the input file
	private String[] literal; //list of literals in this clause
	private boolean[] negated; //negated[i] is true when literal[i] is negated
	private int[] parents = {-1, -1}; //if this clause was derived, then this field would have non -1 parents

	public Clause(int ID, String[] literal, boolean[] negated)
	{
		this.ID = ID;
		this.literal = literal;
		this.negated = negated;
	}

	public Clause(int ID, String[] literal, boolean[] negated, int[] parents)
	{
		this.ID = ID;
		this.literal = literal;
		this.negated = negated;
		this.parents = parents;
	}
	
	//Takes in another clause and if it can be resolved, then it will add the produced clause to the clauseList. Returns true if there was no contradiction. False if there was.
	public boolean resolution(Clause otherClause, LinkedList<Clause> clauseList)
	{
		for(int i = 0; i < literal.length; i++) //Loop through all the literals in this clause
		{
			for(int j = 0; j < otherClause.literal.length; j++) //Loop through all the literals in the otherClause
			{
				if(literal[i].equals(otherClause.literal[j]) && negated[i] != otherClause.negated[j]) //if the literals are the same and the negation is different, then they can be resolution'd
				{
					if(literal.length == 1 && otherClause.literal.length == 1) //If boths lengths are 1, then there is a contradiction
					{
						return false;
					}

					
					//Add the literals from both clauses to the new clause
					String[] newLiteral = new String[literal.length + otherClause.literal.length - 2]; //the new clause will have literals of both minus the removed literal from both
					boolean[] newNegated = new boolean[negated.length + otherClause.negated.length - 2];
					int newLiteralIndex = 0;

					for(int k = 0; k < literal.length; k++)
					{
						if(k != i) //k is not the index of the removed literal
						{
							newLiteral[newLiteralIndex] = literal[k];
							newNegated[newLiteralIndex] = negated[k];
							newLiteralIndex++;
						}
					}
					for(int k = 0; k < otherClause.literal.length; k++)
					{
						if(k != j)
						{
							newLiteral[newLiteralIndex] = otherClause.literal[k];
							newNegated[newLiteralIndex] = otherClause.negated[k];
							newLiteralIndex++;
						}
					}
					String outputStr = "";
					for(int k = 0; k < newLiteral.length; k++)
					{
						if(newNegated[k])
						{
							outputStr += "~";
						}
						outputStr += (newLiteral[k] + " ");
					}
					System.out.println(outputStr);
				}
			}
		}

		return true;
	}
}
