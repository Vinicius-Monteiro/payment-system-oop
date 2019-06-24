package system;

public class Union{
	private int unionGlobalID;

	public Union(){
		unionGlobalID = 1;
	}

	public int getUnionGlobalID(){
		return unionGlobalID;
	}

	public void incrementUnionGlobalID(){
		unionGlobalID++;
	}

	public void submitServiceFee(Employee employee){
		double serviceFee = Main.inputHandler.loadDouble("O preço do serviço prestado:");
		employee.setNextPaymentValue(employee.getNextPaymentValue() - serviceFee);
	}
}