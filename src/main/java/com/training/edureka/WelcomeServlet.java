package com.training.edureka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet(description = "This is my First Servlet Program", urlPatterns = { "/WelcomeServlet" })
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String qs =request.getQueryString();
		String[] kv=qs.split("&");
		out.println("Hello "+ kv[0]+"-"+kv[1]);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.setContentType("text/html");
		
		PrintWriter out=resp.getWriter();
	
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		
		
		ServletContext context=getServletContext();
		ServletConfig config=getServletConfig();
		
		String dburl="jdbc:mysql://localhost:3306/test?useSSL=false";
		//String dburl=config.getInitParameter("link");
		String dbuser="root";
		String dbPwd="Root123$";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.println(dburl);
		out.println(dbuser);
		out.println(dbPwd);
		
		try {
			Connection con=DriverManager.getConnection(dburl,dbuser,dbPwd);
			PreparedStatement ps=con.prepareStatement("insert into appuser values(?,?,?,?)");
			ps.setString(1,username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, phone);
			
			int count=ps.executeUpdate();
			if(count>=1) {
				out.println("<h2>Registration Successful</h2>");
				out.println("<h1>Welcome to our World ! Dear "+username+" </h1>");
			}else {
				out.println("<h1>Something went wrong!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	

}
