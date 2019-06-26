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

	public void printStacks(){
		System.out.println("-----Pilha de undos-----");
		if(undoStack.empty()){
			System.out.println("Pilha vazia");
		} else {
			for(int i = 0; i < undoStack.size(); i++){
				System.out.println("///////////////////////");
				System.out.println("Elemento " + i + " da pilha");
				System.out.println("Dia " + undoStack.get(i).getCalendar().toString());
				System.out.println("Sindicato id global " + undoStack.get(i).getUnion().getUnionGlobalID());
				undoStack.get(i).printEmployees();
				System.out.println("///////////////////////");
			}
		}
		System.out.println("-----Pilha de redos-----");
		if(redoStack.empty()){
			System.out.println("Pilha vazia");
		} else {
			for(int i = 0; i < redoStack.size(); i++){
				System.out.println("///////////////////////");
				System.out.println("Elemento " + i + " da pilha");
				System.out.println("Dia " + redoStack.get(i).getCalendar().toString());
				System.out.println("Sindicato id global " + redoStack.get(i).getUnion().getUnionGlobalID());
				redoStack.get(i).printEmployees();
				System.out.println("///////////////////////");
			}
		}
    }
}