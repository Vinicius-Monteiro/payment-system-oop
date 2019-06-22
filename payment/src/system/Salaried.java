package system;

public class Salaried extends Employee {
	private double salary;

	public Salaried(String name, String address, String paymentMethod, int id, double salary){
		super(name, address, paymentMethod, id);
		this.salary = salary;
	}

	public void setSalary(double salary){
		this.salary = salary;
	}

	public double getSalary(){
		return this.salary;
	}
}