package system;
import java.util.ArrayList;

public class Payroll {
	ArrayList<Employee> employees;
	
	public Payroll(){
		this.employees = new ArrayList<Employee>();
	}

	public void addEmployee(Employee employee){
		employees.add(employee);
	}

	public void printEmployees(){
		for(Employee e: employees){
			System.out.println(e.toString());
		}
	}
}