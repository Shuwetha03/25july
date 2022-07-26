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
 * Servlet implementation class EmployeeDetails
 */
@WebServlet("/Details")
public class EmployeeDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		try {
			Employee e = getEmployeeDetails(email);
			pw.println("Name: " + e.getEmpName());
			pw.println("<br>Employee no: " + e.getEmpNo());
			pw.println("<br>Job Desc: " + e.getJob());
			pw.println("<br>Joined on: " + e.getHireDate());
			pw.println("<br>Reporting Mangaer ID: " + e.getManagerId());
			pw.println("<br>Salary: " + e.getSalary());
			pw.println("<br>Commision: " + e.getCommision());
			pw.println("<br>Department Id: " + e.getDeptNo());
			response.setContentType("text/html");  
			PrintWriter pw1=response.getWriter();  
			  
			response.sendRedirect("HomePage.html");  
			  
			pw1.close();  
			
			pw1.println("<br><br><a class = 'btn btn-primary' href='Logout' role='button'>Logout</a>");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public Employee getEmployeeDetails(String email) throws ClassNotFoundException, SQLException {
		
		Connection connect = getConnection();
		PreparedStatement ps = connect.prepareStatement("select * from Employee where EmpNo = (Select EmpNo from LoginCredentials where emailID = ?)");
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
		return null;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "npci@12345");
	}

}