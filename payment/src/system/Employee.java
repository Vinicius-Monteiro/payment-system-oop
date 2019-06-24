package system;

public abstract class Employee {
	private String name;
	private String address;
	private String paymentMethod;
	private final int id;//handle id abstractly
	private double nextPayment;
	private UnionInfo unionInfo = new UnionInfo();
	private Schedule schedule = new Schedule();

	public Employee(int id){
		this.id = id;
		this.nextPayment = 0;
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
	
	public void setNextPayment(double nextPayment) {
		this.nextPayment = nextPayment;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public String getName(){
		return this.name;
	}

	public String getAddress(){
		return this.address;
	}

	public String getPaymentMethod(){
		return this.paymentMethod;
	}

	public int getId() {
		return this.id;
	}

	public double getNextPayment() {
		return this.nextPayment;
	}

	public UnionInfo getUnionInfo() {
		return this.unionInfo;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	@Override
	public String toString() {
		return ("Empregado " + this.getName() + ", endereço " + this.getAddress() + ", método de pagamento "
		+ this.getPaymentMethod() + ", ID " + this.getId() + ", próximo contra-cheque de " + 
		this.getNextPayment() + ", " + this.getUnionInfo().toString()) + this.getSchedule().toString();
	}
}