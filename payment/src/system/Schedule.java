package system;

public class Schedule {
	private boolean monthlyOrWeekly;//true = monthly / false = weekly
	private int day;
	private String dayOfTheWeek;

	public Schedule(){
	}

	public Schedule(boolean monthlyOrWeekly, int day, String dayOfTheWeek){
		this.monthlyOrWeekly = monthlyOrWeekly;
		this.day = day;
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setMonthlyOrWeekly(boolean monthlyOrWeekly) {
		this.monthlyOrWeekly = monthlyOrWeekly;
	}

	public boolean getMonthlyOrWeekly(){
		return this.monthlyOrWeekly;
	}

	public int getDay() {
		return day;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	@Override
	public String toString() {
		if(this.monthlyOrWeekly){
			if(this.day == -1) return ", recebe no último dia útil do mês";
			else return ", recebe todo dia " + this.day;
		} else
			return ", recebe a cada " + this.day + " semanas, " + this.dayOfTheWeek;
	}
}