package system;
import java.util.Scanner;

public class Main {
    public static final Menu menu = new Menu();
    public static final Scanner in = new Scanner(System.in);
    public static final SalariedManager<Salaried> sManager = new SalariedManager<Salaried>();
    public static final CommissionedManager cManager = new CommissionedManager();
    public static final HourlyManager hManager = new HourlyManager();
    public static final InputHandler inputHandler = new InputHandler();
    public static void main(String[] args){
        menu.printInit();
        menu.printCommands();
        Payroll payroll = new Payroll();

        boolean loop = true;
        while(loop){
            loop = menu.getInput(payroll);
        }
        in.close();
    }
}