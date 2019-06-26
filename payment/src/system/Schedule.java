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

	public String calculatePaymentDate(Payroll payroll){
		Calendar currentYear = payroll.getCalendar();
		int lastDayOfTheYear = currentYear.getYear()[12][31];
		if(++lastDayOfTheYear == 8) lastDayOfTheYear = 1;
		Calendar nextYear = new Calendar(1, 1, currentYear.getCurrentYear() + 1, lastDayOfTheYear);
		
		String[] split = paymentSchedule.split(" ");
		if(split[0].equals("mensal")){
			if(split[1].equals("$")){
				String aux = currentYear.lastBusinessDay(currentYear.getYear(), currentYear.getCurrentMonth());
				if(Integer.parseInt(aux.split("/")[0]) < payroll.getCalendar().getCurrentDay())
					aux = currentYear.lastBusinessDay(currentYear.getYear(), currentYear.getCurrentMonth() + 1);
				if(aux.equals("0/0")) //proximo ano
					return nextYear.lastBusinessDay(nextYear.getYear(), 1) + "/" + nextYear.getCurrentYear();
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
				next = aux.nextXDay(aux.getYear(), day, month, Main.inputHandler.convertDayOfTheWeek(split[2]));
				if(next.equals("0/0")){
					aux = nextYear;
					next = aux.nextXDay(aux.getYear(), 1, 1, Main.inputHandler.convertDayOfTheWeek(split[2]));
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

	public Object clone(){
		return new Schedule(this.getPaymentSchedule());
	}

	@Override
	public String toString() {
		return paymentSchedule;
	}
}