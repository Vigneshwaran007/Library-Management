package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;

public class BookDAO {
	public BookBean fetchBook(String isbn){
		try{
			Connection con=DBUtil.getDBConnection();
			Statement stmt=con.createStatement();
			ResultSet rset=stmt.executeQuery("select *from book_tbl join author_tbl using(author_code) where isbn='"+isbn+"'");
			if(rset.next()){
				AuthorBean ab=new AuthorBean();
				ab.setAuthorCode(rset.getInt("author_code"));
				ab.setAuthorName(rset.getString("author_name"));
				ab.setContactNo(rset.getLong("contact_no"));
				BookBean bb=new BookBean();
				bb.setAuthor(ab);
				bb.setBookName(rset.getString("book_title"));
				bb.setBookType(rset.getString("book_type").charAt(0));
				return bb;
			}
			}
			catch(Exception e){
				System.out.println(e);
			}
			return null;
	}
	public int createBook(BookBean bookBean){
		try{
			Connection con=DBUtil.getDBConnection();
			Statement pstmt=con.createStatement();
			int x=pstmt.executeUpdate("insert into book_tbl values('"+bookBean.getIsbn()+"','"+bookBean.getBookName()+"','"+bookBean.getBookType()+"',"+bookBean.getAuthor().getAuthorCode()+","+bookBean.getCost()+")");
			System.out.println(x);
			if(x>0)
				return 1;
			}
			catch(Exception e){
				return 0;
			}
		return 0;
	}
}
