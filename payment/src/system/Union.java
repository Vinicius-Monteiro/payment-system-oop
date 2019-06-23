package system;
import java.util.ArrayList;

public class Union{
	ArrayList<Employee> registeredEmployees = new ArrayList<Employee>();
	private int unionGlobalID;

	public Union(){
		unionGlobalID = 1;
	}

	public int getUnionGlobalID(){
		return unionGlobalID;
	}

	public void incrementUnionGlobalID(){
		unionGlobalID++;
	}

	public void submitServiceFee(Employee employee){

	}
}