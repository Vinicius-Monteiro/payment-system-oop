package system;
import java.util.ArrayList;

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

	public void printSchedules(ArrayList<Schedule> schedules){
		for(int i = 0; i < schedules.size(); i++){
			System.out.println("\t" + (i + 1) + " - " + schedules.get(i).toString());
		}
	}

	public boolean getInput(Payroll payroll){
		System.out.print(":");
		String input = Main.in.nextLine();

		if(input.equals("manual")) printFunctions();
		else if(input.equals("exit")) return false;
		else if(input.equals("date")) System.out.println(payroll.getCalendar());
		else if(input.equals("schedules")) printSchedules(Main.schedules);
		else if(input.equals("print")) payroll.printEmployees();
		else if(input.equals("1")) {
			System.out.println("Forneça:");
			System.out.print("\tO tipo do contrato:");
			String type = Main.in.nextLine();
			
			if(type.equals("salariado")){
				Salaried employee = new Salaried(Main.sManager.getGlobalId());
				employee = Main.sManager.createEmployee(employee, payroll);
				payroll.addEmployee(employee);
			} 
			else if(type.equals("comissionado")) {
				Commissioned employee = new Commissioned(Main.cManager.getGlobalId());
				employee = Main.cManager.createEmployee(employee, payroll);
				payroll.addEmployee(employee);
			} 
			else if(type.equals("horista")) {
				Hourly employee = new Hourly(Main.hManager.getGlobalId());
				employee = Main.hManager.createEmployee(employee, payroll);
				payroll.addEmployee(employee);
			} 
			else {
				System.out.println("Tipo de contrato incorreto");
				return getInput(payroll);
			}
		} 
		else if(input.equals("2")) {
			System.out.println("Forneça:");
			Employee e = Main.inputHandler.loadEmployee(payroll, "O id do funcionário:");
			payroll.remove(e.getId());
		} 
		else if(input.equals("3")){
			System.out.println("Forneça:");
			
			//checando se o empregado existe e se o casting é válido
			Employee e = Main.inputHandler.loadEmployee(payroll, "O id do funcionário:");
			if(e instanceof Hourly){
				int []arrival = Main.inputHandler.loadInt("A hora de chegada (e.g. 7 30):", 2);
				int []exit = Main.inputHandler.loadInt("A hora de chegada (e.g. 15 20):", 2);
				if(Main.inputHandler.checkTime(arrival, exit))
					((Hourly)e).submitTimeCard(arrival[0], arrival[1], exit[0], exit[1]);
				else
					System.out.println("Horários invalidos");
			} else System.out.println("Funcionário não encontrado ou não é horista");
		} 
		else if(input.equals("4")){
			System.out.println("Forneça:");
			
			//checando se o empregado existe e se o casting é válido
			Employee e = Main.inputHandler.loadEmployee(payroll, "O id do funcionário:");
			if(e instanceof Commissioned){
				double price = Main.inputHandler.loadDouble("O valor da venda:");
				((Commissioned)e).submitSale(price);
			} else System.out.println("O funcionário não é comissionado");
		} 
		else if(input.equals("5")) {
			System.out.println("Forneça:");
			
			Employee e = Main.inputHandler.loadEmployee(payroll, "O id do funcionário:");
			if(!e.getUnionInfo().getBelongs())
				System.out.println("O funcionário não pertence ao sindicato");
			else
				payroll.getUnion().submitServiceFee(e);
		}
		else if(input.equals("6")) {
			System.out.println("Forneça:");

			Employee e = Main.inputHandler.loadEmployee(payroll, "O id do funcionário:");
			
			System.out.print("\tDeseja mudar o nome ? (y/n):");
			String choice = Main.in.nextLine();
			if(choice.equals("y")){
				System.out.print("\tnome:");
				String name = Main.in.nextLine();
				e.setName(name);
			}
			System.out.print("\tDeseja mudar o endereço ? (y/n):");
			choice = Main.in.nextLine();
			if(choice.equals("y")){
				System.out.print("\tendereço:");
				String address = Main.in.nextLine();
				e.setAddress(address);
			}
			System.out.print("\tDeseja mudar o tipo do contrato ? (y/n):");
			choice = Main.in.nextLine();
			if(choice.equals("y")){
				System.out.print("\tcontrato (comissionado, horista, salariado):");
				String contract = Main.in.nextLine();
				if(e instanceof Salaried && !(e instanceof Commissioned)){
					if(!contract.equals("comissionado") && !contract.equals("horista")){
						System.out.println("O funcionário já tem esse tipo");
						return true;
					}
				} else if(e instanceof Commissioned) {
					if(!contract.equals("salaried") && !contract.equals("horista")){
						System.out.println("O funcionário já tem esse tipo");
						return true;
					}
				} else if(e instanceof Hourly) {
					if(!contract.equals("comissionado") && !contract.equals("salaried")){
						System.out.println("O funcionário já tem esse tipo");
						return true;
					}
				}
				
			}
		} 
		else if(input.equals("7")){
			System.out.println("Forneça:");

			Employee e = Main.inputHandler.loadEmployee(payroll, "O id do funcionário:");
			
		}
		else if(input.equals("9")){
			System.out.println("Forneça:");
			Employee e = Main.inputHandler.loadEmployee(payroll, "O id do funcionário:");
			
			System.out.println("Escolha uma das seguintes agendas:");
			printSchedules(Main.schedules);
			int choice = Main.inputHandler.loadInt(":");

			if(choice >= 1 && choice <= Main.schedules.size()){
				e.setSchedule(Main.schedules.get(choice - 1));
				e.setNextPaymentDate(e.getSchedule().calculatePaymentDate(payroll));
			} else {
				System.out.println("Opção inválida");
			}
		}
		else if(input.equals("10")){
			System.out.println("Forneça a agenda de pagamento:");

			String paymentSchedule = Main.inputHandler.loadSchedule();
			for(Schedule e: Main.schedules){
				if(e.getPaymentSchedule().equals(paymentSchedule)){
					System.out.println("Essa agenda já existe");
					return true;
				}
			}
			Main.schedules.add(new Schedule(paymentSchedule));
		}
		else {
			System.out.println("Forneça um comando válido");
			return getInput(payroll);
		}

		return true;
	}
}