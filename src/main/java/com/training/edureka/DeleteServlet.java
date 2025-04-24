package com.training.edureka;

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

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();

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

		
		try {
			Connection con=DriverManager.getConnection(dburl,dbuser,dbPwd);
			String us=request.getParameter("us");
			String nus=request.getParameter("new-us");
			PreparedStatement ps=con.prepareStatement("select * from appuser where username=?");
			ps.setString(1,us);
			
			ResultSet count=ps.executeQuery();
			if(!count.next()) {
				out.println("No username Found!");
			}else {
				String sql ="delete from appuser where username=?";
				PreparedStatement ps1=con.prepareStatement(sql);
				ps1.setString(1,us);
				int c=ps1.executeUpdate();
				if(c>=1) {
					out.println("Deleted successfully");
				}else {
					out.println("Delete Failed");
				}
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
}
