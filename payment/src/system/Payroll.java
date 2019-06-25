package system;
import java.util.ArrayList;

public class Payroll {
	private ArrayList<Employee> employees;
	private Union union = new Union();
	private Calendar calendar;

	public Payroll(){
		employees = new ArrayList<Employee>();

		boolean correctInput = false;
		while(!correctInput){
			System.out.println("Especifique a data de hoje");
			int[] date = Main.inputHandler.loadInt("(dd mm yy):", 3);
			
			System.out.print("Especifique o primeiro dia do ano (e.g. segunda, terca...)\n\t:");
			String dayOfTheWeek = Main.in.nextLine();
			int day = Main.inputHandler.convertDayOfTheWeek(dayOfTheWeek);

			if(day == 0){
				System.out.println("Dia atual inválido");
				continue;
			}

			try{
				calendar = new Calendar(date[0], date[1], date[2], day);
				if(Main.inputHandler.checkDate(date, calendar)){
					System.out.println("Calendário criado");
					correctInput = true;
				} else
					System.out.println("Dia atual inválido");
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Dia atual inválido");
			}
		}
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public Union getUnion() {
		return union;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public void addEmployee(Employee employee){
		employees.add(employee);
	}

	public void remove(int id){
		if(Main.sManager.searchByID(employees, id) != null){
			employees.remove(Main.sManager.searchByID(employees, id));
		} else System.out.println("Empregado não encontrado");
	}

	public void printEmployees(){
		System.out.println("------------------------");
		for(Employee e: employees){
			System.out.println(e);
			System.out.println("------------------------");
		}
	}
}