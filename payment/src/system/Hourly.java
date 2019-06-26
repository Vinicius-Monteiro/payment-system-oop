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
		return hourPay;
	}

	public void submitTimeCard(int arrivalH, int arrivalM, int exitH, int exitM){
		int timeWorked = timeToMinutes(arrivalH, arrivalM, exitH, exitM);
		double payment = relativePay(getHourPay(), timeWorked);
		setNextPaymentValue(getNextPaymentValue() + payment);
	}

	public double relativePay(double hourPay, int timeWorked) {
		double perMinute = hourPay / 60.0;
		if(timeWorked <= (60 * 8)) return perMinute * timeWorked;
		else return ((timeWorked - (60 * 8)) * (1.5 * perMinute)) + ((60 * 8) * perMinute);
	}

	public int timeToMinutes(int arrivalH, int arrivalM, int exitH, int exitM) {
		int hours = (exitH - arrivalH), mins, total;
		total = hours * 60;
		if(exitM < arrivalM) {
			mins = arrivalM - exitM;
			total -= mins;
		} else {
			mins = exitM - arrivalM;
			total += mins;
		}
		return total;
	}

	public Hourly clone(){
		Hourly employee = new Hourly(this.getId());

		employee.setHourPay(this.getHourPay());
		((Employee)employee).clone(this, employee);

		return employee;
	}

	@Override
	public String toString(){
		return super.toString() + "\nContrato: Horista\nSalário/hora de: " + hourPay;
	}
}