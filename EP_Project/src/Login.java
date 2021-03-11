

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet2
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String en = request.getParameter("uname");
		String pn = request.getParameter("password");

		System.out.println("username : "+en +" "+"password : "+pn);
		// load the driver
		try
		{
			// load the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// connection establishment
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","sai","sai");
			
			// creating the statement
			PreparedStatement  pstmt= con.prepareStatement("select * from registrations");
			
			ResultSet rs1 = pstmt.executeQuery();
			
			int flag=0;
	        while (rs1.next())
	        {
	          if(rs1.getString(3).equals(en) && rs1.getString(2).equals(pn)) {
	            flag=1;
	            break;
	          }
	        }
	        if(flag==1) {
	          System.out.println(rs1.getString(3)+" "+rs1.getString(2));
	          System.out.println("Login Successful");
	          response.sendRedirect("./weeks1.html");
	        }
	        else {
	          System.out.println(rs1.getString(3)+" "+rs1.getString(2));
	          System.out.println("Login Failed");
	          response.sendRedirect("./new.html");
	        }
				
				// close the connection
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{		
			System.out.println(e);  
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
