package system;

public class InputHandler{
	public double loadDouble(String required) throws NumberFormatException{
		while(true){
			try{
				System.out.print("\t" + required);
				return Double.parseDouble(Main.in.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Forneça um valor numérico");
			}
		}
	}

	public int loadInt(String required) throws NumberFormatException{
		while(true){
			try{
				System.out.print("\t" + required);
				return Integer.parseInt(Main.in.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Forneça um valor numérico");
			}
		}
	}

	public int[] loadInt(String required, int size) throws NumberFormatException{
		int i = 0;
		int []array = new int[size];
		System.out.print("\t" + required);
		while(true){
			try{
				int number = Integer.parseInt(Main.in.next());
				array[i++] = number;
				if (i == size){
					Main.in.nextLine();
					return array;
				}
			} catch(NumberFormatException e) {
				System.out.println("Forneça um valor numérico");
			}
		}
	}

	public String loadSchedule(){
		while(true){
			System.out.print("\tmensal ou semanal:");
			String type = Main.in.nextLine();

			if(type.equals("mensal")){
				System.out.print("\tdia do mês ou $ para o último dia útil:");
				String helper = Main.in.nextLine();
				if(helper.equals("$") || isNumeric(helper))
					return type + " " + helper;
			} 
			else if(type.equals("semanal")) {
				System.out.print("\tA cada quantas semanas:");
				String helper = Main.in.nextLine();

				if(isNumeric(helper) && Integer.parseInt(helper) >= 1 
				&& Integer.parseInt(helper) <= 7)
					System.out.print("\tDia da semana (e.g. segunda, terca...) :");
					String dayOfTheWeek = Main.in.nextLine();
					if(convertDayOfTheWeek(dayOfTheWeek) != 0)
						return type + " " + helper + " " + dayOfTheWeek;
			}
			System.out.println("Agenda incorreta");
		}
	}

	public boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e) {  
			return false;  
		}  
	  }
	
	public int convertDayOfTheWeek(String dayOfTheWeek){
		if(dayOfTheWeek.equals("domingo")) return 1;
		else if(dayOfTheWeek.equals("segunda")) return 2;
		else if(dayOfTheWeek.equals("terca")) return 3;
		else if(dayOfTheWeek.equals("quarta")) return 4;
		else if(dayOfTheWeek.equals("quinta")) return 5;
		else if(dayOfTheWeek.equals("sexta")) return 6;
		else if(dayOfTheWeek.equals("sabado")) return 7;
		else return 0;
	}

	public boolean checkDate(int[] currentDate, Calendar calendar){
		if(calendar.getYear()[currentDate[1]][currentDate[0]] == -1)
			return false;
		else
			return true;
	}

	public boolean checkTime(int[] arrival, int[] exit){
		if(arrival[0] < exit[1]) return true;
		else if(arrival[0] == exit[0] == arrival[1] <= exit[1]) return true;
		else return false;
	}

}