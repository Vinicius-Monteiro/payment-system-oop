package system;

public class SalariedManager <T extends Salaried> extends EmployeeManager<T>{
	@Override
	public T createEmployee(T employee, Union union){
		incrementGlobalId();
		setBasicInfo(employee, union);
		employee.setSalary(Main.inputHandler.loadDouble("Salário mensal:"));
		employee.setSchedule(Main.schedules.get(0));
		return employee;
	}
}