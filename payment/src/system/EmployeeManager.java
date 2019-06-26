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

	public void setBasicInfo(T employee, Payroll payroll){
		System.out.print("\tNome:");
		String name = Main.in.nextLine();
		employee.setName(name);
		
		System.out.print("\tEndereço:");
		String address = Main.in.nextLine();
		employee.setAddress(address);
		
		String paymentMethod = Main.inputHandler.loadPaymentMethod();
		employee.setPaymentMethod(paymentMethod);

		System.out.print("\tO funcionário pertence ao sindicato ?(y/n):");
		String answer = Main.in.nextLine();
		if(answer.equals("y")) collectUnionInfo(employee, payroll.getUnion());
		else if(answer.equals("n")) employee.getUnionInfo().setBelongs(false);
		else{
			System.out.println("Entrada inválida");
			setBasicInfo(employee, payroll);
		}
	}

	public double getPayment(Employee employee) {
		if(employee.getSchedule().getPaymentSchedule().split(" ")[0].equals("mensal")){
			if(employee instanceof Salaried)
				return employee.getNextPaymentValue() + ((Salaried)employee).getSalary();
			else
				return employee.getNextPaymentValue();
		}
		else {
			double parcelas = Double.parseDouble(employee.getSchedule().getPaymentSchedule().split(" ")[1]);
			if(parcelas == 1) parcelas = 4;
			if(employee instanceof Salaried && !(employee instanceof Commissioned))
				return ((Salaried)employee).getSalary()/parcelas;
			else if(employee instanceof Commissioned){
				return (((Commissioned)employee).getSalary()/parcelas) + employee.getNextPaymentValue();
			} else
				return employee.getNextPaymentValue();
		}
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

	public void copyEmployee(Employee from, Employee to, Payroll payroll){
		to.setName(from.getName());
		to.setAddress(from.getAddress());
		to.setPaymentMethod(from.getPaymentMethod());
		to.setNextPaymentValue(0);
		to.setNextPaymentDate(from.getNextPaymentDate());
		to.setSchedule(from.getSchedule());
		to.setNextPaymentDate(to.getSchedule().calculatePaymentDate(payroll));
		to.getUnionInfo().setBelongs(from.getUnionInfo().getBelongs());
		to.getUnionInfo().setId(from.getUnionInfo().getId());
		to.getUnionInfo().setFee(from.getUnionInfo().getFee());
	}

	public String afterDeduction(double pay, double fee) {
		return Double.toString(pay - ((pay * fee)/100.0));
	}

	public abstract T createEmployee(T employee, Payroll payroll);
}