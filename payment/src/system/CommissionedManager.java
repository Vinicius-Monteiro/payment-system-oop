package system;

public class CommissionedManager extends SalariedManager<Commissioned>{
	@Override
	public Commissioned createEmployee(Commissioned employee, Payroll payroll){
		employee = super.createEmployee(employee, payroll);
		employee.setSchedule(Main.schedules.get(2));
		employee.setNextPaymentValue(0);
		employee.setNextPaymentDate(employee.getSchedule().calculatePaymentDate(payroll));
		employee.setCommission(Main.inputHandler.loadDouble("Porcentagem de comissão:"));

		return employee;
	}
}