public class Util {
	
	public static boolean isNumber(String s)
	{
		if(!s.isEmpty()&&Character.isDigit(s.charAt(0)))
			return true;
		else
			return false;
	}
	
	public static boolean isReference(String s){
		if(!s.isEmpty()&&Character.isLetter(s.charAt(0)))
			return true;
		else 
			return false;
		
	}
	public static boolean isOperator(String s){
		if(!s.isEmpty()&&(s.charAt(0)=='+'||s.charAt(0)=='-'||s.charAt(0)=='*'||s.charAt(0)=='/'))
			return true;
		else 
			return false;
		
	}
	
	public static Double calculater(Double num1, Double num2, String operator) {
		
		Double value=null;
		switch(operator.charAt(0))
		{
		case '+':
			value=new Double(num1+num2);
			break;
		case '-':
			value=new Double(num2-num1);
			break;
		case '*':
			value=new Double(num1*num2);
			break;
		case '/':
			value=new Double(num2/num1);
		}
		return value;
	}
	
	public static Cell getRefCell(String dep,Cell[][] cells) {
		int col;
		int row = Integer.parseInt(dep.substring(1));
		col=Character.toLowerCase(dep.charAt(0)) - 'a';
		//System.out.println("the referenc is cell "+row+" "+col);
		return cells[col][row-1];
	}

}
