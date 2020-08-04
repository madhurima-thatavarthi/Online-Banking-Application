package MyServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutBackend
 */
public class LogoutBackend extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutBackend() 
    {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        //String name=request.getParameter("userid");
       
        HttpSession session=request.getSession(false); 
        
        session.removeAttribute("username");
       
         /*if(session.getAttribute("username")==null)
         {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma","no-cache");
        //request.getRequestDispatcher("Login.jsp").include(request, response); 
        
         }*/
        // session.invalidate(); 
         String username=(String)session.getAttribute("userid");
         if(username==null)
         {
        	 session.invalidate(); 
        	 request.setAttribute("Error", "Session has ended. Please Login");
        	 RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
         }

        out.close();  
	}

}
