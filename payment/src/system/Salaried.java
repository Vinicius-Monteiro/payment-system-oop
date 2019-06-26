package system;

public class Salaried extends Employee {
	private double salary;

	public Salaried(int id){
		super(id);
	}

	public void setSalary(double salary){
		this.salary = salary;
	}

	public double getSalary(){
		return salary;
	}
	
	public Salaried clone(){
		Salaried employee = new Salaried(this.getId());

		employee.setName(this.getName());
		employee.setAddress(this.getAddress());
		employee.setPaymentMethod(this.getPaymentMethod());
		employee.setId(this.getId());//handle id abstractly
		employee.setSalary(this.getSalary());
		employee.setNextPaymentValue(this.getNextPaymentValue());
		employee.setNextPaymentDate(this.getNextPaymentDate());
		employee.setUnionInfo((UnionInfo)this.getUnionInfo().clone());
		employee.setSchedule((Schedule)this.getSchedule().clone());

		return employee;
	}

	@Override
	public String toString(){
		return super.toString() + ", salariado, salário mensal " + salary;
	}
}