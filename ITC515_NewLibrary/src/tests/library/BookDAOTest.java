package tests.library;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import library.daos.BookDAO;
import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.CompareTo;

public class BookDAOTest {

	Book testingBook;
	IBookDAO mockBook;
	ILoan mockLoan;
	
	@Before
	public void setUp()
	{
	mockBook = mock(IBookDAO.class);
	mockLoan = mock(ILoan.class);
	testingBook = new Book("Bob Jones","Subject","123456",4);
	}
	
	@Test
	public void testBookDAOHelper() {
		try
		{
			BookDAO bookDAO = new BookDAO(null);
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testBookDAOAddBook()
	{
			IBook book = new Book ("Bob Jones","Subject","123456",4);
			mockBook.addBook("Bob Jones","Subject","123456");

			assertEquals(mockBook.addBook("Bob Jones","Subject","123456"), testingBook );
			
			
	}
	
	@Test
	public void testBookById() {
		
		
		int Id = 7; 
				
		IBook book = new Book ("Bob Jones","Subject","123456",4);
		mockBook.addBook("Bob Jones","Subject","123456");
		mockBook.getBookByID(Id);
		book.getID();
		
		mockBook.equals(testingBook);
		
		
		assertEquals(mockBook.getBookByID(7), 7);
		
	}
	
	@Test
	public void testBookByAuthor() {
		
		
		
		mockBook.addBook("Bob Jones","Subject","123456");
		
		testingBook.getAuthor();
		assertEquals(mockBook.addBook("Bob Jones","Subject","123456"), testingBook);
	}

	

}
