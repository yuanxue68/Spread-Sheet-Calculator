

import java.util.ArrayList;
import java.util.Scanner;

public class SpreadSheet {
	
	public static void main(String[] args) throws Exception
	{
		Scanner in =new Scanner(System.in);
		int row=in.nextInt();
		int col=in.nextInt();
		//System.out.println("rol is "+row);
		//System.out.println("col is "+col);
		Cell cells[][]=new Cell[col][row];
		in.nextLine();
		for(int i=0; i<col; i++)
		{
    		cells[i]=new Cell[row];
		}
		
		for(int i=0;i<col;i++)
		{
			for(int j=0 ;j<row;j++)
			{
				String input=in.nextLine();
				cells[i][j]=new Cell(input);
			}
		}
		Evaluatable evaluatable = createNewDependencyGraph(cells,row,col);
		solve(cells,evaluatable);
		checkCyclic(cells,row,col);
		printResult(cells,row, col);
	
		
	}
	
	
	private static void checkCyclic(Cell[][] cells, int row, int col) throws Exception {
		for(int i=0; i<col; i++)
		{
    		for(int j=0; j<row; j++)
    		{
    			if(cells[i][j].getValue()==null)
    			{
    				//System.out.println("value is "+cells[i][j].getValue());
    				throw new Exception("cyclic dependencies");
    			}
    		}
    	}
		
	}


	public static Evaluatable createNewDependencyGraph(Cell[][] cells,int row, int col)
	{
		Evaluatable dependency = new Evaluatable();
		
		for(int i=0; i<col; i++)
    	{
    		for(int j=0; j<row; j++)
    		{
    			Cell cur = cells[i][j];
    			//System.out.println();
    			//System.out.println("getting reference of cell "+i+" "+j);
    			ArrayList<String> deps = cur.getDependency();
    			
    			if(deps.size()>0)
    			{
    				for(String dep: deps)
    				{
    					Cell cellDependent = Util.getRefCell(dep, cells);
    					cellDependent.addDependent(cur);
    					//System.out.println("cell with expression "+cellDependent.getExpression()+" is blocking cell with expression"+cur.getExpression());
    				}
    			}
    			else
    			{
    				dependency.addEvaluatable(cur);
    			}
    		}
    	}
		return dependency;
	}
	public static void solve(Cell[][] cells, Evaluatable evaluatable ) {
		while(!evaluatable.getEvaluatable().isEmpty())
		{
			//System.out.println("evalutable size "+evaluatable.size());
			Cell cell = evaluatable.getEvaluatable().removeFirst();
			cell.calculate(cells);
			//System.out.println("cell dependents have size "+cell.getDependents().size());
			for(Cell dep: cell.getDependents())
			{
				//System.out.println("dep has expression "+dep.getExpression()+"with dependency level "+dep.getDependencyLevel());
				if(dep.evaluatable())
				{
					//System.out.println("dep is "+dep.getExpression());
					evaluatable.getEvaluatable().add(dep);
				}
			}
		}
	}
	
	public static void printResult(Cell[][] cells,int row, int col)
	{
		for(int i=0; i<col; i++)
		{
    		for(int j=0; j<row; j++)
    		{
    			System.out.printf("%.5f%n",cells[i][j].getValue());
    		}
    	}
	
	}

}

