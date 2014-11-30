

import java.util.ArrayList;
import java.util.LinkedList;



public class Cell {
	
	private String expression;
	private Double value = null;
	private ArrayList<Cell> dependents=null;
	private int dependencyLevel=0;
	
	public int getDependencyLevel() {
		return dependencyLevel;
	}

	public void setDependencyLevel(int dependencyLevel) {
		this.dependencyLevel = dependencyLevel;
	}

	public Cell(String input) {
		expression= input;
		dependents=new ArrayList<Cell>();
		dependencyLevel=0;
		value = null;
		
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public ArrayList<Cell> getDependents() {
		return dependents;
	}

	public void setDependents(ArrayList<Cell> dependents) {
		this.dependents = dependents;
	}




	public ArrayList<String> getDependency() {
		String[] parts = expression.split(" ");
		ArrayList<String> list=new ArrayList<String>();
		for(String part:parts)
		{
			if(Util.isReference(part))
			{
				list.add(part);
			}
			this.setDependencyLevel(list.size());
		}
		return list;
	}

	public void addDependent(Cell cur) {
		// TODO Auto-generated method stub
		dependents.add(cur);
	}
	
	public boolean evaluatable() {
		dependencyLevel--;
		if(dependencyLevel==0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public void calculate(Cell[][] cells) {
		LinkedList<Double> list = new LinkedList<Double>();
		String[] parts = expression.split(" ");
		//System.out.println(expression);
		for(String part:parts )
		{
			if(Util.isNumber(part))
			{
				list.add((double)Integer.parseInt(part));
			}
			else if(Util.isReference(part))
			{
				list.add(Util.getRefCell(part, cells).getValue());
			}
			else if(Util.isOperator(part))
			{
				double d1=list.removeLast();
				double d2=list.removeLast();
				double result=Util.calculater(d1, d2, part);
				list.add(result);
			}
		}
		this.value=list.removeLast();

	}

	
}
