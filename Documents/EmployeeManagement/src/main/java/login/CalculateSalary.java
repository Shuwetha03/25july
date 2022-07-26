package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CalculateSalary
 */
@WebServlet("/CalculateSalary")
public class CalculateSalary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculateSalary() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		String email = (String) session.getAttribute("email");

		response.setContentType("text/html");
		int days = (int)session.getAttribute("days");
		try {
			
			Employee e1 = getEmployeeDetails(email);
			
			Connection connect = getConnection();
			
			PreparedStatement p = connect.prepareStatement("insert into PaySlip values(?,?,?,?,?,?)");
			p.setInt(1, days);
			p.setInt(2, Integer.valueOf(request.getParameter("empNo"))); 
			p.setLong(3,Long.valueOf(request.getParameter("((e1.getSalary() / 2) / totaldays) * days")));
			p.setLong(4,Long.valueOf(request.getParameter("(((e1.getSalary() / 4) / totaldays) * days)")));
			p.setLong(5,Long.valueOf(request.getParameter("(0.12 * basic)")));
			p.setLong(6,Long.valueOf(request.getParameter("basic + hra - deductions ")));
		
            p.executeUpdate();
            
            p.close();
            connect.close();
		

		} catch (ClassNotFoundException | SQLException e) { 	 	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		//esponse.setContentType("text/html");
		out.print("<br><a href='Logout'>Logout</a>");
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



	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","npci@12345");


	}

}
