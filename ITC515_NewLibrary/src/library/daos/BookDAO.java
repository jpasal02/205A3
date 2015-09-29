package library.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookDAO implements IBookDAO{
	
	private IBookHelper helper; 
	private List<IBook> map;
	private int nextID;
	
	
	public BookDAO (IBookHelper helper){
		if (helper == null)
			throw new IllegalArgumentException ("Error: (Helper)");
	
		
	}
	
	public IBook addBook(String author, String Title, String callNumber){
		
		IBook book = helper.makeBook(author, Title, callNumber, nextID++);
		map.add(book);
		return book;
		
		
	}
	
	public IBook getBookByID(int id){
		
		for (IBook book : map)
			if (book.getID() == id)
				return book;
		
		return null; 
		
	}
	
	public List<IBook> listBooks(){
		return map;
		
	}
	
	public List<IBook> findBooksByAuthor(String author){
		List<IBook> returns = new ArrayList<IBook>();
		
		for (IBook book : map)
			if (author.compareTo(book.getAuthor()) == 0)
				returns.add(book);
		
		return returns;
		
	}
	
	public List<IBook> findBooksByTitle(String title){
		List<IBook> returns = new ArrayList<IBook>();
		
		for (IBook book : map)
			if (title.compareTo(book.getTitle()) == 0)
				returns.add(book);
		
		return returns;
		
	}
	public List<IBook> findBooksByAuthorTitle(String author, String title){
		List<IBook> returns = new ArrayList<IBook>();
		
		for (IBook book : map)
			if (title.compareTo(book.getTitle()) == 0 && author.compareTo(book.getAuthor()) == 0)
				returns.add(book);
		return returns;
				
		
	}

}
