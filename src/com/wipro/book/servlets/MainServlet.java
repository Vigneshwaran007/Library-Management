package com.wipro.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;
import com.wipro.book.util.DBUtil;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	public String addBook(HttpServletRequest request){
		BookBean bb=new BookBean();
		Enumeration en=request.getParameterNames();
		String str[]=new String[6];
		int i=0;
		while(en.hasMoreElements()){
			str[i++]=request.getParameter((String)en.nextElement());
		}
		bb.setIsbn(str[0]);
		bb.setBookName(str[1]);
		bb.setBookType(str[2].charAt(0));
		bb.setCost(Integer.parseInt(str[4]));
		AuthorBean ab=new AuthorDAO().getAuthor(str[3]);
		bb.setAuthor(ab);
		return new Administrator().addBook(bb);
	}
	public BookBean viewBook(String isbn){
		return new Administrator().viewBook(isbn);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("operation").equals("AddBooks")){
			String str=addBook(request);
			if(str.equals("SUCCESS")){
				response.sendRedirect("Menu.html");
			}
			else if(str.equals("FAILURE")){
				response.sendRedirect("Failure.html");
			}
			else if(str.equals("INVALID")){
				response.sendRedirect("Invalid.html");
			}
		}
		BookBean bb;
		Enumeration enu=request.getParameterNames();
		if(request.getParameter("operation").equals("Search"))
			if((bb=viewBook(request.getParameter((String)enu.nextElement())))==null)
				response.sendRedirect("Invalid.html");
			else{
				request.setAttribute("arun",bb);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/View.jsp");
				rd.forward(request, response);
			}
		
	}

}
