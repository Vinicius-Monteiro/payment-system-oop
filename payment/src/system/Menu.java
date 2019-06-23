package system;

public class Menu {
	private static final String init = "Programa inicializado!\n";

	private static final String commands = 
	"Comandos do programa:\n"
	+"\t[1,10]- Funcionalidades\n"
	+"\tmanual- Listar funcionalidades do sistema\n"
	+"\texit- Terminar programa\n"
	+"\tdate- Mostrar a data atual do sistema\n"
	+"\tschedules - Agendas de pagamento cadastradas no sistema\n"
	+"\tprint- Listar empregados no sistema\n";
	
	private static final String functions = 
	"Funcionalidades do programa:\n"
	+"\t1- Adicao de um empregado\n"
	+"\t2- Remocao de um empregado\n"
	+"\t3- Lancar um cartao de ponto\n"
	+"\t4- Lancar um resultado de venda\n"
	+"\t5- Lancar uma taxa de servico\n"
	+"\t6- Alterar detalhes de um empregado\n"
	+"\t7- Rodar a folha de pagamento para hoje\n"
	+"\t8- Undo/redo\n"
	+"\t9- Agenda de pagamento\n"
	+"\t10- Criacao de novas agendas de pagamento\n";
	
	public void printInit(){
		System.out.print(init);
	}

	public void printCommands(){
		System.out.print(commands);
	}

	public void printFunctions(){
		System.out.print(functions);
	}

	public boolean getInput(Payroll payroll){
		System.out.print(":");
		String input = Main.in.nextLine();

		if(input.equals("manual")) this.printFunctions();
		else if(input.equals("exit")) return false;
		else if(input.equals("date")) System.out.println(payroll.calendar);
		else if(input.equals("schedules")) return false;//HANDLE
		else if(input.equals("print")) payroll.printEmployees();
		else if(input.equals("1")) {
			System.out.println("Forneça:");
			System.out.print("\tO tipo do contrato:");
			String type = Main.in.nextLine();
			
			if(type.equals("salaried")){
				Salaried employee = new Salaried(Main.sManager.getGlobalId());
				employee = Main.sManager.createEmployee(employee, payroll.union);
				payroll.addEmployee(employee);
			} else if(type.equals("commissioned")) {
				Commissioned employee = new Commissioned(Main.cManager.getGlobalId());
				employee = Main.cManager.createEmployee(employee, payroll.union);
				payroll.addEmployee(employee);
			} else if(type.equals("hourly")) {
				Hourly employee = new Hourly(Main.hManager.getGlobalId());
				employee = Main.hManager.createEmployee(employee, payroll.union);
				payroll.addEmployee(employee);
			} else {
				System.out.println("Tipo de contrato incorreto");
				return getInput(payroll);
			}
		} else if(input.equals("2")) {
			System.out.println("Forneça:");
			int id = Main.inputHandler.loadInt("O id do funcionário:");
			payroll.remove(id);
		} else {
			System.out.println("Forneça um comando válido");
			return getInput(payroll);
		}

		return true;
	}
}