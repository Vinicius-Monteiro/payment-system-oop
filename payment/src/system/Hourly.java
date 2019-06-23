package system;

public class Hourly extends Employee{
	private double hourPay;

	public Hourly(int id){
		super(id);
	}

	public void setHourPay(double hourPay){
		this.hourPay = hourPay;
	}

	public double getHourPay(){
		return this.hourPay;
	}

	@Override
	public String toString(){
		return super.toString() + ", salário/hora de " + this.hourPay;
	}
}