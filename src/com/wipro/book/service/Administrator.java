package com.wipro.book.service;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {
	public String addBook(BookBean bookBean){
		if(bookBean==null||bookBean.getBookName().length()==0||bookBean.getIsbn().length()==0||bookBean.getBookType()==' '||(bookBean.getBookType()!='G'&&bookBean.getBookType()!='T'||bookBean.getCost()==0||bookBean.getAuthor()==null))
			return "INVALID";
		if(new BookDAO().createBook(bookBean)==1)
			return "SUCCESS";
		return "FAILURE";
	}
	public BookBean viewBook(String isbn){
		if(isbn.length()==0)
			return null;
		else
			return new BookDAO().fetchBook(isbn);
	}
}
