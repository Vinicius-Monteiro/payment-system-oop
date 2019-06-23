package system;

public class InputHandler{
	public double loadDouble(String required) throws NumberFormatException{
		while(true){
			try{
				System.out.print("\t" + required);
				return Double.parseDouble(Main.in.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Forneça um valor numérico");
			}
		}
	}

	public int loadInt(String required) throws NumberFormatException{
		while(true){
			try{
				System.out.print("\t" + required);
				return Integer.parseInt(Main.in.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Forneça um valor numérico");
			}
		}
	}
}