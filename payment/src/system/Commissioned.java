package system;

public class Commissioned extends Salaried{
	public double commission;

	public Commissioned(String name, String address, String paymentMethod, int id, double salary, double commission){
		super(name, address, paymentMethod, id, salary);
		this.commission = commission;
	}

	public void setCommission(double commission){
		this.commission = commission;
	}

	public double getCommission(){
		return this.commission;
	}
}