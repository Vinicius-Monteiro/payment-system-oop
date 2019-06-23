package system;

public abstract class Employee {
	private String name;
	private String address;
	private String paymentMethod;
	private final int id;//handle id abstractly
	private double payCheck;
	public UnionInfo unionInfo = new UnionInfo();

	public Employee(int id){
		this.id = id;
		this.payCheck = 0;
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
	
	public void setPayCheck(double payCheck) {
		this.payCheck = payCheck;
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
		return id;
	}

	public double getPayCheck() {
		return payCheck;
	}

	public String getUnionInfo() {
		return unionInfo.toString();
	}

	@Override
	public String toString() {
		return ("Empregado " + this.getName() + ", endereço " + this.getAddress() + ", método de pagamento "
		+ this.getPaymentMethod() + ", ID " + this.getId() + ", " + this.getUnionInfo());
	}
}