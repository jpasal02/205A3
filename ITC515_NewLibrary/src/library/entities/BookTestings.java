package library.entities;

import static org.junit.Assert.*;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.ILoan;

import org.junit.Before;
import org.junit.Test;

public class BookTestings {
	
	private String author = "author"; 
	private String title = "title";
	private String Num = "callNumber";
	private int bookID = 1;
	

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

}
