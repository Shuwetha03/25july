package login;

public class PaySlip {

	private int empNO;
	private int month;
	private int year;
	private int days;
	private long basic;
	private long hra;
	private long deductions;
	private long salary;
	
	public PaySlip() {
		
	}

	public PaySlip(int empNO, int month, int year, int days, long basic, long hra, long deductions, long salary) {
		super();
		this.empNO = empNO;
		this.month = month;
		this.year = year;
		this.days = days;
		this.basic = basic;
		this.hra = hra;
		this.deductions = deductions;
		this.salary = salary;
	}

	public int getEmpNO() {
		return empNO;
	}

	public void setEmpNO(int empNO) {
		this.empNO = empNO;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public long getBasic() {
		return basic;
	}

	public void setBasic(long basic) {
		this.basic = basic;
	}

	public long getHra() {
		return hra;
	}

	public void setHra(long hra) {
		this.hra = hra;
	}

	public long getDeductions() {
		return deductions;
	}

	public void setDeductions(long deductions) {
		this.deductions = deductions;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	
	
	
}
