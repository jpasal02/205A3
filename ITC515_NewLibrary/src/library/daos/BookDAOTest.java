package library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import org.junit.Before;
import org.junit.Test;

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
			Book book = new Book("Bob Jones","Subject","123456",4);
//
			
			mockBook.addBook("Bob Jones","Subject","123456");

			assertEquals(mockBook.addBook("Bob Jones","Subject","123456"), book);
		
	}
	

}
