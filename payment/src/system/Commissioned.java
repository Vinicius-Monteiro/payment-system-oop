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
		return this.commission;
	}

	public void submitSale(double price) {
		this.setNextPayment(this.getNextPayment() + ((this.commission * price)/100));
	}

	@Override
	public String toString(){
		return super.toString() + ", comissão de " + this.commission + "%";
	}
}