package system;

public class HourlyManager extends EmployeeManager<Hourly>{
	@Override
	public Hourly createEmployee(Hourly employee, Union union){
		incrementGlobalId();
		setBasicInfo(employee, union);
		employee.setHourPay(Main.inputHandler.loadDouble("Salário por hora:"));
		employee.setSchedule(Main.schedules.get(1));
		return employee;
	}
}