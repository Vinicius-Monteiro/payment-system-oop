package system;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Payroll payroll = new Payroll();
        Scanner in = new Scanner(System.in);
        Employee n = new Salaried("Vinicius", "Jatiuca", "Mail", 1, 1200.0);
        in.close();
    }
}