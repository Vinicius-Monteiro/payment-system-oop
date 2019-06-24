package system;

public abstract class Employee {
	private String name;
	private String address;
	private String paymentMethod;
	private final int id;//handle id abstractly
	private double nextPaymentValue;
	private String nextPaymentDate;
	private UnionInfo unionInfo = new UnionInfo();
	private Schedule schedule = new Schedule();

	public Employee(int id){
		this.id = id;
		this.nextPaymentValue = 0;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setAddress(String address){
		this.address = address;	
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}
	
	public void setNextPaymentValue(double nextPaymentValue) {
		this.nextPaymentValue = nextPaymentValue;
	}

	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public String getName(){
		return name;
	}

	public String getAddress(){
		return address;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public int getId() {
		return id;
	}

	public double getNextPaymentValue() {
		return nextPaymentValue;
	}

	public String getNextPaymentDate() {
		return nextPaymentDate;
	}

	public UnionInfo getUnionInfo() {
		return unionInfo;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	@Override
	public String toString() {
		return ("Empregado " + name + ", endereço " + address + ", método de pagamento "
		+ paymentMethod + ", ID " + id + ", próximo contra-cheque de " + 
		nextPaymentValue + " em " + nextPaymentDate + ", " + unionInfo.toString()) 
		+ ", calendário de pagamento " + schedule.toString();
	}
}