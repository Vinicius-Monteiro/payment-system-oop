package system;
import java.util.ArrayList;

public class Menu {
	private static final String init = "Programa inicializado!\n";

	private static final String commands = 
	"Comandos do programa:\n"
	+"\t[1,10]- Funcionalidades\n"
	+"\tmanual- Listar funcionalidades do sistema\n"
	+"\tdata- Mostrar a data atual do sistema\n"
	+"\tagendas - Agendas de pagamento cadastradas no sistema\n"
	+"\tprint- Listar empregados no sistema\n"
	+"\tsair- Terminar programa\n";
	
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
		System.out.print("\n" + init + "\n");
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

	public boolean getInput(){
		System.out.print(":");
		String input = Main.in.nextLine();

		if(Main.inputHandler.isNumeric(input)){
			int command = Integer.parseInt(input);
			if(command < 8){
				Main.states.pushUndo(Main.payroll.clone());
			}
			if(command != 8)
				Main.states.getRedoStack().clear();
		}

		if(input.equals("manual")) printFunctions();
		else if(input.equals("sair")) return false;
		else if(input.equals("data")) System.out.println(Main.payroll.getCalendar());
		else if(input.equals("agendas")) printSchedules(Main.schedules);
		else if(input.equals("print")) Main.payroll.printEmployees();
		else if(input.equals("1")) {
			System.out.println("Forneça:");
			System.out.print("\tO tipo do contrato:");
			String type = Main.in.nextLine();
			
			if(type.equals("salariado")){
				Salaried employee = new Salaried(Main.sManager.getGlobalId());
				employee = Main.sManager.createEmployee(employee, Main.payroll);
				Main.payroll.addEmployee(employee);
			} 
			else if(type.equals("comissionado")) {
				Commissioned employee = new Commissioned(Main.cManager.getGlobalId());
				employee = Main.cManager.createEmployee(employee, Main.payroll);
				Main.payroll.addEmployee(employee);
			} 
			else if(type.equals("horista")) {
				Hourly employee = new Hourly(Main.hManager.getGlobalId());
				employee = Main.hManager.createEmployee(employee, Main.payroll);
				Main.payroll.addEmployee(employee);
			} 
			else {
				System.out.println("Tipo de contrato incorreto");
				Main.states.popUndo();
				return getInput();
			}
			System.out.println("Empregado adicionado");
		} 
		else if(input.equals("2")) {
			System.out.println("Forneça:");
			Employee e = Main.inputHandler.loadEmployee(Main.payroll, "O id do funcionário:");
			Main.payroll.removeEmployee(e.getId());
			System.out.println("Empregado removido");
		} 
		else if(input.equals("3")){
			System.out.println("Forneça:");
			
			//checando se o empregado existe e se o casting é válido
			Employee e = Main.inputHandler.loadEmployee(Main.payroll, "O id do funcionário:");
			if(e instanceof Hourly){
				int []arrival = Main.inputHandler.loadInt("A hora de chegada (e.g. 7 30):", 2);
				int []exit = Main.inputHandler.loadInt("A hora de chegada (e.g. 15 20):", 2);
				if(Main.inputHandler.checkTime(arrival, exit))
					((Hourly)e).submitTimeCard(arrival[0], arrival[1], exit[0], exit[1]);
				else{
					System.out.println("Horários invalidos");
					Main.states.popUndo();
					return getInput();
				}
			} else {
				System.out.println("O funcionário não é horista");
				Main.states.popUndo();
				return getInput();
			}
			System.out.println("Cartão de ponto submetido");
		} 
		else if(input.equals("4")){
			System.out.println("Forneça:");
			
			//checando se o empregado existe e se o casting é válido
			Employee e = Main.inputHandler.loadEmployee(Main.payroll, "O id do funcionário:");
			if(e instanceof Commissioned){
				double price = Main.inputHandler.loadDouble("O valor da venda:");
				((Commissioned)e).submitSale(price);
			} else {
				System.out.println("O funcionário não é comissionado");
				Main.states.popUndo();
				return getInput();
			}
			System.out.println("Venda submetida");
		} 
		else if(input.equals("5")) {
			System.out.println("Forneça:");
			
			Employee e = Main.inputHandler.loadUnionEmployee(Main.payroll, "O id sindical do funcionário:");
			if(e.getUnionInfo().getBelongs())
				Main.payroll.getUnion().submitServiceFee(e);
			else{
				System.out.println("O funcionário não pertence ao sindicato");
				Main.states.popUndo();
				return getInput();
			}
			System.out.println("Taxa de serviço submetida");
		}
		else if(input.equals("6")) {
			System.out.println("Forneça:");

			Employee e = Main.inputHandler.loadEmployee(Main.payroll, "O id do funcionário:");
			
			boolean choice = Main.inputHandler.loadOptions("Deseja mudar o nome ? (y/n):", "y", "n");
			if(choice){
				System.out.print("\tnome:");
				String name = Main.in.nextLine();
				e.setName(name);
				System.out.println("Nome alterado");
			}

			choice = Main.inputHandler.loadOptions("Deseja mudar o endereço ? (y/n):", "y", "n");
			if(choice){
				System.out.print("\tendereço:");
				String address = Main.in.nextLine();
				e.setAddress(address);
				System.out.println("Endereço alterado");
			}

			choice = Main.inputHandler.loadOptions("Deseja mudar o método de pagamento ? (y/n):", "y", "n");
			if(choice){
				String method = Main.inputHandler.loadPaymentMethod();
				e.setPaymentMethod(method);;
				System.out.println("Método de pagamento alterado");
			}
			
			choice = Main.inputHandler.loadOptions("Deseja mudar o tipo do contrato ? (y/n):", "y", "n");
			if(choice){
				System.out.print("\tcontrato (comissionado, horista, salariado):");
				String contract = Main.in.nextLine();
				if(e instanceof Salaried && !(e instanceof Commissioned)){
					if(!contract.equals("comissionado") && !contract.equals("horista")){
						System.out.println("O funcionário já tem esse contrato");
						Main.states.popUndo();
						return getInput();
					}
				} else if(e instanceof Commissioned) {
					if(!contract.equals("salariado") && !contract.equals("horista")){
						System.out.println("O funcionário já tem esse contrato");
						Main.states.popUndo();
						return getInput();
					}
				} else if(e instanceof Hourly) {
					if(!contract.equals("comissionado") && !contract.equals("salariado")){
						System.out.println("O funcionário já tem esse contrato");
						Main.states.popUndo();
						return getInput();
					}	
				}
				if(contract.equals("salariado")){
					Salaried n = new Salaried(e.getId());
					Main.sManager.copyEmployee(e, n, Main.payroll);
					double salary = Main.inputHandler.loadDouble("salário mensal:");
					n.setSalary(salary);
					Main.payroll.removeEmployee(e.getId());
					Main.payroll.addEmployee(n);
					e = n;
				} else if(contract.equals("comissionado")){
					Commissioned n = new Commissioned(e.getId());
					Main.cManager.copyEmployee(e, n, Main.payroll);
					double salary = Main.inputHandler.loadDouble("salário mensal:");
					double commission = Main.inputHandler.loadDouble("comissão:");
					n.setSalary(salary);
					n.setCommission(commission);
					Main.payroll.removeEmployee(e.getId());
					Main.payroll.addEmployee(n);
					e = n;
				} else if(contract.equals("horista")){
					Hourly n = new Hourly(e.getId());
					Main.hManager.copyEmployee(e, n, Main.payroll);
					double hourPay = Main.inputHandler.loadDouble("salário por hora:");
					n.setHourPay(hourPay);
					Main.payroll.removeEmployee(e.getId());
					Main.payroll.addEmployee(n);
					e = n;
				} else {
					System.out.println("Contrato inválido");
					Main.states.popUndo();
					return getInput();
				}
				System.out.println("Contrato alterado");
			}

			choice = Main.inputHandler.loadOptions("deseja mudar alguma informação sindical ? (y/n):", "y", "n");
			if(choice){
				choice = Main.inputHandler.loadOptions("a participação no sindicato ? (y/n):", "y", "n");
				if(choice){
					choice = Main.inputHandler.loadOptions("pertence ao sindicato ? (y/n):", "y", "n");
					if(choice){
						if(e.getUnionInfo().getBelongs()){
							System.out.println("O funcionário já pertence ao sindicato");
							Main.states.popUndo();
							return getInput();
						}
						e.getUnionInfo().setBelongs(true);
						e.getUnionInfo().setId(Main.payroll.getUnion().getUnionGlobalID());
						Main.payroll.getUnion().incrementUnionGlobalID();
					}
					else{
						if(!e.getUnionInfo().getBelongs()){
							System.out.println("O funcionário já não pertence ao sindicato");
							Main.states.popUndo();
							return getInput();
						}
						e.getUnionInfo().setBelongs(false);
					}
					System.out.println("Participação alterada");
				}

				choice = Main.inputHandler.loadOptions("a identificação no sindicato ? (y/n):", "y", "n");
				if(choice){
					int id = Main.inputHandler.loadInt("id:");
					for(Employee aux: Main.payroll.getEmployees()){
						while(aux.getUnionInfo().getBelongs() && aux.getUnionInfo().getId() == id
						&& e.getId() != aux.getId()){
							System.out.println("Outro sindicalista já detém essa identificação");
							id = Main.inputHandler.loadInt("forneça outro id:");
						}
					}
					e.getUnionInfo().setId(id);
					System.out.println("Identificação no sindicato alterada");
				}

				choice = Main.inputHandler.loadOptions("a taxa sindical ? (y/n):", "y", "n");
				if(choice){
					double fee = Main.inputHandler.loadDouble("taxa sindical:");
					e.getUnionInfo().setFee(fee);
					System.out.println("Taxa sindical alterada");
				}
			}
		} 
		else if(input.equals("7")){
			String oldDay = Main.payroll.getCalendar().toString();
			System.out.println("Rodando a folha de pagamento para o dia " + oldDay);

			String nextDay = Main.payroll.getCalendar().nextDay();
			if(nextDay.equals("0/0")){
				int lastDayOfTheYear = Main.payroll.getCalendar().getYear()[12][31];
				if(++lastDayOfTheYear == 8) lastDayOfTheYear = 1;
				Calendar nextYear = new Calendar(1, 1, Main.payroll.getCalendar().getCurrentYear() + 1, lastDayOfTheYear);
				Main.payroll.setCalendar(nextYear);
			} else {
				Main.payroll.getCalendar().setCurrentDay(Integer.parseInt(nextDay.split("/")[0]));
				Main.payroll.getCalendar().setCurrentMonth(Integer.parseInt(nextDay.split("/")[1]));
			}

			for(Employee e: Main.payroll.getEmployees()){
				if(e.getNextPaymentDate().equals(oldDay)){
					double payment = Main.sManager.getPayment(e);
					System.out.println("\t---------------------");
					System.out.println("\tEmpregado " + e.getName() + " com ID " + e.getId() + " recebera hoje");
					if(e.getUnionInfo().getBelongs()){
						System.out.println("\tValor a receber antes das deducoes: $" + payment);
						System.out.println("\tDeducoes sindicais: " + e.getUnionInfo().getFee() + "%");
						System.out.println("\tValor a receber: $" + 
						Main.sManager.afterDeduction(payment, e.getUnionInfo().getFee()));
					} else
						System.out.println("\tValor a receber: " + payment);
					System.out.println("\tMétodo de pagamento: " + e.getPaymentMethod());
					e.setNextPaymentValue(0);
					e.setNextPaymentDate(e.getSchedule().calculatePaymentDate(Main.payroll));
					System.out.println("\tEmpregado pago.");
					System.out.println("\t---------------------");
				}
			}

			System.out.println("Todos os empregados foram pagos.");
			System.out.println("A data de hoje agora é " + Main.payroll.getCalendar().toString());
		}
		else if(input.equals("8")){
			boolean choice = Main.inputHandler.loadOptions("undo ou redo:", "undo", "redo");
			Payroll aux;
			String op;
			if(choice){
				aux = Main.states.popUndo();
				if(aux != null) Main.states.pushRedo(Main.payroll.clone());
				op = "Undo";
			} else {
				aux = Main.states.popRedo();
				op = "Redo";
				if(aux != null) Main.states.pushUndo(Main.payroll.clone());
			}
			if(aux != null) Main.payroll = aux;
			else {
				System.out.println("A operação não pode ser realizada. (Pilha vazia)");
				return getInput();
			}
			System.out.println(op + " realizado");
		}
		else if(input.equals("9")){
			System.out.println("Forneça:");
			Employee e = Main.inputHandler.loadEmployee(Main.payroll, "O id do funcionário:");
			
			System.out.println("Escolha uma das seguintes agendas:");
			printSchedules(Main.schedules);
			int choice = Main.inputHandler.loadInt(":");

			if(choice >= 1 && choice <= Main.schedules.size()){
				e.setSchedule(Main.schedules.get(choice - 1));
				e.setNextPaymentDate(e.getSchedule().calculatePaymentDate(Main.payroll));
			} else {
				System.out.println("Opção inválida");
				return getInput();
			}
			System.out.println("Agenda de pagamento alterada");
		}
		else if(input.equals("10")){
			System.out.println("Forneça a agenda de pagamento:");

			String paymentSchedule = Main.inputHandler.loadSchedule();
			for(Schedule e: Main.schedules){
				if(e.getPaymentSchedule().equals(paymentSchedule)){
					System.out.println("Essa agenda já existe");
					return getInput();
				}
			}
			if(paymentSchedule.split(" ")[0].equals("mensal")){
				int aux = Integer.parseInt(paymentSchedule.split(" ")[1]);
				if(aux > 28){
					System.out.println("Alguns meses não têm esse dia\nAgenda inválida");
					return getInput();
				}
			}
			Main.schedules.add(new Schedule(paymentSchedule));
			System.out.println("Agenda de pagamento criada");
		}
		else {
			System.out.println("Forneça um comando válido");
			return getInput();
		}

		return true;
	}
}