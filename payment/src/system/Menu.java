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
		else if(input.equals("date")) System.out.println(payroll.getCalendar());
		else if(input.equals("schedules")) return false;//HANDLE
		else if(input.equals("print")) payroll.printEmployees();
		else if(input.equals("1")) {
			System.out.println("Forneça:");
			System.out.print("\tO tipo do contrato:");
			String type = Main.in.nextLine();
			
			if(type.equals("salaried")){
				Salaried employee = new Salaried(Main.sManager.getGlobalId());
				employee = Main.sManager.createEmployee(employee, payroll.getUnion());
				payroll.addEmployee(employee);
			} else if(type.equals("commissioned")) {
				Commissioned employee = new Commissioned(Main.cManager.getGlobalId());
				employee = Main.cManager.createEmployee(employee, payroll.getUnion());
				payroll.addEmployee(employee);
			} else if(type.equals("hourly")) {
				Hourly employee = new Hourly(Main.hManager.getGlobalId());
				employee = Main.hManager.createEmployee(employee, payroll.getUnion());
				payroll.addEmployee(employee);
			} else {
				System.out.println("Tipo de contrato incorreto");
				return getInput(payroll);
			}
		} else if(input.equals("2")) {
			System.out.println("Forneça:");
			int id = Main.inputHandler.loadInt("O id do funcionário:");
			payroll.remove(id);
		} else if(input.equals("3")){
			System.out.println("Forneça:");
			int id = Main.inputHandler.loadInt("O id do funcionário:");
			
			//checando se o empregado existe e se o casting é válido
			Employee e = Main.hManager.searchByID(payroll.getEmployees(), new Hourly(-1), id);
			if(e != null){
				int []arrival = Main.inputHandler.loadInt("A hora de chegada (e.g. 7 30):", 2);
				int []exit = Main.inputHandler.loadInt("A hora de chegada (e.g. 15 20):", 2);
				if(Main.inputHandler.checkTime(arrival, exit))
					((Hourly)e).submitTimeCard(arrival[0], arrival[1], exit[0], exit[1]);
				else
					System.out.println("Horários invalidos");
			} else System.out.println("Funcionário não encontrado ou não é horista");
		} else if(input.equals("4")){
			System.out.println("Forneça:");
			int id = Main.inputHandler.loadInt("O id do funcionário:");
			
			//checando se o empregado existe e se o casting é válido
			Employee e = Main.cManager.searchByID(payroll.getEmployees(), new Commissioned(-1), id);
			if(e != null){
				double price = Main.inputHandler.loadDouble("O valor da venda:");
				((Commissioned)e).submitSale(price);
			} else System.out.println("Funcionário não encontrado ou não é comissionado");
		} else if(input.equals("5")) {
			System.out.println("Forneça:");
			int id = Main.inputHandler.loadInt("O id do funcionário:");

			Employee e = Main.sManager.searchByID(payroll.getEmployees(), id);
			if(e != null){
				if(!e.getUnionInfo().getBelongs())
					System.out.println("O funcionário não pertence ao sindicato");
				else
					payroll.getUnion().submitServiceFee(e);
			} else System.out.println("Funcionário não encontrado");
		} else if(input.equals("6")) {
				
		}
		else {
			System.out.println("Forneça um comando válido");
			return getInput(payroll);
		}

		return true;
	}
}