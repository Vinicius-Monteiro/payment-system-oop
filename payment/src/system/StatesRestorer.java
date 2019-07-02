package system;
import java.util.Stack;

public class StatesRestorer{
	private Stack<Payroll> undoStack = new Stack<Payroll>();
	private Stack<Payroll> redoStack = new Stack<Payroll>();

	public Stack<Payroll> getUndoStack() {
		return undoStack;
	}

	public Stack<Payroll> getRedoStack() {
		return redoStack;
	}

	public void pushUndo(Payroll payroll){
		undoStack.push(payroll);
	}

	public void pushRedo(Payroll payroll){
		redoStack.push(payroll);
	}

	public Payroll popUndo(){
		if(!undoStack.empty()) return undoStack.pop();
		else return null;
	}

	public Payroll popRedo(){
		if(!redoStack.empty()) return redoStack.pop();
		else return null;
	}
}