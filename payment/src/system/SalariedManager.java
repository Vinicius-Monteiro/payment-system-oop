package system;

public class SalariedManager <T extends Salaried> extends EmployeeManager<T>{
	@Override
	public T createEmployee(T employee, Payroll payroll){
		incrementGlobalId();
		setBasicInfo(employee, payroll);
		employee.setSalary(Main.inputHandler.loadDouble("Salário mensal:"));
		employee.setSchedule(Main.schedules.get(0));
		return employee;
	}
}