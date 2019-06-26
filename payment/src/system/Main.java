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
	public static final ArrayList<Schedule> schedules = new ArrayList<Schedule>();
    public static final StatesRestorer states = new StatesRestorer();
    public static Payroll payroll = new Payroll();

    public static void main(String[] args){
        menu.printInit();
        menu.printCommands();

		schedules.add(new Schedule("mensal $"));
		schedules.add(new Schedule("semanal 1 sexta"));
		schedules.add(new Schedule("semanal 2 sexta"));

        boolean loop = true;
        while(loop){
            loop = menu.getInput();
        }
        
        in.close();
    }
}