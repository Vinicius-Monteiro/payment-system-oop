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

		employee.setName(this.getName());
		employee.setAddress(this.getAddress());
		employee.setPaymentMethod(this.getPaymentMethod());
		employee.setId(this.getId());//handle id abstractly
		employee.setSalary(this.getSalary());
		employee.setCommission(this.getCommission());
		employee.setNextPaymentValue(this.getNextPaymentValue());
		employee.setNextPaymentDate(this.getNextPaymentDate());
		employee.setUnionInfo((UnionInfo)this.getUnionInfo().clone());
		employee.setSchedule((Schedule)this.getSchedule().clone());

		return employee;
	}

	@Override
	public String toString(){
		return super.toString() + ", comissionado, comissão de " + commission + "%";
	}
}