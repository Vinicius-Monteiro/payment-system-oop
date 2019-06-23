package system;

public abstract class EmployeeManager <T extends Employee> {
	private static int globalId;

	public EmployeeManager(){
		globalId = 1;
	}

	public int getGlobalId(){
		return globalId;
	}

	public void incrementGlobalId(){
		globalId++;
	}
	
	public void collectUnionInfo(T employee, Union union){
		employee.unionInfo.setBelongs(true);
		employee.unionInfo.setId(union.getUnionGlobalID());
		union.incrementUnionGlobalID();
		employee.unionInfo.setFee(Main.inputHandler.loadDouble("Taxa sindical:"));
	}

	public void setBasicInfo(T employee, Union union){
		System.out.print("\tNome:");
		String name = Main.in.nextLine();
		employee.setName(name);
		
		System.out.print("\tEndereço:");
		String address = Main.in.nextLine();
		employee.setAddress(address);
		
		System.out.print("\tMétodo de pagamento:");
		String paymentMethod = Main.in.nextLine();
		employee.setPaymentMethod(paymentMethod);

		System.out.print("\tO funcionário pertence ao sindicato ?(y/n):");
		String answer = Main.in.nextLine();
		if(answer.equals("y")) collectUnionInfo(employee, union);
		else employee.unionInfo.setBelongs(false);
	}

	public abstract T createEmployee(T employee, Union union);
}