package system;

public class Calendar{
	int currentDay;
	int currentMonth;
	int currentYear;
	int[][] year;
	
	public Calendar(int day, int month, int year, String dayOfTheWeek){
		this.currentDay = day;
		this.currentMonth = month;
		this.currentYear = year;
		this.year = new int[13][32];
		buildCalendar(this.year, dayOfTheWeek);
	}

	public void buildCalendar(int[][] year, String dayOfTheWeek){
		int day = 0;
		if(dayOfTheWeek.equals("domingo")) day = 1;
		else if(dayOfTheWeek.equals("segunda")) day = 2;
		else if(dayOfTheWeek.equals("terca")) day = 3;
		else if(dayOfTheWeek.equals("quarta")) day = 4;
		else if(dayOfTheWeek.equals("quinta")) day = 5;
		else if(dayOfTheWeek.equals("sexta")) day = 6;
		else if(dayOfTheWeek.equals("sabado")) day = 7;
		for(int i = 0; i < 13; i++) {//meses
			for(int j = 0; j < 32; j++) {//dias
				year[i][j] = day;
				if (i == 0 || j == 0 || (i == 2 && j > 28)
					|| j > 30 && ((i < 8 && (i % 2) == 0)  
					|| (i > 8 && (i % 2) != 0))) year[i][j] = -1;
				else if(++day == 8) day = 1;
			}
		}
	}

	@Override
	public String toString(){
		return Integer.toString(this.currentDay) + "/" + Integer.toString(this.currentMonth) + "/" + Integer.toString(this.currentYear);
	}
}