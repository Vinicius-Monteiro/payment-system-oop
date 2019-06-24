package system;
import java.util.ArrayList;

public abstract class EmployeeManager <T extends Employee> {
	private static int globalId;

	public EmployeeManager(){
		globalId = 1;
	}

	public int getGlobalId(){
		return globalId;
	}

	public void incrementGlobalId(){
		globalId++;
	}
	
	public void collectUnionInfo(T employee, Union union){
		employee.getUnionInfo().setBelongs(true);
		employee.getUnionInfo().setId(union.getUnionGlobalID());
		union.incrementUnionGlobalID();
		employee.getUnionInfo().setFee(Main.inputHandler.loadDouble("Taxa sindical:"));
	}

	public void setBasicInfo(T employee, Union union){
		System.out.print("\tNome:");
		String name = Main.in.nextLine();
		employee.setName(name);
		
		System.out.print("\tEndereço:");
		String address = Main.in.nextLine();
		employee.setAddress(address);
		
		System.out.print("\tMétodo de pagamento:");
		String paymentMethod = Main.in.nextLine();
		employee.setPaymentMethod(paymentMethod);

		System.out.print("\tO funcionário pertence ao sindicato ?(y/n):");
		String answer = Main.in.nextLine();
		if(answer.equals("y")) collectUnionInfo(employee, union);
		else employee.getUnionInfo().setBelongs(false);
	}

	public Employee searchByID(ArrayList<Employee> employees, int id){
		for(Employee e: employees){
			if(e.getId() == id) return e;
		}
		return null;
	}

	public Employee searchByID(ArrayList<Employee> employees, T c, int id){
		for(Employee e: employees){
			if(e.getId() == id && e.getClass() == c.getClass())
				return e;
		}
		return null;
	}

	public abstract T createEmployee(T employee, Union union);
}