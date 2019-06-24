package system;
import java.util.ArrayList;

public class Payroll {
	private ArrayList<Employee> employees;
	private Union union = new Union();
	private Calendar calendar;

	public Payroll(){
		this.employees = new ArrayList<Employee>();

		boolean correctInput = false;
		while(!correctInput){
			System.out.println("Especifique a data de hoje");
			int[] date = Main.inputHandler.loadInt("(dd mm yy):", 3);
			
			System.out.print("Especifique o primeiro dia do ano (e.g. segunda, terca...):");
			String dayOfTheWeek = Main.in.nextLine();
			
			try{
				this.calendar = new Calendar(date[0], date[1], date[2], dayOfTheWeek);
				if(Main.inputHandler.checkDate(date, calendar) && 
				Main.inputHandler.convertDayOfTheWeek(dayOfTheWeek) != 0){
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

	public void addEmployee(Employee employee){
		employees.add(employee);
	}

	public void remove(int id){
		if(Main.sManager.searchByID(this.employees, id) != null){
			employees.remove(Main.sManager.searchByID(this.employees, id));
			System.out.println("Empregado removido");
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