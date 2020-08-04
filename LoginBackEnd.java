package MyServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginBackEnd
 */
public class LoginBackEnd extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginBackEnd() 
    {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
		 
	
	 /* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
		try
		{  
			PrintWriter out=response.getWriter();
			
			
			String username=request.getParameter("uname");
			String password=request.getParameter("pwd");
			
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/olbank?useSSL=false","root","madhu");  
			
			
			
			if(username.equals("")||password.equals("")) 
			{
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			
			Statement statement=con.createStatement();  
			ResultSet rs=statement.executeQuery("select pwd from usertable where userid='"+username+"'"); 
			if(rs.next()) 
			{
				String pwd=rs.getString("pwd");
				if(!password.equals(pwd))
				{
					out.write("Incorrect Password");
					
				}
				else
				{
					 HttpSession session=request.getSession();  
				     session.setAttribute("username",username); 
					
					RequestDispatcher rd=request.getRequestDispatcher("AcntHomePage.jsp");
					rd.forward(request, response);
					
				}
					
					
			}
			else
			{
				out.write("User does not exist");
			}
			con.close();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		} 
	}
}
