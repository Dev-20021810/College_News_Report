package fake.com;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class serv1
 */
public class serv1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serv1() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String email = request.getParameter("email");
		String data = request.getParameter("data");
		try {
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fakenews","root","tester"); 
			String s = "insert into user_info values(?,?)"; 
			PreparedStatement p = con.prepareStatement(s); 
			p.setString(1, email);
			p.setString(2, data);
			int i = p.executeUpdate();
			if (i>0) {
				Statement stmt=con.createStatement();
			    ResultSet rs= stmt.executeQuery("select * from user_info");
			    out.print("<html><body>"); 
			    out.print("<h3>Details Entered</h3><br/>");
			    while(rs.next())
			    	out.println("|"+rs.getString(1)+"|"+rs.getString(2)+"|"+"<br>");
			    out.print("</body></html>");
			}
		}
		    	 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		}
	}
