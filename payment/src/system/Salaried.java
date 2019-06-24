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

	@Override
	public String toString(){
		return super.toString() + ", salário mensal " + salary;
	}
}