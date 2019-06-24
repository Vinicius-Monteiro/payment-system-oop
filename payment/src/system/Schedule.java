package system;

public class Schedule {
	private String paymentSchedule;

	public Schedule(){
	}

	public Schedule(String paymentSchedule){
		this.paymentSchedule = paymentSchedule;
	}

	public void setPaymentSchedule(String paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}

	public String getPaymentSchedule() {
		return paymentSchedule;
	}

	public static String lastBusinessDay(int [][]calendar, int currentMonth) {
		int i = currentMonth, j = 31;
		for(; i < 13; i++) {
			for(; j >= 1; j--) {
				if(calendar[i][j] != -1 && calendar[i][j] != 7 && calendar[i][j] != 1)
					return Integer.toString(j) + '/' + Integer.toString(i);
			}
		}
		i = 0;
		j = 0;
		return Integer.toString(j) + '/' + Integer.toString(i);
	}

	public String nextXDay(int [][]calendar, int currentDay, int currentMonth, int day) {
		int i = currentMonth, j = currentDay;
		for(i = currentMonth; i < 13; i++){
			if(i != currentMonth) currentDay = 1;
			for(j = currentDay; j < 32; j++)
				if(calendar[i][j] == day) return Integer.toString(j) + '/' + Integer.toString(i);
		}
		i = 0;
		j = 0;
		return Integer.toString(j) + "/" + Integer.toString(i);
	}

	public String calculatePaymentDate(Payroll payroll){
		Calendar currentYear = payroll.getCalendar();
		int lastDayOfTheYear = currentYear.getYear()[12][31];
		if(++lastDayOfTheYear == 8) lastDayOfTheYear = 1;
		Calendar nextYear = new Calendar(1, 1, currentYear.getCurrentYear() + 1, lastDayOfTheYear);
		
		String[] split = paymentSchedule.split(" ");
		if(split[0].equals("mensal")){
			if(split[1].equals("$")){
				String aux = lastBusinessDay(currentYear.getYear(), currentYear.getCurrentMonth());
				if(aux.equals("0/0")) //proximo ano
					return lastBusinessDay(nextYear.getYear(), 1) + "/" + nextYear.getCurrentYear();
				else
					return aux + "/" + currentYear.getCurrentYear();
			} else {
				if(Integer.parseInt(split[1]) >= currentYear.getCurrentDay()){
					return split[1] + "/" + currentYear.getCurrentMonth() + 
					"/" + currentYear.getCurrentYear();
				} else {
					if(currentYear.getCurrentMonth() == 12){
						return split[1] + "/1/" + (currentYear.getCurrentYear() + 1);
					} else {
						return split[1] + "/" + (currentYear.getCurrentMonth() + 1) 
						+ "/" + currentYear.getCurrentYear();
					}
				}
			}
		} else {
			Calendar aux = currentYear;
			int day = currentYear.getCurrentDay();
			int month = currentYear.getCurrentMonth();
			String next = "\0";
			for(int i = 0; i < Integer.parseInt(split[1]); i++){
				next = nextXDay(aux.getYear(), day, month, Main.inputHandler.convertDayOfTheWeek(split[2]));
				if(next.equals("0/0")){
					aux = nextYear;
					next = nextXDay(aux.getYear(), 1, 1, Main.inputHandler.convertDayOfTheWeek(split[2]));
				}
				day = Integer.parseInt(next.split("/")[0]);
				month = Integer.parseInt(next.split("/")[1]);
				if(++day == 32){
					day = 1;
					month++;
				} if(month == 13 && i != (Integer.parseInt(split[1]) - 1))
					aux = nextYear;
			}
			return next + "/" + aux.getCurrentYear();
		}
	}

	@Override
	public String toString() {
		return paymentSchedule;
	}
}