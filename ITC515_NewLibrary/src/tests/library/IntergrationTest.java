package tests.library;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.entities.Book;
import library.entities.Loan;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class IntergrationTest {

	IBook book;
	IMember member;
	ILoan loan;
	
	private String author;
	private String title;
	private String callNumber;
	private int id;

	Date borrowDate, dueDate;
	Calendar calender;
	
	@Before
	public void setUp() throws Exception {
		
		author = "Bob";
		title = "Jones";
		callNumber = "123456";
		id = 1;
		
		book = new Book(author, title, callNumber, id);
		
	}
	
	@After
	public void clearSetUp() throws Exception
	{
		book = null;
	}
	
	@Test
	public void testBook()
	{
		IBook book = new Book(author, title, callNumber, id);
		assertTrue(book instanceof Book);
	}
	
	@Test
	public void testBorrow()
	{		
		Loan loan = new Loan(book, member, borrowDate, dueDate);
				
		book.borrow(loan); 
		
		assertEquals(loan.getBook(), book);
	}
}