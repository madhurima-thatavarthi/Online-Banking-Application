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


/**
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() 
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
		try
		{  
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			String userid=request.getParameter("userid");
			
			if(request.getParameter("acnt").equals("")||request.getParameter("amount").equals("")) 
			{
				RequestDispatcher rd=request.getRequestDispatcher("AcntHomePage.jsp");
				rd.forward(request, response);
			}
			
			
			int acntno=Integer.parseInt(request.getParameter("acnt"));
			int amt=Integer.parseInt(request.getParameter("amount"));
			
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/olbank?useSSL=false","root","7075725533");  
			
			int upamt=0;
			Statement statement1=con.createStatement(); 
			Statement statement2=con.createStatement();
			
			ResultSet rs1=statement2.executeQuery("select userid,balance from usertable where acntno='"+acntno+"'"); 
			ResultSet rs2=statement1.executeQuery("select balance from usertable where userid='"+userid+"'"); 
			
			if(rs2.next()&&rs2.getInt("balance")>=amt) 
			{
				if(rs1.next()) 
				{
					//Date date=new Date();
					//String dt=date.toString();
					String recuserid=rs1.getString("userid");
					if(!(userid.equals("recuserid")))//it will check whether the sender userid and receiver userid are different or not
					{
						int senbal=rs2.getInt("balance");
						upamt=senbal-amt;
						
						String sql="UPDATE usertable SET balance='"+upamt+"' WHERE userid='"+userid+"'";
						statement1.executeUpdate(sql);
						
						statement1.executeUpdate("insert into transaction (userid,res,timestamp) values('"+userid+"','"+upamt+"',current_timestamp())"); 
					
					
						
						int recbal=rs1.getInt("balance");
						int upamt2=recbal+amt;
						
						String sql1="UPDATE usertable SET balance='"+upamt2+"' WHERE userid='"+recuserid+"'";
						statement1.executeUpdate(sql1);
						
						statement1.executeUpdate("insert into transaction (userid,res,timestamp) values('"+recuserid+"','"+upamt2+"',current_timestamp())"); 
						
						
						out.print("The amount "+amt+" has been sent to "+recuserid+" successfully");
						out.print("<br><br><br>The Remaining balance is : "+upamt);
						
						
						ResultSet rs3=stmt.executeQuery("select transactionID,res,timestamp from transaction where userid='"+userid+"' order by timestamp desc"); 
						int c=0;
						String s="<html><head>\r\n" + 
								"<style>\r\n" + 
								"table {\r\n" + 
								"  margin: 0 auto;\r\n"+
								
								"  font-family: arial, sans-serif;\r\n" + 
								"  border-collapse: collapse;\r\n" + 
								"  width: 50%;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"td, th {\r\n" + 
								"  border: 1px solid #dddddd;\r\n" + 
								"  text-align: left;\r\n" + 
								"  padding: 8px;\r\n" + 
								"}\r\n" + 
								"\r\n" + 
								"tr:nth-child(even) {\r\n" + 
								"  background-color: #dddddd;\r\n" + 
								"}\r\n" + 
								"</style>\r\n" + 
								"</head><table border=1 cellspacing=2 cellpadding=5><caption align=bottom>Recent Transactions</caption><tr><th>Transaction ID</th><th>Time of transaction</th><th>Reamining Balance</th>";
						while(rs3.next()&&c<5) 
						{
							s=s+"<tr><td>"+rs3.getInt("transactionID")+"</td><td>"+rs3.getTimestamp("timestamp")+"</td><td>"+rs3.getInt("res")+"</td></tr>";
							c++;
							
						}
		
						out.write(s+"<br/><br/><br/></table></html>");
					}
					else
					{
						out.write("The transaction should not be done to self");
					}
				}
				else
				{
					out.write("Receiver does not exist");
				}
			
			}
			else
			{
				out.write("Insufficient Balance");
			}
			
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		} 
	}

}
