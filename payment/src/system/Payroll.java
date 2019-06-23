package system;
import java.util.ArrayList;

public class Payroll {
	ArrayList<Employee> employees;
	Union union = new Union();
	Calendar calendar;

	public Payroll(){
		this.employees = new ArrayList<Employee>();

		System.out.println("Especifique a data de hoje");
		int day = 0, month = 0, year = 0;
		day = Main.inputHandler.loadInt("dia:");
		month = Main.inputHandler.loadInt("mes:");
		year = Main.inputHandler.loadInt("ano:");
		
		System.out.print("Especifique o primeiro dia do ano (e.g. segunda, terca...):");
		String dayOfTheWeek = Main.in.nextLine();
		
		this.calendar = new Calendar(day, month, year, dayOfTheWeek);
	}

	public void addEmployee(Employee employee){
		employees.add(employee);
	}

	public void remove(int id){
		for(Employee e: employees){
			if(e.getId() == id){
				employees.remove(e);
				System.out.println("Empregado removido");
				return;
			}
		}
		System.out.println("Empregado não encontrado");
	}

	public void printEmployees(){
		System.out.println("------------------------");
		for(Employee e: employees){
			System.out.println(e);
			System.out.println("------------------------");
		}
	}
}