package system;

public class CommissionedManager extends SalariedManager<Commissioned>{
	@Override
	public Commissioned createEmployee(Commissioned employee, Union union){
		employee = super.createEmployee(employee, union);
		employee.setCommission(Main.inputHandler.loadDouble("Porcentagem de comissão:"));
		employee.setSchedule(Main.schedules.get(2));
		return employee;
	}
}