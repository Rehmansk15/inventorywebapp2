package com.training.edureka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		resp.setContentType("text/html");
		
		PrintWriter out=resp.getWriter();

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
			String us=req.getParameter("us");
			String nus=req.getParameter("new-us");
			PreparedStatement ps=con.prepareStatement("select * from appuser where username=?");
			ps.setString(1,us);
			
			ResultSet count=ps.executeQuery();
			if(!count.next()) {
				out.println("No username Found!");
			}else {
				String sql ="update appuser set username=?  where  username=?";
				PreparedStatement ps1=con.prepareStatement(sql);
				ps1.setString(1, nus);
				ps1.setString(2,us);
				int c=ps1.executeUpdate();
				if(c>=1) {
					out.println("Updated successfully");
				}else {
					out.println("Update Failed");
				}
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}
