package system;

public class Commissioned extends Salaried{
	public double commission;

	public Commissioned(int id){
		super(id);
	}

	public void setCommission(double commission){
		this.commission = commission;
	}

	public double getCommission(){
		return this.commission;
	}

	@Override
	public String toString(){
		return super.toString() + ", comissão de " + this.commission + "%";
	}
}