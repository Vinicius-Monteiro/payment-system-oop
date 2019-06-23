package system;

public class SalariedManager <T extends Salaried> extends EmployeeManager<T>{
	@Override
	public T createEmployee(T employee, Union union){
		incrementGlobalId();
		setBasicInfo(employee, union);
		employee.setSalary(Main.inputHandler.loadDouble("Salário mensal:"));
		return employee;
	}
}