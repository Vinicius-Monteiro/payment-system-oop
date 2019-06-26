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

		employee.setSalary(this.getSalary());
		super.clone(this, employee);

		return employee;
	}

	@Override
	public String toString(){
		return (this instanceof Commissioned) ? super.toString() + "\nContrato: Comissionado\nSalário mensal: " + salary: 
		super.toString() + "\nContrato: Salariado\nSalário mensal: " + salary;
	}
}