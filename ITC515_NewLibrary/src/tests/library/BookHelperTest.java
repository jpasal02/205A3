package tests.library;

import static org.junit.Assert.*;

import org.junit.Test;

import library.interfaces.daos.IBookHelper;
import library.daos.BookHelper;

import library.interfaces.entities.IBook;




public class BookHelperTest {

	private final String author = "Bobr";
	private final String title = "Subject";
	private final String callNumber = "123456";
	private int id = 1;

	@Test
	public void testMakeBook() {
		IBookHelper bookHelper = new BookHelper();

		IBook book = bookHelper.makeBook(author, title, callNumber, id);
		
		assertNotNull(book);
        assertEquals(book.getAuthor(), author);
        assertEquals(book.getTitle(), title);
        assertEquals(book.getCallNumber(), callNumber);
        assertEquals(book.getID(), id);
		

	}
}