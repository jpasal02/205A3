package tests.library;

import static org.junit.Assert.*;
import library.entities.Book;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookTestings {
	
	private String author = "author"; 
	private String title = "title";
	private String Num = "callNumber";

	private static Book BookTesting;

	IBook mockBook = mock(IBook.class);
	ILoan mockLoan = mock(ILoan.class);
	Book testingBook = new Book("Bob Jones","Subject","123456",4);
	
	@Before
	public void setup() throws Exception{
	BookTesting = new Book(author, title, Num, 1);
	
	}
	
	@After
	public void Empty()
	{
		mockBook = null;
		mockLoan = null;
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
	public void testborrow() throws Exception {
		
		when(mockLoan.getBook()).thenReturn(testingBook);
		when(mockLoan.isOverDue()).thenReturn(false);
		
		assertEquals(mockLoan.getBook(), testingBook);
		}
	
	@Test
	public void testGetLoan(){
		
		testingBook.setState(EBookState.ON_LOAN);
		
		when(mockLoan.getBook()).thenReturn(testingBook);
    	when(mockLoan.isOverDue()).thenReturn(false);
		
		assertEquals(testingBook.getState(), EBookState.ON_LOAN); 
	}
	
	@Test
	public void testReturnBookDamaged(){
		
		testingBook.setState(EBookState.ON_LOAN);
		
		when(mockLoan.getBook()).thenReturn(testingBook);
		
		assertEquals(testingBook.getState(),EBookState.ON_LOAN);
		
		testingBook.returnBook(true);
		
		assertEquals(testingBook.getState(), EBookState.DAMAGED);
	}
	
	@Test
	public void testReturnBookNotDamaged(){
		
		testingBook.setState(EBookState.ON_LOAN);
		
		when(mockLoan.getBook()).thenReturn(testingBook);
		
		assertEquals(testingBook.getState(),EBookState.ON_LOAN);
		
		testingBook.returnBook(false);
		
		assertEquals(testingBook.getState(), EBookState.AVAILABLE);
	}
	
	@Test 
	public void testLose(){
		
		
		testingBook.setState(EBookState.LOST);
		
		when(mockLoan.getBook()).thenReturn(testingBook);
		
		assertEquals(testingBook.getState(), EBookState.LOST);
		
	}
	@Test
	public void repair(){
		
		testingBook.setState(EBookState.DAMAGED);
		
		assertEquals(testingBook.getState(), EBookState.DAMAGED);
		testingBook.repair();
	}
	@Test
	public void dispose(){
		testingBook.setState(EBookState.DISPOSED);
		
		assertEquals(testingBook.getState(), EBookState.DISPOSED);
		
		
	}
	//Exception Testing
	
	@Test
	public void testGetAuthorIsNull()
	{
		try
		{
			Book authorNull = new Book(null,"Subject","123456",4);
			authorNull.getAuthor();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetAuthorIsEmpty()
	{
		try
		{
			Book authorEmpty = new Book("","Subject","123456",4);
			authorEmpty.getAuthor();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetTitleIsNull()
	{
		try
		{
			Book titleNull = new Book("Bob Jones",null,"123456",4);
			titleNull.getTitle();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetTitleIsEmpty()
	{
		try
		{
			Book titleEmpty = new Book("Bob Jones","","123456",4);
			titleEmpty.getTitle();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testCallNumberIsNull()
	{
		try
		{
			Book callNumberIsNull = new Book("Bob Jones","Subject",null,4);
			callNumberIsNull.getCallNumber();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testCallNumberIsEmpty()
	{
		try
		{
			Book callNumberIsEmpty = new Book("Bob Jones","Subject","",4);
			callNumberIsEmpty.getCallNumber();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testBookIdIsNegative()
	{
		try
		{
			Book idIsNegative = new Book("Bob Jones","Subject",null,-4);
			idIsNegative.getID();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testBookIdIsZero()
	{
		try
		{
			Book idIsZero = new Book("Bob Jones","Subject","123456",0);
			idIsZero.getID();
			fail("Illegal Argument Exception");
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testBorrowNotAvailable()
	{
		ILoan loan = null;
		
		testingBook.setState(EBookState.AVAILABLE);
		try
		{
			testingBook.borrow(loan);
			fail("Runtime Exception");
		}
		catch (RuntimeException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testReturnBookNotOnLoan()
	{		
		testingBook.setState(EBookState.DISPOSED);
		try
		{
			testingBook.returnBook(true);
			fail("Runtime Exception");
		}
		catch (RuntimeException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testLostBookNotOnLoan()
	{		
		testingBook.setState(EBookState.LOST);
		try
		{
			testingBook.lose();
			fail("Runtime Exception");
		}
		catch (RuntimeException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testRepairBookNotDamaged()
	{		
		testingBook.setState(EBookState.ON_LOAN);
		try
		{
			testingBook.repair();
			fail("Runtime Exception");
		}
		catch (RuntimeException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testDisposeBookAvailable()
	{		
		testingBook.setState(EBookState.AVAILABLE);
		try
		{
			testingBook.dispose();
			fail("Runtime Exception");
		}
		catch (RuntimeException e)
		{
			assertTrue(true);
		}
	}
	@Test
	public void testDisposeBookDamaged()
	{		
		testingBook.setState(EBookState.DAMAGED);
		try
		{
			testingBook.dispose();
			fail("Runtime Exception");
		}
		catch (RuntimeException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testDisposeBookLost()
	{		
		testingBook.setState(EBookState.LOST);
		try
		{
			testingBook.dispose();
			fail("Runtime Exception");
		}
		catch (RuntimeException e)
		{
			assertTrue(true);
		}
	}
	
		

}
