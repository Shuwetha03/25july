package login;

public class Employee {

	private int empNo;
	private String empName;
	private String job;
	private String hireDate;
	private int ManagerId;
	private int salary;
	private int commision;
	private int deptNo;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int empNo, String empName, String job, String hireDate, int managerId, int salary, int commision,
			int deptNo) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.hireDate = hireDate;
		ManagerId = managerId;
		this.salary = salary;
		this.commision = commision;
		this.deptNo = deptNo;
	}

	protected int getEmpNo() {
		return empNo;
	}

	protected void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	protected String getEmpName() {
		return empName;
	}

	protected void setEmpName(String empName) {
		this.empName = empName;
	}

	protected String getJob() {
		return job;
	}

	protected void setJob(String job) {
		this.job = job;
	}

	protected String getHireDate() {
		return hireDate;
	}

	protected void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	protected int getManagerId() {
		return ManagerId;
	}

	protected void setManagerId(int managerId) {
		ManagerId = managerId;
	}

	protected int getSalary() {
		return salary;
	}

	protected void setSalary(int salary) {
		this.salary = salary;
	}

	protected int getCommision() {
		return commision;
	}

	protected void setCommision(int commision) {
		this.commision = commision;
	}

	protected int getDeptNo() {
		return deptNo;
	}

	protected void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	
}