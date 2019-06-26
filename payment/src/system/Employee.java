package system;

public abstract class Employee{
	private String name;
	private String address;
	private String paymentMethod;
	private int id;//handle id abstractly
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

	public void setId(int id) {
		this.id = id;
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

	public void setUnionInfo(UnionInfo unionInfo) {
		this.unionInfo = unionInfo;
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
	
	public void clone(Employee from, Employee to){
		to.setName(from.getName());
		to.setAddress(from.getAddress());
		to.setPaymentMethod(from.getPaymentMethod());
		to.setId(from.getId());
		to.setNextPaymentValue(from.getNextPaymentValue());
		to.setNextPaymentDate(from.getNextPaymentDate());
		to.setUnionInfo((UnionInfo)from.getUnionInfo().clone());
		to.setSchedule((Schedule)from.getSchedule().clone());
	}

	@Override
	public String toString() {
		return ("Nome: " + name + "\nEndereço: " + address + "\nMétodo de pagamento: "
		+ paymentMethod + "\nID: " + id + "\nPróximo contra-cheque: " + 
		Main.sManager.getPayment(this) + " em " + nextPaymentDate + unionInfo.toString()) 
		+ "\nCalendário de pagamento: " + schedule.toString();
	}
}