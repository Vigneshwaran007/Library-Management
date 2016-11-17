package com.wipro.book.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {
	AuthorBean getAuthor(int authorCode){
		try{
		Connection con=DBUtil.getDBConnection();
		Statement stmt=con.createStatement();
		ResultSet rset=stmt.executeQuery("select *from author_tbl where authorcode="+authorCode);
		if(rset.next()){
			AuthorBean ab=new AuthorBean();
			ab.setAuthorCode(authorCode);
			ab.setAuthorName(rset.getString("author_name"));
			ab.setContactNo(rset.getInt("contact_no"));
			return ab;
		}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
	public AuthorBean getAuthor(String authorName){
		try{
			AuthorBean ab=new AuthorBean();
			Connection con=DBUtil.getDBConnection();
			Statement stmt=con.createStatement();
			ResultSet rset=stmt.executeQuery("select *from author_tbl where author_name='"+authorName+"'");
			if(rset.next()){
				ab.setAuthorCode(rset.getInt("author_code"));
				ab.setAuthorName(authorName);
				ab.setContactNo(rset.getLong("contact_no"));
				return ab;
			}
			}
			catch(Exception e){
				return null;
			}
		return null;
	}
}
