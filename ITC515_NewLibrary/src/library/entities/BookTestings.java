package library.entities;

import static org.junit.Assert.*;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class BookTestings {
	
	private String author = "author"; 
	private String title = "title";
	private String Num = "callNumber";
	private int bookID = 1;
	private EBookState state;
	

	private static Book BookTesting;
	
	@Before
	public void setup() throws Exception{
	BookTesting = new Book(author, title, Num, 1 );
	}
	
	@Test
	public void testGetAuthor() {
		assertEquals("author", BookTesting.getAuthor());
	}
	
	@Test
	public void testGetTitle() {
		assertEquals("title", BookTesting.getTitle());
	}
	
	@Test
	public void testGetCallNumber() {
		assertEquals("callNumber", BookTesting.getCallNumber());
	}
	
	@Test
	public void testGetID() {
		assertEquals(1, BookTesting.getID());
	}
	@Test 
	public void testborrow(){
		ILoan testBorrowBook = mock(ILoan.class);
		
		//assertEquals(author, title, Num, 1 );
	}
	
	public void setState(EBookState state)
	{
		this.state = state;
	}
	
	@Test
	public void testGetLoan(){
		IBook testGetLoan = mock(IBook.class);
		
		testGetLoan.setState(EBookState.ON_LOAN);
		
		assertEquals(testGetLoan.getState(), EBookState.ON_LOAN); 
	}
	
	@Test
	public void testReturnBook(){
		ILoan testReturn = mock(ILoan.class);
		
		Book test = new Book("author","title","no.",2);
		
		when(testReturn.getBook()).thenReturn(test);
		
		assertEquals(testReturn.getBook() , test);
		
	}
	
	@Test 
	public void testLose(){
		ILoan testLose = mock (ILoan.class);
		Book test = new Book("author","title","no.",2);
		
		test.setState(EBookState.ON_LOAN);
		
//		when(testLose.getBook()).thenReturn(test);
		
		
		
		assertEquals(test.getState(), EBookState.ON_LOAN);
		
//		assertEquals(testLose.getBook(), test);
	}
	
		

}
