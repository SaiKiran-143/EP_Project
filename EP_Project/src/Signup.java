import java.io.*;
import javax.servlet.*; /* import javax.servlet.*; */
import javax.servlet.http.*; /* import javax.servlet.http.*/
import java.sql.*;
import java.util.*;

public class Signup extends HttpServlet {
	
    public Signup() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String p1 = request.getParameter("userid");
		String p2 = request.getParameter("passid");
		String p3 = request.getParameter("username");
		String p4 = request.getParameter("address");
		String p5 = request.getParameter("zip");
		String p6 = request.getParameter("email");
		String p7 = request.getParameter("desc");
		

		System.out.println("username : "+p3 +" "+"password : "+p2);
		try
		{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "student","student");
    		PreparedStatement pstmt=con.prepareStatement("insert into registrations values(?,?,?,?,?,?,?)");
    		pstmt.setString(1, p1);
    		pstmt.setString(2, p2);
    		pstmt.setString(3, p3);
    		pstmt.setString(4, p4);
    		pstmt.setString(5, p5);
    		pstmt.setString(6, p6);
    		pstmt.setString(7, p7);
    		boolean n = pstmt.execute();   			
    		if(n)
    			System.out.println("- - - - One Record inserted successful - - - -");
    		else
    			System.out.println("- - - - One Record inserted successful - - - -");
    		
		}
		catch(ClassNotFoundException | SQLException e)
		{		
			System.out.println(e);  
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}