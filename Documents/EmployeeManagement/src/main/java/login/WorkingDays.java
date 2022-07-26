package login;

public class WorkingDays {

	private int empNo;
	private int month;
	private int year;
	private int workingDays;
	
	public WorkingDays() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkingDays(int empNo, int month, int year, int workingDays) {
		super();
		this.empNo = empNo;
		this.month = month;
		this.year = year;
		this.workingDays = workingDays;
	}

	protected int getEmpNo() {
		return empNo;
	}

	protected void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	protected int getMonth() {
		return month;
	}

	protected void setMonth(int month) {
		this.month = month;
	}

	protected int getYear() {
		return year;
	}

	protected void setYear(int year) {
		this.year = year;
	}

	protected int getWorkingDays() {
		return workingDays;
	}

	protected void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}
	
}