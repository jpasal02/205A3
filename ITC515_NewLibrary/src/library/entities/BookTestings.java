package library.entities;

import static org.junit.Assert.*;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.ILoan;

import org.junit.Before;
import org.junit.Test;

public class BookTestings {
	
	private String author = "author"; 
	private String title; 
	private String callNumber;  
	private int id; 
	private ILoan loan; 
	private EBookState state; 
	private static Book BookTesting;
	
	@Before
	public void setup() throws Exception{
	BookTesting = new Book("author", "title", "No.", 1 );
	}
	
	@Test
	public void testGetAuthor() {
		assertEquals("author", BookTesting.getAuthor());
	}

}
