package tests.library;

import java.util.List;

import library.daos.BookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookDAOTest {

	IBookHelper mockBookHelper;
	IBook mockBook;
	IBook mockBook1;
	BookDAO bookDao;
	String name = "Bob";
	String title = "Subject";
	String callNo1 = "123456";
	String name1 = "Jones";
	String title1 = "63333";
	String callNo2 = "112121";

	@Before
	public void setUp() {
		mockBookHelper = mock(IBookHelper.class);
		mockBook = mock(IBook.class);
		mockBook1 = mock(IBook.class);

		when(mockBook.getAuthor()).thenReturn(name);
		when(mockBook.getTitle()).thenReturn(title);
		when(mockBook.getID()).thenReturn(1);
		when(mockBook1.getAuthor()).thenReturn("Bob");
		when(mockBook1.getTitle()).thenReturn("Subject");
		when(mockBook1.getID()).thenReturn(2);
		

		when(mockBookHelper.makeBook(name, title, callNo1, 1)).thenReturn(mockBook);
		when(mockBookHelper.makeBook(name1, title1, callNo2, 2)).thenReturn(mockBook1);

		bookDao = new BookDAO(mockBookHelper);
	}

	
	
	@After
	public void SetUp() {
		mockBookHelper = null;
		mockBook = null;
		mockBook1 = null;
		bookDao = null;
	}

	@Test
	public void testCreateBookDAO() {
		
		bookDao = new BookDAO(mockBookHelper);

		assertNotNull(bookDao);

	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testBookDAOHelperNull() {

		bookDao = new BookDAO(null);

		fail("IllegalArgumentException");
	}

	
	
	@Test
	public void testAddBook() {

		IBook boook = bookDao.addBook(name, title, callNo1);

		verify(mockBookHelper).makeBook(name, title, callNo1, 1);

		assertEquals(boook, mockBook);
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookAuthorNull() {

		IBook boook = bookDao.addBook(null, title, callNo1);
		verify(mockBookHelper).makeBook(null, title, callNo1, 1);

		assertNull(boook.getAuthor());
		
		fail("IllegalArgumentException");

	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookTitleNull() {

		IBook boook = bookDao.addBook(name1, null, callNo1);
		verify(mockBookHelper).makeBook(name1, null, callNo1, 1);

		assertNull(boook.getTitle());
		
		fail("IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookTitleBlank() {

		IBook boook = bookDao.addBook(name, "", callNo1);

		verify(mockBookHelper).makeBook(name, "", callNo1, 1);

		assertNull(boook.getTitle());
		
		fail("IllegalArgumentException");
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookCallNumberNull() {

		
		IBook boook = bookDao.addBook(name1, title, null);

	
		verify(mockBookHelper).makeBook(name1, title, null, 1);

		assertNull(boook.getCallNumber());
		
		fail("IllegalArgumentException");
	}
	
	
	
	@Test
	public void testGetBookByID() {

		//IBook boook = bookDao.addBook(name1, title, callNo1);

		IBook Book = bookDao.getBookByID(1);

		assertEquals(Book, mockBook1);
	}

	
	
	@Test
	public void testListBooks() {

		bookDao.addBook(name1, title, callNo1);
		bookDao.addBook("jones", "", "");

		List<IBook> listBooks = bookDao.listBooks();

		assertEquals(1, listBooks.size());
		assertTrue(listBooks.contains(mockBook));
		assertTrue(listBooks.contains(mockBook1));
	}

	
	
	@Test
	public void testFindBooksByAuthor() {

		bookDao.addBook("rob", "sub", "12");
		bookDao.addBook("mary", "subb", "13");

		List<IBook> listBooks = bookDao.findBooksByAuthor("rob");

		IBook sub = listBooks.get(0);

		assertEquals(1, listBooks.size());
		assertEquals("rob", sub.getAuthor());
	}

	
	
	@Test
	public void testFindBooksByTitle() {
	
		bookDao.addBook("rob", "sub", "12");
		bookDao.addBook("mary", "subb", "13");

		List<IBook> listBooks = bookDao.findBooksByTitle("subb");

		IBook subb = listBooks.get(0);

		assertEquals("subb", subb.getTitle());
	}

	
	
	@Test
	public void testFindBooksByAuthorTitle() {

		bookDao.addBook("rob", "sub", "12");
		bookDao.addBook("mary", "subb", "13");;

		List<IBook> listBooks = bookDao.findBooksByAuthorTitle("rob", "sub");

		assertEquals(1, listBooks.size());
		assertTrue(listBooks.contains(mockBook));
	}
}