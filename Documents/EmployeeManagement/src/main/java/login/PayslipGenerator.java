package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PayslipGenerator
 */
@WebServlet("/PayslipGenerator")
public class PayslipGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayslipGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		response.setContentType("text/html");

		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		try {
			Employee e1 = getEmployeeDetails(email);
			int joinMonth = Integer.parseInt(e1.getHireDate().substring(3, 5));
			int joinYear = Integer.parseInt(e1.getHireDate().substring(6));

			if ((month >= joinMonth) && (year >= joinYear)) {
				LocalDate date = LocalDate.now();
				int currMonth = date.getMonthValue();
				int currYear = date.getYear();
				if ((month != currMonth) && (year <= currYear)) {
					PaySlip p = getPayslip(e1.getEmpNo());
					Connection connect = getConnection();
					//		String name = getEmpName(e1.getEmpNo());

					pw.println("<a class = 'btn btn-secondary btn-lg disabled' href='Logout' role='button' aria-disabled=\"true\" style='float: right'>Logout</a>");

					pw.println("<br><h2 >Hiii " + email + " Your PaySlip</h2> ");
					PreparedStatement ps = connect.prepareStatement("select * from PaySlip where EmpNo = (Select EmpNo from LoginCredentials where emailID = ?) and month = ? and year = ?");
					ps.setString(1, email);
					ps.setInt(2, month);
					ps.setInt(3, year);



					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						pw.println("\n"
								+ "<html>\n"
								+ "    <head>\n"
								+ "    <style>\n"
								+ "    table{\n"
								+ "    width: 100%;\n"
								+ "    border-collapse:collapse;\n"
								+ "    border: 2px solid black;\n"
								+ "    padding: 50px;;\n"
								+ "    }\n"
								+ "    table td{line-height:25px;padding-left:15px;}\n"
								+ "    table th{background-color:#11e9d7; \n"
								+ "        padding: 10px;\n"
								+ "        color:#363636;}\n"
								+ "    </style>\n"
								+ "    \n"
								+ "    </head>\n"
								+ "    <body>\n"
								+ "        <h1></h1>\n"
								+ "    <table border=\"1\">\n"
								+ "    <tr height=\"100px\" style=\"background-color:#434747;color:#ffffff;text-align:center;font-size:24px; font-weight:600;\">\n"
								+ "    <td colspan='4'>Salary Slip </td>\n"
								+ "    </tr>\n"
								+ "    </tr>\n"
								+ "    <th>EmpNo:</th>\n"
								+ "    <td>" + p.getEmpNO() + "</td>\n"
								+ "    </tr>\n"
								+ "    <th>Month</th>\n"
								+ "    <td>" + p.getMonth() + "</td>\n"
								+ "    </tr>\n"
								+ "    <!------3 row---->\n"
								+ "    <tr>\n"
								+ "    <th>Year</th>\n"
								+ "    <td>" + p.getYear() + "</td>\n"
								+ "    </tr>\n"
								+ "    <!------4 row---->\n"
								+ "    <tr>\n"
								+ "    <th>No Of Working days</th>\n"
								+ "    <td>" + p.getDays()+ "</td>\n"
								+ "    </tr>\n"
								+ "    <!------5 row---->\n"
								+ "    <tr></tr>\n"
								+ "    <br/>\n"
								+ "    <table border=\"1\">\n"
								+ "    <tr>\n"
								+ "    <th >Earnings</th>\n"
								+ "    <th>Amount</th>\n"
								+ "    <th >Deductions</th>\n"
								+ "    <th>Amount</th>\n"
								+ "    </tr>\n"
								+ "    <tr>\n"
								+ "    <td>House Rent Allowence</td>\n"
								+ "    <td>Rs." + p.getHra() + "</td>\n"
								+ "    <td>Provident fund</td>\n"
								+ "    <td>Rs." + p.getDeductions() + "</td>\n"
								+ "    </tr>\n"
								+ "    <tr>\n"
								+ "    <tr>\n"
								+ "    <td>Basics</td>\n"
								+ "    <td>Rs." + p.getBasic() + "</td>\n"
								+ "    </tr>\n"
								+ "    <tr>\n"
								+ "    <th>Net Pay</th>\n"
								+ "    <td> Rs." + p.getSalary() + "</td>\n"
								+ "    <th >Net Deductions</th>\n"
								+ "    <td>Rs." + p.getDeductions() + "</td>\n"
								+ "    </tr>\n"
								+ "   \n"
								+ "    </table>\n"
								+ "    </body>\n"
								+ "    </html>");


						response.setContentType("text/html");  
					}

				} else {
					pw.println("Payslip is not generated.");
					pw.println("<a class = 'btn btn-primary' href='Logout' role='button' style='float: right'>Logout</a>");
				}
			} else {
				pw.println("<h1 >You entered month before joining Date , Please enter valid month...</h1>");

				pw.println("<a class = 'btn btn-primary' href='Logout' role='button' style='float: right'>Logout</a>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public Employee getEmployeeDetails(String email) throws ClassNotFoundException, SQLException {

		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement(
				"select * from Employee where EmpNo = (Select EmpNo from LoginCredentials where emailID = ?)");
		ps.setString(1, email);

		ResultSet rs = ps.executeQuery();
		if (rs.next())
			return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
					rs.getInt(6), rs.getInt(7), rs.getInt(8));
		return null;
	}


	public PaySlip getPayslip(int empNo) throws ClassNotFoundException, SQLException {

		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement("select * from PaySlip where EmpNo = ?");
		ps.setInt(1, empNo);

		ResultSet rs = ps.executeQuery();
		if (rs.next())
			return new PaySlip(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getInt(4), rs.getLong(5), rs.getLong(6), rs.getLong(7),rs.getLong(8));
		return null;
	}


	public String getEmpName(int empNo) throws ClassNotFoundException, SQLException {
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement("select EName from Employee where EmpNo = ?");
		ps.setInt(1, empNo);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
			return rs.getString(2);
		return null;
	}


	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "npci@12345");
	}

}
