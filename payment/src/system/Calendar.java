package system;

public class Calendar{
	private int currentDay;
	private int currentMonth;
	private int currentYear;
	private int[][] year;
	
	public Calendar(int day, int month, int year, int dayOfTheWeek){
		this.currentDay = day;
		this.currentMonth = month;
		this.currentYear = year;
		this.year = new int[13][32];
		buildCalendar(this.year, dayOfTheWeek);
	}

	public void buildCalendar(int[][] year, int dayOfTheWeek){
		for(int i = 0; i < 13; i++) {//meses
			for(int j = 0; j < 32; j++) {//dias
				year[i][j] = dayOfTheWeek;
				if (i == 0 || j == 0 || (i == 2 && j > 28)
					|| j > 30 && ((i < 8 && (i % 2) == 0)  
					|| (i > 8 && (i % 2) != 0))) year[i][j] = -1;
				else if(++dayOfTheWeek == 8) dayOfTheWeek = 1;
			}
		}
	}

	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	public void setYear(int[][] year) {
		this.year = year;
	}

	public int getCurrentDay() {
		return currentDay;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public int[][] getYear() {
		return year;
	}

	@Override
	public String toString(){
		return Integer.toString(currentDay) + "/" + Integer.toString(currentMonth) + "/" + Integer.toString(currentYear);
	}
}