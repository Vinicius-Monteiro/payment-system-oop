package system;

public class Commissioned extends Salaried{
	private double commission;

	public Commissioned(int id){
		super(id);
	}

	public void setCommission(double commission){
		this.commission = commission;
	}

	public double getCommission(){
		return commission;
	}

	public void submitSale(double price) {
		setNextPaymentValue(getNextPaymentValue() + ((commission * price)/100));
	}

	public Commissioned clone(){
		Commissioned employee = new Commissioned(this.getId());

		employee.setSalary(this.getSalary());
		employee.setCommission(this.getCommission());
		((Employee)employee).clone(this, employee);

		return employee;
	}

	@Override
	public String toString(){
		return super.toString() + "\nComissão de: " + commission + "%";
	}
}