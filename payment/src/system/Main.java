package system;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static final Menu menu = new Menu();
    public static final Scanner in = new Scanner(System.in);
    public static final SalariedManager<Salaried> sManager = new SalariedManager<Salaried>();
    public static final CommissionedManager cManager = new CommissionedManager();
    public static final HourlyManager hManager = new HourlyManager();
    public static final InputHandler inputHandler = new InputHandler();
	public static ArrayList<Schedule> schedules = new ArrayList<Schedule>();

    public static void main(String[] args){
        menu.printInit();
        menu.printCommands();
        Payroll payroll = new Payroll();
		schedules.add(new Schedule(true, -1, "\0"));//mensal $
		schedules.add(new Schedule(false, 1, "sexta"));//semanal 1 sexta
		schedules.add(new Schedule(false, 2, "sexta"));//semanal 2 sexta

        boolean loop = true;
        while(loop){
            loop = menu.getInput(payroll);
        }
        in.close();
    }
}