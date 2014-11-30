

import java.util.LinkedList;



public class Evaluatable {

	public LinkedList<Cell> getEvaluatable() {
		return evaluatable;
	}

	public void setEvaluatable(LinkedList<Cell> evaluatable) {
		this.evaluatable = evaluatable;
	}

	public LinkedList<Cell> evaluatable = null;
	
	Evaluatable(){
		evaluatable = new LinkedList<Cell>();
	}

	public void addEvaluatable(Cell cur) {
		this.evaluatable.add(cur);
	}


}
