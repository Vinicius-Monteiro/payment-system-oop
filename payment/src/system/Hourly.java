package system;

public class Hourly extends Employee{
	private double hourPay;

	public Hourly(String name, String address, String paymentMethod, int id, double hourPay){
		super(name, address, paymentMethod, id);
		this.hourPay = hourPay;
	}

	public void setHourPay(double hourPay){
		this.hourPay = hourPay;
	}

	public double getHourPay(){
		return this.hourPay;
	}
}